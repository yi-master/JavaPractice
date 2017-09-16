package Other;

public class StringComparison {
	public static void main(String[] args) {

		// 下面这种赋值方式只能用euqals()方法比较内容是否相等
		String a = new String("abc");//a在栈中，“abc”在堆中
		String b = new String("abc");//b在栈中，“abc”在堆中

		// 下面这种种赋值方式既euqals()方法，可以用==比较内容是否相等
		//下面这种赋值方式会在堆中增加一块字符池指向堆中“abc”的空间
		//当赋值内容相同时，在字符池中找有没有指向“abc”的引用
		// String a="abc";
		// String b="abc";

		// equals()方法比较两个字符串的内容是否相等
		// 而==是比较两个字符串的地址是否相等
		if (a.equals(b)) {// if(a==b)
			System.out.println("ok");
		}
		//.equalsIgnoreCase()方法不区分大小写判断字符串是否相等
	}
}
