/*
 * Hashtable��HashMap�÷�����
 * 
 * ����Hashtable
 * Hashtable ������ = new Hashtable();
 * Map ������ = new Hashtable();//�����޸�
 * 
 * ��Ӷ���
 * ������.put(key,value);
 * key(��)����������ֵ�ı��
 * value(ֵ)��������Ҫ��ŵ�����
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
		// Hashtable���ܷſ�ֵ
		// ht.put(null, null);//�ᱨ����ָ���쳣��
		// HashMap���Էſ�ֵ
		hm2.put(null, null);

		System.out.println("test: " + hm2.get(null));
	}

}
