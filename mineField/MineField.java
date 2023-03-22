package mineField;
import mvc.*;
import java.util.Random;
import javax.swing.JOptionPane;
public class MineField extends Model{
    private int dim;
    private Patch patches[][];
    private int numMines;
    //private int numRevealed;

    private boolean gameExit;
    private Direction direction;

    private int xLoc, yLoc;

    public MineField(int dim, int numMines) {
        this.dim = dim;
        this.numMines = numMines;
        //this.numRevealed = 0;
        this.xLoc = 0;
        this.yLoc = 0;
        this.gameExit = false;

        this.patches = new Patch[dim][dim];
        for(int row = 0; row < dim; row++) {
            for(int col = 0; col < dim; col++) {
                patches[row][col] = new Patch(row, col);
            }
        }
        placeMines();
        calculateAdjacentMines();

        patches[0][0].setRevealed(true);
        patches[patches.length - 1][patches[0].length - 1].setGoal(true);
    }

    public int getDim() {
        return dim;
    }

    public Patch getPatch(int row, int col) {
        return patches[row][col];
    }

    private void placeMines() {
        Random rand = new Random();
        int minesPlaced = 0;
        while (minesPlaced < numMines) {
            int row = rand.nextInt(dim);
            int col = rand.nextInt(dim);
            if (!patches[row][col].isOccupied()) {
                patches[row][col].setOccupied(true);
                minesPlaced++;
            }
        }
    }

    /*
    private void makeGoal() {
        patches[patches.length - 1][patches[0].length - 1].setGoal(true);
    } */

    private void calculateAdjacentMines() {
        for(int row = 0; row < dim; row++) {
            for(int col = 0; col < dim; col++) {
                int adjacentMines = 0;
                for(int i = row-1; i <= row+1; i++) {
                    for(int j = col-1; j <= col+1; j++) {
                        if(i >= 0 && i < dim && j >= 0 && j < dim) {
                            if(patches[i][j].isOccupied()) {
                                adjacentMines++;
                            }
                        }
                    }
                }
                patches[row][col].setAdjacentMines(adjacentMines);
            }
        }
    }

    /*
    private void revealPatch(int row, int col) {
        Patch patch = patches[row][col];
        if(!patch.isRevealed()) {
            patch.setRevealed(true);
            numRevealed++;
            if(patch.isGoal()) {
                // game over, player wins
            }
            else if(patch.isOccupied()) {
                JOptionPane.showMessageDialog(null, "Game over!");
            }
            else if(patch.getAdjacentMines() == 0) {
                // recursively reveal all adjacent patches
                for(int i = row-1; i <= row+1; i++) {
                    for(int j = col-1; j <= col+1; j++) {
                        if(i >= 0 && i < dim && j >= 0 && j < dim) {
                            revealPatch(i, j);
                        }
                    }
                }
            }
        }
    } */

    public void move(Direction direction) {
        if(!gameExit){
            switch (direction) {
                case S: xLoc++;break;
                case W: yLoc--;break;
                case E: yLoc++;break;
                case N: xLoc--;break;
                case NW: yLoc--;xLoc--;break;
                case NE: yLoc++;xLoc--;break;
                case SW: yLoc--;xLoc++;break;
                case SE: yLoc++;xLoc++;break;
            }
            try{
                patches[xLoc][yLoc].setRevealed(true);
            }catch(Exception e){
                switch (direction) {
                    case S: xLoc--;break;
                    case W: yLoc++;break;
                    case E: yLoc--;break;
                    case N: xLoc++;break;
                    case NW: yLoc++;xLoc++;break;
                    case NE: yLoc--;xLoc++;break;
                    case SW: yLoc++;xLoc--;break;
                    case SE: yLoc--;xLoc--;break;
                }
                Utilities.error("Out of bounds");
            }
            if(patches[xLoc][yLoc].isOccupied()){
                Utilities.error("Stepped on mine–Gameover!");
                gameExit = true;
            }
            if(patches[xLoc][yLoc].isGoal()){
                Utilities.inform("You win!!");
                gameExit = true;
            }
            changed(); // from Model, sets changed flag and fires changed event
        }
        else{
            Utilities.inform("Gameover! Start a new game");
        }
    }

    /*
    public void move(Direction direction) {
        xLoc += x;
        yLoc += y;
        Patch patch = patches[xLoc][yLoc];
        System.out.println("xLoc + x = " + xLoc);
        System.out.println("yLoc + y = " + yLoc);
        if (!patch.isRevealed()) {
            revealPatch(yLoc, xLoc);
        }
        changed();
    }*/

    public void setDirection(Direction direction) {
        this.direction = direction;
        firePropertyChange("direction", null, direction);
    }

    public Patch[][] getPatches() {
        return patches;
    }

    /*
    public boolean isGameOver() {
        return numRevealed == dim*dim - numMines;
    }*/

    public int getXLoc() {
        return this.xLoc;
    }

    public int getYLoc() {
        return this.yLoc;
    }

    /*
    public void reset() {
        this.numRevealed = 0;
        for(int row = 0; row < dim; row++) {
            for(int col = 0; col < dim; col++) {
                patches[row][col].reset();
            }
        }
        placeMines();
        calculateAdjacentMines();
    }*/
}