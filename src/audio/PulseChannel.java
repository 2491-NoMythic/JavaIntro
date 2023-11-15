package audio;

public final class PulseChannel {
	final Audio audio;
	int volume;
	int dutyCycle;
	int nextDutyCycle;
	int ticksPerSample;
	int balance;
	int state;
	int volumeTarget;
	int volumePerSample;
	int volumeState;
	int frequencyTarget;
	int frequencyPerSample;
	int frequencyState;
	int timeToInactive;
	int samplesRemaining;

	PulseChannel(Audio audio) {
		this.audio = audio;
		setVolume(1);
		setDutyCycle(0.5);
	}

	private static int ToInt256(double val) {
		return (int)(val * (float)0x100 + 0.5f);
	}

	private static int ToInt4096(double val) {
		return (int)(val * (float)0x1000 + 0.5f);
	}

	private int calcTicksPerSample(double hz) {
		double ticks = hz * (double)((long)1 << 31) / (double) audio.sampleRate;
		return (int)Math.max(Math.min(ticks + 0.5, (double)Integer.MAX_VALUE), 0);
	}

	private int calcSweep(int from, int to, double time) {
		int distance = Math.abs(from - to);
		return (int)(((long)distance * (long)0x10000) / (long)(time * audio.sampleRate + 0.5f));
	}

	public void setDutyCycle(double dutyCycle) {
		synchronized (audio) {
			long val = (long)((float)((long)1 << 31) * dutyCycle + 0.5f);
			this.nextDutyCycle = (int)Math.min(val, Integer.MAX_VALUE);
		}
	}

	public void setBalance(double balance) {
		synchronized (audio) {
			int bal = ToInt256(Math.abs(balance));
			this.balance = balance < 0 ? -bal : bal;
		}
	}

	public void setVolume(double volume) {
		synchronized (audio) {
			this.volume = ToInt4096(volume);
			this.volumeTarget = this.volume;
		}
	}

	public void sweepVolume(double startVolume, double endVolume, double time) {
		synchronized (audio) {
			this.volume = ToInt4096(startVolume);
			this.volumeTarget = ToInt4096(endVolume);
			this.volumePerSample = calcSweep(this.volume, this.volumeTarget, time);
		}
	}

	public void startTone(double hz) {
		synchronized (audio) {
			this.ticksPerSample = calcTicksPerSample(hz);
			this.frequencyTarget = this.ticksPerSample;
			this.samplesRemaining = Integer.MAX_VALUE;
		}
	}

	public void stopTone() {
		synchronized (audio) {
			this.samplesRemaining = 0;
		}
	}

	public void playTone(double hz, double time) {
		synchronized (audio) {
			this.ticksPerSample = calcTicksPerSample(hz);
			this.frequencyTarget = this.ticksPerSample;
			this.samplesRemaining = (int)(time * audio.sampleRate + 0.5);
		}
	}

	public void startSweep(double startHz, double endHz, double timeToEnd) {
		synchronized (audio) {
			this.ticksPerSample = calcTicksPerSample(startHz);
			this.frequencyTarget = calcTicksPerSample(endHz);
			this.frequencyPerSample = calcSweep(this.frequencyTarget, this.ticksPerSample, timeToEnd);
			this.samplesRemaining = Integer.MAX_VALUE;
		}
	}

	public void playSweep(double startHz, double endHz, double timeToEnd) {
		synchronized (audio) {
			this.ticksPerSample = calcTicksPerSample(startHz);
			this.frequencyTarget = calcTicksPerSample(endHz);
			this.frequencyPerSample = calcSweep(this.frequencyTarget, this.ticksPerSample, timeToEnd);
			this.samplesRemaining = (int)(timeToInactive * audio.sampleRate + 0.5);
		}
	}

	public void destroy() {
		synchronized (audio) {
			audio.pulseChannels.remove(this);
		}
	}
}