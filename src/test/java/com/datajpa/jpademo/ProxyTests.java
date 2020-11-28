package com.datajpa.jpademo;

import com.datajpa.jpademo.simplefactory.CheesePizza;
import com.datajpa.jpademo.simplefactory.Pizza;
import org.junit.Test;
import org.springframework.cglib.proxy.*;

import java.lang.reflect.Method;

public class ProxyTests {

    /**
     * JDK动态代理
     */
    @Test
    public void test1() {
        //参数说明
        // 1. 类加载器ClassLoader:借用类加载器，创建代理类的Class对象
        // 2. 原始类的getClass().getInterfaces()方法 获取方法
        // 3. 额外功能
        final Pizza pizza = new CheesePizza(); //Pizza接口类  CheesePizza 接口实现类
        Object o = Proxy.newProxyInstance(SpringTests.class.getClassLoader(), pizza.getClass().getInterfaces(), new InvocationHandler(){
            //1: 实现类
            //2：执行的方法
            //3: 方法参数
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                //方法执行前可操作的内容
                System.out.println("SpringTests.proxy [[begin]]");
                //原始方法运行
                Object invoke = method.invoke(pizza, objects);
                //方法执行结果
                return invoke;
            }
        });
        Pizza pizza2 = (Pizza) o;
        pizza2.prepare();
        pizza.cut();
    }

    /**
     * CGlib动态代理
     */
    @Test
    public void test2() {
        CheesePizza pizza = new CheesePizza();
        Enhancer enhancer = new Enhancer();
        //1.类加载器
        enhancer.setClassLoader(this.getClass().getClassLoader());
        //2.父类
        enhancer.setSuperclass(CheesePizza.class);//or enhancer.setSuperclass(pizza.getClass());
        //3.额外功能
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
            System.out.println("ProxyTests.intercept");
            Object invoke = method.invoke(pizza, objects);
            return invoke;
        });
        //代理创建
        CheesePizza cheesePizza = (CheesePizza) enhancer.create();
        cheesePizza.cut();
    }
}
