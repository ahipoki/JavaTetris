import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board implements ActionListener{
    JFrame frame = new JFrame("Tetris");
    JButton pause = new JButton("Pause");
    JLabel scoreLabel = new JLabel("Score: 0");
    
    JPanel panel = new JPanel();
    
    public Board(){
        panel.add(pause);
        panel.add(scoreLabel);
        frame.setSize(500, 750);
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.EAST);
        pause.addActionListener(this);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    @Override
    public void actionPerformed (ActionEvent e){
        if (e.getSource().equals(pause)){
            //pauseGame();
        }
    }
    
    public void pauseGame(){
        
    }
    
    public void clearRows(){
        int linesCleared = 0;
        int score = 0;
        
        if (linesCleared == 1){//1 Line cleared
            score += 40;
            scoreLabel.setText("Score: " + score);
        }
        else if (linesCleared == 2){//2 Lines cleared
            score += 80;
            scoreLabel.setText("Score: " + score);
        }
        else if (linesCleared == 3){//3 Lines cleared
            score += 120;
            scoreLabel.setText("Score: " + score);
        }
        else{//4 Lines cleared
            score += 160;
            scoreLabel.setText("Score: " + score);
        }
    }
    
    public static void main(String[] args) {
        new Board();
    }
}
