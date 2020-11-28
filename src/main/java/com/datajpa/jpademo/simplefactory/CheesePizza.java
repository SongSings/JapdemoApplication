package com.datajpa.jpademo.simplefactory;

public class CheesePizza implements Pizza {
    @Override
    public void bake() {

    }

    @Override
    public void box() {

    }

    @Override
    public void cut() {
        System.out.println("cut");
    }

    @Override
    public void prepare() {
        System.out.println("cheese");
    }
}
