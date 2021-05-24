import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.*;

public class Tetris extends JComponent implements ActionListener{
    JFrame frame = new JFrame("Tetris");
    JButton pause = new JButton("Pause");
    JButton resume = new JButton("Resume");
    JLabel scoreLabel = new JLabel("Score: 0");
    JLabel pieceTest = new JLabel("Current Piece:");
    JPanel panel = new JPanel();
    
    /*ArrayList<String> myPieces = new ArrayList<String>();
        myPieces.add("I");
        myPieces.add("O");
        myPieces.add("T");
        myPieces.add("L");
        myPieces.add("J");
        myPieces.add("S");
        myPieces.add("Z");
        Collections.shuffle(myPieces);*/
    
    public Tetris(){
        panel.add(pause);
        panel.add(resume);
        panel.add(scoreLabel);
        frame.setSize(500, 750);
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.EAST);
        pause.addActionListener(this);
        resume.addActionListener(this);
        resume.setEnabled(false);
        frame.add(pieceTest, BorderLayout.CENTER);
        //frame.addKeyListener(this);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    @Override
    public void actionPerformed (ActionEvent e){
        if (e.getSource().equals(pause)){
            pauseGame();
        }
        else if (e.getSource().equals(resume)){
            resumeGame();
        }
    }
    
    //@Override
    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_UP){
            dropDown();
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN){
            move(2);
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT){
            move(0);
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            move(1);
        }
        else if (e.getKeyCode() == KeyEvent.VK_X){
            //getCurrentPiece();
            rotate(1, getCurrentPiece());
        }
        else if (e.getKeyCode() == KeyEvent.VK_Z){
            //getCurrentPiece();
            rotate(0, getCurrentPiece());
        }
        else if (e.getKeyCode() == KeyEvent.VK_SHIFT){
            holdBlock();
        }
    }
    
    public int getNextPiece(){
        int nextPiece = (int)(Math.random()*7);
        return nextPiece;
    }
    
    public String getCurrentPiece(){
        String currentPiece = "";
        int nextPiece = getNextPiece();
        if (nextPiece == 0){
            currentPiece = "I";
            pieceTest.setText("Current Piece: I");
        }
        else if (nextPiece == 1){
            currentPiece = "O";
            pieceTest.setText("Current Piece: O");
        }
        else if (nextPiece == 2){
            currentPiece = "T";
            pieceTest.setText("Current Piece: T");
        }
        else if (nextPiece == 3){
            currentPiece = "L";
            pieceTest.setText("Current Piece: L");
        }
        else if (nextPiece == 4){
            currentPiece = "J";
            pieceTest.setText("Current Piece: J");
        }
        else if (nextPiece == 5){
            currentPiece = "S";
            pieceTest.setText("Current Piece: S");
        }
        else if (nextPiece == 6){
            currentPiece = "Z";
            pieceTest.setText("Current Piece: Z");
        }
        return currentPiece;
    }
    
    public void pauseGame(){
        //stop block auto-moving, disable ability to move, rotate, drop, or hold blocks
        resume.setEnabled(true);
        pause.setEnabled(false);
        getCurrentPiece();
    }
    
    public void resumeGame(){
        //resume block auto-moving, enable ability to move, rotate, drop, or hold blocks
        resume.setEnabled(false);
        pause.setEnabled(true);
        getCurrentPiece();
    }
    
    public void paintComponent(Graphics g){
        //draw playfield outline
        //draw current piece in play
        //draw out of play piece in box to top left of board
        super.paintComponent(g);
    }
    
    public void rotate(int direction, String currentPiece){
        int collisions = 0;
        if (direction == 0){//0 is left
            if (currentPiece == "I"){
                //check for collisions with rotation
                if (collisions == 0){
                    //rotate block once
                }
                else{
                    //don't rotate block
                }
            }
            else if (currentPiece == "O"){
                //check for collisions with rotation
                if (collisions == 0){
                    //rotate block once
                }
                else{
                    //don't rotate block
                }
            }
            else if (currentPiece == "T"){
                //check for collisions with rotation
                if (collisions == 0){
                    //rotate block once
                }
                else{
                    //don't rotate block
                }
            }
            else if (currentPiece == "L"){
                //check for collisions with rotation
                if (collisions == 0){
                    //rotate block once
                }
                else{
                    //don't rotate block
                }
            }
            else if (currentPiece == "J"){
                //check for collisions with rotation
                if (collisions == 0){
                    //rotate block once
                }
                else{
                    //don't rotate block
                }
            }
            else if (currentPiece == "S"){
                //check for collisions with rotation
                if (collisions == 0){
                    //rotate block once
                }
                else{
                    //don't rotate block
                }
            }
            else if (currentPiece == "Z"){//Z piece
                //check for collisions with rotation
                if (collisions == 0){
                    //rotate block once
                }
                else{
                    //don't rotate block
                }
            }
        }
        else{//1 is right
            if (currentPiece == "I piece"){
                //check for collisions with rotation
                if (collisions == 0){
                    //rotate block once
                }
                else{
                    //don't rotate block
                }
            }
            else if (currentPiece == "O piece"){
                //check for collisions with rotation
                if (collisions == 0){
                    //rotate block once
                }
                else{
                    //don't rotate block
                }
            }
            else if (currentPiece == "T piece"){
                //check for collisions with rotation
                if (collisions == 0){
                    //rotate block once
                }
                else{
                    //don't rotate block
                }
            }
            else if (currentPiece == "L piece"){
                //check for collisions with rotation
                if (collisions == 0){
                    //rotate block once
                }
                else{
                    //don't rotate block
                }
            }
            else if (currentPiece == "J piece"){
                //check for collisions with rotation
                if (collisions == 0){
                    //rotate block once
                }
                else{
                    //don't rotate block
                }
            }
            else if (currentPiece == "S piece"){
                //check for collisions with rotation
                if (collisions == 0){
                    //rotate block once
                }
                else{
                    //don't rotate block
                }
            }
            else{//Z piece
                //check for collisions with rotation
                if (collisions == 0){
                    //rotate block once
                }
                else{
                    //don't rotate block
                }
            }
        }
    }
    
    public void move(int direction){
        if (direction == 0){//0 is left
        //check if there is anything 1 square to the left of each spot in the current active piece
            //if nothing
                //move each square in the current active piece 1 square to the left
            //if there is a collision
                //don't let the piece move
        }
        else if (direction == 1){//1 is right
        //check if there is anything 1 square to the right of each spot in the current active piece
            //if nothing
                //move each square in the current active piece 1 square to the right
            //if there is a collision
                //don't let the piece move
        }
        else{//2 is down
            //move block down 1 spot
            //check if there is anything 1 square down from any spot on the piece
                //if nothing
                    //move each spot on the piece down 1 square
                //if there is a collision
                    //don't move the piece
        }
    }
    
    public void dropDown(){
        //Drop current block in play to bottom of screen
        //move each square in the current active piece down 1 square at a time, each time checking for collisions
        //the piece stops moving once there are any collisions with the bottom of a square
        //while (currentPieceMoving == true){
            //move block down 1 spot
            //check if there is anything 1 square down from any spot on the piece
                //if nothing
                    //move each spot on the piece down 1 square
                //if there is a collision
                    //currentPieceMoving = false;
        //}
    }
    
    public void holdBlock(){
        //check if a block is already held
        //if held, switch with current active block
        //if empty, remove current block from play and keep track of it until next switch
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
        new Tetris();
    }
}
