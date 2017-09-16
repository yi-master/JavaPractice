package collection_Framework;

import java.io.*;
import java.util.*;

public class ArraryListPractice {

	public static void main(String[] args) throws Exception {

		// 创建EmployeeManage对象
		EmployeeManage em = new EmployeeManage();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 菜单
		while (true) {
			System.out.println("Please select what you want to do:");
			System.out.println("1.Add an employee");
			System.out.println("2.Lookup an employee");
			System.out.println("3.Modify an employee's salary");
			System.out.println("4.Delete an employee");
			System.out.println("5.Display all employee information");
			System.out.println("6.exit");
			String x = br.readLine();

			switch (x) {

			case "1"://添加员工
				System.out.println("Please input ID:");
				String ID = br.readLine();
				System.out.println("Please input name:");
				String name = br.readLine();
				System.out.println("Please input salary:");
				float salary = Float.parseFloat(br.readLine());

				// 创建对象并把刚才输入的信息传递给对象
				Employee employee = new Employee(ID, name, salary);

				// 调用EmployeeManage中的方法添加员工
				em.addEmployee(employee);

				System.out.println("Successful operation!");
				break;
				
			case "2"://查找员工
				System.out.println("Please input ID:");
				String ID2 = br.readLine();
				em.showInfo(ID2);
				break;
				
			case "3"://修改员工工资
				System.out.println("Please input ID:");
				String ID3 = br.readLine();
				System.out.println("Please input new salary:");
				float newSalary = Float.parseFloat(br.readLine());
				em.salaryModification(ID3, newSalary);
				System.out.println("Successful operation!");
				break;
				
			case "4"://删除员工
				System.out.println("Please input ID:");
				String ID4=br.readLine();
				em.deleteEmployee(ID4);
				System.out.println("Successful operation!");
				break;
				
			case "5"://显示所有员工信息
				em.showAllEmployee();
				break;
			
			case "6"://退出
				System.exit(0);
				break;
				
			default:
				System.out.println("Input error,Please re-enter the operation!");
				break;
			}
		}
	}
}

// 雇员管理类
class EmployeeManage {
	private ArrayList al = null;

	// 构造函数(实例化ArrayList)
	public EmployeeManage() {
		al = new ArrayList();
	}

	// 加入员工
	public void addEmployee(Employee employee) {
		al.add(employee);
	}

	// 查找并显示员工的相关信息
	public void showInfo(String ID) {

		// 遍历整个ArrayList
		for (int i = 0; i < al.size(); i++) {
			// 取出Employee对象
			Employee employee = (Employee) al.get(i);

			// 比较编号
			if (employee.getID().equals(ID)) {
				System.out.println("Find the employee,His information:");
				System.out.println("ID: " + employee.getID());
				System.out.println("Name: " + employee.getName());
				System.out.println("Salary: " + employee.getSalary());
			}else{
				System.out.println("No such employee.");
			}
		}
	}

	// 修改工资
	public void salaryModification(String ID, float newSalary) {

		for (int i = 0; i < al.size(); i++) {

			Employee employee = (Employee) al.get(i);
			if (employee.getID().equals(ID)) {
				// 修改薪水
				employee.setSalary(newSalary);
			}else{
				System.out.println("No such employee.");
			}
		}
	}

	// 删除员工
	public void deleteEmployee(String ID) {

		for (int i = 0; i < al.size(); i++) {

			Employee employee = (Employee) al.get(i);
			if (employee.getID().equals(ID)) {
				// 删除员工
				al.remove(employee);
			}else{
				System.out.println("No such employee.");
			}
		}
	}

	//显示所有员工
	public void showAllEmployee(){
		
		for(int i=0;i<al.size();i++){
			// 取出Employee对象
			Employee employee = (Employee) al.get(i);
			System.out.println("ID: " + employee.getID());
			System.out.println("Name: " + employee.getName());
			System.out.println("Salary: " + employee.getSalary());
		}
		
		if(al.size()==0){
			System.out.println("No staff information is available now.");
		}
	}
}

class Employee {

	// 属性
	private String ID;
	private String name;
	private float salary;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public Employee(String ID, String name, float salary) {
		this.ID = ID;
		this.name = name;
		this.salary = salary;
	}
}