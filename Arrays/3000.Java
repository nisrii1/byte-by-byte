class Solution {
    public int areaOfMaxDiagonal(int[][] dimensions) {
        int maxDiagonalSquare = 0;
        int maxRectArea = 0;
        for (int[] dimension : dimensions) {
            int diagonalSquareArea = dimension[0] * dimension[0] + dimension[1] * dimension[1];
            if (maxDiagonalSquare == diagonalSquareArea) {
                int rectArea = dimension[0] * dimension[1];
                if (maxRectArea <= rectArea) {
                    maxRectArea = rectArea;
                    maxDiagonalSquare = diagonalSquareArea;
                }
            }
            if(maxDiagonalSquare < diagonalSquareArea) {
                maxRectArea =  dimension[0] * dimension[1];
                maxDiagonalSquare = diagonalSquareArea;
            }
        }
        return maxRectArea;
    }
}
