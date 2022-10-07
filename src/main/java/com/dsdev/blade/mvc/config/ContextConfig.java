package com.dsdev.blade.mvc.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClientBuilder;
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterRequest;
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterResult;
import com.hellokaton.anima.Anima;
import com.hellokaton.blade.Blade;
import com.hellokaton.blade.ioc.annotation.Bean;
import com.hellokaton.blade.loader.BladeLoader;
import com.hellokaton.blade.template.JetbrickTemplateEngine;

@Bean
public class ContextConfig implements BladeLoader {

    @Override
    public void load(Blade blade) {
        // Template to use html
        blade.templateEngine(new JetbrickTemplateEngine());

        AWSSimpleSystemsManagement ssmClient = AWSSimpleSystemsManagementClientBuilder.defaultClient();
        GetParameterRequest getParameterRequest = new GetParameterRequest();
        String myCustomParam = blade.environment().getOrNull("my.param");
        getParameterRequest.withName((myCustomParam)).withWithDecryption(true);
        GetParameterResult getParameterResult = ssmClient.getParameter(getParameterRequest);
        System.out.println(getParameterResult.getParameter().getValue());

        // Database configuration
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(blade.environment().getOrNull("jdbc.url"));
        dataSource.setUsername(blade.environment().getOrNull("jdbc.username"));
        dataSource.setPassword(blade.environment().getOrNull("jdbc.password"));
        Anima.open(dataSource);
    }

}