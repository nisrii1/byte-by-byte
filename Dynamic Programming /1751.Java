public class Solution {
    public int maxValue(int[][] events, int k) {
        // Sort by start time
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));
        int n = events.length;
        // DP table: dp[i][j] -> max value from i-th event with j selections left
        int[][] dp = new int[n][k + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        
        return dfs(0, k, events, dp);
    }

    private int dfs(int index, int k, int[][] events, int[][] dp) {
        if (index >= events.length || k == 0) return 0;
        if (dp[index][k] != -1) return dp[index][k];

        // Skip this event
        int notTake = dfs(index + 1, k, events, dp);

        // Take this event
        int nextIndex = findNextEvent(events, events[index][1]);
        int take = events[index][2] + dfs(nextIndex, k - 1, events, dp);

        return dp[index][k] = Math.max(take, notTake);
    }

    // Binary search to find the next non-overlapping event
    private int findNextEvent(int[][] events, int endTime) {
        int low = 0, high = events.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (events[mid][0] > endTime) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
