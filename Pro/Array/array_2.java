/*
 * ���������ʹ��
 * 1.������Դ��ͬһ��������
 * 2.����������(int,float��)���飬����ֱ�Ӹ�ֵ
 * 3.���������ڶ���󣬸�ֵʱ��Ҫ�ٴ�Ϊÿ���������ռ�
 * ������new ����
 * 4.�����С��������ָ��
 * 5.�������������Ϊִ�������׵�ַ������
 * 6.�����±��Ǵ�0��ʼ��ŵ�
 * 
 * ����
 * 1.���� ������[]=new ����[��С];
 * 2.���� ������[];������=new new ����[��С];
 * 
 * ע��
 * �Ƚ��ַ��������Ƿ����ʱӦ����equals()����,��������== 
 */
package Array;

import java.io.*;

public class array_2 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		// ����һ�����Դ����ֻ���Ķ�������
		Dog dogs[] = new Dog[4];
		// ����������ֵ
		// dogs[0]=new Dog();
		// dogs[0].setName("huat");
		// dogs[0].setWeight(45);

		// �ӿ���̨�������������Ϣ
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);

		for (int i = 0; i < dogs.length; i++) {// ѭ����ӡ��
			dogs[i] = new Dog();
			System.out.println("Please input " + (i + 1) + "th dog's name:");
			// �ӿ���̨��ȡ����"
			String name = br.readLine();
			// �����ָ�������
			dogs[i].setName(name);
			System.out.println("Please input dog's weight");
			String s_weight = br.readLine();
			int weight = Integer.parseInt(s_weight);
			// �����ָ�������
			dogs[i].setWeight(weight);
		}

		// ����������
		int allWeight = 0;
		for (int i = 0; i < dogs.length; i++) {
			allWeight += dogs[i].getWeight();
		}
		// ����ƽ������
		float avgWeight = allWeight / dogs.length;
		System.out.println("Total weight: " + allWeight + "\nAverage weight: " + avgWeight);
		// �ҳ��������Ĺ�
		// �����һ�����������
		int maxWeight = dogs[0].getWeight();
		int maxIndex = 0;
		// ��˳��ͺ���Ĺ��Ƚ�
		for (int i = 1; i < dogs.length; i++) {
			if (maxWeight < dogs[i].getWeight()) {
				// �޸�
				maxWeight=dogs[i].getWeight();
				maxIndex=i;
			}	
		}
		System.out.println("The "+(maxIndex+1)+"th is max\n"+"Max weight: "+maxWeight+"	Dog's name: "+dogs[maxIndex].getName());
	}

}

// ����
class Dog {
	private String name;
	private int weight;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

}