package Tank_4;

import java.io.*;
import java.util.*;
import javax.sound.sampled.*;

public class Members {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

// ���ô��ڵĴ�С
class WindowSize {

	int wide = 600, high = 500;

	public int getWide() {
		return wide;
	}

	public void setWide(int wide) {
		this.wide = wide;
	}

	public int getHigh() {
		return high;
	}

	public void setHigh(int high) {
		this.high = high;
	}

}

// ��Ч��
class AePlayWave extends Thread {

	private String filename;

	public AePlayWave(String wavfile) {
		filename = wavfile;

	}

	public void run() {

		File soundFile = new File(filename);

		AudioInputStream audioInputStream = null;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(soundFile);
		} catch (Exception e1) {
			e1.printStackTrace();
			return;
		}

		AudioFormat format = audioInputStream.getFormat();
		SourceDataLine auline = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

		try {
			auline = (SourceDataLine) AudioSystem.getLine(info);
			auline.open(format);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		auline.start();
		int nBytesRead = 0;
		// ���ǻ���
		byte[] abData = new byte[512];

		try {
			while (nBytesRead != -1) {
				nBytesRead = audioInputStream.read(abData, 0, abData.length);
				if (nBytesRead >= 0)
					auline.write(abData, 0, nBytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} finally {
			auline.drain();
			auline.close();
		}

	}

}

// ��¼�����ͷ���
class Node {
	int x, y;
	int direct;

	public Node(int x, int y, int direct) {
		this.x = x;
		this.y = y;
		this.direct = direct;
	}
}

// ��¼��(�ɱ����������)
class Recorder {

	// ��¼ÿ���ж��ٵ���
	private static int enemyNumber = 20;
	// ������ĵ�������
	private static int destroyEnemy = 0;
	// �����ҵ�����
	private static int myLife = 3;// ���������

	// ���ļ��лָ���¼��
	static Vector<Node> nodes = new Vector<Node>();

	private static FileWriter fw = null;
	private static BufferedWriter bw = null;

	private static FileReader fr = null;
	private static BufferedReader br = null;

	private Vector<EnemyTank> ets = new Vector<EnemyTank>();

	public Vector<EnemyTank> getEts() {
		return ets;
	}

	public void setEts(Vector<EnemyTank> ets) {
		this.ets = ets;
	}

	// ��ȡ��¼
	public Vector<Node> getNodesAndEnemeyNumber() {
		try {
			fr = new FileReader("E:/123.txt");
			br = new BufferedReader(fr);
			// ��ȡ��һ��
			String n = br.readLine();
			// ����һ�к����n��
			while ((n = br.readLine()) != null) {
				// ���ݿո񷵻����飨��ÿ����һ���ո�;ͽ��ո�ǰ������ݷ������飩
				String xyz[] = n.split(" ");

				Node node = new Node(Integer.parseInt(xyz[0]), Integer.parseInt(xyz[1]), Integer.parseInt(xyz[2]));
				nodes.add(node);
			}
			destroyEnemy = Integer.parseInt(n);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return nodes;
	}

	// ������ٵ������������̹������ͷ���
	public void keepRecordEnemy() {
		try {
			fw = new FileWriter("E:/123.txt");
			bw = new BufferedWriter(fw);

			bw.write(destroyEnemy + "\r\n");

			// ���浱ǰ�����ŵĵ���̹������ͷ���
			for (int i = 0; i < ets.size(); i++) {
				// ȡ����i��̹��
				EnemyTank et = ets.get(i);
				if (et.isLive) {
					String recode = et.x + " " + et.y + " " + et.direct;

					// д��
					bw.write(recode + "\r\n");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// �ر��������ȹأ�
			try {
				bw.close();
				fw.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}

	// ���ļ��ж�ȡ����̹�����������ļ�¼
	public static void getRecording() {
		try {
			fr = new FileReader("E:/123.txt");
			br = new BufferedReader(fr);

			String n = br.readLine();
			destroyEnemy = Integer.parseInt(n);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}

	// ������һ��ٵ���̹����
	public static void keepRecording() {
		try {
			fw = new FileWriter("E:/123.txt");
			bw = new BufferedWriter(fw);

			bw.write(destroyEnemy + "\r\n");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// �ر��������ȹأ�
			try {
				bw.close();
				fw.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}

	public static int getDestroyEnemy() {
		return destroyEnemy;
	}

	public static void setDestroyEnemy(int destroyEnemy) {
		Recorder.destroyEnemy = destroyEnemy;
	}

	public static int getEnemyNumber() {
		return enemyNumber;
	}

	public static void setEnemyNumber(int enemyNumber) {
		Recorder.enemyNumber = enemyNumber;
	}

	public static int getMyLife() {
		return myLife;
	}

	public static void setMyLife(int myLife) {
		Recorder.myLife = myLife;
	}

	// ����̹���������͵���̹�˱���������
	public static void reduceEnemyNumber() {

		--enemyNumber;
		++destroyEnemy;
	}

}

// �ӵ���ը��
class Bomb {

	// ����ը����
	int x, y;
	int life = 9;// �ӵ���ը��������������ͼƬ���
	boolean isLive = true;

	public Bomb(int x, int y) {

		this.x = x;
		this.y = y;
	}

	// ��������ֵ
	public void lifeDown() {
		if (life > 0) {
			life--;
		} else {
			this.isLive = false;
		}
	}
}

// �ӵ���
class Shot implements Runnable {

	WindowSize ws = new WindowSize();

	int x;
	int y;
	int direct;
	int speed = 2;// �ӵ���ʼ�ٶ�Ϊ1
	// �ӵ��Ƿ񻹻���
	boolean isLive = true;

	public Shot(int x, int y, int direct) {
		this.x = x;
		this.y = y;
		this.direct = direct;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {

			try {

				Thread.sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
			}
			switch (direct) {

			case 0:// �ӵ�����
				y -= speed;
				break;
			case 1:// �ӵ�����
				x += speed;
				break;
			case 2:// �ӵ�����
				y += speed;
				break;
			case 3:// �ӵ�����
				x -= speed;
				break;
			}
			// ����ӵ�����
			System.out.println("shot: x=" + x + "  y=" + y);

			// �ӵ��߳�����
			// �ж��ӵ��Ƿ�������Ե
			if (x < 0 || x > ws.getWide() - 150 || y < 0 || y > ws.getHigh() - 170) {
				this.isLive = false;
				break;
			}
		}
	}
}

// ̹����
class Tank {

	// ̹�˵ĺ�����
	int x = 0;
	// ̹�˵�������
	int y = 0;
	// ̹�˵ķ���(0��ʾ���ϣ�1��ʾ�ң�2��ʾ�£�3��ʾ��)
	int direct = 0;// ��ʼ��������
	// ̹�˵��ٶ�(��ʼ�ٶ�Ϊ1)
	int speed = 1;
	// ̹�˵�����
	int type = 0;
	// �ж�̹���Ƿ���
	boolean isLive = true;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getDirect() {
		return direct;
	}

	public void setDirect(int direct) {
		this.direct = direct;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Tank(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

// �ҵ�̹��
class Hero extends Tank {

	// �ӵ�
	Vector<Shot> ss = new Vector<Shot>();
	Shot s = null;

	public Hero(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	// �����ӵ�
	public void shotEnemy() {

		switch (this.direct) {

		case 0:// ̹�˷�����
				// ����һ���ӵ�
			s = new Shot(x + 10, y, 0);
			// ���ӵ���������
			ss.add(s);
			break;
		case 1:// ̹�˷�����
			s = new Shot(x + 30, y + 10, 1);
			ss.add(s);
			break;
		case 2:// ̹�˷�����
			s = new Shot(x + 10, y + 30, 2);
			ss.add(s);
			break;
		case 3:// ̹�˷�����
			s = new Shot(x, y + 10, 3);
			ss.add(s);
			break;
		}
		// �����ӵ��߳�
		Thread t = new Thread(s);
		t.start();
	}

	// ̹�������ƶ�
	public void moveUp() {
		y -= speed;
	}

	// ̹�������ƶ�
	public void moveRight() {
		x += speed;
	}

	// ̹�������ƶ�
	public void moveDown() {
		y += speed;
	}

	// ̹�������ƶ�
	public void moveLeft() {
		x -= speed;
	}
}

// ���˵�̹�ˣ�����̹�������̣߳�
class EnemyTank extends Tank implements Runnable {

	WindowSize ws = null;

	int times = 0;// �����ۻ��˱���
	// ������������ŵ��˵��ӵ�
	Vector<Shot> ss = new Vector<Shot>();
	// ��������ӵ����ڸոմ�������̹�˺͵���̹���ӵ�������

	// ����һ�����������Է���MyPanel�����е���̹��
	Vector<EnemyTank> ets = new Vector<EnemyTank>();

	public EnemyTank(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		ws = new WindowSize();
	}

	// �õ�MyPanel�ĵ���̹������
	public void setEts(Vector<EnemyTank> ets) {

		this.ets = ets;
	}

	// �ж��Ƿ���ײ����������̹��
	public boolean isTouchOhterEnemy() {

		boolean b = false;// false��ʾδ������ײ,true��ʾ������ײ

		switch (this.direct) {

		case 0:
			// ̹������
			// ȡ�����е���̹��
			for (int i = 0; i < ets.size(); i++) {

				// ȡ����i��̹��
				EnemyTank et = ets.get(i);
				// (�����thisָ����et�Լ�)
				if (et != this) {// ��ȡ����̹�˲����Լ�

					if (et.direct == 0 || et.direct == 2) {// ������˷������ϻ�����

						// �жϵ�ǰ̹�����Ͻǵĵ�����������ж�̹�˵��������ǵĶ��������ĺ��������ϵ
						if (this.x >= et.x && this.x <= et.x + 20 && this.y >= et.y && this.y <= et.y + 30) {
							return true;
						}
						// �жϵ�ǰ̹�����Ͻǵĵ�����������ж�̹�˵��������ǵĶ��������ĺ��������ϵ
						if (this.x + 20 >= et.x && this.x + 20 <= et.x + 20 && this.y >= et.y && this.y <= et.y + 30) {
							return true;
						}
					}
					if (et.direct == 1 || et.direct == 3) {// ������˷������һ�����

						// �жϵ�ǰ̹�����Ͻǵĵ�����������ж�̹�˵��������ǵĶ��������ĺ��������ϵ
						if (this.x >= et.x && this.x <= et.x + 30 && this.y >= et.y && this.y <= et.y + 20) {
							return true;
						}
						// �жϵ�ǰ̹�����Ͻǵĵ�����������ж�̹�˵��������ǵĶ��������ĺ��������ϵ
						if (this.x + 20 >= et.x && this.x + 20 <= et.x + 30 && this.y >= et.y && this.y <= et.y + 20) {
							return true;
						}
					}
				}
			}
			break;
		case 1:
			// ̹������
			// ȡ�����е���̹��
			for (int i = 0; i < ets.size(); i++) {

				// ȡ����i��̹��
				EnemyTank et = ets.get(i);
				// (�����thisָ����et�Լ�)
				if (et != this) {// ��ȡ����̹�˲����Լ�

					if (et.direct == 0 || et.direct == 2) {// ������˷������ϻ�����

						// �жϵ�ǰ̹�����Ͻǵĵ�����������ж�̹�˵��������ǵĶ��������ĺ��������ϵ
						if (this.x + 30 >= et.x && this.x + 30 <= et.x + 20 && this.y >= et.y && this.y <= et.y + 30) {
							return true;
						}
						// �жϵ�ǰ̹�����½ǵĵ�����������ж�̹�˵��������ǵĶ��������ĺ��������ϵ
						if (this.x + 30 >= et.x && this.x + 30 <= et.x + 20 && this.y + 20 >= et.y
								&& this.y + 20 <= et.y + 30) {
							return true;
						}
					}
					if (et.direct == 1 || et.direct == 3) {// ������˷������һ�����

						// �жϵ�ǰ̹�����Ͻǵĵ�����������ж�̹�˵��������ǵĶ��������ĺ��������ϵ
						if (this.x + 30 >= et.x && this.x + 30 <= et.x + 30 && this.y >= et.y && this.y <= et.y + 20) {
							return true;
						}
						// �жϵ�ǰ̹�����½ǵĵ�����������ж�̹�˵��������ǵĶ��������ĺ��������ϵ
						if (this.x + 30 >= et.x && this.x + 30 <= et.x + 30 && this.y + 20 >= et.y
								&& this.y + 20 <= et.y + 20) {
							return true;
						}
					}
				}
			}
			break;
		case 2:
			// ̹������
			// ȡ�����е���̹��
			for (int i = 0; i < ets.size(); i++) {

				// ȡ����i��̹��
				EnemyTank et = ets.get(i);
				// (�����thisָ����et�Լ�)
				if (et != this) {// ��ȡ����̹�˲����Լ�

					if (et.direct == 0 || et.direct == 2) {// ������˷������ϻ�����

						// �жϵ�ǰ̹�����Ͻǵĵ�����������ж�̹�˵��������ǵĶ��������ĺ��������ϵ
						if (this.x >= et.x && this.x <= et.x + 20 && this.y + 30 >= et.y && this.y + 30 <= et.y + 30) {
							return true;
						}
						// �жϵ�ǰ̹�����Ͻǵĵ�����������ж�̹�˵��������ǵĶ��������ĺ��������ϵ
						if (this.x + 20 >= et.x && this.x + 20 <= et.x + 20 && this.y + 30 >= et.y
								&& this.y + 30 <= et.y + 30) {
							return true;
						}
					}
					if (et.direct == 1 || et.direct == 3) {// ������˷������һ�����

						// �жϵ�ǰ̹�����½ǵĵ�����������ж�̹�˵��������ǵĶ��������ĺ��������ϵ
						if (this.x >= et.x && this.x <= et.x + 30 && this.y + 30 >= et.y && this.y + 30 <= et.y + 20) {
							return true;
						}
						// �жϵ�ǰ̹�����½ǵĵ�����������ж�̹�˵��������ǵĶ��������ĺ��������ϵ
						if (this.x + 20 >= et.x && this.x + 20 <= et.x + 30 && this.y + 30 >= et.y
								&& this.y + 30 <= et.y + 20) {
							return true;
						}
					}
				}
			}
			break;
		case 3:
			// ̹������
			// ȡ�����е���̹��
			for (int i = 0; i < ets.size(); i++) {

				// ȡ����i��̹��
				EnemyTank et = ets.get(i);
				// (�����thisָ����et�Լ�)
				if (et != this) {// ��ȡ����̹�˲����Լ�

					if (et.direct == 0 || et.direct == 2) {// ������˷������ϻ�����

						// �жϵ�ǰ̹�����Ͻǵĵ�����������ж�̹�˵��������ǵĶ��������ĺ��������ϵ
						if (this.x >= et.x && this.x <= et.x + 20 && this.y >= et.y && this.y <= et.y + 30) {
							return true;
						}
						// �жϵ�ǰ̹�����½ǵĵ�����������ж�̹�˵��������ǵĶ��������ĺ��������ϵ
						if (this.x >= et.x && this.x <= et.x + 20 && this.y + 20 >= et.y && this.y + 20 <= et.y + 30) {
							return true;
						}
					}
					if (et.direct == 1 || et.direct == 3) {// ������˷������һ�����

						// �жϵ�ǰ̹�����Ͻǵĵ�����������ж�̹�˵��������ǵĶ��������ĺ��������ϵ
						if (this.x >= et.x && this.x <= et.x + 30 && this.y >= et.y && this.y + 20 <= et.y + 20) {
							return true;
						}
						// �жϵ�ǰ̹�����½ǵĵ�����������ж�̹�˵��������ǵĶ��������ĺ��������ϵ
						if (this.x >= et.x && this.x <= et.x + 30 && this.y + 20 >= et.y && this.y + 20 <= et.y + 20) {
							return true;
						}
					}
				}
			}
			break;
		}

		return b;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// ̹�˵��ƶ���Χ��ս�����Ĵ�С�й�
		while (true) {

			switch (this.direct) {

			case 0:// ����̹������������
				for (int i = 0; i < 30; i++) {
					if (y > 0 && this.isTouchOhterEnemy() == false) {// ��ֹ̹��Խ�磬��֤̹����ָ����Χ���ƶ�
						y -= speed;
					}
					try {
						Thread.sleep(50);// 30*50=1500ms=1.5s
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				break;
			case 1:// ����̹������������
				for (int i = 0; i < 30; i++) {
					if ((x < (ws.getWide() - 180)) && this.isTouchOhterEnemy() == false) {// ��ֹ̹��Խ�磬��֤̹����ָ����Χ���ƶ�
						x += speed;
					}
					try {
						Thread.sleep(50);// 30*50=1500ms=1.5s
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				break;
			case 2:// ����̹������������
				for (int i = 0; i < 30; i++) {
					if ((y < (ws.getHigh() - 200)) && this.isTouchOhterEnemy() == false) {// ��ֹ̹��Խ�磬��֤̹����ָ����Χ���ƶ�
						y += speed;
					}
					try {
						Thread.sleep(50);// 30*50=1500ms=1.5s
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				break;
			case 3:// ����̹������������
				for (int i = 0; i < 30; i++) {
					if (x > 0 && this.isTouchOhterEnemy() == false) {// ��ֹ̹��Խ�磬��֤̹����ָ����Χ���ƶ�
						x -= speed;
					}
					try {
						Thread.sleep(50);// 30*50=1500ms=1.5s
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				break;
			}

			this.times++;// ÿ��1.5s��1

			// �Ƿ���Ҫ��̹�˼����µ��ӵ�
			if (times % 2 == 0) {// ÿ��1.5s��һ���ӵ�

				// �ж��ӵ��Ƿ����
				if (isLive == true) {
					if (ss.size() < 5) {// �з��ӵ��������5��

						Shot s = null;
						// û���ӵ�(���)
						switch (direct) {

						case 0:// ̹�˷�����
								// ����һ���ӵ�
							s = new Shot(x + 10, y, 0);
							// ���ӵ���������
							ss.add(s);
							break;
						case 1:// ̹�˷�����
							s = new Shot(x + 30, y + 10, 1);
							ss.add(s);
							break;
						case 2:// ̹�˷�����
							s = new Shot(x + 10, y + 30, 2);
							ss.add(s);
							break;
						case 3:// ̹�˷�����
							s = new Shot(x, y + 10, 3);
							ss.add(s);
							break;
						}

						// �����ӵ��߳�
						Thread t = new Thread(s);
						t.start();

					}
				}
			}
			// ��̹���������һ���µķ���
			// this.direct=(int)(Math.random()*10)%4;
			this.direct = (int) (Math.random() * 4);

			// �жϵ���̹���Ƿ�����
			if (this.isLive == false) {
				// ��̹���������˳��߳�
				break;
			}

		}
	}

}
