package com.datajpa.jpademo.simplefactory;

/**
 * 工厂模式示例
 */
public class SimplePizzaFactory {
    public Pizza CreatePizza(String ordertype) {
        Pizza pizza = null;
        if (ordertype.equals("cheese")) {
            pizza = new CheesePizza();
        } else if (ordertype.equals("pepper")) {
            pizza = new PepperPizza();
        }
        return pizza;
    }
}
