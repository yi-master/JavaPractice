package GUI_Practice;
/*
 * ���񲼾֣�
 * �����С�ɿ��������С
 * �����С����ͬ
 * 
 * 1.�̳�JFrame
 * 2.��������Ҫ�����
 * 3.�������
 * 4.�������񲼾�
 * 5.������
 * 6.������������
 * 7.��ʾ����
 */
import javax.swing.*;
import java.awt.*;

public class Demo_4 extends JFrame{

	//�������
	JButton jbs[]=new JButton[9];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo_4 demo_4=new Demo_4();
	}
	//���캯��
	public Demo_4(){
		//�������
		for(int i=0;i<9;i++)
			jbs[i]=new JButton(String.valueOf(i));
		
		//�������񲼾�
		this.setLayout(new GridLayout(3,3,10,10));//�������е����񣬺���������Ϊˮƽ��ֱ��϶
		
		//������
		for(int i=0;i<9;i++)
			this.add(jbs[i]);
		
		//���ô�������
		this.setTitle("���񲼾�");
		this.setSize(350,200);
		this.setLocation(200,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//��ʾ����
		this.setVisible(true);
	}
	
}
