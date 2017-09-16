/*
 * ��ˮ����
 * 8����ί���
 * �˶�Ա�ɼ���8���ɼ�ȥ��һ����߷ֺ���ͷ�
 * ʣ�µ�6��������ƽ���־������÷�
 * �ҳ�����߷ֺ���ͷֵ���ί
 */
package Max_Min_Search_Practice;

import java.io.*;

public class Max_Min_Search {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Judge judge = new Judge();
		System.out.println("Final results: " + judge.lastGrade());
		judge.Find();
		judge.BestJudge();
	}

}

class Judge {

	// ����һ�����Դ��8��С��������
	int size = 4;
	float grades[] = null;

	// ���캯��
	public Judge() {
		grades = new float[size];

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {

			for (int i = 0; i < grades.length; i++) {// ѭ������ÿ�����е�����

				System.out.println("Please enter " + (i + 1) + "th referee's score");
				grades[i] = Float.parseFloat(br.readLine());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				// �ر���
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// 1.ȥ����ͷ֣��ҵ���ͷֵ��±꣩
	public int getMinGradeIndex() {

		// ѡ��
		// ��Ϊ��һ������ͷ�
		float minGrade = grades[0];
		int minIndex = 0;

		for (int i = 1; i < grades.length; i++) {

			if (grades[i] < minGrade) {

				minIndex = i;
				minGrade = grades[i];
			}
		}
		return minIndex;
	}

	// 2.ȥ����߷֣��ҵ���߷ֵ��±꣩
	public int getMaxGradeIndex() {

		// ѡ��
		// ��Ϊ��һ������߷�
		float maxGrade = grades[0];
		int maxIndex = 0;

		for (int i = 1; i < grades.length; i++) {

			if (grades[i] > maxGrade) {

				maxIndex = i;
				maxGrade = grades[i];
			}
		}
		return maxIndex;
	}

	// 3.�õ��˶�Ա�����÷�
	public float lastGrade() {

		float total = 0;
		int minIndex = this.getMinGradeIndex();
		int maxIndex = this.getMaxGradeIndex();

		for (int i = 0; i < grades.length; i++) {

			if (i != minIndex && i != maxIndex) {

				total += grades[i];
			}
		}
		return total / (grades.length - 2);
	}

	// �ҳ�����߷ֺ���ͷֵ���ί
	public void Find() {
		int minJudge = this.getMinGradeIndex();
		int maxJudge = this.getMaxGradeIndex();
		System.out.println("The judges who played the highest points: " + (maxJudge + 1) + "th"
				+ "\nThe judges who played the lowest points: " + (minJudge + 1) + "th");
	}

	// �ҳ���õ���ί
	public void BestJudge() {
		float lastGrade = this.lastGrade();
		// �����һ����ί����õ�
		int bestIndex = 0;
		float bestGrade = Math.abs(grades[0] - lastGrade);// �����ճɼ��Ĳ�ֵ
		float tempGrade = 0f;

		for (int i = 1; i < grades.length; i++) {// �������������ճɼ��Ĳ�ֵ

			tempGrade = Math.abs(grades[i] - lastGrade);
			if (bestGrade > tempGrade) {

				bestIndex = i;
				bestGrade = tempGrade; // �Ѳ�ֵС�ĸ�����ֵ���
			}
		}
		System.out.println("The best judge " + (bestIndex + 1) + "th");
	}

}
