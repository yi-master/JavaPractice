package GUI_Practice;
import java.awt.*; 
 import javax.swing.*; 
 import java.awt.event.*; 

 public class JDialogDemo extends JFrame implements ActionListener{
 public JDialogDemo(){
      Container contentPane=this.getContentPane();
      JButton jButton1=new JButton("��ʾ�Ի���");
      jButton1.addActionListener(this);
      contentPane.add(jButton1);
      this.setTitle("JDialogDemo");
      this.setSize(300,300);
      this.setVisible(true);
      this.setLocationRelativeTo(null);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 }

 /* ��Ӧ����İ�ť�¼� */
 public void actionPerformed(ActionEvent e){
      if(e.getActionCommand().equals("��ʾ�Ի���")){
         HelloDialog hw=new HelloDialog(this);
      }
 }

 /* ���尴ť������ */
 class HelloDialog implements ActionListener{
      JDialog jDialog1=null; //����һ���յĶԻ������
      HelloDialog(JFrame jFrame){
         /* ��ʼ��jDialog1
          * ָ���Ի����ӵ����ΪjFrame,����Ϊ"Dialog",���Ի���Ϊ����ʱ,������������
          * �����û�������(��̬�Ի���) */
         jDialog1=new JDialog(jFrame,"JDialog",true);
      
         //����һ����ť����,�ö�����ӵ��Ի�����
         JButton jButton1=new JButton("�ر�");
         jButton1.addActionListener(this);
    
         //��"�ر�"��ť����������Ի���������
         jDialog1.getContentPane().add(jButton1);

         /* ���öԻ���ĳ�ʼ��С */
         jDialog1.setSize(80,80);
     
         /* ���öԻ����ʼ��ʾ����Ļ���е�λ�� */
         jDialog1.setLocationRelativeTo(null);
    
         /* 
          * ���öԻ���Ϊ�ɼ�(ǰ����������HelloDialog����)
          * �������ʹ��setVisible(true)ʹJDialog�ɼ�
          * ��������
          */
         jDialog1.setVisible(true);
         
         jDialog1.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
         
       }

       //��Ӧ�Ի����еİ�ť�¼�
       public void actionPerformed(ActionEvent e){
          if(e.getActionCommand().equals("�ر�")){
             // �������ȼ���jDialog1.setVisible(false);
             /* public void dispose()
              * �ͷ��ɴ� Window�������������ӵ�е������������ʹ�õ����б�����Ļ��Դ��
              * ����Щ Component ����Դ�����ƻ�������ʹ�õ������ڴ涼�����ص�����ϵͳ��
              * �������Ǳ��Ϊ������ʾ�� */
             jDialog1.dispose();
          }
       }
    }
    public static void main(String[] args){
       JDialogDemo test=new JDialogDemo();
    }
 }