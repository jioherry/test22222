/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
 *  2. 為了避免影響效能:
 *        所以動態產生萬用SQL的部份,本範例無意採用MetaData的方式,也只針對個別的Table自行視需要而個別製作之
 * */


package com.rep.model;

import java.util.*;

public class RepCompositeQuery {

	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;

		if ("rep_id".equals(columnName) || "mem_id".equals(columnName) || "emp_id".equals(columnName) ) // 用於其他
			aCondition = columnName + "=" + value;
		else if ("cou_id".equals(columnName) || "rep_cont".equals(columnName)|| "rep_status".equals(columnName) ) // 用於varchar
			aCondition = columnName + " like '%" + value + "%'";
		else if ("rep_date".equals(columnName))                          // 用於Oracle的date
			aCondition = "to_timestamp(" + columnName + ",'dd-mm-yyyy hh24:mi:ss')='" + value + "'";
		
		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_Oracle(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		
		return whereCondition.toString();
	}
	public static void main(String argv[]) {

		// 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("rep_id", new String[] { "2001" });
		map.put("mem_id", new String[] { "7002" });
		map.put("emp_id", new String[] { "8001" });
		map.put("cou_id", new String[] { "C000011" });
		map.put("rep_cont", new String[] { "測試1" });
		map.put("rep_date", new String[] { "27-12-17 08:32:29" });
		map.put("rep_status", new String[] { "審核" });
//		map.put("action", new String[] { "getXXX" }); // 注意Map裡面會含有action的key

		String finalSQL = "select * from report "
				          + RepCompositeQuery.get_WhereCondition(map)
				          + "order by rep_id";
		System.out.println("●●finalSQL = " + finalSQL);

	}
}
