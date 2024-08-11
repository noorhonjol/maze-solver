package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;


public class CellPanel extends JPanel  {
    private final Cell cell;
    static boolean paintBlocks=false;
    public CellPanel(int row, int column) {
        this.cell=new Cell(row,column);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //add(new JLabel(row+" "+column));//eases debugging TODO : to be removed
        addMouseListener(new OnCellClickedListener());
    }
    public static void resetCellPanels(boolean hardReset) {
        for(List<CellPanel> cellPanels : SettingsManger.cellPanels){
            for(CellPanel cellPanel : cellPanels){
                if(hardReset){
                    cellPanel.getCell().setCellType(CellType.NormalCell);
                    SettingsManger.setStartPoint(null);
                    SettingsManger.removeAllGoals();

                }
                if(hardReset||cellPanel.getCell().getCellType()==CellType.NormalCell){
                    cellPanel.setBackground(Color.WHITE);
                }

            }
        }
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
                        clickedPanel.setBackground(Color.BLUE);
                        clickedPanel.cell.setCellType(CellType.StartCell);
                    }
                    else{
                        startPanel.setBackground(Color.white);
                        clickedPanel.setBackground(Color.BLUE);
                        startPanel.cell.setCellType(CellType.NormalCell);
                    }
                    SettingsManger.setStartPoint(clickedPanel);

                }
                case GoalCell -> {
                    clickedPanel.setBackground(Color.GREEN);
                    clickedPanel.cell.setCellType(CellType.GoalCell);
                    SettingsManger.addToGoalPoints(clickedPanel);

                }
                case BlockCell -> {
                    clickedPanel.setBackground(Color.BLACK);
                    clickedPanel.cell.setCellType(CellType.BlockCell);
                }
                case NormalCell -> {
                    clickedPanel.setBackground(Color.white);
                    clickedPanel.cell.setCellType(CellType.NormalCell);

                }
            }

        }
        public void mousePressed(MouseEvent e) {
            paintBlocks=true;
            CellPanel clickedPanel = (CellPanel) e.getSource();
            createBlock(clickedPanel);
        }

        public void mouseReleased(MouseEvent e) {
            paintBlocks=false;
        }

        public void mouseEntered(MouseEvent e) {
            if (paintBlocks) {
                CellPanel clickedPanel = (CellPanel) e.getSource();
                createBlock(clickedPanel);
            }
        }
        private static void createBlock(CellPanel clickedPanel){

            CellType cellType = SettingsManger.getSelectedTypeForCell();
            if (cellType == CellType.BlockCell) {
                clickedPanel.setBackground(Color.BLACK);
                clickedPanel.cell.setCellType(CellType.BlockCell);
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