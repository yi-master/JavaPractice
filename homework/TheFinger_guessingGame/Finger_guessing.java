package TheFinger_guessingGame;

import java.util.*;

public class Finger_guessing {

	static Scanner scanner;
	static int p;
	static int x;
	static int count = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Finger_guessing fg = new Finger_guessing();
		scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Please input 0:stone,1:scissors,2:cloth,3:game over.");
			p = scanner.nextInt() % 4;
			if (p != 3) {
				fg.Guess();
				fg.Judge();
			} else {
				System.out.println("Game Over");
				System.out.println("The player won the " + count + " game");
				break;
			}
		}

	}

	public void Guess() {
		x = (int) (Math.random() * 10 % 3);

	}

	public void Judge() {
		if (p == x) {
			System.out.println("It ends in a draw");
		} else if ((p == 0 && x == 1) || (p == 1 && x == 2) || (p == 2 && x == 0)) {
			System.out.println("Player wins");
			count++;
		} else if ((x == 0 && p == 1) || (x == 1 && p == 2) || (x == 2 && p == 0)) {
			System.out.println("Player lose");
		}

		if (x == 0) {// 电脑出石头
			System.out.println("The computer shows stone.");
		} else if (x == 1) {// 电脑出剪刀
			System.out.println("The computer shows scissors.");
		} else if (x == 2) {// 电脑出布
			System.out.println("The computer shows cloth.");
		}

	}

}