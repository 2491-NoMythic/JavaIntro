package examples.e_strings;

// we will build up the alphabet one character at a time
public class Alphabet {
    public static void main(String[] args) throws Exception {
        // our alphabet string starts out empty
        String alphabet = "";
        // this number will represent the ascii value of a letter
        int number = 0;
        // a char is a data type that represents an ascii character very similar to a number
        char letter;

        for (int i=0; i < 26; i++) {
            // the ascii character for 'A' is the number 65
            number = 65 + i;
            // we 'cast' or convert the number to a char. we can do this because they are so similar
            letter = (char)number;
            // we concatenate the letter to the end of our string
            alphabet = alphabet + letter;
        }

        System.out.println(alphabet);
    }
}
