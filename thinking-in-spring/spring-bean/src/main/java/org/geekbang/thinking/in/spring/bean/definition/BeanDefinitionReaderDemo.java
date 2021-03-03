package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

import java.beans.PropertyDescriptor;

public class BeanDefinitionReaderDemo {
    public static void main(String[] args) {
        ClassPathResource resource = new ClassPathResource("META-INF/bean-definitions-context.xml");
        System.out.println("resource.getPath() = " + resource.getPath());
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions(resource);
        String[] beanDefinitionNames = xmlBeanDefinitionReader.getBeanFactory().getBeanDefinitionNames();
        System.out.println("beanFactory = " + beanFactory.equals(xmlBeanDefinitionReader.getBeanFactory()));
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("beanDefinitionName = " + beanDefinitionName);
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);
            System.out.println("beanDefinition = " + beanDefinition);
        }
        User user = beanFactory.getBean("user", User.class);
        System.out.println("user = " + user);


        /*测试BeanWrapper的使用*/
        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(user);
        PropertyDescriptor[] descriptors = beanWrapper.getPropertyDescriptors();
        for (PropertyDescriptor pd : descriptors) {
            String name = pd.getName();
            if (pd.getReadMethod() != null) {
                Object propertyValue = beanWrapper.getPropertyValue(name);
                if (propertyValue != null) {
                    System.out.println("beanWrapper.getPropertyType(name) = " + beanWrapper.getPropertyType(name));
                    System.out.println("name:" + name + "==>value:" + propertyValue.toString());
                }
            }
        }
    }


}
