package main;

import javax.swing.*;

public class AlgorithmSettingsPanel extends JPanel {

    public AlgorithmSettingsPanel(){

        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        add(new AlgorithmTypeChoicesPanel());

        add(new TypeOfCellChoicesPanel());



    }

}
