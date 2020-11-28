package com.datajpa.jpademo.simplefactory;

public class PepperPizza implements Pizza {
    public void PepperPizza(){
        System.out.println("pepper");
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
        System.out.println("pepper");
    }
}
