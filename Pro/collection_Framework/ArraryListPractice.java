package collection_Framework;

import java.io.*;
import java.util.*;

public class ArraryListPractice {

	public static void main(String[] args) throws Exception {

		// ����EmployeeManage����
		EmployeeManage em = new EmployeeManage();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// �˵�
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

			case "1"://���Ա��
				System.out.println("Please input ID:");
				String ID = br.readLine();
				System.out.println("Please input name:");
				String name = br.readLine();
				System.out.println("Please input salary:");
				float salary = Float.parseFloat(br.readLine());

				// �������󲢰Ѹղ��������Ϣ���ݸ�����
				Employee employee = new Employee(ID, name, salary);

				// ����EmployeeManage�еķ������Ա��
				em.addEmployee(employee);

				System.out.println("Successful operation!");
				break;
				
			case "2"://����Ա��
				System.out.println("Please input ID:");
				String ID2 = br.readLine();
				em.showInfo(ID2);
				break;
				
			case "3"://�޸�Ա������
				System.out.println("Please input ID:");
				String ID3 = br.readLine();
				System.out.println("Please input new salary:");
				float newSalary = Float.parseFloat(br.readLine());
				em.salaryModification(ID3, newSalary);
				System.out.println("Successful operation!");
				break;
				
			case "4"://ɾ��Ա��
				System.out.println("Please input ID:");
				String ID4=br.readLine();
				em.deleteEmployee(ID4);
				System.out.println("Successful operation!");
				break;
				
			case "5"://��ʾ����Ա����Ϣ
				em.showAllEmployee();
				break;
			
			case "6"://�˳�
				System.exit(0);
				break;
				
			default:
				System.out.println("Input error,Please re-enter the operation!");
				break;
			}
		}
	}
}

// ��Ա������
class EmployeeManage {
	private ArrayList al = null;

	// ���캯��(ʵ����ArrayList)
	public EmployeeManage() {
		al = new ArrayList();
	}

	// ����Ա��
	public void addEmployee(Employee employee) {
		al.add(employee);
	}

	// ���Ҳ���ʾԱ���������Ϣ
	public void showInfo(String ID) {

		// ��������ArrayList
		for (int i = 0; i < al.size(); i++) {
			// ȡ��Employee����
			Employee employee = (Employee) al.get(i);

			// �Ƚϱ��
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

	// �޸Ĺ���
	public void salaryModification(String ID, float newSalary) {

		for (int i = 0; i < al.size(); i++) {

			Employee employee = (Employee) al.get(i);
			if (employee.getID().equals(ID)) {
				// �޸�нˮ
				employee.setSalary(newSalary);
			}else{
				System.out.println("No such employee.");
			}
		}
	}

	// ɾ��Ա��
	public void deleteEmployee(String ID) {

		for (int i = 0; i < al.size(); i++) {

			Employee employee = (Employee) al.get(i);
			if (employee.getID().equals(ID)) {
				// ɾ��Ա��
				al.remove(employee);
			}else{
				System.out.println("No such employee.");
			}
		}
	}

	//��ʾ����Ա��
	public void showAllEmployee(){
		
		for(int i=0;i<al.size();i++){
			// ȡ��Employee����
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

	// ����
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