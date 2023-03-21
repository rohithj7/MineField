package mineField;
import mvc.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MineFieldView extends View {
    private Cell cells[][];

    /*
    public MineFieldView(MineField m) {

        super(m);
        int dim = m.getDim();
        cells = new Cell[dim][dim];
        setLayout(new GridLayout(dim,dim));
        for(int row = 0; row < dim; row++) {
            for(int col = 0; col < dim; col++) {
                cells[row][col] = new Cell();
                cells[row][col].setText("?");
                cells[row][col].patch = m.getPatch(row,col);
                cells[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                /*
                if(cells[row][col].patch.occupied) {
                    cells[row][col].setBackground(Color.RED);
                    cells[row][col].setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    cells[row][col].setText("" + cells[row][col].patch);
                }
                if(cells[row][col].patch.goal) {
                    cells[row][col].setBackground(Color.WHITE);
                    cells[row][col].setBorder(BorderFactory.createLineBorder(Color.GREEN));
                }
                this.add(cells[row][col]);
            }
        }
        initView();
        setPreferredSize(new Dimension(dim * 40, dim * 40));
    }
    private void initView() {
        cells[0][0].setText("" + cells[0][0].patch);
        cells[0][0].setBorder(BorderFactory.createLineBorder(Color.WHITE));
    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        MineField field = (MineField)model;
        System.out.println("xLoc = " + field.getXLoc());
        System.out.println("yLoc = " + field.getYLoc());
        cells[field.getXLoc()][field.getYLoc()].setBorder(BorderFactory.createLineBorder(Color.WHITE));
        Color oldColor = gc.getColor();
    } */

    public MineFieldView(MineField minefield){
        super(minefield);
        propertyChange(null);//This needs to be fixed
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        removeAll();
        MineField mf = (MineField) model;
        int dim = mf.getDim();
        setLayout(new GridLayout(dim,dim));
        cells = new Cell[dim][dim];
        for(int i=0; i<mf.getPatches().length; i++){
            for(int j=0; j<mf.getPatches()[i].length; j++){
                cells[i][j] = new Cell();
                cells[i][j].setText("?");
                cells[i][j].patch = mf.getPatch(i,j);
                cells[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                Cell current = cells[i][j];
                Patch currentPatch = mf.getPatches()[i][j];
                if(currentPatch.isRevealed()){
                    current.setBorder(BorderFactory.createLineBorder(Color.white));
                    current.setText(String.valueOf(mf.getPatches()[i][j].getAdjacentMines()));
                    if(currentPatch.isOccupied()){
                        current.setBorder(BorderFactory.createLineBorder(Color.pink));
                        current.setBackground(Color.pink);
                        current.setText("M");
                    }
                }
                if(currentPatch.isGoal()){
                    current.setBorder(BorderFactory.createLineBorder(Color.green));
                    current.setText("E");
                }
                if(currentPatch == mf.getPatches()[mf.getXLoc()][mf.getYLoc()]){
                    current.setBorder(BorderFactory.createLineBorder(Color.cyan));
                    if(currentPatch.isGoal()){
                        current.setBorder(BorderFactory.createLineBorder(Color.orange));
                        current.setBackground(Color.orange);
                        current.setText("W");
                    }
                }
                add(current);
            }
        }
    }

}