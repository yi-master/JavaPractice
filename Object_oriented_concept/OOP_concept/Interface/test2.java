/*
 * ʵ�ֽӿڿ��Կ����ǶԼ̳е�һ�ֲ���
 * ʵ�ֽӿڿ��ڲ����Ƽ̳й�ϵ��ǰ���£���ĳ��������չ���ǳ����
 */

package Interface;

public class test2 {
	public static void main(String[] args) {
		LittleMonkey lm=new LittleMonkey();
	}
}

interface Fish{
	public void swimming();
}

interface Bird{
	public void Fly();
}
class Monkey{
	int name;
	public void jump(){
		System.out.println("Monkey can jump");
	}
}
class LittleMonkey extends Monkey implements Fish,Bird{

	@Override
	public void swimming() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Fly() {
		// TODO Auto-generated method stub
		
	}
	
}