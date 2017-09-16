/*
 *  Hashtable与HashMap用法类似
 *  
 * 创建HashMap
 * HashMap 对象名 = new HashMap();
 * Map 对象名 = new HashMap();//方便修改
 * 
 * 添加对象
 * 对象名.put(key,value);
 * key(键)：就是你存的值的编号
 * value(值)：就是你要存放的数据
 */
package collection_Framework;

import java.util.*;

public class HashMapPractice {
	public static void main(String[] args) {
		// 创建一个HashMap对象
		HashMap hm = new HashMap();

		Employee employee1 = new Employee("20001", "yasuo", 5000f);
		Employee employee2 = new Employee("20002", "liqing", 9000f);
		Employee employee3 = new Employee("20003", "yi", 7000f);

		// 将Employee对象放到hm(key值重复会被后面的对象覆盖)
		hm.put("20001", employee1);
		hm.put("20002", employee2);
		// 会覆盖原来编号为20001的内容，不会改变HashMap中对象的总数
		hm.put("20001", employee3);

		// 查找编号为20001
		if (hm.containsKey("20001")) {
			System.out.println("The employee exists.");

			// 取出对应的key值
			Employee employee = (Employee) hm.get("20001");
			System.out.println("name: " + employee.getName());
		} else {
			System.out.println("The employee does not exist.");
		}

		// 遍历HashMapz中所有的Key和value
		Iterator i = hm.keySet().iterator();
		// hasNext()判断有没有下一个，返回一个布尔值
		while (i.hasNext()) {
			// 取出Key(Key值是字符串类型，所以用字符类型接收)
			String key = i.next().toString();// (i.next()返回的是对象类型，toString()方法把对象转换成字符串)
			// 通过key取出value
			Employee employee = (Employee) hm.get(key);
			System.out.println("name: " + employee.getName());
			System.out.println("salary:" + employee.getSalary());
		}
	}
}
