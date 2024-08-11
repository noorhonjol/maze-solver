package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class CellPanel extends JPanel  {
    private final Cell cell;
    static boolean paintBlocks=false;
    public CellPanel(int row, int column) {
        this.cell=new Cell(row,column);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        addMouseListener(new OnCellClickedListener());
    }

    static class OnCellClickedListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            CellPanel clickedPanel = (CellPanel) e.getSource();
            SettingsManger.getGoalPoints().remove(clickedPanel);
            CellType cellType = SettingsManger.getSelectedTypeForCell();
            switch (cellType) {

                case StartCell -> {
                    CellPanel startPanel = SettingsManger.getStartPoint();
                    if (startPanel != null) {
                        startPanel.setBackground(Color.white);
                        startPanel.cell.setCellType(CellType.NormalCell);
                    }
                    clickedPanel.setBackground(Color.BLUE);
                    clickedPanel.cell.setCellType(CellType.StartCell);
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



    public Cell getCell() {
        return cell;
    }

}