package uploadjava;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import uploadjava.Composite_Query;

// �U�άd�ߡA�i�H�N�W����N�Q�d�ߪ����
public class Composite_Query {
	// �]�w�d�����
	public static String get_aCondition_For_Oracle(String columnName, String value) {
		String aCondition = null;
		// �Ω��L
//		if ("mov_cat_id".equals(columnName) || "mov_time_length".equals(columnName))
//			aCondition = columnName + "=" + value;
		// �Ω� varchar
		if ("mov_id".equals(columnName) || "mov_name".equals(columnName)) 
			aCondition = columnName + " like '%" + value + "%'";
		// �Ω� Oracle��date
//		if ("hiredate".equals(columnName))
//			aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";
		return aCondition + " ";
	}
	
	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer(); // �}�s�u
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
				System.out.println("���e�X�d�߸�ƪ�����count = " + count);
			}
		}
		return whereCondition.toString();
	}

	public static void main(String argv[]) {
		// �t�X req.getParameterMap()��k �^�� java.util.Map<java.lang.String,java.lang.String[]> ������
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("mov_id", new String[] { "M000000001" });
		map.put("mov_cat_id", new String[] { "1000000001" });
		map.put("mov_name", new String[] { "��1" });
		map.put("mov_info", new String[] { "�ݸɥR" });
		map.put("mov_img", new String[] { "" });
		map.put("mov_level", new String[] { "NORMAL" });
		map.put("mov_time_length", new String[] { "9" });
		map.put("mov_video", new String[] { "" });// �`�NMap�̭��|�t��action��key

		String finalSQL = "select * from movement "
				          + Composite_Query.get_WhereCondition(map)
				          + "order by mov_id";
		System.out.println("finalSQL = " + finalSQL);
	}
}
