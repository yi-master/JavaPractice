/*
 * Hashtable与HashMap用法类似
 * 
 * 创建Hashtable
 * Hashtable 对象名 = new Hashtable();
 * Map 对象名 = new Hashtable();//方便修改
 * 
 * 添加对象
 * 对象名.put(key,value);
 * key(键)：就是你存的值的编号
 * value(值)：就是你要存放的数据
 */
package collection_Framework;

import java.util.*;

public class HashtablePractice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Hashtable ht = new Hashtable();
		HashMap hm2 = new HashMap();
		Map mht = new Hashtable();
		Map mhm = new HashMap();
		// Hashtable不能放空值
		// ht.put(null, null);//会报错（空指针异常）
		// HashMap可以放空值
		hm2.put(null, null);

		System.out.println("test: " + hm2.get(null));
	}

}
