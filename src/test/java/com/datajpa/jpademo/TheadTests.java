package com.datajpa.jpademo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.lang.model.SourceVersion;
import java.util.*;

@Slf4j
public class TheadTests{

    private long sum(){
        long r=0;
        for (long i = 0; i < 10000; i++) {
            r+=i;
        }
        return r;
    }

    private void 单线程(){
        long l = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            System.out.println(sum());
        }
        long e = System.nanoTime();
        double s = (e-l)/1000_000_000.0;
        System.out.println("\n单线程运行时间 = " + s);
    }

    private void 多线程() throws InterruptedException {
        long l = System.nanoTime();
        Thread[] threads = new Thread[10];
        Runnable runnable = () -> System.out.println(sum());
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(runnable);
            threads[i].start();
        }
        System.out.println(sum());

        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }

        long e = System.nanoTime();
        double s = (e-l)/1000_000_000.0;
        System.out.println("\t多线程运行时间 = " + s);
    }

    @Test
    public void method() throws InterruptedException {
        单线程();
        System.out.println("---------------------------------- ----------------------- ");
        多线程();
    }


    @Test
    public void test(){
        long begin = System.currentTimeMillis();
        long nanoTime = System.nanoTime();
        Thread[] threads = new Thread[10];

        Runnable runnable = () -> test2();
        //runnable.run();

        for (int i = 0; i < threads.length-1; i++) {
            //threads[i] = new Thread(runnable);
            new Thread(runnable).start();
            //new Thread(runnable).run();
        }
        long l = System.currentTimeMillis() - begin;
        System.out.println("\n消耗时间 = " + l);
    }

    private void test2(){
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            System.out.print(i +"\n");
            //log.info(i+"\t");
        }
        System.out.println("TheadTests.test2");
        long l = System.currentTimeMillis() - begin;
        //System.out.println("\n消耗时间 = " + l);
    }

    @Test
    public void testMethod(){
        int[] nums = new int[]{1,2,3,4};
        System.out.println("isRepeatArray(nums) = " + isRepeatArray(nums));
    }

    private boolean isRepeatArray(int[] nums) {
        Map<Integer, Integer> map = new HashMap();
        Integer count = 0;
        for (int i = 0; i < nums.length; i++) {
            count = map.get(nums[i]);
            count = count == null ? 1 : nums[i] + count;
            if (count > 1) return true;
            map.put(nums[i], count);
        }
        return false;
    }

    public class MinStack {
        Stack<Integer> stack;
        Stack<Integer> minStack;
        public MinStack(){
            stack = new Stack<>();
            minStack = new Stack<>();
        }
        public void push(int value){
            stack.push(value);
            if(minStack.isEmpty() || value<minStack.peek()){
                minStack.push(value);
            } else {
                minStack.push(minStack.peek());
            }
        }
        public void pop(){
            stack.pop();
            minStack.pop();
        }
        public int top(){
            return stack.peek();
        }
        public int getMin(){
            return minStack.peek();
        }
    }

    @Test
    public void test3(){
        MinStack stock = new MinStack();
        stock.push(-2);
        stock.push(0);
        stock.push(-3);
        System.out.println("stock.getMin() = " + stock.getMin());
        stock.pop();
        System.out.println("stock.top() = " + stock.top());
        System.out.println("stock.getMin() = " + stock.getMin());
    }


    private int matchCount(String text){
        String target = "aabab";
        Map<String, Integer> targetMap = new HashMap<>();
        Map<String, Integer> textMap = new HashMap<>();
        Integer count;
        for (int i = 0; i < target.length(); i++) {
            count = targetMap.get(String.valueOf(target.charAt(i)));
            targetMap.put(String.valueOf(target.charAt(i)), count == null ? 1 : count + 1);
        }
        for (int i = 0; i < text.length(); i++) {
            count = textMap.get(String.valueOf(text.charAt(i)));
            textMap.put(String.valueOf(text.charAt(i)), count == null ? 1 : count + 1);
        }
        count = 0;
        boolean flag = true;
        while (flag){
            for (String key : targetMap.keySet()) {
                if (textMap.get(key) == null || targetMap.get(key) > textMap.get(key)) {
                    flag = false;
                    return count;
                }
                textMap.put(key,textMap.get(key)-targetMap.get(key));
            }
            count++;
        }
        return 0;
    }

    @Test
    public void test4(){
        System.out.println("matchCount(\"abc\") = " + matchCount("aa"));
        System.out.println(5/2);
    }

    /**
     * @see 2020.8.21
     * QA:为什么不能直接在控制层写业务代码？缺点都有哪些
     *
     * 控制层主要是用来接收接口的请求，处理数据交互的部分，是接口的一个门面
     * 控制层是用来在接收请求和返回参数的，再次同时添加大量业务代码 会导致项目维护性差,全部都掺杂在一起 项目的可读性也会降低，代码的耦合性也会相应的提高
     * 缺点
     * 1.耦合性太高,代码太集中
     * 2.方法的复用性比较低,重构的风险比较大
     * 3.方法行数过多，比较臃肿,会导致可读性和维护性太差
     * 4.扩展性也比较差 版本迭代改动大
     */
    @Test
    public void testMethod1(){

        System.out.println("getMinTwoItemArray(new int[]{4,2,1,3}) = " + getMinTwoItemArray(new int[]{4, 2, 1, 3}));
        System.out.println("getMinTwoItemArray(new int[]{1,3,6,10,15}) = " + getMinTwoItemArray(new int[]{1, 3, 6, 10, 15}));
    }

    private List<List<Integer>> getMinTwoItemArray(int[] array){
        //1.升序排序
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length-i-1; j++) {
                int min;
                if (array[j] > array[j + 1]) {
                    min = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = min;
                }
            }
        }
        int value;
        int minValue = 0;
        List<Integer> resultValueCollect = new ArrayList<>();
        //2.差值结果集记录和最小差值记录
        for (int i = 0; i < array.length-1; i++) {
            value = array[i+1]-array[i];
            resultValueCollect.add(value);
            //第一次循环或差值小于记录的最小值
            if(i==0 || value < minValue){
                minValue = value;
            }
        }
        List<Integer> minValueIndex;
        List<List<Integer>> lists = new ArrayList<>();
        //3.取出最小差值的下标
        for (int i = 0; i < resultValueCollect.size(); i++) {
            minValueIndex = new ArrayList<>();
            if (resultValueCollect.get(i).intValue() == minValue){
                minValueIndex.add(array[i]);
                minValueIndex.add(array[i+1]);
                lists.add(minValueIndex);
            }
        }
        return lists;
    }

    private boolean isRepeatArray2(int[]nums){
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if(i!=j && nums[i] == nums[j]){
                    return true;
                }
            }
        }
        return false;
    }

    @Test
    public void testMethod2(){
        System.out.println("isRepeatArray2(new int[]{1, 2, 3, 1}) = " + isRepeatArray2(new int[]{1, 2, 3, 1}));
        System.out.println("isRepeatArray2(new int[]{1, 2, 3, 4}) = " + isRepeatArray2(new int[]{1, 2, 3, 4}));
        HashSet val = new HashSet();
        val.add("a");
        val.add("b");
        val.add("1");
        val.add("b");
        System.out.println("hashSet = " + val);
        Set set = new TreeSet();
        set.add("c");
        set.add("c");
        System.out.println("treeset = " + set);
        //treeMap实现了升序排序，
        TreeMap<Object, Object> treeMap = new TreeMap<>();
        treeMap.put("a","1");
        treeMap.put("c","3");
        treeMap.put("b",2);
        System.out.println("treeMap = " + treeMap);

        //
        Vector Vector = new Vector();
        Vector.add(2);
        Vector.add(0,3);
        Vector.add(1,1);
        System.out.println("Vector = " + Vector);

    }
}
