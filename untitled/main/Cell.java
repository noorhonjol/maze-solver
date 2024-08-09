package main;

import java.util.Set;

public class Cell {

    Cell parent;
    public Integer f=Integer.MAX_VALUE;
    public Integer g=Integer.MAX_VALUE;
    public Integer h=Integer.MAX_VALUE;
    private final int row;
    private final int column;
    CellType cellType;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        cellType = CellType.NormalCell;
    }
    public Cell(int row, int column, CellType cellType) {
        this(row,column);
        this.cellType = cellType;
    }
    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
    public Integer getF() {
        return f;
    }

    public void setF(Integer f) {
        this.f = f;
    }

    public Integer getG() {
        return g;
    }

    public void setG(Integer g) {
        this.g = g;
    }

    public Integer getH() {
        return h;
    }

    public void setH(Integer h) {
        this.h = h;
    }
    public void setParent(Cell parent) {
        this.parent = parent;
    }
    public Cell getParent() {
        return parent;
    }
    public CellType getCellType() {
        return cellType;
    }
    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }
    public int calculateHeuristic() {
        int result = Integer.MAX_VALUE;
        Set<CellPanel> goals = SettingsManger.getGoalPoints();
        for(CellPanel goal:goals) {
            int current=Math.abs(goal.getCell().getRow()-row)+Math.abs(goal.getCell().getColumn()-column);
            result=Math.min(result,current);
        }
        return result;
    }

}