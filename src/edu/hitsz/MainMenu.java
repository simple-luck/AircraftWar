package edu.hitsz;

import edu.hitsz.application.Game;
import edu.hitsz.application.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static edu.hitsz.application.Main.cardLayout;

public class MainMenu {
    private JPanel mainPanel;
    private JButton EasyButton;
    private JButton MediumButton;
    private JButton HardButton;
    private JRadioButton radioButton;
    private JLabel RadioLabel;

    public MainMenu() {
        EasyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    Game game=new Game(1);
                    Main.cardPanel.add(game);
                    Main.cardLayout.last(Main.cardPanel);
                    game.action();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });


        MediumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Game game=new Game(2);
                    Main.cardPanel.add(game);
                    Main.cardLayout.last(Main.cardPanel);
                    game.action();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        HardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Game game=new Game(3);
                    Main.cardPanel.add(game);
                    Main.cardLayout.last(Main.cardPanel);
                    game.action();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
