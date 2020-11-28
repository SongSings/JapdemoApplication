package com.datajpa.jpademo;


import org.junit.Test;

import java.util.*;

public class PlanTests {

    @Test
    public void method(){
        int[] nums = new int[]{2,7,11,5};
        int target = 9;
        int[] indexArray = getIndexArray(nums, target);

        if(indexArray!=null){
            for (int index : indexArray) {
                System.out.println("index = " + index);
            }
        } else {
            System.out.println("indexArray = " + indexArray);
        }
    }

    private int[] getIndexArray(int[] nums, int target){
        int[] indexArray = null;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if(i!=j){
                    if ((nums[i]+nums[j])==target){
                        indexArray = new int[]{i,j};
                        return indexArray;
                    }
                }
            }
        }
        //不存在
        return indexArray;
    }

    /**
     * 单调数组判定算法
     */
    @Test
    public void method2(){
        int[] ia = new int[10000];
        for (int i = 0; i < ia.length; i++) {
            ia[i] = (int) (Math.random() * 10);
        }
        int[]A = new int[]{2,4,4,1};
        long beginTime = System.currentTimeMillis();
        System.out.println("isMonotoneArray(A) = " + isMonotoneArray(A));
        long endTime = System.currentTimeMillis();
        System.out.println("(endTime-beginTime) = " + (endTime - beginTime));

    }

    //缺少判断 不可用
    private boolean isMonotoneArray(int[] nums) {
        if (nums.length <= 1) return true;
        //单调递增判断
        if (nums[0] <= nums[1]) {
            int[] remainArray = Arrays.copyOfRange(nums, 1, nums.length);
            return isMonotoneArray(remainArray);
        }
        else if (nums[0] >= nums[1]) { //单调递减判断
            int[] remainArray = Arrays.copyOfRange(nums, 1, nums.length);
            return isMonotoneArray(remainArray);
        }
        return false;
    }

    private boolean func(int[] nums, int numsLength){
        if(numsLength<=1) return true;
        boolean flag = nums[numsLength-1]<=nums[numsLength-2] || nums[numsLength-1]>=nums[numsLength-2];
        if(numsLength==2){
            return flag;
        }
        return func(nums,numsLength-1) && flag;
    }


    @Test
    public void method3(){
        //int[]A = new int[]{1,4,4,1};
        //int[]A = new int[]{6,5,4,5};
        int[]A = new int[]{6,5,4,1};
        System.out.println("result= " + (A.length > 1 && isMonotoneArray(A, new ArrayList<>())));
    }

    //单调数组判定算法
    private boolean isMonotoneArray(int[] nums, List<String> strList) {
        if (nums.length <= 1) return true;

        boolean strBool = nums[0] == nums[1] ? strList.add("=") : nums[0] < nums[1] ? strList.add("+") : strList.add("-");
        //int[] remainArray = Arrays.copyOfRange(nums, 1, nums.length);
        int[] remainArray = new int[nums.length - 1];
        //数组截取
        for (int i = 1; i < nums.length; i++) {
            remainArray[i - 1] = nums[i];
        }

        Map<String, Integer> map = new HashMap<>();
        for (String key : strList) {
            Integer count = map.get(key);
            map.put(key, count == null ? 1 : count + 1);
        }

        //+和-在数组中不能同时出现，否则不符合条件
        boolean flag = !(map.containsKey("+") && map.containsKey("-"));
        //boolean flag = (map.get("+") != null && map.get("-") == null) || (map.get("+") == null && map.get("-") != null);
        return isMonotoneArray(remainArray, strList) && flag;
    }
}
