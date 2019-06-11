import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
ID: ny834271
LANG: JAVA
TASK: namenum
 */
class namenum {

    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(new File("namenum.in"));
            Scanner dictScanner = new Scanner(new File("dict.txt"));
            PrintWriter out = new PrintWriter("namenum.out")) {
            String cowId = scanner.nextLine();
            boolean matchedExisting = false;
            while (dictScanner.hasNextLine()) {
                String word = dictScanner.nextLine();
                if (isMatched(word, cowId)) {
                    matchedExisting = true;
                    out.println(word);
                }
            }

            if (!matchedExisting) {
                out.println("NONE");
            }
        }
    }

    private static char[] MAPPINGS = {
        '2', '2', '2', '3', '3', '3', '4', '4', '4', '5', '5', '5',
        '6', '6', '6', '7', '0', '7', '7', '8', '8', '8', '9', '9', '9', '0'
    };

    static boolean isMatched(String word, String cowId) {
        if (word == null || cowId == null) {
            throw new IllegalArgumentException("Either target word or cow id is null!");
        }

        if (word.length() != cowId.length()) {
            return false;
        }

        for (int i = 0; i < word.length(); i++) {
            char converted = MAPPINGS[word.charAt(i) - 'A'];
            if (cowId.charAt(i) != converted) {
                return false;
            }
        }

        return true;
    }
}
