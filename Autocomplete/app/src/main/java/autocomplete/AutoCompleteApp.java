package autocomplete;

import java.util.List;
import java.util.Scanner;
import java.io.File;

public class AutoCompleteApp {

    public static void main(String[] args) {
        Trie trie = new Trie();
        loadWordsFromFile(trie, "words.txt");  // loads words from file
        System.out.println("Autocomplete loaded!");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a prefix (or type 'exit'): ");
            String input = scanner.nextLine();

            if (input.equals("exit")) break;

            List<String> suggestions = trie.autoComplete(input);
            System.out.println("Suggestions: " + suggestions);
        }

        scanner.close();
    }

    private static void loadWordsFromFile(Trie trie, String filename) {
        try (Scanner fileScanner = new Scanner(new File(filename))) {
            while (fileScanner.hasNextLine()) {
                String word = fileScanner.nextLine().trim();
                if (!word.isEmpty()) {
                    trie.insert(word.toLowerCase());
                }
            }
        } catch (Exception e) {
            System.err.println("Error loading dictionary: " + e.getMessage());
        }
    }
}
