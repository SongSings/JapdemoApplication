package com.datajpa.jpademo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * 排序测试类
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SortTests {

    @Test
    public void testSort(){
        int size = 10000;
        int[] ia = new int[size];
        int[] arr1 = new int[size];

        for (int i = 0; i < ia.length; i++) {
            ia[i] = (int) (Math.random() * 10);
            arr1[i] = ia[i];
        }

        //冒泡排序
        System.out.println("----------------------");
        System.out.println("冒泡开始-----------");
        long beginTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < ia.length - 1; i++) {
            for (int j = 0; j < ia.length - 1 - i; j++) {
                int min;
                if (ia[j] > ia[j + 1]) {
                    min = ia[j + 1];
                    ia[j + 1] = ia[j];
                    ia[j] = min;
                }
            }
        }
        long endTimeMillis = System.currentTimeMillis();
        log.warn("冒泡排序占用时间:" + (endTimeMillis - beginTimeMillis));
        System.out.println("----------------------");
        Arrays.stream(ia).forEach(System.out::print);

        //希尔排序（性能好）
        System.out.println("----------------------");
        System.out.println("希尔排序开始-----------");
        beginTimeMillis = System.currentTimeMillis();
        int length = arr1.length;
        //分组间隔设置
        for (int gap = length / 2; gap > 0; gap = gap / 2) {
            for (int i = gap; i < length; i++) {
                int temp = arr1[i];
                int j;
                for (j = i - gap; j > 0 && temp < arr1[j]; j = j - gap) {
                    arr1[j + gap] = arr1[j];
                }
                arr1[j + gap] = temp;
            }
        }
        endTimeMillis = System.currentTimeMillis();
        log.warn("希尔排序占用时间:" + (endTimeMillis - beginTimeMillis));
        System.out.println("--------------");
        Arrays.stream(arr1).forEach(System.out::print);
        System.out.println("-------------");
        //流排序
        System.out.println("流排序开始-----------");
        beginTimeMillis = System.currentTimeMillis();
        Arrays.stream(ia).sorted().forEach(System.out::print);
        endTimeMillis = System.currentTimeMillis();
        System.out.println("-------------");
        log.warn("流排序占用时间:" + (endTimeMillis - beginTimeMillis));

    }
}
