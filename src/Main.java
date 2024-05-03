package src;

import java.util.*;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        boolean usetrie = true;

        WordLoader wl = new WordLoader("words.txt");
        UCSSolver ucs = new UCSSolver();

        // HashSet<String> dictionary = wl.load();
        // ucs.setDict(dictionary);

        Trie trie = wl.loadTrie();
        ucs.setTrie(trie);

        while (1) {

            ArrayList<String> x = ucs.solve(s1, s2, usetrie);
            
            for (int i = 0; i < x.size(); ++i) {
                System.out.println(x.get(i));
            }
        }
    }
}