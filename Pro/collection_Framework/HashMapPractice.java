/*
 *  Hashtable��HashMap�÷�����
 *  
 * ����HashMap
 * HashMap ������ = new HashMap();
 * Map ������ = new HashMap();//�����޸�
 * 
 * ��Ӷ���
 * ������.put(key,value);
 * key(��)����������ֵ�ı��
 * value(ֵ)��������Ҫ��ŵ�����
 */
package collection_Framework;

import java.util.*;

public class HashMapPractice {
	public static void main(String[] args) {
		// ����һ��HashMap����
		HashMap hm = new HashMap();

		Employee employee1 = new Employee("20001", "yasuo", 5000f);
		Employee employee2 = new Employee("20002", "liqing", 9000f);
		Employee employee3 = new Employee("20003", "yi", 7000f);

		// ��Employee����ŵ�hm(keyֵ�ظ��ᱻ����Ķ��󸲸�)
		hm.put("20001", employee1);
		hm.put("20002", employee2);
		// �Ḳ��ԭ�����Ϊ20001�����ݣ�����ı�HashMap�ж��������
		hm.put("20001", employee3);

		// ���ұ��Ϊ20001
		if (hm.containsKey("20001")) {
			System.out.println("The employee exists.");

			// ȡ����Ӧ��keyֵ
			Employee employee = (Employee) hm.get("20001");
			System.out.println("name: " + employee.getName());
		} else {
			System.out.println("The employee does not exist.");
		}

		// ����HashMapz�����е�Key��value
		Iterator i = hm.keySet().iterator();
		// hasNext()�ж���û����һ��������һ������ֵ
		while (i.hasNext()) {
			// ȡ��Key(Keyֵ���ַ������ͣ��������ַ����ͽ���)
			String key = i.next().toString();// (i.next()���ص��Ƕ������ͣ�toString()�����Ѷ���ת�����ַ���)
			// ͨ��keyȡ��value
			Employee employee = (Employee) hm.get(key);
			System.out.println("name: " + employee.getName());
			System.out.println("salary:" + employee.getSalary());
		}
	}
}
