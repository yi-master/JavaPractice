/*
 * �����෽��ʹ�ã�����.�෽����()���߶�����.������
 * �෽������̬�������в��ܷ��ʷǾ�̬�������������
 * �෽������̬������ֻ�ܷ��ʾ�̬����
 * �Ǿ�̬�������ܷ��ʾ�̬������Ҳ�ܷ��ʷǾ�̬����
 * 
 * ���������̬������
 * ����static��Ϊ�������̬�����������Ϊʵ������
 * ���������̬��������������صĹ���������
 * ʵ����������ÿ������������
 * ���������̬����������ͨ������.�������ֱ�ӷ���
 * 
 * �෽������̬������
 * �෽������̬����������������صĹ����ķ���
 * ʵ����������ÿ���������ķ���
 * �෽������̬����������ͨ������.������ֱ�ӷ���
 */
package Object_oriented;

public class VariableAndMethod {

	static int i = 1;
	static {
		// �þ�̬�����ִֻ��һ��
		// ����������������Ի�ִ��
		System.out.println("Static area be executed.");
		i++;
	}

	public VariableAndMethod() {
		System.out.println("Construction method be executed.");
		i++;
	}

	public static void main(String args[]) {
		 VariableAndMethod vam1=new VariableAndMethod();
		 System.out.println(vam1.i);
		 VariableAndMethod vam2=new VariableAndMethod();
		 System.out.println(vam2.i);

		student stu1 = new student(33, "aaa", 500);
		student stu2 = new student(22, "bbb", 960);
		System.out.println(student.getTotalTuition());
	
		
	}
}


class student {
	int age;
	String name;
	int tuition;
	static int totalTuition;

	public student(int age, String name, int tuition) {
		this.age = age;
		this.name = name;
		totalTuition += tuition;
	}

	// ������ѧ��(����һ���෽������̬������)
	// java�й��������ԭ�������෽��ȥ����
	public static int getTotalTuition() {
		return totalTuition;
	}
}
