package Other;

public class StringComparison {
	public static void main(String[] args) {

		// �������ָ�ֵ��ʽֻ����euqals()�����Ƚ������Ƿ����
		String a = new String("abc");//a��ջ�У���abc���ڶ���
		String b = new String("abc");//b��ջ�У���abc���ڶ���

		// ���������ָ�ֵ��ʽ��euqals()������������==�Ƚ������Ƿ����
		//�������ָ�ֵ��ʽ���ڶ�������һ���ַ���ָ����С�abc���Ŀռ�
		//����ֵ������ͬʱ�����ַ���������û��ָ��abc��������
		// String a="abc";
		// String b="abc";

		// equals()�����Ƚ������ַ����������Ƿ����
		// ��==�ǱȽ������ַ����ĵ�ַ�Ƿ����
		if (a.equals(b)) {// if(a==b)
			System.out.println("ok");
		}
		//.equalsIgnoreCase()���������ִ�Сд�ж��ַ����Ƿ����
	}
}
