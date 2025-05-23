class Solution {
    public int maxRemoval(int[] nums, int[][] queries) {
        int queryIndex = 0;
        Queue<Integer> available = new PriorityQueue<>(Collections.reverseOrder()); // Max-heap for available queries
        Queue<Integer> running = new PriorityQueue<>(); // Min-heap for running queries

        // Sort queries based on start index
        Arrays.sort(queries, Comparator.comparingInt(a -> a[0]));

        for (int i = 0; i < nums.length; ++i) {
            // Add all queries starting at or before index i to available
            while (queryIndex < queries.length && queries[queryIndex][0] <= i) {
                available.offer(queries[queryIndex++][1]);
            }

            // Remove queries from running that have ended before index i
            while (!running.isEmpty() && running.peek() < i) {
                running.poll();
            }

            // Apply queries from available to running as needed
            while (nums[i] > running.size()) {
                if (available.isEmpty() || available.peek() < i) {
                    return -1; // Cannot reduce nums[i] to zero
                }
                running.offer(available.poll());
            }
        }

        // Return the number of queries that were not applied
        return available.size();
    }
}
