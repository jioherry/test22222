package aboutselect;

import java.util.*;

public class CompositeQuery {

	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;

		if ("mem_id".equals(columnName) || "cou_int".equals(columnName) || "cou_cal_cns".equals(columnName) || "cou_time_length".equals(columnName))
			aCondition = columnName + "=" + value;
		else if ("cou_id".equals(columnName) || "cou_cat_id".equals(columnName) || "cou_intor".equals(columnName) || "cou_name".equals(columnName) || "cou_permi".equals(columnName) || "cited_count".equals(columnName))
			aCondition = columnName + " like '%" + value + "%'";
		else if ("cre_date".equals(columnName))
			aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";

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

				System.out.println("無法取得資料80 = " + count);
			}
		}
		
		return whereCondition.toString();
	}

}
