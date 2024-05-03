package src;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        WordLoader wl = new WordLoader("words.txt");
        Solver ucs = new UCSSolver();
        Solver astar = new AStarSolver();
        Solver gbfs = new GBFSSolver();

        Trie trie = wl.loadTrie();
        ucs.setTrie(trie);
        astar.setTrie(trie);
        gbfs.setTrie(trie);

        Scanner sc = new Scanner(System.in);
        String s1, s2;
        while (true) {

            s1 = sc.next();
            if (s1.equals("EXIT")) break;

            s2 = sc.next();

            if (s1.length() != s2.length()) {
                System.out.println("Lengths are different");
                continue;
            }
            if (!trie.exists(s1)) {
                System.out.println("Word 1 is not a thing");
                continue;
            }
            
            if (!trie.exists(s2)) {
                System.out.println("Word 2 is not a thing");
                continue;
            }

            long startTime = System.nanoTime();
            try {
                
                ArrayList<String> ans = ucs.solve(s1, s2);
                long stopTime = System.nanoTime();


                System.out.println("\n----------");
                for (int i = 0; i < ans.size(); ++i) {
                    System.out.printf("%d. ", i + 1);
                    System.out.println(ans.get(i));
                }
                System.out.println("----------");
                System.out.printf("Done! Execution time: %d ms\n", (stopTime - startTime) / 1000000);

            } catch (OutOfMemoryError e) {
                System.out.println(e.getMessage());
                System.out.println("Perhaps it doesn't exist?\n");
                long stopTime = System.nanoTime();
                System.out.printf("Exited after: %d ms\n", (stopTime - startTime) / 1000000);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                long stopTime = System.nanoTime();
                System.out.printf("Exited after: %d ms\n", (stopTime - startTime) / 1000000);

            }

        }
        sc.close();
    }
}