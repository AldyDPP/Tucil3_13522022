package src;

import java.util.*;

class StringDepth {
    private String string;
    private int depth;

    public StringDepth(String s, int d) {
        string = s;
        depth = d;
    }

    public int getDepth() {
        return depth;
    }
    public String getStr() {
        return string;
    }
}

class AStarComp implements Comparator<StringDepth> {
    
    private String endstr;
    public AStarComp(String str) {
        endstr = str;
    }

    public int compare(StringDepth obj1, StringDepth obj2) {

        int depth1 = obj1.getDepth();
        int depth2 = obj2.getDepth();
        int ans1 = depth1;
        int ans2 = depth2;
        String str1 = obj1.getStr();
        String str2 = obj2.getStr();

        int l = endstr.length();
        for (int i = 0; i < l; i++) {
            if (endstr.charAt(i) != str1.charAt(i)) ans1++;
            if (endstr.charAt(i) != str2.charAt(i)) ans2++;
        }
        if (ans1 == ans2) return depth1 - depth2; 
        return ans1  - ans2;

    }
}

public class AStarSolver implements Solver {

    private Trie trie;
    public void setTrie(Trie trie_) {
        trie = trie_;
    }
    
    public ArrayList<String> solve(String startword, String endword) throws Exception,OutOfMemoryError {

        AStarComp comp = new AStarComp(endword);
        PriorityQueue<StringDepth> pq = new PriorityQueue<StringDepth>(comp);
        Integer totalNodes = 0;

        HashMap<String, String> parentHashMap = new HashMap<String, String>();
        parentHashMap.put(startword, startword);
        pq.add(new StringDepth(startword, 0));
        boolean found = false;

        while (!pq.isEmpty()) {
            
            StringDepth obj = pq.poll();
            String currentstr = obj.getStr();
            int depth = obj.getDepth();
            totalNodes++;

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
                        pq.add(new StringDepth(newstr, depth + 1));
                        parentHashMap.put(newstr, currentstr);
                    }
                }
            }
        }

        if (!found) {
            throw new Exception("Solution doesn't exist\nTotal nodes visited: " + totalNodes.toString());
        }

        ArrayList<String> path = new ArrayList<String>();
        String str = endword;
        while (!str.equals(startword)) {
            path.add(str);
            str = parentHashMap.get(str);
        }
        
        path.add(startword);
        Collections.reverse(path);
        path.add(totalNodes.toString());
        
        return path;
    }
}


