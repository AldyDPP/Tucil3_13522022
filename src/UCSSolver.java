package src;
import java.util.*;

public class UCSSolver {

    private HashSet<String> dictionary;
    private Trie trie;
    
    public UCSSolver() {}
    public void setTrie(Trie trie_) {
        trie = trie_;
    }
    public void setDict(HashSet<String> dictionary_) {
        dictionary = dictionary_;
    }

    public ArrayList<String> solve(String startword, String endword, boolean usetrie) {
        
        Queue<String> queue = new LinkedList<String>();
        Queue<Integer> qsteps = new LinkedList<Integer>();
        HashMap<String, String> parentHashMap = new HashMap<String, String>();
        parentHashMap.put(startword, startword);
        queue.add(startword);
        qsteps.add(0);

        while (!queue.isEmpty()) {
            
            String currentstr = queue.poll();
            int steps = qsteps.poll();
            int l = currentstr.length();

            if (currentstr.equals(endword)) {
                break;
            }

            for (int i = 0; i < l; ++i) {

                if (endword.charAt(i) == currentstr.charAt(i)) continue;

                for (int j = 0; j < 26; j++) {
                    
                    String newstr = "";
                    
                    if (i != 0) newstr += currentstr.substring(0, i);

                    newstr += Character.toString((char) j + 97); // ascii for 'a'

                    if (i != l - 1) newstr += currentstr.substring(i + 1, l);

                    boolean exists;
                    if (usetrie) exists = trie.exists(newstr);
                    else exists = dictionary.contains(newstr);

                    if (exists && !parentHashMap.containsKey(newstr)) {
                        queue.add(newstr);
                        qsteps.add(steps + 1);
                        parentHashMap.put(newstr, currentstr);
                    }
                }
            }
        }

        ArrayList<String> path = new ArrayList<String>();
        String str = endword;
        while (str != startword) {
            path.add(str);
            str = parentHashMap.get(str);
        }
        
        path.add(startword);
        int l = path.size();
        for (int i = 0; i < l / 2; i++) {
            String temp1 = path.get(i);
            String temp2 = path.get(l - i - 1);
            path.set(i, temp2);
            path.set(l - i - 1, temp1);
        }

        
        return path;
    }

    
}