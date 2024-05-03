package src;

import java.util.*;

class GBFSComp implements Comparator<String> {
    private String endstr;
    public GBFSComp(String str){
        endstr = str;
    }

    public int compare(String str1, String str2) {
        int ans1 = 0;
        int ans2 = 0;
        int l = endstr.length();
        for (int i = 0; i < l; i++) {
            if (endstr.charAt(i) == str1.charAt(i)) ans1++;
            if (endstr.charAt(i) == str2.charAt(i)) ans2++;
        }
        return ans2 - ans1; // Java Prioqueue has ascending priority
    }
}

public class GBFSSolver implements Solver {
    
    private Trie trie;
    public void setTrie(Trie trie_) {
        trie = trie_;
    }
    
    public ArrayList<String> solve(String startword, String endword) throws Exception,OutOfMemoryError {
        
        GBFSComp comp = new GBFSComp(endword);
        PriorityQueue<String> pq = new PriorityQueue<String>(comp);


        HashMap<String, String> parentHashMap = new HashMap<String, String>();
        parentHashMap.put(startword, startword);
        pq.add(startword);
        boolean found = false;

        while (!pq.isEmpty()) {
            
            String currentstr = pq.poll();
            int l = currentstr.length();
            if (currentstr.equals(endword)) {
                found = true;
                break;
            }

            for (int i = 0; i < l; ++i) {

                for (int j = 0; j < 26; j++) {
                    
                    String newstr = currentstr.substring(0, i) + Character.toString((char) j + 97) + currentstr.substring(i + 1, l);
                    
                    boolean exists = trie.exists(newstr);
                    if (exists && !parentHashMap.containsKey(newstr)) {
                        pq.add(newstr);
                        parentHashMap.put(newstr, currentstr);
                    }
                }
            }
        }

        if (!found) {
            throw new Exception("Solution doesn't exist");
        }

        ArrayList<String> path = new ArrayList<String>();
        String str = endword;
        while (!str.equals(startword)) {
            path.add(str);
            str = parentHashMap.get(str);
        }
        
        path.add(startword);
        Collections.reverse(path);

        return path;
    }
}
