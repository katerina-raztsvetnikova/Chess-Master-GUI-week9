import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.undo.UndoManager;


public class ChessBoard extends JFrame  implements ActionListener{   

   String whitePlayer = " ";
   String blackPlayer = " ";  

   public static JLabel message = new JLabel(" ",SwingConstants.CENTER);
   public static JLabel message1 = new JLabel(" ",SwingConstants.CENTER);      
   public static Buttons newGame = new Buttons("D:\\Programming\\Java-Eclipse\\Chess Master GUI\\Chess Pieces\\326-3265399_new-game-button-start-new-game-button.png"); 
   private UndoManager undoManager = new UndoManager();
   private JMenuBar menuBar = new JMenuBar();
   private JMenu editMenu = new JMenu("Edit");
   private JMenuItem undo = new JMenuItem("Undo");
   private JMenuItem redo = new JMenuItem("Redo");
   public static JLabel whiteScore = new JLabel(Game.whiteScore + "");  
   JLabel whitePlayerName = new JLabel(whitePlayer);    
   public static JLabel blackScore = new JLabel(Game.blackScore + "");  
   JLabel blackPlayerName = new JLabel(blackPlayer);    
   Font font = new Font("Arial", Font.BOLD, 12);
 
   public ChessBoard(){
      initialize();
   }

   public void initialize(){
      this.setSize(800,800);
      this.setTitle("Chess"); 
      this.setLayout(new BorderLayout(10,10)); 

      menuBar.add(editMenu);
      editMenu.add(undo);
      editMenu.add(redo);
      this.setJMenuBar(menuBar);
                          
      JPanel whitePlayerPanel = new JPanel();
      whitePlayerPanel.setLayout(new GridLayout(0,1));      

      JPanel midPanel = new JPanel(new GridLayout(0,2));
      midPanel.add(new JLabel("Name:"));
      midPanel.add(whitePlayerName);
      midPanel.add(new JLabel("Score:"));
      midPanel.add(whiteScore);

      whitePlayerPanel.add(new JLabel("WHITE PLAYER"));
      whitePlayerPanel.add(midPanel);
      message.setVisible(false);
      whitePlayerPanel.add(message);    
      this.add(whitePlayerPanel, BorderLayout.WEST);

      JPanel blackPlayerPanel = new JPanel();
      blackPlayerPanel.setLayout(new GridLayout(0,1));           

      JPanel midPanel2 = new JPanel(new GridLayout(0,2));
      midPanel2.add(new JLabel("Name:"));
      midPanel2.add(blackPlayerName);
      midPanel2.add(new JLabel("Score:"));
      midPanel2.add(blackScore);
      
      blackPlayerPanel.add(new JLabel("BLACK PLAYER"));
      blackPlayerPanel.add(midPanel2);
      message1.setVisible(false);
      blackPlayerPanel.add(message1);
      this.add(blackPlayerPanel,BorderLayout.EAST);
 
      JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
      buttons.add(newGame); 

      newGame.addActionListener(this);
      this.add(buttons,BorderLayout.SOUTH);

      JPanel board = new JPanel();
      board.setLayout(new GridLayout(8,8));
   
      for(int i = 0; i <8; i++) {
         for(int j = 0; j < 8; j++) {
            Game.squares[i][j] = new Buttons(i,j,null);
            if ((j+i)%2 != 0) {
               Game.squares[i][j].setBackground(Color.BLACK);
            } else{
               Game.squares[i][j].setBackground(Color.WHITE);
            }
            board.add(Game.squares[i][j]);
            validate(); 
            Game.squares[i][j].setEnabled(false);  
         }
      }
      
      this.add(board, BorderLayout.CENTER);
                          
      this.setVisible(true);
      this.setLocationRelativeTo(null);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
   }  

   public void actionPerformed(ActionEvent e){   
      JButton button = new JButton();
      button = (JButton)e.getSource();
      whitePlayerName.setFont(font);
      blackPlayerName.setFont(font);
    
       if(button.getIcon().equals(newGame.getIcon())){
       
    	   whitePlayer = JOptionPane.showInputDialog("Input the name of white player: ");
         if(whitePlayer == null || whitePlayer.equals("")){
            whitePlayerName.setText("Player1");
         } else{
            whitePlayerName.setText(whitePlayer);
         }

         blackPlayer = JOptionPane.showInputDialog("Input the name of black player: ");
         if(blackPlayer == null || blackPlayer.equals("")){
            blackPlayerName.setText("Player2");
         }else{
            blackPlayerName.setText(blackPlayer);
         }      
         for(int i = 0; i <8; i++){
            for(int j = 0; j < 8; j++){
               Game.squares[i][j].setEnabled(true);
            }
         }  
         
      }
     
   }
}

