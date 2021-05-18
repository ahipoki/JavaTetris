import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board implements ActionListener{
    JFrame frame = new JFrame("Tetris");
    JButton pause = new JButton("Pause");
    JLabel score = new JLabel("Score: ");
    
    JPanel panel = new JPanel();
    
    public Board(){
        panel.add(pause);
        panel.add(score);
        frame.setSize(500, 750);
        frame.setLayout(new BorderLayout());
        //frame.add(pause, BorderLayout.EAST);
        frame.add(panel, BorderLayout.EAST);
        pause.addActionListener(this);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    @Override
    public void actionPerformed (ActionEvent e){
        if (e.getSource().equals(pause)){
            
        }
    }
    
    public static void main(String[] args) {
        new Board();
    }
}
