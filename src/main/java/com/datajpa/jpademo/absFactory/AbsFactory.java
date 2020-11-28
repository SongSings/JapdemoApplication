package com.datajpa.jpademo.absFactory;

import com.datajpa.jpademo.simplefactory.Pizza;

/**
 * 抽象工厂类
 */
public interface AbsFactory {
    Pizza CreatePizza(String ordertype) ;
    public static String val1="33";
}
