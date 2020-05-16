// https://leetcode.com/explore/learn/card/trie/149/practical-application-ii/1057/

class Solution {
    // soln: https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/discuss/595227/Java-Using-trie
    class Trie {
        public int val;
        public Trie left, right;
        public Trie(int val) {
            this.val = val;
            left = null;
            right = null;
        }
    }
    
    
    public int findMaximumXOR(int[] nums) {
        int result = 0;
        Trie root = new Trie(-1);
        for(int num: nums) {
            insertTrie(root, num);
            result = Math.max(result, findMaxForNum(root, num));
        }
        return result;
    }
    
    private void insertTrie(Trie curr, int num) {
        for(int i=30; i>=0; i--) {
            int digit = (num >> i) & 1;
            if(digit == 0) {
                if(curr.left == null) curr.left = new Trie(0);
                curr = curr.left;
            } else {
                if(curr.right == null) curr.right = new Trie(1);
                curr = curr.right;
            }
        }
    }
    
    private int findMaxForNum(Trie curr, int num) {
        int result = Integer.MAX_VALUE;
        for(int i=30; i>=0; i--) {
            int digit = (num >> i) & 1;
            if(digit == 0 && curr.right != null) curr = curr.right;
            else if(digit == 1 && curr.left != null) curr = curr.left;
            else {
                result ^= (1<<i);
                if(curr.left != null) curr = curr.left;
                else curr = curr.right;
            }
        }
        return result;
    }
}
