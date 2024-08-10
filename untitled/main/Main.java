package main;

import SearchStrategies.AlgorithmType;
import SearchStrategies.AbstractSearchStrategy;
import SearchStrategies.SearchStrategyFactory;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


public class Main {
    static List<List<CellPanel>> cellPanels =new ArrayList<>();
    static private int width=25;
    static private int height=25;

    public static void main(String[] args){
        JFrame frame = new JFrame();
        MazePanel mazePanel = new MazePanel(width,height);

        frame.setLayout(new BorderLayout());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        AlgorithmSettingsPanel algorithmSettingsPanel = new AlgorithmSettingsPanel();

        JButton btn = new JButton("search");//ide says we can extract method, look into that

        btn.setSize(50, 50);

        btn.addActionListener(e -> {
            resetCellPanels(false);
            AlgorithmType chosenAlgorithmType = SettingsManger.getAlgorithmType();

            AbstractSearchStrategy chosenAlgorithmStrategy = SearchStrategyFactory.createSearchStrategy(chosenAlgorithmType);

            SearchContext searchContext = new SearchContext(chosenAlgorithmStrategy);
            Set<Cell> goals = SettingsManger.getGoalPoints().stream().map(CellPanel::getCell).collect(Collectors.toSet());
            //todo handle things being null, maybe use try catch (?)
            Cell tracePath=searchContext.search(mazePanel.getGraph(), SettingsManger.getStartPoint().getCell(),goals);

            if(tracePath==null){
                JOptionPane.showMessageDialog(frame, "No path found");
            }
            else while(tracePath.cellType != CellType.StartCell) {
                if(tracePath.cellType != CellType.GoalCell){
                    Main.cellPanels.get(tracePath.getRow()).get(tracePath.getColumn()).setBackground(Color.pink);
                }
                tracePath=tracePath.parent;
            }

        });

        algorithmSettingsPanel.add(btn);
        JButton hardResetBtn = new JButton("reset");
        hardResetBtn.setSize(50, 50);
        hardResetBtn.addActionListener(e -> resetCellPanels(true));
        algorithmSettingsPanel.add(hardResetBtn);
        JButton generateMaze = new JButton("generate maze");
        generateMaze.setSize(50, 50);
        generateMaze.addActionListener(e -> randomMaze());
        algorithmSettingsPanel.add(generateMaze);
        frame.add(algorithmSettingsPanel,BorderLayout.WEST);

        frame.add(mazePanel,BorderLayout.CENTER);





        frame.setVisible(true);


    }

    private static void randomMaze() {
        resetCellPanels(true);
        Random rand = new Random();
        for (List<CellPanel> cellPanelList : cellPanels) {
            for (CellPanel cell : cellPanelList) {
                if (rand.nextDouble() < 0.35) {
                    cell.getCell().setCellType(CellType.BlockCell);
                    cell.setBackground(Color.black);
                }
            }
        }
    }
    private static void resetCellPanels(boolean hardReset) {
        for(List<CellPanel> cellPanels : cellPanels){
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
}
