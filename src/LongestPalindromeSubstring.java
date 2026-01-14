

/**
 * LeetCode 5: Longest Palindromic Substring
 * Strategy: Expand Around Center (Iterative)
 * Time Complexity: O(N^2)
 * Space Complexity: O(1) - No extra DP table used!
 */
public class LongestPalindromeSubstring {

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";

        int n = s.length();
        int start = 0;
        int end = 0;

        for (int i = 0; i < n; i++) {
            // Case 1: Odd Length Palindrome (Center is 'i')
            // e.g., "aba" -> center is 'b'
            int len1 = expand(s, i, i);

            // Case 2: Even Length Palindrome (Center is between 'i' and 'i+1')
            // e.g., "abba" -> center is gap between 'b' and 'b'
            int len2 = expand(s, i, i + 1);

            int len = Math.max(len1, len2);

            // If we found a longer palindrome, update the boundaries
            if (len > (end - start)) {
                // MATH EXPLANATION:
                // To find the start, we move back half the length from center 'i'.
                // We use (len - 1) / 2 to handle both even and odd lengths correctly.
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        // Return substring from start to end (inclusive in logic, exclusive in Java API)
        return s.substring(start, end + 1);
    }

    /**
     * Helper function to expand outwards from the center.
     * @return The length of the valid palindrome found.
     */
    private int expand(String s, int left, int right) {
        // Expand while characters match and are within bounds
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // The loop breaks when pointers are at INVALID positions (one step too far).
        // Length formula: (right - 1) - (left + 1) + 1 = right - left - 1
        return right - left - 1;
    }


    public static void main(String[] args) {
        LongestPalindromeSubstring solver = new LongestPalindromeSubstring();
        String input = "babad";
        String result = solver.longestPalindrome(input);

        // Expected: "bab" or "aba"
        System.out.println("Input: " + input);
        System.out.println("Output: " + result);
    }
}