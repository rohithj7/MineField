package MineField;
import mvc.*;
public class  MoveCommand extends Command{
    private MineField field;
    private int x, y;

    public MoveCommand(Model m) {

        super(m);
        this.field = (MineField)m;
    }

    public void setMove(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public void execute() {
        field.move(x, y);
    }
}
