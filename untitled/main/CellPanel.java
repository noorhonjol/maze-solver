package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CellPanel extends JPanel  {

    private final Cell cell;

    public CellPanel(int row, int column) {
        this.cell=new Cell(row,column);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        addMouseListener(new OnCellClickedListener());
    }

    static class OnCellClickedListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            CellPanel clickedPanel = (CellPanel) e.getSource();
            CellType cellType = SettingsManger.getSelectedTypeForCell();
            switch (cellType) {

                case StartCell -> {
                    CellPanel startPanel = SettingsManger.getStartPoint();
                    if (startPanel == null) {
                        clickedPanel.setBackground(Color.GREEN);
                        clickedPanel.cell.setCellType(CellType.StartCell);
                    }else{
                        startPanel.setBackground(Color.white);
                        clickedPanel.setBackground(Color.GREEN);
                        startPanel.cell.setCellType(CellType.NormalCell);
                    }
                    SettingsManger.setStartPoint(clickedPanel);

                }
                case GoalCell -> {
                    clickedPanel.setBackground(Color.RED);
                    clickedPanel.cell.setCellType(CellType.GoalCell);
                    SettingsManger.addToEndPoints(clickedPanel);

                }
                case BlockCell -> {
                    clickedPanel.setBackground(Color.BLACK);
                }

            }

        }
    }

    public CellPanel(int row, int column, CellType cellType) {
        this (row,column);
        this.cell.setCellType(cellType);
    }

    public Cell getCell() {
        return cell;
    }

}