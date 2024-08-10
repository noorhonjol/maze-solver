package main;

import SearchStrategies.AlgorithmType;

import java.util.HashSet;
import java.util.Set;

public class SettingsManger {

    private static AlgorithmType algorithmType= AlgorithmType.AStar;
    private static CellType selectedTypeForCell = CellType.StartCell;

    private static CellPanel startPoint = null ;
    private static final Set<CellPanel> goalPoints =new HashSet<>();
    //todo : resetting a cell should remove it from this set if its a goal node
    public static AlgorithmType getAlgorithmType() {
        return algorithmType;
    }

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
    public static void addToEndPoints(CellPanel cellPanel) {
        SettingsManger.goalPoints.add(cellPanel);
    }

    public static void removeAllGoals() {
        SettingsManger.goalPoints.clear();
    }
}