package com.datajpa.jpademo.Util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.zxing.WriterException;
import javassist.expr.NewArray;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Maps;
import org.hibernate.type.FloatType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;

@Log4j2
public class TestMain {
    public static void main(String[] args) throws WriterException, IOException {

        String valll = "InitialData(data=PE1TRz48TUVUQT48VEl4yMDIwMDQxNzE3Mjgy0RBVD48L01TR\n" +
                "z4=, dataType=none, dataLength=1685, seqNo=17839, hashCode=8eb43c85b559440f311d311025d5822d)\n";

        String[] strings = valll.split("\\(");
        String[] strings1 = strings[1].split("\\)");
        strings1 = strings1[0].split(",");
        for (String item : strings1){
            String data = item.contains("data=") ? item.split("data=")[1]:null;
            System.out.println(data);
        }


        System.out.println("short bytes size:" + Short.BYTES + ",bits:" + Short.SIZE);
        System.out.println("long bytes size:" + Long.BYTES + ",bits:" + Long.SIZE);
        System.out.println("y/\ny\t7");

        int[] ia = new int[10000];
        int[] arr1 = new int[10000];

        for (int i = 0; i < ia.length; i++) {
            ia[i] = (int) (Math.random() * 10);
            arr1[i] = ia[i];
        }
        //流排序
        Arrays.stream(ia).sorted().forEach(System.out::print);
        Arrays.stream(ia).forEach(System.out::print);
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
//        boolean successFlag = false;
//        JSONObject jsonResultFalse = new JSONObject();
//        jsonResultFalse.put("success", successFlag);
//        Object a = new Object();
//
//        JSON resultFalse = (JSON) JSONObject.toJSON(jsonResultFalse);
//        System.out.println(resultFalse);
//
//        // 格式化模版
//        DateTimeFormatter DATETIME19 = DateTimeFormatter.ofPattern("yyyyMMdd");
//
//        // 时间转字符串
//        String dtStr = DATETIME19.format(LocalDateTime.now());
//        System.out.println(LocalDateTime.now().format(DATETIME19));

//        BufferedImage image = com.google.zxing.client.j2se.MatrixToImageWriter.toBufferedImage(new com.google.zxing.qrcode.QRCodeWriter().encode("1|20200410|0001|NB03|null|1|A0101|01|null|null|20170412|null|null|500",com.google.zxing.BarcodeFormat.QR_CODE,600,600));
//
//        String FORMAT = "JPG";
//        System.out.println(image);
//        String destPath = "D://test";
//        mkdirs(destPath);
//        String fileName = new Random().nextInt(9999) + "." + FORMAT.toLowerCase();
//        ImageIO.write(image, FORMAT, new File(destPath + "/" + fileName));

        System.out.println(fmtMicrometer("1234456789"));
        LinkedList<String> lsit = new LinkedList<>();
        lsit.isEmpty();
        Lists.newArrayList();
        Arrays.asList();
        Map<String, String> stringMap = new HashMap<String, String>();
        {
            {
                stringMap.put("key1", "val");
            }
        }
        ;
        log.error(stringMap.isEmpty());
        log.info(Objects.equals("a", "a"));
        for (Map.Entry entry : stringMap.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

        BigDecimal b = BigDecimal.valueOf(1.000000);
        BigDecimal dd;
        dd = BigDecimal.valueOf(Double.valueOf(b.toString()));
        log.error(dd);
        log.info(Double.valueOf(b.toString()));
        System.out.println(b);

        DecimalFormat df = new DecimalFormat("##0");
        String text = fmtMicrometer("1.22002000");
        double number = Double.parseDouble(text);
        System.out.println(text);
    }

    /**
     * @param text
     * @return String    返回类型
     * @Title: fmtMicrometer
     * @Description: 格式化数字为千分位
     */
    public static String fmtMicrometer(String text) {
        if (null == text) return "0.000";
        DecimalFormat df = null;
        if (text.indexOf(".") > 0) {
            if (text.length() - text.indexOf(".") - 1 == 0) {
                df = new DecimalFormat("###,##0");
            } else if (text.length() - text.indexOf(".") - 1 == 1) {
                df = new DecimalFormat("###,##0.0");
            } else {
                df = new DecimalFormat("###,##0.000");
            }
        } else {
            df = new DecimalFormat("###,##0");
        }
        //df = new DecimalFormat("###,##0.000");
        double number = 0.0;
        try {
            number = Double.parseDouble(text);
        } catch (Exception e) {
            number = 0.0;
        }
        return df.format(number);
    }

    /**
     * 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．
     * (mkdir如果父目录不存在则会抛出异常)
     *
     * @param destPath 存放目录
     */
    public static void mkdirs(String destPath) {
        File file = new File(destPath);
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
    }
}
