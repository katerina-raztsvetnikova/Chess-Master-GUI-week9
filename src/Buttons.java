import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Buttons extends JButton{

   int a;
   int b; 
   String color;
  
   public Buttons(String icon)
   {
      super();
      this.setIcon(new ImageIcon(icon));
      this.setBorderPainted(false);
      this.setContentAreaFilled(false);
   }
   public Buttons(int a, int b, Icon newIcon)
   {
      super(); 
      this.a = a; 
      this.b = b; 
      this.setIcon(newIcon);
   }

   public int getA()
   {
      return a; 
   }
   public int getB()
   {
      return b; 
   }
}
