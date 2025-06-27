class Solution {
    public int longestSubsequence(String s, int k) {
        int sum = 0;        //  This is our running total.  Think of it as how much "food" we've eaten so far. Starts at zero (empty stomach!).
        int len = 0;        //  This is the length of our chosen subsequence (how much "food" we've picked). Starts at zero (no food yet!).
        int bitPos = 0;     //  This keeps track of the "place value" of each bit (like units, tens, hundreds).

        for (int i = s.length() - 1; i >= 0; i--) { //  We're walking through the secret code *backwards* (sneaky!).

            char ch = s.charAt(i);                  //  Look at the current character ('0' or '1').

            if (ch == '0') {                      // 0️ If it's a zero...
                len++;                           //  We *always* add it.  Zeros are free calories! 0 is always good!
            } else {                              //  If it's a one...
                if (bitPos < 31 && sum + (1 << bitPos) <= k) { //  Can we add this one without going over our calorie limit (k)?
                    //  bitPos < 31: Makes sure we're not trying to add a '1' that is "too big" to be represented by an integer.
                    //  sum + (1 << bitPos) <= k: Check if adding this '1' to the current sum makes us exceed k. (1 << bitPos) is the power of 2 for this bit.
                    sum += (1 << bitPos);          //  Yes! Add the calorie value (2 to the power of bitPos) to our sum.
                    len++;                           // And increase our subsequence length!
                }
            }
            bitPos++;                            //  Move to the next bit position. (Think units, then twos, then fours, etc.)
        }

        return len;                             //  Return the total length of the longest subsequence we found. (The most "food" we could eat!)
    }
}
