package SHS.Tetris;

import java.util.Random;

public class Shape{
    protected enum Tetromino{ None, Z, S, I, T, O, L, J}//Shapes
    private Tetromino pieceShape;//piece shape
    private int coords[][];//coordinates of the piece
    private int[][][] coordsTable;//coordinates of all possible piece shapes
    
    public Shape(){
        initializeShape();
    }
    
    private void initializeShape(){//initialize
        coords = new int[4][2];//board
        
        coordsTable = new int[][][]{//piece coordinates
            { {0,0}, {0,0}, {0,0}, {0,0} },//No shape
            { {0,-1}, {0,0}, {-1,0}, {-1,1} },//S
            { {0,-1}, {0,0}, {1,0}, {1,1} },//Z
            { {0,-1}, {0,0}, {0,1}, {0,2} },//I
            { {-1,0}, {0,0}, {1,0}, {0,1} },//T
            { {0,0}, {1,0}, {0,1}, {1,1} },//O
            { {-1,-1}, {0,-1}, {0,0}, {0,1} },//J
            { {1,-1}, {0,-1}, {0,0}, {0,1} }//L
        };
        
        setShape(Tetromino.None);//Set the shape to no shape
    }
    
    protected void setShape(Tetromino shape){//Set shape
        for (int x = 0; x < 4; x++){//loop through board
            for (int y = 0; y < 2; y++){
                coords[x][y] = coordsTable[shape.ordinal()][x][y];//Set the shape's coordinates
            }
        }
        pieceShape = shape;
    }
    
    private void setX(int index, int x){//Set x
        coords[index][0] = x;
    }
    private void setY(int index, int y){//Set y
        coords[index][1] = y;
    }
    public int x(int index){
        return coords[index][0];
    }
    public int y(int index){
        return coords[index][1];
    }
    public Tetromino getShape(){//Get shape
        return pieceShape;//return piece shape
    }
    
    public void setRandomShape(){//Set new piece to random piece
        Random r = new Random();//Random
        int x = Math.abs(r.nextInt()) % 7 +1;//Random number 1-7
        Tetromino[] values = Tetromino.values();
        setShape(values[x]);//Set shape based on what number was chosen
    }
    
    public int minX(){//Minimum x
        int m = coords[0][0];
        for (int x = 0; x < 4; x++){
            m = Math.min(m, coords[x][0]);
        }
        return m;
    }
    public int minY(){//minimum y
        int m = coords[0][1];
        for (int x = 0; x < 4; x++){
            m = Math.min(m, coords[x][1]);
        }
        return m;
    }
    
    public Shape rotateLeft(){//rotate piece left
        if (pieceShape == Tetromino.O){//If current piece is an 'O'
            return this;//return because O can't rotate
        }
        Shape result = new Shape();//get the shape
        result.pieceShape = pieceShape;
        for (int i = 0; i < 4; i++){//Update x and y of piece if it rotates
            result.setX(i, y(i));
            result.setY(i, -x(i));
        }
        return result;//return the rotated piece
    }
    public Shape rotateRight(){//rotate piece right
        if (pieceShape == Tetromino.O){//if current piece is an 'O'
            return this;//return because O can't rotate
        }
        Shape result = new Shape();
        result.pieceShape = pieceShape;
        for (int i = 0; i < 4; i++){//Try and rotate, if successful, update piece
            result.setX(i, -y(i));
            result.setY(i, x(i));
        }
        return result;//return updated piece
    }
}
