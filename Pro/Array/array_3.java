/*
 * 二维数组
 * 定义
 * 类型 数组名[][]=new 类型[大小][大小]
 * 如：int a[][]=new int[2][3]
 * 不给数组赋值，其默认值为0
 */
package Array;

public class array_3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a[][]=new int[4][6];
		
		//不给数组赋值，其默认值为0
		//行
		for(int i=0;i<4;i++){
			//列
			for(int j=0;j<6;j++){
				System.out.print(a[i][j]+" ");
			}
			System.out.println();
		}
	}

}
