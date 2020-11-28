package com.datajpa.jpademo.factoryfunction;

import com.datajpa.jpademo.simplefactory.Pizza;

public class LDPepperPizza implements OrderPizza{

    String name;

    @Override
    public Pizza createPizza() {
        return null;
    }

    @Override
    public void bake() {

    }

    @Override
    public void box() {

    }

    @Override
    public void cut() {

    }

    @Override
    public void prepare() {
        System.out.println(this.getClass().getName());
    }
}
