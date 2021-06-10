package SHS.Tetris;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

public class Tetris{
    private JLabel statusBar;
    JFrame frame = new JFrame("Tetris");
    
    public Tetris(){
        initializeUI();
    }
    
    private void initializeUI(){
        statusBar = new JLabel(" 0");
        frame.add(statusBar, BorderLayout.SOUTH);
        
        Board board = new Board(this);
        frame.add(board);
        board.start();
        
        frame.setSize(200, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    JLabel getStatusBar(){
        return statusBar;
    }
    
    public static void main(String[] args) {
        //EventQueue.invokeLater(() ->{
        new Tetris();
        //});
    }
}
