/*
 * final
 * ����
 * �������α����򷽷�
 * 1.����ϣ�������ĳ�����������า�ǣ�override��ʱ������ʹ��final�ؼ�������
 * 2.����ϣ�����ĳ��������ֵ���޸ģ�������final����
 * ���һ��������final�����ڶ���ʱ���븳��ʼֵ�������������
 * 3.����ϣ��ĳ���౻�̳�ʱ��������final����
 * 
 * ע������
 * 1.final���εı����ֽ� ������һ����xx_xx_xx������
 * 2.final���εı����ڶ���ʱ�����븳ֵ�������Ժ����ٸ�ֵ
 * 
 * ʲôʱ����
 * 1.��Ϊ��ȫ�Ŀ��ǣ����ĳ�������������޸�
 * 2.�಻�ᱻ�����ļ̳�
 * 3.ĳЩ����ֵ�ǹ̶�����ģ���Բ���ʦУ�3.1415926��
 */
package Object_oriented;

public class UseFinal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Aa aa=new Aa();
		aa.show();
	}

}
//final�������ʾ���಻�ܱ��̳�
final class Aa{
	int a=0;//���������ʼֵ��a=0
	
	final float rate=3.1415926f;

	//��������final���Σ����ʾ���ܱ��޸ģ����ܱ�����
	final public void sendMessage(){
		System.out.println("Send message");
	}
	public void show(){
		System.out.println(rate);
	}
}

