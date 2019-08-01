package com.datajpa.jpademo.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import javax.annotation.PostConstruct;

@Configuration
public class ThymeleafConfig {

    @Autowired
    private SpringTemplateEngine templateEngine;

    @PostConstruct
    public void init() {
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();

        resolver.setPrefix("/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("LEGACYHTML5");
        resolver.setOrder(templateEngine.getTemplateResolvers().size());

        templateEngine.addTemplateResolver(resolver);
    }

}