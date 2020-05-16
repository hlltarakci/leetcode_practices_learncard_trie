// https://leetcode.com/explore/learn/card/trie/149/practical-application-ii/1056/

class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        Set<String> list = new HashSet<>();
        
        for(String word: words) {
            if(list.contains(word)) continue;
            for(int r=0; r<board.length; r++) {
                boolean isFound = false;
                for(int c=0; c<board[0].length; c++) {
                    if(findWord(board, r, c, word, 0)) {
                        isFound = true;
                        list.add(word);
                        break;
                    }
                }
                if(isFound) break;
            }
        }
        return new ArrayList<>(list);
    }
    
    private boolean findWord(char[][] board, int r, int c, String word, int w) {
        if(word == null || w == word.length()) return true;
        if(r < 0 || r >= board.length || 
           c < 0 || c >= board[0].length ) return false;
        
        int[][] directions = {
            {-1,0}, {0,-1}, {0,1}, {1,0}
        };
    
        if(board[r][c] == word.charAt(w)) { 
            char temp = board[r][c];
            board[r][c] = '-';
            
            for(int[] dir: directions) {
                int newR = r + dir[0];
                int newC = c + dir[1];
                
                if(findWord(board, newR, newC, word, w+1)) {
                    board[r][c] = temp;
                    return true;
                }
            }
            
            board[r][c] = temp;
        }       
                     
        return false;        
    }
}
