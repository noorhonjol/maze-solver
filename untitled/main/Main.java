package main;

import SearchStrategies.AlgorithmType;
import SearchStrategies.AbstractSearchStrategy;
import SearchStrategies.SearchStrategyFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class Main {
    static List<List<CellPanel>> CellPanels=new ArrayList<>();

    public static void main(String[] args){
        JFrame frame = new JFrame();
        MazePanel mazePanel = new MazePanel(10,10);

        frame.setLayout(new BorderLayout());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(700, 700);

        AlgorithmSettingsPanel algorithmSettingsPanel = new AlgorithmSettingsPanel();

        JButton btn = new JButton("search");

        btn.setSize(50, 50);

        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                AlgorithmType chosenAlgorithmType = SettingsManger.getAlgorithmType();

                AbstractSearchStrategy chosenAlgorithmStrategy = SearchStrategyFactory.createSearchStrategy(chosenAlgorithmType);

                SearchContext searchContext = new SearchContext(chosenAlgorithmStrategy);

                Set<Cell> goals = SettingsManger.getEndPoints().stream().map(CellPanel::getCell).collect(Collectors.toSet());

                Cell tracePath=searchContext.search(mazePanel.getGraph(), SettingsManger.getStartPoint().getCell(),goals);

                while(tracePath.cellType != CellType.StartCell) {

                    Main.CellPanels.get(tracePath.getRow()).get(tracePath.getColumn()).setBackground(Color.pink);
                    tracePath=tracePath.parent;
                }

            }
        });

        algorithmSettingsPanel.add(btn);

        frame.add(algorithmSettingsPanel,BorderLayout.WEST);

        frame.add(mazePanel,BorderLayout.CENTER);





        frame.setVisible(true);


    }
}
