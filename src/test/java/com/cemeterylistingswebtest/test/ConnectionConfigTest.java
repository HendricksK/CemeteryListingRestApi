package com.cemeterylistingswebtest.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import com.cemeterylistingsweb.Application;

import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 *
 * @author kurvin
 */
@Configuration 
@ComponentScan(basePackageClasses = Application.class, excludeFilters = @ComponentScan.Filter({Controller.class, Configuration.class}))
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.cemeterylistingsweb.repository")
public class ConnectionConfigTest {
 
    @Bean
    public DataSource dataSource(){
        BasicDataSource dataSrc = new org.apache.commons.dbcp.BasicDataSource();
        dataSrc.setDriverClassName("org.apache.derby.jdbc.ClientDriver");
        dataSrc.setUrl("jdbc:derby://localhost:1527/sample");
        dataSrc.setUsername("app");
        dataSrc.setPassword("app");
        return dataSrc;
    }
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory
        (DataSource dataSource, JpaVendorAdapter jpaVendorAdapter){
                LocalContainerEntityManagerFactoryBean lefb = 
                new LocalContainerEntityManagerFactoryBean();    
                lefb.setDataSource(dataSource);
                lefb.setJpaVendorAdapter(jpaVendorAdapter);
                lefb.setPackagesToScan("com.cemeterylistingsweb.domain");
                return lefb;
    }
        
    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter =
                new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(false);
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        hibernateJpaVendorAdapter.setDatabase(Database.DERBY);
        return hibernateJpaVendorAdapter;
    }
    
    @Bean
    public PlatformTransactionManager transactionManager(){
        return new JpaTransactionManager();
    }
}
