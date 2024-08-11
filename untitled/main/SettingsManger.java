package main;
import SearchStrategies.AlgorithmType;
import javax.swing.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class SettingsManger {
    public static final int width=50;
    public static final int height=50;
    public static MazePanel mazePanel = new MazePanel(width,height);
    public static List<List<CellPanel>> cellPanels;
    private static AlgorithmType algorithmType= AlgorithmType.AStar;
    private static CellType selectedTypeForCell = CellType.StartCell;
    private static CellPanel startPoint = null ;
    private static final Set<CellPanel> goalPoints =new HashSet<>();
    public static JLabel costLabel;

    public static void setAlgorithmType(AlgorithmType algorithmType) {
        SettingsManger.algorithmType = algorithmType;
    }

    public static CellType getSelectedTypeForCell() {
        return selectedTypeForCell;
    }

    public static void setSelectedTypeForCell(CellType selectedTypeForCell) {
        SettingsManger.selectedTypeForCell = selectedTypeForCell;
    }

    public static CellPanel getStartPoint() {
        return startPoint;
    }

    public static void setStartPoint(CellPanel startPoint) {
        SettingsManger.startPoint = startPoint;
    }
    public static Set<CellPanel> getGoalPoints() {
        return goalPoints;
    }
    public static void addToGoalPoints(CellPanel cellPanel) {
        SettingsManger.goalPoints.add(cellPanel);
    }

    public static void removeAllGoals() {
        SettingsManger.goalPoints.clear();
    }
    public static List<List<CellPanel>> getCellPanels() {
        return cellPanels;
    }
    public static AlgorithmType getAlgorithmType() {
        return algorithmType;
    }
}