/*
 * 丢手帕问题
 */
package JosephProblem;

public class Joseph {
	public static void main(String[] args) {
		CycLink cl = new CycLink();
		cl.setLength(5);
		cl.setK(2);
		cl.setM(2);
		cl.createdLink();
		cl.show();
		cl.play();
	}
}

class Child {
	int number;
	Child nextChild = null;

	public Child(int number) {

		this.number = number;
	}
}

// 环形链表
class CycLink {

	// 先定义一个指向链表第一个孩子的引用
	// 指向第一个小孩的引用不能动
	Child firstChild = null;
	Child temp = null;
	int length = 0;// 表示共有的孩子数
	int k = 0;
	int m = 0;

	// 设置链表大小
	public void setLength(int length) {
		this.length = length;
	}

	// 设置从第k个人开始数数
	public void setK(int k) {
		this.k = k;
	}

	// 设置报m号的人出圈
	public void setM(int m) {
		this.m = m;
	}

	// 开始play
	public void play() {
		Child temp = this.firstChild;

		// 1.先找到开始数数的人
		for (int i = 1; i < k; i++) {
			temp = temp.nextChild;
		}
		while (this.length != 1) {
			// 2.数m次
			for (int j = 1; j < m; j++) {
				temp = temp.nextChild;
			}

			// 找到要出圈的前一个孩子
			Child temp2 = temp;
			while (temp2.nextChild != temp) {
				temp2 = temp2.nextChild;
			}

			// 3.将数到m的小孩退出圈
			temp2.nextChild = temp.nextChild;
			// 让temp指向下一个数数的小孩
			temp = temp.nextChild;
			
			this.length--;
		}
		// 最后一个孩子
		System.out.println("Final lap:"+temp.number);
	}

	// 初始化环形链表
	public void createdLink() {

		for (int i = 1; i <= length; i++) {
			if (i == 1) {
				// 创建第一个 个孩子
				Child ch = new Child(i);
				this.firstChild = ch;
				this.temp = ch;
			} else if (i == length) {// 创建最后一个孩子
				Child ch = new Child(i);
				temp.nextChild = ch;
				temp = ch;
				temp.nextChild = this.firstChild;
			} else {
				// 继续创建小孩
				Child ch = new Child(i);
				temp.nextChild = ch;
				temp = ch;// temp指向下一个孩子
			}
		}
	}

	// 打印环形链表
	public void show() {
		// 定义一个跑龙套
		Child temp = this.firstChild;
		do {
			System.out.print(temp.number+" ");
			temp = temp.nextChild;
		} while (temp != this.firstChild);
		System.out.println();
	}
}