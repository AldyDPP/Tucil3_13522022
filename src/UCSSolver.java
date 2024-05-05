package src;
import java.util.*;

public class UCSSolver implements Solver {

    private Trie trie;
    
    public UCSSolver() {}
    public void setTrie(Trie trie_) {
        trie = trie_;
    }

    public ArrayList<String> solve(String startword, String endword) throws Exception, OutOfMemoryError {
        
        Queue<String> queue = new LinkedList<String>();
        HashMap<String, String> parentHashMap = new HashMap<String, String>();
        parentHashMap.put(startword, startword);
        queue.add(startword);
        boolean found = false;
        Integer totalNodes = 0;

        while (!queue.isEmpty()) {
            
            String currentstr = queue.poll();
            int l = currentstr.length();
            if (currentstr.equals(endword)) {
                found = true;
                break;
            }
            totalNodes++;

            for (int i = 0; i < l; ++i) {

                for (int j = 0; j < 26; j++) {
                    
                    String newstr = currentstr.substring(0, i) + Character.toString((char) j + 97) + currentstr.substring(i + 1, l);
                    
                    boolean exists = trie.exists(newstr);
                    if (exists && !parentHashMap.containsKey(newstr)) {
                        queue.add(newstr);
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
        path.add(totalNodes.toString());

        return path;
    }

    
}