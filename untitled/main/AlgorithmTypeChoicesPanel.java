package main;

import SearchStrategies.AlgorithmType;
import javax.swing.*;
import java.awt.*;

public class AlgorithmTypeChoicesPanel extends JPanel {

    JRadioButton AStarBtn,UniformCostBtn,BestCostBtn;

    AlgorithmTypeChoicesPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        AStarBtn = new JRadioButton("AStar");
        UniformCostBtn = new JRadioButton("Uniform Cost");
        BestCostBtn = new JRadioButton("Best Cost");

        AStarBtn.setSelected(true);

        ButtonGroup group = new ButtonGroup();
        group.add(AStarBtn);
        group.add(UniformCostBtn);
        group.add(BestCostBtn);

        AStarBtn.addActionListener(e -> SettingsManger.setAlgorithmType(AlgorithmType.AStar));
        UniformCostBtn.addActionListener(e -> SettingsManger.setAlgorithmType(AlgorithmType.UniformCostSearch));
        BestCostBtn.addActionListener(e ->  SettingsManger.setAlgorithmType(AlgorithmType.BestFirstSearch));

        add(AStarBtn);
        add(UniformCostBtn);
        add(BestCostBtn);
        setBorder(BorderFactory.createMatteBorder(0,0, 1, 0, Color.BLACK));
    }

}
