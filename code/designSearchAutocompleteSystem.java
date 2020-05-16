// https://leetcode.com/explore/learn/card/trie/148/practical-application-i/1054/

class AutocompleteSystem {
    class Trie {
        public char ch;
        public Trie[] children;
        public int times;
        public boolean isEnd;
        
        public Trie(char ch) {
            this.ch = ch;
            children = new Trie[27];
        }
    }
    
    class Node {
        public String sentence;
        public int times;
        public Node(String sentence, int times) {
            this.sentence = sentence;
            this.times = times;
        }
    }
    
    private int index(char ch) {
        return ch == ' ' ? 26: ch - 'a';
    }
    
    Trie root;
    String inputSoFar;

    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new Trie('-');
        inputSoFar = "";
        for(int i=0; i<sentences.length; i++)
            insertTrie(sentences[i], times[i]);
    }
    
    private void insertTrie(String sentence, int times) {
        Trie curr = root;
        for(char ch: sentence.toCharArray()) {
            int ndx = index(ch);
            if(curr.children[ndx] == null) curr.children[ndx] = new Trie(ch);
            curr = curr.children[ndx];
        }
        curr.isEnd = true;
        curr.times += times;
    }
    
    private List<Node> getAllPossibleSentences(String prefix) {
        Trie curr = root;
        for(char ch: prefix.toCharArray()) {
            int ndx = index(ch);
            if(curr.children[ndx] == null) return new ArrayList<>();
            curr = curr.children[ndx];
        }
        
        return traverse(prefix, curr);
    }
    
    private List<Node> traverse(String prefix, Trie trie) {
        List<Node> list = new ArrayList<>();
        
        if(trie.times > 0) list.add(new Node(prefix, trie.times));
        for(Trie child: trie.children) {
            if(child == null) continue;
            list.addAll(traverse(prefix + child.ch, child));
        }
        
        return list;
    }
    
    public List<String> input(char c) {
        List<String> list = new ArrayList<>();
        
        if( c != '#') {
            inputSoFar = inputSoFar + c;
            List<Node> allPossibleSentences = getAllPossibleSentences(inputSoFar);
            Collections.sort(
              allPossibleSentences,
              (a, b) -> a.times == b.times ? a.sentence.compareTo(b.sentence) : b.times - a.times);
            for(int i=0; i< Math.min(3, allPossibleSentences.size()); i++)
                list.add(allPossibleSentences.get(i).sentence);
        } else {
            insertTrie(inputSoFar, 1);
            inputSoFar = "";
        }
        
        return list;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
