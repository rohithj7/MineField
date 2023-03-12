package mvc;

/*
Jelinne 3/11/2023
 */

abstract public class Command {
    private Model model;

    public Command(Model model) {
        this.model = model;
    }

    public abstract void execute();
}
