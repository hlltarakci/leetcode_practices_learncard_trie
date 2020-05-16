// https://leetcode.com/explore/learn/card/trie/148/practical-application-i/1058/

class MapSum {
    public char ch;
    public Map<Character, MapSum> map;
    public int count;
    public boolean isWord;

    /** Initialize your data structure here. */
    public MapSum() {
        ch = '-';
        map = new HashMap<>();
        count = 0;
    }
    
    public MapSum(char ch) {
        this.ch = ch;
        map = new HashMap<>();
        count = 0;
    }
    
    public void insert(String key, int val) {
        MapSum curr = update(key, val);
        if(curr.count != 0 && curr.isWord) curr = update(key, -curr.count);
        
        curr.isWord = true;
        curr.count = val;
        
        for(MapSum trie: curr.map.values()) curr.count += trie.count;
    }
    
    private MapSum update(String key, int val) {
        MapSum curr = this;
        for(char c: key.toCharArray()) {
            if(!curr.map.containsKey(c)) curr.map.put(c, new MapSum(c));
            curr.count += val;
            curr = curr.map.get(c);
        }
        
        return curr;
    }
    
    public int sum(String prefix) {
        MapSum curr = this;
        for(char c: prefix.toCharArray()) {
            if(!curr.map.containsKey(c)) return 0;
            curr = curr.map.get(c);
        }
        
        return curr.count;
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */
