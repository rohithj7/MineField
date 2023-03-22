package MineField;

public class Patch {
    private int row;
    private int col;
    public boolean occupied;
    private int adjacentMines;
    private boolean revealed;
    public boolean goal;

    public Patch(int row, int col) {
        this.row = row;
        this.col = col;
        this.occupied = false;
        this.adjacentMines = 0;
        this.revealed = false;
        this.goal = false;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public int getAdjacentMines() {
        return adjacentMines;
    }

    public void setAdjacentMines(int adjacentMines) {
        this.adjacentMines = adjacentMines;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }

    public boolean isGoal() {
        return goal;
    }

    public void setGoal(boolean goal) {
        this.goal = goal;
    }

    public void reset() {
        this.occupied = false;
        this.adjacentMines = 0;
        this.revealed = false;
        this.goal = false;
    }

    public String toString() {
        if (occupied) {
            return "M";
        }
        return Integer.toString(adjacentMines);
    }
}
