package com.datajpa.jpademo;

import com.datajpa.jpademo.absFactory.LDFactory;
import com.datajpa.jpademo.factoryfunction.LDOrderPizza;
import com.datajpa.jpademo.simplefactory.Pizza;
import com.datajpa.jpademo.simplefactory.SimplePizzaFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * junit 单元测试简单工厂
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SimpleFactoryTests {

    /**
     * 简单工厂模式
     */
    @Test
    public void test(){
        SimplePizzaFactory simplePizzaFactory = new SimplePizzaFactory();
        Pizza pepper = simplePizzaFactory.CreatePizza("pepper");
        pepper.prepare();
        //Assert.assertArrayEquals();
    }

    /**
     * 工厂方法
     */
    @Test
    public void functionFactory(){
        LDOrderPizza ldOrderPizza = new LDOrderPizza();
        Pizza pizza = ldOrderPizza.createPizza();
        pizza.prepare();
    }
    /**
     * 抽象工厂
     */
    public void absFactory(){
        LDFactory ldFactory = new LDFactory();
        Pizza pepper = ldFactory.CreatePizza("pepper");
        pepper.prepare();
    }
}
