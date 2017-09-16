/*
 * 对象数组的使用
 * 1.数组可以存放同一类型数据
 * 2.简单数据类型(int,float等)数组，可以直接赋值
 * 3.对象数组在定义后，赋值时需要再次为每个对象分配空间
 * （即：new 对象）
 * 4.数组大小必须事先指定
 * 5.数组名可以理解为执行数组首地址的引用
 * 6.数组下标是从0开始编号的
 * 
 * 定义
 * 1.类名 对象名[]=new 类名[大小];
 * 2.类名 对象名[];对象名=new new 类名[大小];
 * 
 * 注意
 * 比较字符串内容是否相等时应该用equals()方法,而不是用== 
 */
package Array;

import java.io.*;

public class array_2 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		// 定义一个可以存放四只狗的对象数组
		Dog dogs[] = new Dog[4];
		// 给给个狗赋值
		// dogs[0]=new Dog();
		// dogs[0].setName("huat");
		// dogs[0].setWeight(45);

		// 从控制台输入各个狗的信息
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);

		for (int i = 0; i < dogs.length; i++) {// 循环打印狗
			dogs[i] = new Dog();
			System.out.println("Please input " + (i + 1) + "th dog's name:");
			// 从控制台读取狗名"
			String name = br.readLine();
			// 将名字赋给对象
			dogs[i].setName(name);
			System.out.println("Please input dog's weight");
			String s_weight = br.readLine();
			int weight = Integer.parseInt(s_weight);
			// 将名字赋给对象
			dogs[i].setWeight(weight);
		}

		// 计算总体重
		int allWeight = 0;
		for (int i = 0; i < dogs.length; i++) {
			allWeight += dogs[i].getWeight();
		}
		// 计算平均体重
		float avgWeight = allWeight / dogs.length;
		System.out.println("Total weight: " + allWeight + "\nAverage weight: " + avgWeight);
		// 找出体重最大的狗
		// 假设第一个狗体重最大
		int maxWeight = dogs[0].getWeight();
		int maxIndex = 0;
		// 按顺序和后面的狗比较
		for (int i = 1; i < dogs.length; i++) {
			if (maxWeight < dogs[i].getWeight()) {
				// 修改
				maxWeight=dogs[i].getWeight();
				maxIndex=i;
			}	
		}
		System.out.println("The "+(maxIndex+1)+"th is max\n"+"Max weight: "+maxWeight+"	Dog's name: "+dogs[maxIndex].getName());
	}

}

// 狗类
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