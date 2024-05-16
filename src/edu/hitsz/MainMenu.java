package edu.hitsz;

import edu.hitsz.application.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import static edu.hitsz.application.Main.cardLayout;

public class MainMenu {
    private JPanel mainPanel;
    private JButton EasyButton;
    private JButton MediumButton;
    private JButton HardButton;
    private JRadioButton radioButton;
    private JLabel RadioLabel;
    private boolean hasMusic;

    public MainMenu() {
        EasyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    easyGame game=new easyGame(hasMusic);
                    Main.cardPanel.add(game);
                    Main.cardLayout.last(Main.cardPanel);
                    game.process();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });


        MediumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mediumGame game=new mediumGame(hasMusic);
                    Main.cardPanel.add(game);
                    Main.cardLayout.last(Main.cardPanel);
                    game.process();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        HardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    HardGame game=new HardGame(hasMusic);
                    Main.cardPanel.add(game);
                    Main.cardLayout.last(Main.cardPanel);
                    game.process();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        radioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // 选中时播放音乐
                   hasMusic=true;
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    // 未选中时停止播放音乐
                    hasMusic=false;
                }
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
