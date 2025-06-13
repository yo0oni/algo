import java.util.*;

class Solution {
    static HashSet<String> gemVar = new HashSet<>();
    static HashMap<String, Integer> gemIndex = new HashMap<>();
    static int[] getCount;

    public int[] solution(String[] gems) {
        int n = gems.length;
        for (String gem : gems) gemVar.add(gem);
        
        int idx = 0;
        for (String gem : gemVar) gemIndex.put(gem, idx++);
        
        int kinds = gemVar.size();
        getCount = new int[kinds];
        
        int haveKinds = 0;
        int start = 0, end = 0;
        int minStart = 0, minEnd = n;

        while (end < n) {
            int eIdx = gemIndex.get(gems[end]);
            
            if (getCount[eIdx] == 0) haveKinds++;
            getCount[eIdx]++;
            end++;

            while (haveKinds == kinds && start < end) {
                if (end - start < minEnd - minStart) {
                    minStart = start;
                    minEnd = end;
                }
                
                int sIdx = gemIndex.get(gems[start]);
                getCount[sIdx]--;
                if (getCount[sIdx] == 0) haveKinds--;
                start++;
            }
        }

        return new int[]{minStart + 1, minEnd};
    }
}
