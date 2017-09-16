package Encapsulation;
/*
 * Java��װ:���ʿ������η�(��Ա�����ͷ������ɱ�����)
 * �����η�����Ȩ��
 * ��������public���Σ����⹫����ͬ�࣬ͬ�������࣬��ͬ�����ܷ��ʣ�
 * �ܱ�������protected���Σ��������ͬһ�����е��๫����ͬ�࣬ͬ�������඼�ܷ��ʣ�
 * Ĭ�ϼ���û�����η��ţ���ͬһ�������๫����ͬ�࣬ͬ�����ܷ��ʣ�
 * ˽�м���privateֻ���౾����Է��ʣ������⹫����ֻ��ͬ����Է��ʣ�
 */



public class Encapsulation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Clerk clerk1=new Clerk("aerfa", 18, 2600);
		System.out.println("The name is "+clerk1.name+"The salary is "+clerk1.getSalary());
	}

}
class Clerk {
	public String name;
	//˽�����ԣ�ֻ�ܱ���ǰ����ʣ�
	private int age;
	private float salary;

	public Clerk(String name, int age, float salary) {
		this.name = name;
		this.age = age;
		this.salary = salary;
	}
	
	//ͨ��һ����Ա����ȥ���ƺͷ���˽�е�����
	public float getSalary(){
		return salary;
	}
}
