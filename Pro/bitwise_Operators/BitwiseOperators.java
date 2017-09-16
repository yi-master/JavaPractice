/*
 * 各种按位运算符的运算演示
 * 
 * 原码、反码、补码：
 * 1.二进制的最高位是符号位（0表示正数，1表示负数）
 * 2.正数的原码，反码，补码都一样
 * 3.负数的反码=它原码符号位不变，其他位取反
 * 4.负数的补码=它的反码+1
 * 5.Java中的数都是有符号的
 * 6.在计算机运算时，都是以补码得方式来运算的
 * 
 * ※运算步骤
 * 1.求原码
 * 2.求补码
 * 3.用以求的补码根据运算符运算规则进行运算
 * 4.对运算结果再次进行求补码得最终解
 * 
 * 移位运算符
 * 算术右移(>>):低位溢出，符号位不变，并用符号位补溢出的高位
 * 算术左移(<<):符号位不变，低位补零
 * 逻辑右移(>>>):低位溢出，高位补零
 */
package bitwise_Operators;

public class BitwiseOperators {
	public static void main(String[] args) {
		System.out.println("~2="+(~2));
		System.out.println("2&3="+(2&3));
		System.out.println("2|3="+(2|3));
		System.out.println("~-5="+(~-5));
		System.out.println("-3^3="+(-3^3));
	
		System.out.println("1>>2="+(1>>2));
		System.out.println("-1>>2="+(-1>>2));
		System.out.println("1<<2="+(1<<2));
		System.out.println("-1<<2="+(-1<<2));
		System.out.println("3>>>2="+(3>>>1));
	}
}
