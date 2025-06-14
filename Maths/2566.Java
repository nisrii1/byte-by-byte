class Solution {
    public int minMaxDifference(int num) {
        String numStr = String.valueOf(num);
        char firstDigit = numStr.charAt(0);

        // Construct the maximum number
        StringBuilder maxStr = new StringBuilder();
        for (char c : numStr.toCharArray()) {
            maxStr.append(c == firstDigit ? '9' : c);
        }

        // Construct the minimum number
        StringBuilder minStr = new StringBuilder();
        for (char c : numStr.toCharArray()) {
            minStr.append(c == firstDigit ? '0' : c);
        }

        // Calculate the difference
        int max = Integer.parseInt(maxStr.toString());
        int min = Integer.parseInt(minStr.toString());
        return max - min;
    }
}
