/*
 * ������װ
 * ����
 * �������ؾ������ͬһ�ֹ��ܵĶ���ʵ�ַ�ʽ
 * ����ʹ�����ַ�ʽ��ȡ���ڵ����߸����Ĳ���
 * 
 * ע������
 * ��������ͬ
 * �����Ĳ������ͣ�������˳��������һ�ͬ
 * �����������Ϳ��Բ�ͬ(ֻ�Ƿ������Ͳ�ͬ���ܹ���������)
 * ���������η����Բ�ͬ(ֻ�ǿ��Ʒ������η��Ų�һ�������ܹ�������)
 */
package Inherit;

public class OverLoad {

	public static void main(String[] args) {
	
		Abc abc1=new Abc();
		System.out.println(abc1.getMax(0.4f, 3.99f));
	}

}

//�������ط�ʽ
class Abc{
	//���ؽϴ�����
	public int getMax(int i, int j){
		if(i>j){
			return i;
		}else{
			return j;
		}
	}
	
	public float getMax(float i,float j){
		if(i>j){
			return i;
		}else{
			return j;
		}
	}
	
	public float getMax(double i,float j){
		if(i>j){
			return (float)i;
		}else{
			return (float)j;
		}
	}
	
	public float getMax(float j,double i){
		if(i>j){
			return (float)i;
		}else{
			return (float)j;
		}
	}
	
//	//ֻ�Ƿ������Ͳ�ͬ�����ܹ�������
//	public double getMax(float j,double i){
//		if(i>j){
//			return i;
//		}else{
//			return j;
//		}
//	}
//	//ֻ�ǿ��Ʒ������η��Ų�һ�������ܹ�������
//	protected float getMax(float j,double i){
//		if(i>j){
//			return (float)i;
//		}else{
//			return (float)j;
//		}
//	}
}