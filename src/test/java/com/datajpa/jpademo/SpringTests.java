package com.datajpa.jpademo;

import com.datajpa.jpademo.Util.SpringContextUtil;
import com.datajpa.jpademo.Util.ThymeleafConfig;
import com.datajpa.jpademo.model.vo.User;
import com.datajpa.jpademo.simplefactory.CheesePizza;
import com.datajpa.jpademo.simplefactory.Pizza;
import org.junit.Test;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Method;
import java.util.Optional;
import java.util.OptionalInt;

public class SpringTests {
    @Test
    public void test1() throws NoSuchMethodException {
        //spring工厂
        ApplicationContext context = new ClassPathXmlApplicationContext("/ApplicationContext.xml");
        System.out.println("context = " + context);
        User bean = (User) context.getBean("user");
        System.out.println("bean = " + bean);
        boolean user = context.isSingleton("user");
        System.out.println("user = " + user);

        //参数说明
        // 1. 类加载器ClassLoader:借用类加载器，创建代理类的Class对象
        // 2. 原始类的getClass().getInterfaces()方法 获取方法
        // 3. 额外功能
        final Pizza pizza = new CheesePizza(); //Pizza接口类  CheesePizza 接口实现类
        //1: 实现类
        //2：执行的方法
        //3: 方法参数
        Object o = Proxy.newProxyInstance(SpringTests.class.getClassLoader(), pizza.getClass().getInterfaces(), (o1, method, objects) -> {
            //方法执行前可操作的内容
            System.out.println("SpringTests.proxy [[begin]]");
            //原始方法运行
            Object invoke = method.invoke(pizza, objects);
            //方法执行结果
            return invoke;
        });
        Pizza pizza2 = (Pizza) o;
        pizza2.prepare();
        pizza.cut();

        String om = "kk";
        byte[] bytes = om.getBytes();

    }
}
