package mineField;
import mvc.*;
public class MoveCommand extends Command{

    Direction direction;

    public MoveCommand(Model m, Direction direction) {
        super(m);
        this.direction = direction;
    }

    /*
    public void setMove(int x, int y) {
        this.x = x;
        System.out.println("x = " + x);
        this.y = y;
        System.out.println("y = " + y);
    }*/


    public void execute() {
        MineField field = (MineField) model;
        field.move(direction);
    }
}