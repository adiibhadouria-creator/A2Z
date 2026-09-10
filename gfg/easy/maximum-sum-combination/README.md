# Maximum Sum Combination

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

You are given two integer arrays  **`a[]`**  and  **`b[]`**  of equal size. A sum combination is formed by adding one element from  **`a[]`**  and one from  **`b[]`**, using each index pair  **`(i, j)`**  at most once. Return the top **`k` maximum** sum combinations, sorted in non-increasing order.

 **Examples:** 

```
Input: a[] = [3, 2], b[] = [1, 4], k = 2
Output: [7, 6]
Explanation: Possible sums: 3 + 1 = 4, 3 + 4 = 7, 2 + 1 = 3, 2 + 4 = 6, Top 2 sums are 7 and 6.

```

```
Input: a[] = [1, 4, 2, 3], b[] = [2, 5, 1, 6], k = 3
Output: [10, 9, 9]
Explanation: The top 3 maximum possible sums are : 4 + 6 = 10, 3 + 6 = 9, and 4 + 5 = 9

```

**Constraints:
**1 ≤ a.size() = b.size() ≤ 105
1 ≤ k ≤ a.size()
1 ≤ a[i], b[i] ≤ 104

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-09-10T09:08:45.617Z  

```java
// Brute Approach -> Generate every combination and add it into list and then sort that list in descending order and return first k elements 
                      TC->O(n^2 * log n^2) SC-> O(n^2)

//Better Approach -> Use a min heap , generate every pair , add them into minheap if size is lesser than k , if size is greater than k then check if it is greater than 
                      heap top element then remove heap top element and add this sum to heap
                      TC->O(n^2 * log K) SC-> O(K)

//Optimal Approach -> Sort both the arrays in descending order then I know the first elements of both array will form the highest pair sum
                      then other pairs which may form higher sum will be either i+1,j or i,j+1 so put them both in maxheap and extract the maximum one , and also as can 
                      use only one pair once so use a set whenever use a pair put it into set
                      TC->O(nlog n) SC-> O(K)
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
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/maximum-sum-combination/1)
