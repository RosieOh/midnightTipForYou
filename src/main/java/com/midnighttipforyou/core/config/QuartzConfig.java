package com.midnighttipforyou.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class QuartzConfig {
    
    /**
     * Quartz Scheduler 설정
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource, PlatformTransactionManager transactionManager) {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setDataSource(dataSource);
        schedulerFactory.setTransactionManager(transactionManager);
        
        Properties quartzProperties = new Properties();
        quartzProperties.setProperty("org.quartz.scheduler.instanceName", "MidnightTipScheduler");
        quartzProperties.setProperty("org.quartz.scheduler.instanceId", "AUTO");
        quartzProperties.setProperty("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
        quartzProperties.setProperty("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.StdJDBCDelegate");
        quartzProperties.setProperty("org.quartz.jobStore.tablePrefix", "QRTZ_");
        quartzProperties.setProperty("org.quartz.jobStore.isClustered", "false");
        quartzProperties.setProperty("org.quartz.jobStore.useProperties", "false");
        quartzProperties.setProperty("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        quartzProperties.setProperty("org.quartz.threadPool.threadCount", "5");
        quartzProperties.setProperty("org.quartz.threadPool.threadPriority", "5");
        quartzProperties.setProperty("org.quartz.plugin.jobInitializer.class", "org.quartz.plugins.xml.XMLSchedulingDataProcessorPlugin");
        quartzProperties.setProperty("org.quartz.plugin.jobInitializer.fileNames", "quartz-jobs.xml");
        quartzProperties.setProperty("org.quartz.plugin.jobInitializer.failOnFileNotFound", "false");
        quartzProperties.setProperty("org.quartz.plugin.jobInitializer.scanInterval", "0");
        
        schedulerFactory.setQuartzProperties(quartzProperties);
        schedulerFactory.setJobFactory(new SpringBeanJobFactory());
        schedulerFactory.setAutoStartup(true);
        schedulerFactory.setStartupDelay(5);
        
        return schedulerFactory;
    }
    
    /**
     * Quartz Work Factory
     */
    @Bean
    public SpringBeanJobFactory springBeanJobFactory() {
        return new SpringBeanJobFactory();
    }
}
