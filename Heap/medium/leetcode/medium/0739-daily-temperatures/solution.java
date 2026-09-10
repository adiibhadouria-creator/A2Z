class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        //I will maintain a monotonic stack in which it will store next greater element but with a twist that is gap of days between them
        int n = temperatures.length;
        int[] ans = new int[n];
        Deque<Integer> st = new ArrayDeque<>();
        for(int i=n-1;i>=0;i--){
            while(!st.isEmpty()&& temperatures[st.peek()]<=temperatures[i] ){
                st.pop();
            }
            ans[i] = st.isEmpty() ? 0 : st.peek()-i;
            st.push(i);
        }
        return ans;
    }
}