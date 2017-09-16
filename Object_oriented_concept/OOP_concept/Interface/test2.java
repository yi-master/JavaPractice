/*
 * 实现接口可以看做是对继承的一种补充
 * 实现接口可在不打破继承关系的前提下，对某个功能扩展，非常灵活
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