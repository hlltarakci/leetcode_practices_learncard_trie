// https://leetcode.com/explore/learn/card/trie/148/practical-application-i/1053/

class Solution {
    public String replaceWords(List<String> dict, String sentence) {
        if(sentence == null || dict == null || dict.size() == 0) return sentence;
        StringBuilder sb = new StringBuilder();
        
        Set<String> dictSet = new HashSet<>(dict);
        String[] words = sentence.split(" ");
        
        for(String word: words) {
            StringBuilder prefix = new StringBuilder();
            boolean isFound = false;
            for(char ch: word.toCharArray()) {
                prefix.append(ch);
                if(dictSet.contains(prefix.toString())) {
                    sb.append(prefix);
                    isFound = true;
                    break;
                }
            }
            if(!isFound) sb.append(word);
            sb.append(' ');
        }
        sb.deleteCharAt(sb.length()-1);
        
        return sb.toString();
    }
}
