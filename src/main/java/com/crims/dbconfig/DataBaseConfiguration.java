package com.crims.dbconfig;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/*
* 数据源配置抽象类
*/
@Configuration
public  class DataBaseConfiguration {

   @Autowired
   private Environment env;

   @Value("${datasources}")
   private String datasources;

	/*
	 * 默认数据源。
	 */
	protected  DataSource getDefaultDataSource(){
       DruidDataSource defaultDS = new DruidDataSource();
       defaultDS.setUrl(env.getProperty("spring.datasource.url"));
       defaultDS.setUsername(env.getProperty("spring.datasource.username"));
       defaultDS.setPassword(env.getProperty("spring.datasource.password"));
       defaultDS.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));

       return defaultDS;
   }
	
	/*
	 * 可动态路由的数据源。
	 */
   protected Map<Object, Object> getDataSources(){

       Map<Object,Object> map = new HashMap();

       if (datasources != null && datasources.length() > 0) {
           String[] names = datasources.split(",");
           for (String name : names) {
               DruidDataSource dataSource = new DruidDataSource();
               dataSource.setUrl(env.getProperty("spring.datasource." + name + ".url"));
               dataSource.setUsername(env.getProperty("spring.datasource." + name + ".username"));
               dataSource.setPassword(env.getProperty("spring.datasource." + name + ".password"));
               dataSource.setDriverClassName(env.getProperty("spring.datasource." + name + ".driver-class-name"));

               map.put(name, dataSource);
           }
       }

       return map;
   }
	
	/*
	 * Mapper 文件位置。
	 */
	protected  String getMapperLocation(){
       return "classpath*:com/crims/apps/mapper/**/*.xml";
   }
	
   @Bean
   public DynamicDataSource dynamicDataSource() {

       DynamicDataSource dynamicDataSource = DynamicDataSource.getInstance();
       
       Map<Object, Object> dataSources = getDataSources();
       if (dataSources.size() > 0) {
       	dynamicDataSource.setTargetDataSources( dataSources );
       }

       DataSource ds = getDefaultDataSource();
       if (ds != null) {
       	dynamicDataSource.setDefaultTargetDataSource( ds );
       }

       return dynamicDataSource;
   }

   @Bean(name = "sqlSessionTemplate")
   public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
       return new SqlSessionTemplate(sqlSessionFactory);
   }

   @Bean
   public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dynamicDataSource)throws Exception {
       SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
       bean.setDataSource(dynamicDataSource);
       bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources( getMapperLocation() ));

       return bean.getObject();
   }

}