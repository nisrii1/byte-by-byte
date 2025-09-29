class Solution {
    int triangle(int s, int e, int[] values,int[][] dp) {
        if(e-s < 2) {
            return 0;
        }
        if(dp[s][e] != -1)
            return dp[s][e];
        int min = Integer.MAX_VALUE;
        for(int i=s+1;i<e;i++) {
            min = Math.min(min, values[s]*values[i]*values[e] + triangle(s,i,values,dp) + triangle(i,e,values,dp));
        }
        return dp[s][e] = min;
    }
    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        int[][] dp = new int[n][n];
        for(int i=0;i<n;i++)
            Arrays.fill(dp[i],-1);
        return triangle(0,n-1,values,dp);
    }
}
