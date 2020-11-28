package com.datajpa.jpademo.factoryfunction;

import com.datajpa.jpademo.simplefactory.Pizza;

public interface OrderPizza extends Pizza{
    Pizza createPizza();
}
