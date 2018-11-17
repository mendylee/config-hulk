package com.xiangrikui.hulk.web.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class DruidDataSourceConfig
{
	@Value("${druid.url}")
    private String url;

    @Value("${druid.username}")
    private String jdbcUsername;

    @Value("${druid.password}")
    private String jdbcPassword;

    @Value("${druid.driverClass}")
    private String driverClass;

    /**
     * 初始化时建立物理连接的个数
     */
    @Value("${druid.initialSize}")
    private Integer initialSize;

    /**
     * 最小连接池数量
     */
    @Value("${druid.minIdle}")
    private Integer minIdle;

    /**
     * 最大连接池数量
     */
    @Value("${druid.maxActive}")
    private Integer maxActive;

    /**
     * 获取连接时最大等待时间，单位毫秒
     */
    @Value("${druid.maxWait}")
    private Long maxWait;

    /**
     * Destroy线程检测连接的间隔时间
     */
    @Value("${druid.timeBetweenEvictionRunsMillis}")
    private Long timeBetweenEvictionRunsMillis;

    /**
     * 连接保持空闲而不被驱逐的最长时间
     */
    @Value("${druid.minEvictableIdleTimeMillis}")
    private Long minEvictableIdleTimeMillis;

    /**
     * 用来检测连接是否有效的sql
     */
    @Value("${druid.validationQuery}")
    private String validationQuery;

    /**
     * 执行validationQuery检测连接是否有效
     */
    @Value("${druid.testWhileIdle}")
    private Boolean testWhileIdle;

    /**
     * 申请连接时执行validationQuery检测连接是否有效
     */
    @Value("${druid.testOnBorrow}")
    private Boolean testOnBorrow;

    /**
     * 归还连接时执行validationQuery检测连接是否有效
     */
    @Value("${druid.testOnReturn}")
    private Boolean testOnReturn;

    public DataSource targetDataSource(String url, String jdbcUsername, String jdbcPassword) {

        DruidDataSource druidDataSource = new DruidDataSource();

        druidDataSource.setUrl(url);
        druidDataSource.setDriverClassName(driverClass);
        druidDataSource.setUsername(jdbcUsername);
        druidDataSource.setPassword(jdbcPassword);
        druidDataSource.setInitialSize(initialSize);
        druidDataSource.setMinIdle(minIdle);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setMaxWait(maxWait);
        druidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        druidDataSource.setValidationQuery(validationQuery);
        druidDataSource.setTestWhileIdle(testWhileIdle);
        druidDataSource.setTestOnBorrow(testOnBorrow);
        druidDataSource.setTestOnReturn(testOnReturn);


        return new LazyConnectionDataSourceProxy(druidDataSource);
    }

    public DataSource targetDataSource() {

        return targetDataSource(url, jdbcUsername, jdbcPassword);

    }
}
