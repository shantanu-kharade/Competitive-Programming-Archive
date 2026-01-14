import java.util.Arrays;

/// LeetCode 516: Longest Palindromic Subsequence
/// Strategy: Dynamic Programming (Bottom-Up / Recursion with Memo)
/// Time Complexity: O(N^2)
/// Optimization: Solved directly on one string (Two Pointers) instead of LCS(s, reverse(s)).
public class LongestPalindromeSubseq {

    public int longestPalindromeSubSeq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        };
        // Start from the two ends of the string
        return solve(0, n - 1, s, dp);
    }

    public int solve(int i, int j, String s, int[][] dp) {
        // Base Case 1: Pointers have crossed
        if (i > j) return 0;
        // Base Case 2: Pointers at exact same spot (middle of odd palindrome)
        if (i == j) return 1;

        if (dp[i][j] != -1) return dp[i][j];

        if (s.charAt(i) == s.charAt(j)) {
            // MATCH: We add 2 because i and j are pointing to two different physical characters that match.
            // Then we move both pointers inward.
            return dp[i][j] = 2 + solve(i + 1, j - 1, s, dp);
        } else {
            // NO MATCH: We try two scenarios:
            // 1. Skip left character (move i forward)
            // 2. Skip right character (move j backward)
            return dp[i][j] = Math.max(solve(i, j - 1, s, dp), solve(i + 1, j, s, dp));
        }
    }

    public static void main(String[] args) {
        // Simple test case to verify logic before commit
        LongestPalindromeSubseq solver = new LongestPalindromeSubseq();
        String test = "bbbab";
        System.out.println("Expected: 4, Actual: " + solver.longestPalindromeSubSeq(test));
    }
}
