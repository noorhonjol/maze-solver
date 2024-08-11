package main;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MazePanel extends JPanel {

    private final List<List<Cell>> cells;

//    public static final List<List<CellPanel>> cellPanels=new ArrayList<>();

    public MazePanel(int width, int height) {
        SettingsManger.cellPanels=new ArrayList<>();
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
            SettingsManger.cellPanels.add(panelRow);
        }
    }

    public static void createRandomMaze() {
        CellPanel.resetCellPanels(true);
        Random rand = new Random();
        for (List<CellPanel> cellPanelList : SettingsManger.cellPanels) {
            for (CellPanel cell : cellPanelList) {
                if (rand.nextDouble() < 0.35) {
                    cell.getCell().setCellType(CellType.BlockCell);
                    cell.setBackground(Color.black);
                }
            }
        }
    }
    public List<List<Cell>> getGraph() {
        return cells;
    }
}
