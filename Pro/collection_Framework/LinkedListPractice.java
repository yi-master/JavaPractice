package collection_Framework;

import java.util.*;

public class LinkedListPractice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList ll=new LinkedList();
		Employee employee1=new Employee("20001", "yasuo", 5000f);
		Employee employee2=new Employee("20002", "liqing", 5000f);
		
		//�ȼӵ��ں��棨����ӵ���ǰ�棩
		ll.addFirst(employee1);
		ll.addFirst(employee2);
		
		//�ȼӵ���ǰ�棨����ӵ��ں��棩
		ll.addLast(employee1);
		ll.addLast(employee2);
		
		for(int i=0;i<ll.size();i++){
			System.out.println(((Employee)ll.get(i)).getName());
		}
	}

}
