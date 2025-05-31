public class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length; // Size of the board
        int[] flattenedBoard = flattenBoard(board); // Convert 2D board to 1D array
        boolean[] visited = new boolean[n * n + 1]; // Track visited cells
        Queue<int[]> queue = new LinkedList<>(); // BFS queue (stores cell index and moves)
        
        queue.add(new int[]{1, 0}); // Start from the first cell
        visited[1] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cell = current[0];
            int moves = current[1];

            // If the last cell is reached, return the number of moves
            if (cell == n * n) {
                return moves;
            }

            // Roll the dice: Try moving 1 to 6 steps ahead
            for (int dice = 1; dice <= 6; dice++) {
                int nextCell = cell + dice;
                if (nextCell > n * n) break; // Skip if it goes beyond the board

                // Check if the next cell has a snake/ladder
                if (flattenedBoard[nextCell] != -1) {
                    nextCell = flattenedBoard[nextCell];
                }

                // Add the next cell to the queue if not visited
                if (!visited[nextCell]) {
                    visited[nextCell] = true;
                    queue.add(new int[]{nextCell, moves + 1});
                }
            }
        }

        return -1; // If no path to the last cell is found
    }

    // Helper function to flatten the 2D board into a 1D array
    private int[] flattenBoard(int[][] board) {
        int n = board.length;
        int[] flattened = new int[n * n + 1];
        int index = 1; // Flattened board starts at index 1
        boolean isLeftToRight = true;

        for (int i = n - 1; i >= 0; i--) { // Start from the last row
            if (isLeftToRight) {
                for (int j = 0; j < n; j++) {
                    flattened[index++] = board[i][j];
                }
            } else {
                for (int j = n - 1; j >= 0; j--) {
                    flattened[index++] = board[i][j];
                }
            }
            isLeftToRight = !isLeftToRight; // Alternate row direction
        }
        return flattened;
    }

}    
