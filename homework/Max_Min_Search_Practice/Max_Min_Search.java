/*
 * 跳水比赛
 * 8个评委打分
 * 运动员成绩是8个成绩去掉一个最高分和最低分
 * 剩下的6个分数的平均分就是最后得分
 * 找出打最高分和最低分的评委
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

	// 定义一个可以存放8个小数的数组
	int size = 4;
	float grades[] = null;

	// 构造函数
	public Judge() {
		grades = new float[size];

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {

			for (int i = 0; i < grades.length; i++) {// 循环输入每个裁判的评分

				System.out.println("Please enter " + (i + 1) + "th referee's score");
				grades[i] = Float.parseFloat(br.readLine());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				// 关闭流
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// 1.去掉最低分（找到最低分的下标）
	public int getMinGradeIndex() {

		// 选择法
		// 认为第一个是最低分
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

	// 2.去掉最高分（找到最高分的下标）
	public int getMaxGradeIndex() {

		// 选择法
		// 认为第一个是最高分
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

	// 3.得到运动员的最后得分
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

	// 找出打最高分和最低分的评委
	public void Find() {
		int minJudge = this.getMinGradeIndex();
		int maxJudge = this.getMaxGradeIndex();
		System.out.println("The judges who played the highest points: " + (maxJudge + 1) + "th"
				+ "\nThe judges who played the lowest points: " + (minJudge + 1) + "th");
	}

	// 找出最好的评委
	public void BestJudge() {
		float lastGrade = this.lastGrade();
		// 假设第一个评委是最好的
		int bestIndex = 0;
		float bestGrade = Math.abs(grades[0] - lastGrade);// 与最终成绩的差值
		float tempGrade = 0f;

		for (int i = 1; i < grades.length; i++) {// 遍历所有与最终成绩的差值

			tempGrade = Math.abs(grades[i] - lastGrade);
			if (bestGrade > tempGrade) {

				bestIndex = i;
				bestGrade = tempGrade; // 把差值小的赋给差值大的
			}
		}
		System.out.println("The best judge " + (bestIndex + 1) + "th");
	}

}
