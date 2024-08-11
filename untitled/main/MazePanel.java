package main;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MazePanel extends JPanel {

    public MazePanel(int width, int height) {
        SettingsManger.cellPanels=new ArrayList<>();

        setLayout(new GridLayout(width, height));
        for (int i = 0; i < width; i++) {
            List<CellPanel> panelRow = new ArrayList<>();
            for (int j = 0; j < height; j++) {
                CellPanel cellPanel = new CellPanel(i, j);
                add(cellPanel);
                panelRow.add(cellPanel);

            }
            SettingsManger.cellPanels.add(panelRow);
        }
    }

    public static void createRandomMaze() {
        Utilities.resetCellPanels(true);
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

}
