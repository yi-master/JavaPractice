/*
 * java常用集合类
 * List结构的集合类
 * ArrayList类、LiskedList类、Vector类、Stack类
 * Map结构的集合类
 * HashMap类，Hashtable类
 * Set结构的集合类
 * HashSet类，TreeSet类
 * Queue结构的集合
 * Queue接口
 * 相同点都是集合类，都可用来存放Java对象
 */
/*
 * HashMap和Hashtable区别
 * 主要区别：
 * 1.同步性
 * Hashtable是同步的（线程同步）。
 * 这个类中的一些方法保证了Hashtable中的对象是线程安全的。
 * HashMap是异步的，因此HashMap中的对象并不是线程安全的。
 * 因为同步的要求会影响执行效率，
 * 所以如果你不需要线程安全的集合或永远只用一个线程访问，那么使用HashMap时一个较好的选择
 * 可以避免由于同步带来的不必要的性能开销，从而提高效率
 * 2.值
 * HashMap可以让你将空值作为一个表的条目的key会value
 * Hashtable不能放入空值（null）
 */
/*
 * ArrayList和Vector区别
 * 1.同步性
 * Vector是同步的。这个类中的一些方法保证了Vector中的对象是线程安全的。
 * ArrayList是异步的。因此ArraryList中的对象并不是线程安全的。
 * 因为同步的要求会影响执行效率，
 * 所以如果你不需要线程安全的集合或永远只用一个线程访问，那么使用ArrayList时一个较好的选择
 * 可以避免由于同步带来的不必要的性能开销，从而提高效率
 * 2.数据增长
 * 当向这两种类型中增加元素的时，如果元素的数目超出了内部数组目前的长度，它们都需要扩展内部数组长度
 * Vector缺省情况下自动增长原来一倍的数组长度。
 * ArrayList是原来的50%。
 * 因此最后获得这个集合所占用的空间总是比你实际需要的大
 * 如果要在集合中保存大量数据，那么使用Vector有一些优势
 * 因为你可以通过设置集合的初始化大小来避免不必要的资源开销
 */
/*
 * 集合框架的选择
 * 如果要求线程安全，使用Vector、Hashtable
 * 如果不要求线程安全，使用ArrayList、LinkedList、HashMap
 * 如果要求键值对，使用HashMap、Hashtable
 * 如果数据量很大，又要线程安全，则考虑Vector
 */
package collection_Framework;

import java.util.*;
public class CollectionFramework_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//定义一个ArrayList对象
		ArrayList al=new ArrayList();
		
		//显示大小
		System.out.println("ArrayList对象al大小："+al.size());
		
		
		//创建职员
		Clerk clerk1=new Clerk("yasuo",34,1000);
		Clerk clerk2=new Clerk("liqing",33,1200);
		Clerk clerk3=new Clerk("yi",32,2200);
		
		//向al中加入数据(类型是Object)
		//将clerk1加入al(可多次添加同一个对象)
		al.add(clerk1);
		al.add(clerk2);
		al.add(clerk3);
		al.add(clerk1);
		
		//显示大小
		System.out.println("ArrayList对象al大小："+al.size());
		
//		//访问al中对象（数据）
//		//访问第一个对象
//		Clerk temp=(Clerk) al.get(0);
//		System.out.println("The first name: "+temp.getName());
			
		//删除al中的第一个对象
		al.remove(0);
		System.out.println("Delete first yasuo");
		
		//变量al中所有对象
		for(int i=0;i<al.size();i++){
			Clerk temps=(Clerk)al.get(i);
			System.out.println(temps.getName());
		}

	}

}
//员工类
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