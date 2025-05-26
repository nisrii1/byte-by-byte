public class Solution {
    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[n];

        // Initialize graph and in-degree array
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            inDegree[edge[1]]++;
        }

        // Topological sort using Kahn's algorithm
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        int[][] freq = new int[n][26]; // Frequency array for each node
        int processedNodes = 0;
        int maxColorValue = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            processedNodes++;
            int colorIndex = colors.charAt(node) - 'a';
            freq[node][colorIndex]++;

            // Update maximum color value
            maxColorValue = Math.max(maxColorValue, freq[node][colorIndex]);

            for (int neighbor : graph.get(node)) {
                for (int i = 0; i < 26; i++) {
                    freq[neighbor][i] = Math.max(freq[neighbor][i], freq[node][i]);
                }

                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // If there are unprocessed nodes, there is a cycle
        return processedNodes == n ? maxColorValue : -1;
    }

}    
