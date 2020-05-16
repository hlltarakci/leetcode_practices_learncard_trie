// https://leetcode.com/explore/learn/card/trie/149/practical-application-ii/1055/

class Solution {
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> squares = new ArrayList<>();
        Map<String, Set<String>> prefixMap = populatePrefixMap(words);
        
        backtrack(squares, prefixMap, new ArrayList<String>(), words[0].length());
        return squares;
    }
    
    private void backtrack(List<List<String>> squares, 
                           Map<String, Set<String>> prefixMap, 
                           List<String> square, int squareLen) {
        if(square.size() == squareLen) {
            squares.add(new ArrayList<String>(square));
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        for(String word: square) sb.append(word.charAt(square.size()));
        
        Set<String> candidates = prefixMap.getOrDefault(sb.toString(), new HashSet<>());
        for(String candidate: candidates) {
            square.add(candidate);
            backtrack(squares, prefixMap, square, squareLen);
            square.remove(square.size()-1);
        }
        
    }
    
    private Map<String, Set<String>> populatePrefixMap(String[] words) {
        Map<String, Set<String>> map = new HashMap<>();
        
        for(String word: words) {
            for(int i=0; i<=word.length(); i++) {
                String prefix = word.substring(0, i);
                Set<String> set = map.getOrDefault(prefix, new HashSet<>());
                set.add(word);
                map.put(prefix, set);
            }
        }
        
        return map;
    }
}
