package uploadjava;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import uploadjava.Composite_Query;

// 萬用查詢，可隨意增減任意想查詢的欄位
public class Composite_Query {
	// 設定查詢欄位
	public static String get_aCondition_For_Oracle(String columnName, String value) {
		String aCondition = null;
		// 用於其他
//		if ("mov_cat_id".equals(columnName) || "mov_time_length".equals(columnName))
//			aCondition = columnName + "=" + value;
		// 用於 varchar
		if ("mov_id".equals(columnName) || "mov_name".equals(columnName)) 
			aCondition = columnName + " like '%" + value + "%'";
		// 用於 Oracle的date
//		if ("hiredate".equals(columnName))
//			aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";
		return aCondition + " ";
	}
	
	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer(); // 開連線
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_Oracle(key, value.trim());
				if (count == 1) {
					whereCondition.append(" where " + aCondition);
				} else {
					whereCondition.append(" and " + aCondition);
				}
				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		return whereCondition.toString();
	}

	public static void main(String argv[]) {
		// 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("mov_id", new String[] { "M000000001" });
		map.put("mov_cat_id", new String[] { "1000000001" });
		map.put("mov_name", new String[] { "胸1" });
		map.put("mov_info", new String[] { "待補充" });
		map.put("mov_img", new String[] { "" });
		map.put("mov_level", new String[] { "NORMAL" });
		map.put("mov_time_length", new String[] { "9" });
		map.put("mov_video", new String[] { "" });// 注意Map裡面會含有action的key

		String finalSQL = "select * from movement "
				          + Composite_Query.get_WhereCondition(map)
				          + "order by mov_id";
		System.out.println("finalSQL = " + finalSQL);
	}
}
