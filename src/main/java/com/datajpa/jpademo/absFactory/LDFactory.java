package com.datajpa.jpademo.absFactory;

import com.datajpa.jpademo.factoryfunction.LDPepperPizza;
import com.datajpa.jpademo.simplefactory.Pizza;

public class LDFactory implements AbsFactory {
    @Override
    public Pizza CreatePizza(String ordertype) {
        Pizza pizza = null;
//        if ("cheese".equals(ordertype)) {
//            pizza = new LDCheesePizza();
//        } else
        if ("pepper".equals(ordertype)) {
            pizza = new LDPepperPizza();
        }
        return pizza;
    }
}
