import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

class Router {
    static HashSet<String> set;
    static String[] queue = new String[(int) 1e5];
    static int l, r;
    static int MAX;
    static HashMap<Integer, ArrayList<Integer>> map;

    public Router(int memoryLimit) {
        MAX = memoryLimit;
        l = r = 0;
        set = new HashSet<>();
        map = new HashMap<>();
    }
    // O(n)
    public static boolean addPacket(int source, int destination, int timestamp) {
        String key = getKey(source,destination,timestamp);
        if(!set.add(key))return false;
        if(r-l==MAX){
            forwardPacket();
        }
        queue[r++] = key;
        ArrayList<Integer> list = map.get(destination);
        if(list == null){
            list = new ArrayList<>();
            list.add(timestamp);
            map.put(destination, list);
        } else {
            list.add(timestamp);
        }
        return true;
    }
    // O(n)
    public static int[] forwardPacket() {
        if(l==r)return new int[0];
        String[] split = queue[l].split(",");
        set.remove(queue[l++]);
        int destination = Integer.parseInt(split[1]);
        int timestamp = Integer.parseInt(split[2]);
        ArrayList<Integer> list = map.get(destination);
        int index = Collections.binarySearch(list, timestamp);
        list.remove(index);
        return new int[]{Integer.parseInt(split[0]), destination, timestamp};
    }
    // O(logn)
    public static int getCount(int destination, int startTime, int endTime) {
        ArrayList<Integer> list = map.get(destination);
        if(list == null)return 0;
        int left = lower(list, startTime)+1;
        int right = list.size()-upper(list, endTime);
        return list.size()-left-right;
    }
    // O(1)
    private static String getKey(int s, int d, int t){
        return s+","+d+","+t;
    }
    // O(logn)
    private static int lower(ArrayList<Integer> list, int target){
        int l = 0, r = list.size()-1;
        int ans = -1;
        while (l<=r){
            int mid = l+((r-l)>>1);
            if(list.get(mid) < target){
                ans = mid;
                l = mid+1;
            } else r = mid-1;
        }
        return ans;
    }
    // O(logn)
    private static int upper(ArrayList<Integer> list, int target){
        int l = 0, r = list.size()-1;
        int ans = r+1;
        while (l<=r){
            int mid = l+((r-l)>>1);
            if(list.get(mid)>target){
                r = mid-1;
                ans = mid;
            } else l = mid+1;
        }
        return ans;
    }
}

/**
 * Your Router object will be instantiated and called as such:
 * Router obj = new Router(memoryLimit);
 * boolean param_1 = obj.addPacket(source,destination,timestamp);
 * int[] param_2 = obj.forwardPacket();
 * int param_3 = obj.getCount(destination,startTime,endTime);
 */
