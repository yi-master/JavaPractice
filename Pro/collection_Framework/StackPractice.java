package collection_Framework;

import java.util.Stack;

public class StackPractice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack s=new Stack();
		Employee employee1=new Employee("20001", "yasuo", 5000f);
		Employee employee2=new Employee("20002", "liqing", 5000f);
		
		s.add(employee1);
		s.add(employee2);
		
		for(int i=0;i<s.size();i++){
			System.out.println(((Employee)s.get(i)).getName());
		}
	}

}
