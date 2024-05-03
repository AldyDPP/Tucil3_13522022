package src;

import java.util.ArrayList;

public interface Solver {
    ArrayList<String> solve(String startword, String endword) throws Exception, OutOfMemoryError;
    public void setTrie(Trie trie_);
}