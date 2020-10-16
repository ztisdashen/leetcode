package com.zt.my26;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ztisdashen
 * @program leetcode
 * @description
 * @create 2020-10-10 21:38
 */
public class Test39 {
    List<List<Integer>> list=new ArrayList<>();
    List<Integer> path=new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(candidates,target,0);
        return list;
    }
    private void dfs(int[] candidates, int target,int index){
        if(target==0){
            list.add(new ArrayList<>(path));
            return;
        }
        if (index > 0)index--;
        for(int i=index;i<candidates.length;i++){
            if(candidates[i]<=target){
                if(i>index&&candidates[i]==candidates[i-1]){
                    continue;
                }
                path.add(candidates[i]);
                dfs(candidates,target-candidates[i],i+1);
                path.remove(path.size()-1);
            }
        }
    }

    public static void main(String[] args) {
        Test39 t= new Test39();
        //System.out.println(t.combinationSum(new int[]{2,3,6,7},7));
        System.out.println(t.combinationSum(new int[]{2,3,5},8));
    }
}
