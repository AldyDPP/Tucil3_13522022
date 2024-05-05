package src;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        
        
        WordLoader wl = new WordLoader("words.txt");
        Solver ucs, astar, gbfs;
        Trie trie;
        
        System.out.println("Attempting to load dictionary... (this process shouldn't take more than a few seconds)");

        try {
            ucs = new UCSSolver();
            astar = new AStarSolver();
            gbfs = new GBFSSolver();

            trie = wl.loadTrie();
            ucs.setTrie(trie);
            astar.setTrie(trie);
            gbfs.setTrie(trie);

        } catch (FileNotFoundException f) {
            System.out.println("Dictionary load failed, \"words.txt\" file not found. Perhaps it has been moved somewhere?");
            System.out.println("Exiting program.");
            return;
        } catch (Exception e) {
            System.out.println("Dictionary load failed, unknown error occured.");
            System.out.println("Exiting program.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        String s1, s2, alg;

        System.out.println("Dictionary successfully loaded. The program is now running. Input \"/help\" to get a link to the use guide.");
        while (true) {
            
            System.out.printf(">> ");
            s1 = sc.next();
            s1 = s1.toLowerCase();
            
            if (s1.equals("/quit")) break;
            if (s1.equals("/help")) {
                System.out.println("https://github.com/AldyDPP/Tucil3_13522022?tab=readme-ov-file#how-to-use");
                sc.nextLine();
                continue;
            }

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
                    System.out.println("Algorithm name is incorrect. Please input \"UCS\", \"ASTAR\", or \"GBFS\" as the third input string (case insensitive).");
                    continue;
                }
                
                long stopTime = System.nanoTime();


                System.out.println("\n----------");
                for (int i = 0; i < ans.size() - 1; ++i) {
                    System.out.printf("%d. ", i + 1);
                    System.out.println(ans.get(i));
                }
                System.out.println("----------");
                System.out.printf("Done! Execution time: %d ms\n", (stopTime - startTime) / 1000000);
                System.out.printf("Total nodes visited: %s\n", ans.get(ans.size() - 1));

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