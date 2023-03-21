package mineField;

import mvc.AppFactory;
import mvc.Command;
import mvc.Model;
import mvc.View;


/*
    3/20/23: Nathan Duong
        Adjusted number of mines to be 5% of the board.
 */

public class MineFieldFactory implements AppFactory {

    public Model makeModel() {
        int dim = 20;
        int numMines = (int) Math.round((dim*dim) * (5 / 100.0));
        return new MineField(dim, numMines);
    }

    public View makeView(Model m) {
        return new MineFieldView((MineField) m);
    }

    public String[] getEditCommands() {

        return new String[]{"NW", "N", "NE", "W", "E", "SW", "S", "SE"};
    }

    public Command makeEditCommand(Model model, String type, Object o) {
        Command cmd = null;
        if (type.equals("NW")) {
            cmd = new MoveCommand(model);
            ((MoveCommand) cmd).setMove(-1, -1);
        } else if (type.equals("N")) {
            cmd = new MoveCommand(model);
            ((MoveCommand) cmd).setMove(-1, 0);
        } else if (type.equals("NE")) {
            cmd = new MoveCommand(model);
            ((MoveCommand) cmd).setMove(-1, 1);
        } else if (type.equals("W")) {
            cmd = new MoveCommand(model);
            ((MoveCommand) cmd).setMove(0, -1);
        } else if (type.equals("E")) {
            cmd = new MoveCommand(model);
            ((MoveCommand) cmd).setMove(0, 1);
        } else if (type.equals("SW")) {
            cmd = new MoveCommand(model);
            ((MoveCommand) cmd).setMove(1, -1);
        } else if (type.equals("S")) {
            cmd = new MoveCommand(model);
            ((MoveCommand) cmd).setMove(1, 0);
        } else if (type.equals("SE")) {
            cmd = new MoveCommand(model);
            ((MoveCommand) cmd).setMove(1, 1);
        }
        return cmd;
    }

    public String getTitle() {
        return "Mine Field Simulator";
    }

    public String[] getHelp() {
        return new String[]{""};
    }

    public String about() {
        return "MineField Simulator version 1.0 Copyright 2023 by ";
    }
}
