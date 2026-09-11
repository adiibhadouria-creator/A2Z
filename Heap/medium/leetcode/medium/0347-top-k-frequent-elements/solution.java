class Solution {
    // static class Pair{
    //     int value;
    //     int freq;
    //     Pair(int value , int freq){
    //         this.value = value;
    //         this.freq = freq;
    //     }
    // }
    public int[] topKFrequent(int[] nums, int k) {
        //First approach using hashmap and priority queue TC-O(nlogk)
        //Second approach using hashmap and bucket algo TC-O(N) SC-O(N)
        // PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)->Integer.compare(a.freq,b.freq));
        Map<Integer,Integer> map = new HashMap<>();
        for(int n:nums){
            map.put(n,map.getOrDefault(n,0)+1);
        }
        //get the key and push key and freq both in the priority queue then maintain k size also
        // for(Map.Entry<Integer,Integer> entry:map.entrySet()){
        //     int num = entry.getKey();
        //     int freq = entry.getValue();
        //     Pair p = new Pair(num,freq);
        //     pq.add(p);
        //     if(pq.size()>k) pq.poll();
        // }
        // int[] topK = new int[k];
        // int i=0;
        // while(!pq.isEmpty()&&i<k){
        //     Pair p = pq.poll();
        //     topK[i++] = p.value;
        // }
        // return topK;

        //Bucket sort algo - 
        //1.
        List<Integer>[] buckets = new List[nums.length+1];
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            int num = entry.getKey();
            int freq = entry.getValue();
            if(buckets[freq] == null){
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(num);
        }
        List<Integer> topK = new ArrayList<>();
        for(int i=buckets.length-1;i>=0 && topK.size()<k;i--){
            if(buckets[i]!=null){
                topK.addAll(buckets[i]);
            }
        }
        return topK.stream().mapToInt(i -> i).toArray();
    }
    //TC->O(N) SC->O(N)
}