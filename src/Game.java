import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class Game extends JFrame{

	  Stack oldStack;
	  Stack newStack;

	public static ImageIcon blackPawn = new ImageIcon("D:\\Programming\\Java-Eclipse\\Chess Master GUI\\Chess Pieces\\75px-Chess_pdt45.svg.png");
	public static ImageIcon blackRook = new ImageIcon("D:\\Programming\\Java-Eclipse\\Chess Master GUI\\Chess Pieces\\75px-Chess_rdt45.svg.png");
	public static ImageIcon blackBishop = new ImageIcon("D:\\Programming\\Java-Eclipse\\Chess Master GUI\\Chess Pieces\\75px-Chess_bdt45.svg.png");
	public static ImageIcon blackKnight = new ImageIcon("D:\\Programming\\Java-Eclipse\\Chess Master GUI\\Chess Pieces\\75px-Chess_ndt45.svg.png");
	public static ImageIcon blackQueen = new ImageIcon("D:\\Programming\\Java-Eclipse\\Chess Master GUI\\Chess Pieces\\75px-Chess_qdt45.svg.png");
	public static ImageIcon blackKing = new ImageIcon("D:\\Programming\\Java-Eclipse\\Chess Master GUI\\Chess Pieces\\75px-Chess_kdt45.svg.png");
	
	public static ImageIcon whitePawn = new ImageIcon("D:\\Programming\\Java-Eclipse\\Chess Master GUI\\Chess Pieces\\Chess_plt45.svg.png");
	public static ImageIcon whiteRook = new ImageIcon("D:\\Programming\\Java-Eclipse\\Chess Master GUI\\Chess Pieces\\75px-Chess_rlt45.svg.png");
	public static ImageIcon whiteBishop = new ImageIcon("D:\\Programming\\Java-Eclipse\\Chess Master GUI\\Chess Pieces\\Chess_blt45.svg.png");
	public static ImageIcon whiteKnight = new ImageIcon("D:\\Programming\\Java-Eclipse\\Chess Master GUI\\Chess Pieces\\75px-Chess_nlt45.svg.png");
	public static ImageIcon whiteQueen = new ImageIcon("D:\\Programming\\Java-Eclipse\\Chess Master GUI\\Chess Pieces\\75px-Chess_qlt45.svg.png");
	public static ImageIcon whiteKing = new ImageIcon("D:\\Programming\\Java-Eclipse\\Chess Master GUI\\Chess Pieces\\Chess_klt45.svg.png");
	
   public static Icon icon = null;
  
   public static int previousX = -1;
   public static int previousY = -1;
   public static int nextX = -1;
   public static int nextY = -1;
  
   public Buttons new1 = null; 
   public Buttons new2 = null;
   public Buttons new3 = null;
   
   public static int whiteScore = 0;  
   public static int blackScore = 0;
  
   public final static Buttons[][] squares = new Buttons[8][8];
   Font font = new Font("Arial", Font.BOLD, 16);
  
   public Game(){  
      new ChessBoard();
      for(int i = 0; i<8; i++){
         for(int j = 0; j<8; j++){
        	 squares[0][0].setIcon(blackRook);
             squares[0][1].setIcon(blackKnight);
             squares[0][2].setIcon(blackBishop);
             squares[0][3].setIcon(blackQueen);
             squares[0][4].setIcon(blackKing);
             squares[0][5].setIcon(blackBishop);
             squares[0][6].setIcon(blackKnight);
             squares[0][7].setIcon(blackRook);
             squares[1][0].setIcon(blackPawn);
             squares[1][1].setIcon(blackPawn);
             squares[1][2].setIcon(blackPawn);
             squares[1][3].setIcon(blackPawn);
             squares[1][4].setIcon(blackPawn);
             squares[1][5].setIcon(blackPawn);
             squares[1][6].setIcon(blackPawn);
             squares[1][7].setIcon(blackPawn);
             
             squares[6][0].setIcon(whitePawn);
             squares[6][1].setIcon(whitePawn);
             squares[6][2].setIcon(whitePawn);
             squares[6][3].setIcon(whitePawn);
             squares[6][4].setIcon(whitePawn);
             squares[6][5].setIcon(whitePawn);
             squares[6][6].setIcon(whitePawn);
             squares[6][7].setIcon(whitePawn);
             squares[7][0].setIcon(whiteRook);
             squares[7][1].setIcon(whiteKnight);
             squares[7][2].setIcon(whiteBishop);
             squares[7][3].setIcon(whiteQueen);
             squares[7][4].setIcon(whiteKing);
             squares[7][5].setIcon(whiteBishop);
             squares[7][6].setIcon(whiteKnight);
             squares[7][7].setIcon(whiteRook);
            System.out.println(squares[i][j].getA() + " " + squares[i][j].getB() +" "+ squares[i][j].getIcon());
            squares[i][j].addActionListener(new CheckerHandler(i,j));
            oldStack = new Stack();
         }
      }
      ChessBoard.newGame.requestFocus();
      ChessBoard.whiteScore.setFont(font);
      ChessBoard.blackScore.setFont(font);    
    }
  
    public class CheckerHandler implements ActionListener{
     
      private int x = -1;
      private int y = -1;
    
      public CheckerHandler(int newX, int newY){
         x = newX;
         y = newY;
      }
          
      public void actionPerformed(ActionEvent e) {
         if(previousX <0){       
        	 previousX = x;
        	 previousY = y;
    
            if(squares[x][y].getIcon() == null){
               JOptionPane.showMessageDialog(squares[x][y],"Invalid click!");
               reset();
               return;
            }
            if(icon == null){
               icon = squares[x][y].getIcon();
            }
            if(!icon.equals(squares[x][y].getIcon())){
               JOptionPane.showMessageDialog(null,"It's not your turn!");
               reset();
               return;
            }
              
               rotateTurn();              
            }
            else if(previousX >= 0){
            	nextX = x;
            	nextY = y;
               makeMove(newStack);            
            }
      }
      
      private void undoMove() {
    	  if(squares[previousX][previousY] != null) {
    	  squares[previousX][previousY] = squares[nextX][nextY];
    	  remove(squares[nextX][nextY]);
    	  reset();
    	  }
      }

      private void redoMove() {
          if (squares[nextX][nextY] != null) {
        	squares[nextX][nextY] = squares[previousX][previousY];
        	remove(squares[previousX][previousY]);
        	reset();
          }
      }
         public void makeMove(Stack moves) {
         
            if(previousX == nextX && previousY == nextY){
               JOptionPane.showMessageDialog(null,"Try again!");
               rotateTurn();
               resetColor();
               reset();
               return;
            }
            Icon pic1 = squares[previousX][previousY].getIcon();
            if(pic1.equals(null)){
               JOptionPane.showMessageDialog(null,"Invalid click!");
               rotateTurn();
               resetColor();
               reset();
               return;
            }
            Icon pic2 = squares[nextX][nextY].getIcon();
            if(pic1.equals(pic2)){
               JOptionPane.showMessageDialog(null,"Can't move the same pieces!");
               rotateTurn(); 
               resetColor();       
               reset();
               return;
            }
             if(squares[nextX][nextY].getBackground() != Color.YELLOW){
                JOptionPane.showMessageDialog(null,"Ivalid move!");
                rotateTurn();
                resetColor();
                reset();
                return;
             }
                        
            squares[nextX][nextY].setIcon(pic1);
            Icon pic3 = squares[nextX][nextY].getIcon(); 
            squares[previousX][previousY].setIcon(null);
      
            if(pic1.equals(whitePawn)){
               if(x == 0){
                  JOptionPane.showMessageDialog(null, "Game over!White player wins!");
                  System.exit(1);
                  
               }
               ChessBoard.message.setVisible(false);
               ChessBoard.message1.setVisible(true);
               whiteScore++;
               ChessBoard.whiteScore.setText(whiteScore + "");
            }else{
               if(x == 7){
                  JOptionPane.showMessageDialog(null,"Game over!Black player wins!");
                  System.exit(1);
               }
               ChessBoard.message.setVisible(true);
               ChessBoard.message1.setVisible(false);
               blackScore++;
               ChessBoard.blackScore.setText(blackScore + "");
            }
        
             squares[previousX][previousY].setIcon(null);
             
            
             if(pic1.equals(whiteRook)){
                if(x == 0){
                   
                   JOptionPane.showMessageDialog(null, "White player wins! Game over.");
                   System.exit(1);
                }
                ChessBoard.message.setVisible(false);
                ChessBoard.message1.setVisible(true);
                whiteScore++;
                ChessBoard.whiteScore.setText(whiteScore + "");
             }else{
                if(x == 7){
                   JOptionPane.showMessageDialog(null,"Black player wins! Game over.");
                   System.exit(1);
                }
                ChessBoard.message.setVisible(true);
                ChessBoard.message1.setVisible(false);
                blackScore++;
                ChessBoard.blackScore.setText(blackScore + "");
             }
                     
            resetColor();
            reset(); 
       
         }

         public void rotateTurn()
         {
        	 
            if(icon.equals(whitePawn)){
                  icon = blackPawn; 
            }else{
                  icon = whitePawn; 
            }if(icon.equals(whiteRook)) {
            	icon = blackRook;
            }else {
            	icon = blackRook;
            }
        	 
         }

         public void resetColor(){
            for(int i = 0; i < 8; i++){
               for(int j=0; j<8; j++){
                  if((j+i)%2 != 0){
                     squares[i][j].setBackground((Color.BLACK));
                  }
                  else{
                     squares[i][j].setBackground((Color.WHITE));
                  }
               }
            }
         }
         
         public void reset(){
            Game.previousX = -1;
            Game.previousY = -1;
            Game.nextX = -1;
            Game.nextY = -1;  
            new1 = null; 
            new2 = null;
            new3 = null; 
         } 
         private Stack getBishopMove(int x, int y, String piece) {
             Stack moves = new Stack();

        	 if(squares[x][y].getIcon().equals(whiteBishop)) {
        		 if(squares[x][y].getA()==6) {
        			for(int i =0; i<8; i++) {
        			 new1=squares[squares[x][y].getA()-i][squares[x][y].getB()-i];
        			 new2 = squares[squares[x][y].getA()+i][squares[x][y].getB()+i];
        			
        			 if(new1.getIcon() == null){
        				 
                        squares[squares[x][y].getA()-i][squares[x][y].getB()-i].setBackground(Color.YELLOW);
        				 
        			 }
        			 if(new2.getIcon() == null){
        				
                         squares[squares[x][y].getA()+i][squares[x][y].getB()+i].setBackground(Color.YELLOW);
        			 } 
        			}
        			 
        		 }else if(squares[x][y].getA() == 1){
        			 for(int i=0; i<8; i++) {
        			 
        			 new1 = squares[squares[x][y].getA()-i][squares[x][y].getB()-i];
        			 new2 = squares[squares[x][y].getA()+i][squares[x][y].getB()+i];
        			 
        			 if(new1.getIcon() == null){
        				 
                        squares[squares[x][y].getA()-i][squares[x][y].getB()-i].setBackground(Color.YELLOW);      
        				 
        			 }
        			 
        			 if(new2.getIcon() == null){
        				 
                        squares[squares[x][y].getA()+i][squares[x][y].getB()+i].setBackground(Color.YELLOW);      
        				 
        			 }
        			 if(new1.getIcon() != whiteBishop){
        				 
                        squares[squares[x][y].getA()-i][squares[x][y].getB()-i].setBackground(Color.YELLOW);
        				 
        			 }
        			 if(new2.getIcon() != whiteBishop){
        				
                         squares[squares[x][y].getA()+i][squares[x][y].getB()+i].setBackground(Color.YELLOW);
        			 }
        			}
        			 	
        	}else {
        		for(int i =0; i<8; i++) {
        		new1 = squares[squares[x][y].getA()-i][squares[x][y].getB()-i];
        		new1 = squares[squares[x][y].getA()+i][squares[x][y].getB()+i];
        		
        		if(new1.getIcon() == null){
        			
                   squares[squares[x][y].getA()-i][squares[x][y].getB()-i].setBackground(Color.YELLOW);
        			
        		}
        		if(new2.getIcon() == null){
        			
                    squares[squares[x][y].getA()+i][squares[x][y].getB()+i].setBackground(Color.YELLOW);
        			
         		}
        		if(new1.getIcon() != whiteBishop){
        			
        				squares[squares[x][y].getA()-i][squares[x][y].getB()-i].setBackground(Color.YELLOW);
        			
        		}
        		if(new2.getIcon() != whiteBishop){
        			
                    squares[squares[x][y].getA()+i][squares[x][y].getB()+i].setBackground(Color.YELLOW);
        		}
        		}
        	}

        }else if(squares[x][y].getIcon().equals(blackBishop)){ 
      
            if(squares[x][y].getA() == 6){       
            	for(int i =0; i<8; i++) {
               new1 = squares[squares[x][y].getA()+i][squares[x][y].getB()+i];
               new2 = squares[squares[x][y].getA()-i][squares[x][y].getB()-i];
            	
               if(new1.getIcon() == null){
            	   
                  squares[squares[x][y].getA()+i][squares[x][y].getB()+i].setBackground(Color.YELLOW);
            	   
               }
               if(new2.getIcon() == null) {
            	  
                  squares[squares[x][y].getA()-i][squares[x][y].getB()-i].setBackground(Color.YELLOW);
            	   
               	}
            }
               
            }else if(squares[x][y].getA() == 1){
            	for(int i =0; i<8; i++) {
               new1 = squares[squares[x][y].getA()+i][squares[x][y].getB()+i];
               new2 = squares[squares[x][y].getA()+i][squares[x][y].getB()-i];
            	
               if(new1.getIcon() == null){
                  squares[squares[x][y].getA()+i][squares[x][y].getB()+i].setBackground(Color.YELLOW);
                  
               }
               if(new2.getIcon() == null){
                   squares[squares[x][y].getA()+i][squares[x][y].getB()+i].setBackground(Color.YELLOW);
               }  
             }        
            }else{
            	for(int i =0; i<8; i++) {
               new1 = squares[squares[x][y].getA()+i][squares[x][y].getB()+i];
               new2 = squares[squares[x][y].getA()-i][squares[x][y].getB()-i];
               
              
               if(new1.getIcon() == null){
                  squares[squares[x][y].getA()+i][squares[x][y].getB()+i].setBackground(Color.YELLOW);
               }
               if(new2.getIcon() == null){
                   squares[squares[x][y].getA()-i][squares[x][y].getB()-i].setBackground(Color.YELLOW);
                }
               if(new1.getIcon() != blackBishop){
                  squares[squares[x][y].getA()+i][squares[x][y].getB()+i].setBackground(Color.YELLOW);
                  
               }
               if(new2.getIcon() != blackBishop){
                   squares[squares[x][y].getA()-i][squares[x][y].getB()-i].setBackground(Color.YELLOW);
               } 
              }           
            }
        }	return moves;
 }
   
   private Stack getRookMove(int x, int y, String piece) {
	   Stack moves = new Stack();
	   
        	 if(squares[x][y].getIcon().equals(whiteRook)) {
        		 for(int i =0; i<8; i++) {
        		 if(squares[x][y].getA()==7) {
        			
        			 new1=squares[squares[x][y].getA()-i][squares[x][y].getB()];
        			
        			 if(new1.getIcon() == null){
        				
                        squares[squares[x][y].getA()-i][squares[x][y].getB()].setBackground(Color.YELLOW);
        				
        			 }
        		 }else if(squares[x][y].getA() == 0){
        			 
        			 new1 = squares[squares[x][y].getA()-i][squares[x][y].getB()];
        			 
        			 if(new1.getIcon() == null){
        				
                        squares[squares[x][y].getA()-i][squares[x][y].getB()].setBackground(Color.YELLOW);
        				 
        			 }
        			 if(new1.getIcon() != whiteRook){
        				
                        squares[squares[x][y].getA()-i][squares[x][y].getB()].setBackground(Color.YELLOW);
        			
        		}
        	}else {
        		
        		new1 = squares[squares[x][y].getA()-i][squares[x][y].getB()];
        		
        		if(new1.getIcon() == null){
       
                   squares[squares[x][y].getA()-i][squares[x][y].getB()].setBackground(Color.YELLOW);
                
        		}
        		if(new1.getIcon() != whiteRook){
        
                   squares[squares[x][y].getA()-i][squares[x][y].getB()].setBackground(Color.YELLOW);
        		}
        	}
        }
        		 
        } else if(squares[x][y].getIcon().equals(blackRook)){ 
        	for(int i =0; i<8; i++) {
            if(squares[x][y].getA() == 7){       
               
               new1 = squares[squares[x][y].getA()+i][squares[x][y].getB()];
               
               if(new1.getIcon() == null)
               {
                  squares[squares[x][y].getA()+i][squares[x][y].getB()].setBackground(Color.YELLOW);
               }
               
            }else if(squares[x][y].getA() == 0){
               new1 = squares[squares[x][y].getA()+i][squares[x][y].getB()];
               
               if(new1.getIcon() == null){
                  squares[squares[x][y].getA()+i][squares[x][y].getB()].setBackground(Color.YELLOW);
               }
               
            }else{
     
               new1 = squares[squares[x][y].getA()+i][squares[x][y].getB()];
               
               if(new1.getIcon() == null){
                  squares[squares[x][y].getA()+i][squares[x][y].getB()].setBackground(Color.YELLOW);
               }
               if(new1.getIcon() != blackRook){
                  squares[squares[x][y].getA()+i][squares[x][y].getB()].setBackground(Color.YELLOW);
               }
               
            }
         }
       } return moves;
   }
        	 private Stack getPawnMove(int x, int y, String piece) {
        		 Stack moves = new Stack();
      
            if(squares[x][y].getIcon().equals(whitePawn)){  

               if(squares[x][y].getB() == 7){
            	   
                  new1 = squares[squares[x][y].getA()-1][squares[x][y].getB()];
                  new2 = squares[squares[x][y].getA()-1][squares[x][y].getB()-1];
                  if(new1.getIcon() == null){
                     squares[squares[x][y].getA()-1][squares[x][y].getB()].setBackground(Color.YELLOW);
                  }
                  if(new2.getIcon() != whitePawn){
                     squares[squares[x][y].getA()-1][squares[x][y].getB()-1].setBackground(Color.YELLOW);
                  }
               }else if(squares[x][y].getB() == 0){
                  new1 = squares[squares[x][y].getA()-1][squares[x][y].getB()];
                  new3 = squares[squares[x][y].getA()-1][squares[x][y].getB()+1]; 
                  if(new1.getIcon() == null){
                     squares[squares[x][y].getA()-1][squares[x][y].getB()].setBackground(Color.YELLOW);
                  }
                  if(new3.getIcon() != whitePawn){
                     squares[squares[x][y].getA()-1][squares[x][y].getB()+1].setBackground(Color.YELLOW);
                  }
               }else{     
                  new1 = squares[squares[x][y].getA()-1][squares[x][y].getB()];
                  new2 = squares[squares[x][y].getA()-1][squares[x][y].getB()-1];
                  new3 = squares[squares[x][y].getA()-1][squares[x][y].getB()+1];
                  if(new1.getIcon() == null){
                     squares[squares[x][y].getA()-1][squares[x][y].getB()].setBackground(Color.YELLOW);
                  }
                  if(new2.getIcon() != whitePawn){
                     squares[squares[x][y].getA()-1][squares[x][y].getB()-1].setBackground(Color.YELLOW);
                  }
                  if(new3.getIcon() != whitePawn){
                     squares[squares[x][y].getA()-1][squares[x][y].getB()+1].setBackground(Color.YELLOW);
                  }
               }
            }
            else if(squares[x][y].getIcon().equals(blackPawn)){ 
               if(squares[x][y].getB() == 7){       
                  new1 = squares[squares[x][y].getA()+1][squares[x][y].getB()];
                  new2 = squares[squares[x][y].getA()+1][squares[x][y].getB()-1];
                  if(new1.getIcon() == null){
                     squares[squares[x][y].getA()+1][squares[x][y].getB()].setBackground(Color.YELLOW);
                  }
                  if(new2.getIcon() != blackPawn){
                     squares[squares[x][y].getA()+1][squares[x][y].getB()-1].setBackground(Color.YELLOW);
                  }
               }
               else if(squares[x][y].getB() == 0){
                  new1 = squares[squares[x][y].getA()+1][squares[x][y].getB()];
                  new3 = squares[squares[x][y].getA()+1][squares[x][y].getB()+1];
                  if(new1.getIcon() == null){
                     squares[squares[x][y].getA()+1][squares[x][y].getB()].setBackground(Color.YELLOW);
                  }
                  if(new3.getIcon() != blackPawn){
                     squares[squares[x][y].getA()+1][squares[x][y].getB()+1].setBackground(Color.YELLOW);
                  }
               }else{
                  new1 = squares[squares[x][y].getA()+1][squares[x][y].getB()];
                  new2 = squares[squares[x][y].getA()+1][squares[x][y].getB()-1];
                  new3 = squares[squares[x][y].getA()+1][squares[x][y].getB()+1];
                  if(new1.getIcon() == null){
                     squares[squares[x][y].getA()+1][squares[x][y].getB()].setBackground(Color.YELLOW);
                  }
                  if(new2.getIcon() != blackPawn){
                     squares[squares[x][y].getA()+1][squares[x][y].getB()-1].setBackground(Color.YELLOW);
                  }
                  if(new3.getIcon() != blackPawn){
                     squares[squares[x][y].getA()+1][squares[x][y].getB()+1].setBackground(Color.YELLOW);
                  }
               }
            }   return moves;
         }	
     } 
}
