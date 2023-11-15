package audio;

import javax.sound.sampled.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import java.util.ArrayList;

public class Audio {
	private static Audio instance;
	private Thread thread;
	public final float sampleRate;
	private boolean running;
	ArrayList<PulseChannel> pulseChannels = new ArrayList<>();

	private static class AudioThread extends Thread {
		final Audio audio;
		final int bufferSampleSize;

		public AudioThread(Audio audio) {
			this.audio = audio;
			bufferSampleSize = (int) audio.sampleRate / 64;
		}

		private SourceDataLine getLine() throws LineUnavailableException {
			AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, audio.sampleRate, 16, 2, 4, audio.sampleRate, false);
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
			SourceDataLine line = (SourceDataLine)AudioSystem.getLine(info);
			int bytesPerSample = 2 * 2;
			line.open(format, bufferSampleSize * bytesPerSample);
			return line;
		}

		@Override
		public void run() {
			try {
				SourceDataLine line = getLine();
				int sampleBytes = line.getBufferSize();
				line.start();
				ByteBuffer buffer = ByteBuffer.allocate(sampleBytes);
				buffer.order(ByteOrder.LITTLE_ENDIAN);
				ShortBuffer sbuffer = buffer.asShortBuffer();
				while (audio.running) {
					synchronized (audio) {
						for (int i = 0; i < bufferSampleSize; i++) {
							int left = 0;
							int right = 0;
							for (PulseChannel pulse : audio.pulseChannels) {
								if (pulse.samplesRemaining <= 0)
									continue;
								pulse.state += pulse.ticksPerSample;
								pulse.dutyCycle = pulse.state < 0 ? pulse.nextDutyCycle : pulse.dutyCycle;
								pulse.state &= 0x7fffffff;
								int lVolume = (Math.min(0x100 + pulse.balance, 0x100) * pulse.volume) >> 8;
								int rVolume = (Math.min(0x100 - pulse.balance, 0x100) * pulse.volume) >> 8;
								left  += pulse.state > pulse.dutyCycle ? -lVolume : lVolume;
								right += pulse.state > pulse.dutyCycle ? -rVolume : rVolume;

								pulse.volumeState += pulse.volumePerSample;
								int volAdj = pulse.volumeState >> 16;
								pulse.volumeState &= 0xffff;
								if (pulse.volume < pulse.volumeTarget)
									pulse.volume = Math.min(pulse.volume + volAdj, pulse.volumeTarget);
								else
									pulse.volume = Math.max(pulse.volume - volAdj, pulse.volumeTarget);

								pulse.frequencyState += pulse.frequencyPerSample;
								int freqAdj = pulse.frequencyState >> 16;
								pulse.frequencyState &= 0xffff;
								if (pulse.ticksPerSample < pulse.frequencyTarget)
									pulse.ticksPerSample = Math.min(pulse.ticksPerSample + freqAdj, pulse.frequencyTarget);
								else
									pulse.ticksPerSample = Math.max(pulse.ticksPerSample - freqAdj, pulse.frequencyTarget);
								if (pulse.samplesRemaining < Integer.MAX_VALUE)
								{
									pulse.samplesRemaining--;
									if (pulse.samplesRemaining == 0) {
										pulse.state = 0;
									}
								}
							}
							left  = Math.max(Math.min(left,  Short.MAX_VALUE), Short.MIN_VALUE);
							right = Math.max(Math.min(right, Short.MAX_VALUE), Short.MIN_VALUE);
							sbuffer.put(i * 2 + 0, (short)left);
							sbuffer.put(i * 2 + 1, (short)right);
						}
					}
					line.write(buffer.array(), 0, sampleBytes);
				}
			} catch (LineUnavailableException e) {
				throw new RuntimeException("Failed to create audio output");
			}
		}
	}

	private Audio() {
		sampleRate = 48000;
		thread = new AudioThread(this);
		start();
	}

	public static Audio getInstance() {
		if (instance == null) {
			instance = new Audio();
		}
		return instance;
	}

	public void start() {
		if (running)
			return;
		running = true;
		thread.start();
	}

	public void shutdown() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			System.err.println("Audio thread failed to join: " + e.getLocalizedMessage());
		}
	}

	/** Make a new pulse channel */
	public PulseChannel makePulse() {
		PulseChannel pulse = new PulseChannel(this);
		synchronized (this) { pulseChannels.add(pulse); }
		return pulse;
	}

	@Override
	protected void finalize() {
		shutdown();
	}
}
