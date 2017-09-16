/*
 * java���ü�����
 * List�ṹ�ļ�����
 * ArrayList�ࡢLiskedList�ࡢVector�ࡢStack��
 * Map�ṹ�ļ�����
 * HashMap�࣬Hashtable��
 * Set�ṹ�ļ�����
 * HashSet�࣬TreeSet��
 * Queue�ṹ�ļ���
 * Queue�ӿ�
 * ��ͬ�㶼�Ǽ����࣬�����������Java����
 */
/*
 * HashMap��Hashtable����
 * ��Ҫ����
 * 1.ͬ����
 * Hashtable��ͬ���ģ��߳�ͬ������
 * ������е�һЩ������֤��Hashtable�еĶ������̰߳�ȫ�ġ�
 * HashMap���첽�ģ����HashMap�еĶ��󲢲����̰߳�ȫ�ġ�
 * ��Ϊͬ����Ҫ���Ӱ��ִ��Ч�ʣ�
 * ��������㲻��Ҫ�̰߳�ȫ�ļ��ϻ���Զֻ��һ���̷߳��ʣ���ôʹ��HashMapʱһ���Ϻõ�ѡ��
 * ���Ա�������ͬ�������Ĳ���Ҫ�����ܿ������Ӷ����Ч��
 * 2.ֵ
 * HashMap�������㽫��ֵ��Ϊһ�������Ŀ��key��value
 * Hashtable���ܷ����ֵ��null��
 */
/*
 * ArrayList��Vector����
 * 1.ͬ����
 * Vector��ͬ���ġ�������е�һЩ������֤��Vector�еĶ������̰߳�ȫ�ġ�
 * ArrayList���첽�ġ����ArraryList�еĶ��󲢲����̰߳�ȫ�ġ�
 * ��Ϊͬ����Ҫ���Ӱ��ִ��Ч�ʣ�
 * ��������㲻��Ҫ�̰߳�ȫ�ļ��ϻ���Զֻ��һ���̷߳��ʣ���ôʹ��ArrayListʱһ���Ϻõ�ѡ��
 * ���Ա�������ͬ�������Ĳ���Ҫ�����ܿ������Ӷ����Ч��
 * 2.��������
 * ��������������������Ԫ�ص�ʱ�����Ԫ�ص���Ŀ�������ڲ�����Ŀǰ�ĳ��ȣ����Ƕ���Ҫ��չ�ڲ����鳤��
 * Vectorȱʡ������Զ�����ԭ��һ�������鳤�ȡ�
 * ArrayList��ԭ����50%��
 * �����������������ռ�õĿռ����Ǳ���ʵ����Ҫ�Ĵ�
 * ���Ҫ�ڼ����б���������ݣ���ôʹ��Vector��һЩ����
 * ��Ϊ�����ͨ�����ü��ϵĳ�ʼ����С�����ⲻ��Ҫ����Դ����
 */
/*
 * ���Ͽ�ܵ�ѡ��
 * ���Ҫ���̰߳�ȫ��ʹ��Vector��Hashtable
 * �����Ҫ���̰߳�ȫ��ʹ��ArrayList��LinkedList��HashMap
 * ���Ҫ���ֵ�ԣ�ʹ��HashMap��Hashtable
 * ����������ܴ���Ҫ�̰߳�ȫ������Vector
 */
package collection_Framework;

import java.util.*;
public class CollectionFramework_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//����һ��ArrayList����
		ArrayList al=new ArrayList();
		
		//��ʾ��С
		System.out.println("ArrayList����al��С��"+al.size());
		
		
		//����ְԱ
		Clerk clerk1=new Clerk("yasuo",34,1000);
		Clerk clerk2=new Clerk("liqing",33,1200);
		Clerk clerk3=new Clerk("yi",32,2200);
		
		//��al�м�������(������Object)
		//��clerk1����al(�ɶ�����ͬһ������)
		al.add(clerk1);
		al.add(clerk2);
		al.add(clerk3);
		al.add(clerk1);
		
		//��ʾ��С
		System.out.println("ArrayList����al��С��"+al.size());
		
//		//����al�ж������ݣ�
//		//���ʵ�һ������
//		Clerk temp=(Clerk) al.get(0);
//		System.out.println("The first name: "+temp.getName());
			
		//ɾ��al�еĵ�һ������
		al.remove(0);
		System.out.println("Delete first yasuo");
		
		//����al�����ж���
		for(int i=0;i<al.size();i++){
			Clerk temps=(Clerk)al.get(i);
			System.out.println(temps.getName());
		}

	}

}
//Ա����
class Clerk{
	private String name;
	private int age;
	private float salary;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public Clerk(String name,int age,float salary){
		this.name=name;
		this.age=age;
		this.salary=salary;
	}
}