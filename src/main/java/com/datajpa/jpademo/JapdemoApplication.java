package com.datajpa.jpademo;

import com.datajpa.jpademo.web.BookController;
import org.jboss.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling//启动类启用定时
public class JapdemoApplication extends SpringBootServletInitializer {

    static Logger logger = Logger.getLogger(JapdemoApplication.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(JapdemoApplication.class);
    }

    public static void main(String[] args) {
        logger.info("SpringBoot开始加载");
        SpringApplication.run(JapdemoApplication.class, args);
        logger.info("SpringBoot加载完毕");
    }

    //Tomcat large file upload connection reset
   /* @Bean
    public TomcatEmbeddedServletContainerFactory tomcatEmbedded() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
        tomcat.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {
            if ((connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?>)) {
                //-1 means unlimited
                ((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);
            }
        });
        return tomcat;
    }*/
}
