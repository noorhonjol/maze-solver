package main;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MazePanel extends JPanel {

    private final List<List<Cell>> cells;

//    public static final List<List<CellPanel>> cellPanels=new ArrayList<>();

    public MazePanel(int width, int height) {
        setLayout(new GridLayout(width, height));
        cells = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            List<Cell> row = new ArrayList<>();
            List<CellPanel> panelRow = new ArrayList<>();

            for (int j = 0; j < height; j++) {
                CellPanel cellPanel = new CellPanel(i, j);
                add(cellPanel);
                row.add(cellPanel.getCell());
                panelRow.add(cellPanel);

            }
            cells.add(row);
            Main.cellPanels.add(panelRow);
        }
    }
//
//    public List<List<CellPanel>> getCellPanels() {
//        return cellPanels;
//    }

    public List<List<Cell>> getGraph() {
        return cells;
    }
}
