package collection_Framework;

import java.util.*;

public class VectorPractice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Vector”√∑®
		Vector v=new Vector();
		Employee employee1=new Employee("20001", "yasuo", 5000f);
		Employee employee2=new Employee("20002", "liqing", 5000f);
		
		v.add(employee1);
		v.add(employee2);
		
		for(int i=0;i<v.size();i++){
			System.out.println(((Employee)v.get(i)).getName());
		}
	}

}
