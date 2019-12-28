package com.datajpa.jpademo.Util;

import com.datajpa.jpademo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.text.SimpleDateFormat;

@Component//Scheduler2Task
public class Dingshi {


    @Autowired
    EmailService emailService;

    @Autowired
    TemplateEngine templateEngine;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    int count;

    /*@Scheduled(fixedRate = 6000) ：上一次开始执行时间点之后6秒再执行
    @Scheduled(fixedDelay = 6000) ：上一次执行完毕时间点之后6秒再执行
    @Scheduled(initialDelay=1000, fixedRate=6000) ：第一次延迟1秒后执行，之后按fixedRate的规则每6秒执行一次*/
    @Scheduled(fixedRate = 10000)
    public void reportCurrentTime() {
        //System.out.println("现在时间：" + dateFormat.format(new Date()));
       /* Book book = new Book();
        book.setName("零四" + (count++));
        bookRepository.save(book);*/

        //String filePath="D:\\内存卡\\图片\\1.jpg";
        //emailService.sendAttachmentsEmail("403741953@qq.com", "主题：带附件的邮件", "有附件，请查收！", filePath);

        //创建邮件正文
        Context context = new Context();
        context.setVariable("id", "006");
        String emailContent = templateEngine.process("emailTemplate", context);//第一个参数是邮件模板html

        //emailService.sendHtmlEmail("403741953@qq.com","QQ系统管理员：你的QQ马上到期了，请及时续费！",emailContent);
    }

}
