import javax.swing.*;

public class Board extends JPanel {
    private final int BoardWidth = 10;//Board width
    private final int BoardHeight = 22;//Board height
    
    private boolean stillFalling = true;//Piece still falling
    private int LinesCleared = 0;//Number of lines cleared
    private JLabel statusbar;//Status bar
    
    public Board(){
        
    }
    
    private int squareWidth(){
        return (int) getSize.getWidth() / BoardWidth;
    }
    
    private int squareHeight(){
        return (int) getSize().getHeight() / BoardHeight;
    }

    public static void main(String[] args) {

    }
}
