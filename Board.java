package SHS.Tetris;

import SHS.Tetris.Shape.Tetromino;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel{
    private final int BOARD_WIDTH = 10;
    private final int BOARD_HEIGHT = 22;
    
    private Timer timer;
    private boolean isFallingDone = false;
    private boolean isPaused = false;
    private int numLinesCleared = 0;
    private int currentX = 0;
    private int currentY = 0;
    private JLabel statusBar;
    private Shape currentPiece;
    private Tetromino[] board;
    
    public Board(Tetris parent){
        initBoard(parent);
    }
    private void initBoard(Tetris parent){
        statusBar = parent.getStatusBar();
        addKeyListener(new TAdapter());
    }
    
    private int squareWidth(){
        return (int) getSize().getWidth() / BOARD_WIDTH;
    }
    private int squareHeight(){
        return (int) getSize().getHeight() / BOARD_HEIGHT;
    }
    private Tetromino shapeAt(int x, int y){
        return board[(y * BOARD_WIDTH) +x];
    }
    void start(){
        currentPiece = new Shape();
        board = new Tetromino[BOARD_WIDTH * BOARD_HEIGHT];
        
        clearBoard();
        newPiece();
        
        timer = new Timer(300, new GameCycle());
        timer.start();
    }
    private void pause(){
        isPaused = !isPaused;
        if (isPaused == true){
            statusBar.setText("Paused");
        }
        else{
            statusBar.setText(String.valueOf(numLinesCleared));
        }
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        doDrawing(g);
    }
    
    private void doDrawing(Graphics g){
        double size = getSize().getHeight() * getSize().getWidth();
        int boardTop = (int) size.getHeight() - BOARD_HEIGHT * squareHeight();//The only issue seems to be here where it's giving me a "double cannot be dereferenced error". It should be because of the size.getHeight() part but I don't know what variable type I need to change it to.
        for (int x = 0; x < BOARD_HEIGHT; x++){
            for (int y = 0; y < BOARD_WIDTH; y++){
                Tetromino shape = shapeAt(y, BOARD_HEIGHT - x - 1);
                if (shape != Tetromino.None){
                    drawSquare(g, y * squareWidth(), boardTop + x * squareHeight(), shape);
                }
            }
        }
        if (currentPiece.getShape() != Tetromino.None){
            for (int i = 0; i < 4; i++){
                int x = currentX + currentPiece.x(i);
                int y = currentY + currentPiece.y(i);
                drawSquare(g, x * squareWidth(), boardTop + (BOARD_HEIGHT - y - 1) * squareHeight(), currentPiece.getShape());
            }
        }
    }
    
    private void dropDown(){
        int newY = currentY;
        while (newY > 0){
            if (!tryMove(currentPiece, currentX, newY - 1)){
                break;
            }
            newY--;
        }
        pieceDropped();
    }
    
    private void oneLineDown(){
        if (!tryMove(currentPiece, currentX, currentY - 1)){
            pieceDropped();
        }
    }
    
    private void clearBoard(){
        for (int x = 0; x < BOARD_HEIGHT * BOARD_WIDTH; x++){
            board[x] = Tetromino.None;
        }
    }
    
    private void pieceDropped(){
        for (int i = 0; i < 4; i++){
            int x = currentX + currentPiece.x(i);
            int y = currentY + currentPiece.y(i);
            board[(y * BOARD_WIDTH) + x] = currentPiece.getShape();
        }
        clearLines();
        if (!isFallingDone){
            newPiece();
        }
    }
    
    private void newPiece(){
        currentPiece.setRandomShape();
        currentX = BOARD_WIDTH / 2 + 1;
        currentY = BOARD_HEIGHT - 1 + currentPiece.minY();
        if (!tryMove(currentPiece, currentX, currentY)){
            currentPiece.setShape(Tetromino.None);
            timer.stop();
            String message = String.format("Game over. Score: %d", numLinesCleared);
            statusBar.setText(message);
        }
    }
    
    private boolean tryMove(Shape newPiece, int newX, int newY){
        for (int i = 0; i < 4; i++){
            int x = newX + newPiece.x(i);
            int y = newY - newPiece.y(i);
            if (x < 0 || x >= BOARD_WIDTH || y < 0 || y >= BOARD_HEIGHT){
                return false;
            }
            if (shapeAt(x,y) != Tetromino.None){
                return false;
            }
        }
        currentPiece = newPiece;
        currentX = newX;
        currentY = newY;
        repaint();
        return true;
    }
    
    private void clearLines(){
        int numFullLines = 0;
        for (int i = BOARD_HEIGHT - 1; i >= 0; i--){
            boolean lineIsFull = true;
            for (int j = 0; j < BOARD_WIDTH; j++){
                if (shapeAt(j,i) == Tetromino.None){
                    lineIsFull = false;
                    break;
                }
            }
            if (lineIsFull == true){
                numFullLines++;
                for (int x = i; x < BOARD_HEIGHT-1; x++){
                    for (int j = 0; j < BOARD_WIDTH; j++){
                        board[(x * BOARD_WIDTH) + j] = shapeAt(j,x+1);
                    }
                }
            }
        }
        if (numFullLines > 0){
            numLinesCleared += numFullLines;
            statusBar.setText(String.valueOf(numLinesCleared));
            isFallingDone = true;
            currentPiece.setShape(Tetromino.None);
        }
    }
    
    private void drawSquare(Graphics g, int x, int y, Tetromino shape){
        Color colors[] = {new Color(0,0,0), new Color(0,255,255), new Color(255,255,0), new Color(128,0,128), new Color(0,255,0), new Color(255,0,0), new Color(0,0,255), new Color(255,127,0)};
        Color color = colors[shape.ordinal()];
        g.setColor(color);
        g.fillRect(x+1, y+1, squareWidth()-2, squareHeight()-2);
        g.setColor(color.brighter());
        g.drawLine(x, y+squareHeight()-1, x, y);
        g.drawLine(x, y, x+squareWidth()-1, y);
        g.setColor(color.darker());
        g.drawLine(x+1, y+squareHeight()-1, x+squareWidth()-1, y+squareHeight()-1);
        g.drawLine(x+squareWidth()-1, y+squareHeight()-1, x+squareWidth()-1, y+1);
    }
    
    private class GameCycle implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e){
            doGameCycle();
        }
    }
    
    private void doGameCycle(){
        update();
        repaint();
    }
    
    private void update(){
        if (isPaused){
            return;
        }
        if (isFallingDone == true){
            isFallingDone = false;
            newPiece();
        }
        else{
            oneLineDown();
        }
    }
    
    class TAdapter extends KeyAdapter{
        
        @Override
        public void keyPressed(KeyEvent e){
            if (currentPiece.getShape() == Tetromino.None){
                return;
            }
            int keycode = e.getKeyCode();
            
            switch(keycode){
                case KeyEvent.VK_P:
                    pause();
                case KeyEvent.VK_LEFT:
                    tryMove(currentPiece, currentX-1, currentY);
                case KeyEvent.VK_RIGHT:
                    tryMove(currentPiece, currentX+1, currentY);
                case KeyEvent.VK_DOWN:
                    tryMove(currentPiece.rotateRight(), currentX, currentY);
                case KeyEvent.VK_UP:
                    tryMove(currentPiece.rotateLeft(), currentX, currentY);
                case KeyEvent.VK_SPACE:
                    dropDown();
                case KeyEvent.VK_D:
                    oneLineDown();
            }
        }
    }
}
