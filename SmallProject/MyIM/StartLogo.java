

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import javax.swing.*;
import java.lang.Runnable;
import java.awt.*;
public class StartLogo extends JWindow implements Runnable {

 JButton jButton = new JButton("Dreamcode Home");
 JPanel panel = new JPanel();
 BorderLayout borderLayout1 = new BorderLayout();

  public StartLogo() {
    try {
     jbInit();
     pack();
   }
   catch(Exception ex) {
     ex.printStackTrace();
   }
   this.setSize(400,300);
  //this.setLocation(260,200);
  Toolkit kit = Toolkit.getDefaultToolkit();
  Dimension screenSize = kit.getScreenSize();
  setLocation((screenSize.width-320)/2,(screenSize.height-240)/2);
  }
  private void jbInit() throws Exception {
    panel.setLayout(borderLayout1);
    jButton.setIcon(new ImageIcon(StartLogo.class.getResource("SystemImage/frogIcon.gif")));
    getContentPane().add(panel);
    panel.add(jButton, BorderLayout.CENTER);

  }
  public void run() {
    /**@todo Implement this java.lang.Runnable method*/
    //throw new java.lang.UnsupportedOperationException("Method run() not yet implemented.");
    this.toFront();
    this.setVisible(true);
    try {
      Thread.sleep(500);
    }
    catch (InterruptedException ex) {
    }
   this.setVisible(false);
  }
}
