package org.tutor.botweb.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.Configuration;

/**
 *  将 Bean 注入规则改成注入全类名
 */
@Configuration
public class UniqueNameGenerator implements BeanNameGenerator {
//    private static final Log log = LogFactory.getLog(UniqueNameGenerator.class);

    @Override
    public String generateBeanName(BeanDefinition beanDefinition, BeanDefinitionRegistry beanDefinitionRegistry) {
//        log.warn(beanDefinition.getBeanClassName());
        return beanDefinition.getBeanClassName();
    }
}
