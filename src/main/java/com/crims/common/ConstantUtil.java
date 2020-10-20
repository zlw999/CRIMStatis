package com.crims.common;

import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public class ConstantUtil {
/*
 * CCSPCommStruct.h中定义
 * 	#define defCCSPDefaultALLNodeCode	"FFFFFFFFFFFFFFFFFFFFFFFFFFF"	///< 表示所有节点
 * 	#define DefDefaultSTSDT_MS			"2000-01-01 01:01:01:000" ///< 缺省系统日期时间
 * 	#define DefDefaultSTSDT_S			"2000-01-01 01:01:01" ///<缺省系统日期时间
 * 	#define DefCCSP_DataTime_NULL_Str	"2000-01-01 01:01:01"///<日期时间默认值(字符串)
 * 	#define DefDateTimeFormat			_T("%Y-%m-%d %H:%M:%S")///<日期时间的字符串显示格式
*/
	public static final int SECOND_DIFFERENCE = 1;	// 以秒计算时间差
	public static final int MILLIS_DIFFERENCE = 2;	// 以毫秒计算时间差	
	
	public static Date getDefaultTime()
    {
    	Calendar cal = Calendar.getInstance();
    	cal.set(Calendar.YEAR, 2000);
    	cal.set(Calendar.MONTH, 0);
    	cal.set(Calendar.DAY_OF_MONTH, 1);
    	cal.set(Calendar.HOUR_OF_DAY, 1);
    	cal.set(Calendar.MINUTE, 1);
    	cal.set(Calendar.SECOND, 1);
    	cal.set(Calendar.MILLISECOND, 0);
    	return cal.getTime();
    }
	
	public static boolean isDefaultTime(Date time)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		if( year <= 2000
			&& month == 0
			&& day == 1)
		{
			return true;
		}

		return false;
	}
	
	public static long calculateTimeDifferenceByDuration(int unit, Instant lefttime, Instant righttime)
	{
		if( unit == SECOND_DIFFERENCE )
		{
			return Duration.between(lefttime, righttime).getSeconds();
		}
		else
		{
			return Duration.between(lefttime, righttime).toMillis();
		}
	}
}
