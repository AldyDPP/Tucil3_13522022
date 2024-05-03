package src;
import java.util.*;
import java.io.*;

public class WordLoader {

    private String filename;
    
    public WordLoader(String _filename) {
        filename = _filename;
    }

    public Trie loadTrie() throws FileNotFoundException {
        
        File file = new File(filename);

        Scanner scanner = new Scanner(file);

        Trie dictionary = new Trie();

        while (scanner.hasNextLine()) {
            String word = scanner.nextLine();
            dictionary.insert(word);
        }

        scanner.close();
        
        return dictionary;

    }
}