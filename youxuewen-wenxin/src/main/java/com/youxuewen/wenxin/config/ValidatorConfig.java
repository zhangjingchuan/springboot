package com.youxuewen.wenxin.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * 验证配置类
 */
@Configuration
public class ValidatorConfig {

    /**
     * 配置参数验证，有一个条件不符合时不再继续验证其他内容，直接返回
     * @return
     */
    @Bean
    public Validator validator(){
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(true)
                .buildValidatorFactory();

        Validator validator = validatorFactory.getValidator();

        return validator;
    }
}
