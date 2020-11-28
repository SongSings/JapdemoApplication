package com.datajpa.jpademo;

import com.alibaba.fastjson.JSON;
import com.oneslogi.base.dbflute.exentity.TMoveInstB;
import com.oneslogi.base.dbflute.exentity.TMoveInstH;
import com.oneslogi.base.dbflute.exentity.TMoveRecordB;
import com.oneslogi.wms.core.logic.stockupdate.StockUpdateLogic;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class JapdemoApplicationTests {

    @Test
    public void contextLoads() {
        log.info("1223");
        System.out.println("666");
        Object[] a = new Object[0];
        Object[] b = new Object[0];
        Assert.assertArrayEquals("666",a,b);

    }

}
