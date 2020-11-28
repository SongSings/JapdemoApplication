package com.datajpa.jpademo;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.junit.Test;

import java.util.Optional;

public class OptionalTests {

    @Test
    public void test1() throws Exception {
        Optional<Object> empty = Optional.empty();
        empty.ifPresent(x->System.out.println(x));
        //empty.orElseThrow(()-> new Exception("HHA"));
        Object o = empty.orElse("else");
        System.out.println(o);
        Object o1 = empty.orElseGet(() -> "elseGet");
        System.out.println("o1 = " + o1);

        String val = "optional";
        Optional<String> val1 = Optional.ofNullable(val);
        val1.ifPresent(x->System.out.println(x));
        if (val1.isPresent()) {
            System.out.println("OptionalTests.test1");
        }
        System.out.println("val1 = " + val1.get());


    }
}
