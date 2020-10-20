package com.crims.dbconfig;

//只对当前执行线程有效
public class DataSourceContextHolder {
  private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
  public static synchronized void setKey(String key){
      contextHolder.set(key);
  }
  public static String getKey(){
      return contextHolder.get();
  }
  public static void clearKey(){
      contextHolder.remove();
  }
}