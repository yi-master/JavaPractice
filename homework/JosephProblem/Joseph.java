/*
 * ����������
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

// ��������
class CycLink {

	// �ȶ���һ��ָ�������һ�����ӵ�����
	// ָ���һ��С�������ò��ܶ�
	Child firstChild = null;
	Child temp = null;
	int length = 0;// ��ʾ���еĺ�����
	int k = 0;
	int m = 0;

	// ���������С
	public void setLength(int length) {
		this.length = length;
	}

	// ���ôӵ�k���˿�ʼ����
	public void setK(int k) {
		this.k = k;
	}

	// ���ñ�m�ŵ��˳�Ȧ
	public void setM(int m) {
		this.m = m;
	}

	// ��ʼplay
	public void play() {
		Child temp = this.firstChild;

		// 1.���ҵ���ʼ��������
		for (int i = 1; i < k; i++) {
			temp = temp.nextChild;
		}
		while (this.length != 1) {
			// 2.��m��
			for (int j = 1; j < m; j++) {
				temp = temp.nextChild;
			}

			// �ҵ�Ҫ��Ȧ��ǰһ������
			Child temp2 = temp;
			while (temp2.nextChild != temp) {
				temp2 = temp2.nextChild;
			}

			// 3.������m��С���˳�Ȧ
			temp2.nextChild = temp.nextChild;
			// ��tempָ����һ��������С��
			temp = temp.nextChild;
			
			this.length--;
		}
		// ���һ������
		System.out.println("Final lap:"+temp.number);
	}

	// ��ʼ����������
	public void createdLink() {

		for (int i = 1; i <= length; i++) {
			if (i == 1) {
				// ������һ�� ������
				Child ch = new Child(i);
				this.firstChild = ch;
				this.temp = ch;
			} else if (i == length) {// �������һ������
				Child ch = new Child(i);
				temp.nextChild = ch;
				temp = ch;
				temp.nextChild = this.firstChild;
			} else {
				// ��������С��
				Child ch = new Child(i);
				temp.nextChild = ch;
				temp = ch;// tempָ����һ������
			}
		}
	}

	// ��ӡ��������
	public void show() {
		// ����һ��������
		Child temp = this.firstChild;
		do {
			System.out.print(temp.number+" ");
			temp = temp.nextChild;
		} while (temp != this.firstChild);
		System.out.println();
	}
}