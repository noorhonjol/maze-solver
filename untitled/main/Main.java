package main;
import javax.swing.*;
import java.awt.*;



public class Main {


    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        AlgorithmSettingsPanel algorithmSettingsPanel = new AlgorithmSettingsPanel();
        frame.add(algorithmSettingsPanel,BorderLayout.WEST);
        frame.add(SettingsManger.mazePanel,BorderLayout.CENTER);
        frame.setVisible(true);


    }


}


