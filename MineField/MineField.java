package MineField;
import mvc.*;
import java.util.Random;
import javax.swing.JOptionPane;
public class MineField extends Model{
    private int dim;
    private Patch patches[][];
    private int numMines;
    private int numRevealed;
    private Direction direction;

    public MineField(int dim, int numMines) {
        this.dim = dim;
        this.numMines = numMines;
        this.numRevealed = 0;
        this.patches = new Patch[dim][dim];
        for(int row = 0; row < dim; row++) {
            for(int col = 0; col < dim; col++) {
                patches[row][col] = new Patch(row, col);
            }
        }
        placeMines();
        calculateAdjacentMines();
    }

    public int getDim() {
        return dim;
    }

    public Patch getPatch(int row, int col) {
        return patches[row][col];
    }
    public enum Direction {
        NW, N, NE, W, E, SW, S, SE
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

    public void revealPatch(int row, int col) {
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
    }

    public void move(int x, int y) {
        Patch patch = patches[x][y];
        if (!patch.isRevealed()) {
            revealPatch(x, y);
        }
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
        firePropertyChange("direction", null, direction);
    }

    public boolean isGameOver() {
        return numRevealed == dim*dim - numMines;
    }

    public void reset() {
        this.numRevealed = 0;
        for(int row = 0; row < dim; row++) {
            for(int col = 0; col < dim; col++) {
                patches[row][col].reset();
            }
        }
        placeMines();
        calculateAdjacentMines();
    }
}
