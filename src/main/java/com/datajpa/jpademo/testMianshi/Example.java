package com.datajpa.jpademo.testMianshi;

public class Example {
    public static void main(String args[]){
        String str=new String("good");
        char[] ch = {'a','b','c'};
        change(str, ch);
        System.out.print(str+" and ");
        System.out.print(ch);
    }
    public static void change(String str,char ch[]){
        str="test ok";
        ch[0]='g';
    }
}