/*
 * �̳еı�Ҫ��
 * ���˸����˽�����Ժͷ��������Ա�����̳�
 * �������ֻ�ܼ̳�һ������
 * Java��������ʰObject�������
 * ���ϣ���������е��ø���Ĺ��췽������Ҫ������Ĺ��캯���е���
 */
package Inherit;

public class Inherit_1 {

	public static void main(String[] args) {
		Pupil p1= new Pupil();
		p1.printName();
	}
}

//��ѧ�����������������һ������
class Student{
	//�����Ա����
	public int age;
	public String name;
	public float tuition;
	private String Occupation;
	
	//����У�����㲻ϣ������̳�ĳ�����Ի��߷������ͽ�������Ϊ˽�У�private�����ɡ�
	
	public void printName(){
		System.out.println("The name is "+name);
	}
}


//Сѧ����
class Pupil extends Student{
	
	//�ɷ�
	public void pay(float tuition){
		this.tuition=tuition;
	}
	
}

//С���ࣨ�̳�Pupil�࣬��Pupil��̳�Student�࣬��ӵ��Student���Pupil����������Ժͷ�����
class Kid extends Pupil{
	
}

//��ѧ����
class MiddleStudent extends Student{
		
		//�ɷ�
		public void pay(float tuition){
			this.tuition=tuition*0.8f;
		}
}

//��ѧ����
class CollegeStudent extends Student{
	
	//�ɷ�
	public void pay(float tuition){
		this.tuition=tuition*0.1f;
	}
}