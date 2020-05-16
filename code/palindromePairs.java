// https://leetcode.com/explore/learn/card/trie/149/practical-application-ii/1138/

class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> indexes = new ArrayList<>();
        if(words == null) return indexes;
        for(int i=0; i<words.length; i++) {
            for(int j=i+1; j<words.length; j++) {
                if(isPalindrome(words[i] + words[j])) indexes.add(Arrays.asList(i, j));
                if(isPalindrome(words[j] + words[i])) indexes.add(Arrays.asList(j, i));
            }
        }
        
        return indexes;
    }
    
    private boolean isPalindrome(String str) {
        int left = 0, right = str.length()-1;
        while(left < right) {
            if(str.charAt(left) != str.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }
}
