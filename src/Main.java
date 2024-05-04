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
        String s1, s2, alg;
        while (true) {
            

            s1 = sc.next();
            s1 = s1.toLowerCase();
            if (s1.equals("EXIT")) break;
            s2 = sc.next();
            s2 = s2.toLowerCase();
            alg = sc.next();
            sc.nextLine();

            
            boolean check1,check2;
            try {
                check1 = trie.exists(s1);
                check2 = trie.exists(s2);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("You should input english words correctly, do not use numbers/symbols.");
                continue;
            }

            if (!check1) {
                System.out.println("Couldn't find word 1 in dictionary.");
                continue;
            }
            
            if (!check2) {
                System.out.println("Couldn't find word 2 in dictionary.");
                continue;
            }
            
            if (s1.length() != s2.length()) {
                System.out.println("You should input same length words.");
                continue;
            }

            long startTime = System.nanoTime();
            try {
                
                ArrayList<String> ans;
                
                if (alg.compareToIgnoreCase("ucs") == 0) {
                    ans = ucs.solve(s1, s2);
                }
                else if (alg.compareToIgnoreCase("astar") == 0) {
                    ans = astar.solve(s1, s2);
                }
                else if (alg.compareToIgnoreCase("gbfs") == 0) {
                    ans = gbfs.solve(s1, s2);
                }
                else {
                    throw new Exception("That is not the correct name of an algorithm. Please input \"UCS\", \"ASTAR\", or \"GBFS\" as the third input string (case insensitive).");
                }
                
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
    }
}