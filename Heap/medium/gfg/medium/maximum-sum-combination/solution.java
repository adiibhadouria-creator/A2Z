class Solution {
    public ArrayList<Integer> topKSumPairs(int[] a, int[] b, int k) {
        // code here
        int n = a.length;
        Arrays.sort(a);
        Arrays.sort(b);
        
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((x,y)->Integer.compare(y[0],x[0]));
        Set<String> visited = new HashSet<>();
        //Array of sum , i , j
        maxHeap.add(new int[]{a[n-1]+b[n-1],n-1,n-1});
        visited.add((n-1)+","+(n-1));
        ArrayList<Integer> ans = new ArrayList<>();
        while(k>0 && !maxHeap.isEmpty()){
            int[] curr = maxHeap.poll();
            int sum = curr[0];
            int i = curr[1] , j = curr[2];
            ans.add(sum);
            k--;
            
            if(i-1>=0){
                String key = (i-1)+","+j;
                if(!visited.contains(key)){
                    maxHeap.add(new int[]{a[i-1]+b[j],i-1,j});
                    visited.add(key);
                }
            }
            if(j-1>=0){
               String key = (i)+","+(j-1);
                if(!visited.contains(key)){
                    maxHeap.add(new int[]{a[i]+b[j-1],i,j-1});
                    visited.add(key);
                } 
            }
        }
        return ans;
    }
}