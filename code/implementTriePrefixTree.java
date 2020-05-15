// https://leetcode.com/explore/learn/card/trie/147/basic-operations/1047/

class Trie {
    int N = 26;
    public Trie[] children;
    public char ch;
    public boolean isWord;
    
    /** Initialize your data structure here. */
    public Trie() {
        ch = '-';
        children = new Trie[26];
    }
    
    public Trie(char ch) {
        this.ch = ch;
        children = new Trie[26];
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        Trie curr = this;
        for(char ch: word.toCharArray()) {
            int index = ch-'a';
            if(curr.children[index] == null) curr.children[index] = new Trie(ch);
            curr = curr.children[index];
        }
        
        curr.isWord = true;
    }
    
    private Trie find(String str) {
        Trie curr = this;
        for(char ch: str.toCharArray()) {
            int index = ch-'a';
            if(curr.children[index] == null) return null;
            curr = curr.children[index];
        }
        return curr;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie node = find(word);
        return node == null ? false : node.isWord;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie node = find(prefix);
        return node == null ? false : true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
