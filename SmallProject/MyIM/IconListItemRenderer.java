import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;

public class IconListItemRenderer extends JLabel implements ListCellRenderer
{
	private MyIM p2pSys;
	private Border
    selectedBorder = BorderFactory.createLineBorder(Color.orange,1),
    emptyBorder = BorderFactory.createEmptyBorder(1,1,1,1);

	public IconListItemRenderer(MyIM p2pSys)
	{
		 this.p2pSys =p2pSys;
	}
	
	public Component getListCellRendererComponent( 
			JList list,
            Object value,
            int index,
            boolean isSelected, 
            boolean cellHasFocus) 
	{
		// TODO Auto-generated method stub
		 
		
		IconListItem item = (IconListItem)value;
        this.setIcon(item.getIcon());
        this.setText(item.getText());
        if (isSelected) 
        {
           setBackground(list.getSelectionBackground());
	       setForeground(list.getSelectionForeground());
	       setBorder (selectedBorder);

	       p2pSys.selectip = item.getText();//返回选中对象文本
	       p2pSys.selecticon= (ImageIcon) item.getIcon();//返回选中对象图像
	              	       
	    }
        else 
        {
	       setBackground(list.getBackground());
	       setForeground(list.getForeground());
	       setBorder(emptyBorder);

	    }
       
	    setEnabled(list.isEnabled());
	    setFont(list.getFont());
        setOpaque(true);
        return this;
	}
}
