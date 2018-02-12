/*
 *  1. �U�νƦX�d��-�i�ѫȤ���H�N�W�����Q�d�ߪ����
 *  2. ���F�קK�v�T�į�:
 *        �ҥH�ʺA���͸U��SQL������,���d�ҵL�N�ĥ�MetaData���覡,�]�u�w��ӧO��Table�ۦ���ݭn�ӭӧO�s�@��
 * */


package jdbc.util.CompositeQuery;

import java.util.*;

public class jdbcUtil_CompositeQuery_Plan {

	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;

		if ("mem_id".equals(columnName) || "interval".equals(columnName) || "cycle".equals(columnName))  // �Ω��L
			aCondition = columnName + "=" + value;
		else if ("plan_id".equals(columnName) || "shareplan".equals(columnName)|| "plan_name".equals(columnName)) // �Ω�varchar
			aCondition = columnName + " like '%" + value + "%'";
		else if ("startdate".equals(columnName))                          // �Ω�Oracle��date
			aCondition = "to_char(" + columnName + ",'yyyy-mm')='" + value + "'";

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

				System.out.println("���e�X�d�߸�ƪ�����count = " + count);
			}
		}
		
		return whereCondition.toString();
	}

	public static void main(String argv[]) {

		// �t�X req.getParameterMap()��k �^�� java.util.Map<java.lang.String,java.lang.String[]> ������
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("plan_id", new String[] { "P00001" });
		map.put("mem_id", new String[] { "7002" });
		map.put("shareplan", new String[] { "ON" });
		map.put("plan_name", new String[] { "OLDWUPLAN" });
		map.put("startdate", new String[] { "2017-12-14" });
		map.put("interval", new String[] { "0" });
		map.put("cycle", new String[] { "0" });
		map.put("action", new String[] { "getXXX" }); // �`�NMap�̭��|�t��action��key

		String finalSQL = "select * from plan "
				          + jdbcUtil_CompositeQuery_Plan.get_WhereCondition(map)
				          + "order by plan_id";
		System.out.println("����finalSQL = " + finalSQL);

	}
}
