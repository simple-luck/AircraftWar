import edu.hitsz.application.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Selection {
    private JButton Button1;
    private JButton Button2;
    private JButton Button3;
    private JPanel mainPanel;

    private Game gamePanel;

    public Selection() {

        Button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    // 当用户选择模式时，切换到游戏面板
                try {
                    switchToGamePanel(1);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        Button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    switchToGamePanel(2);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        Button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    switchToGamePanel(3);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }



    private void switchToGamePanel(int mode) throws IOException {
        // 创建游戏面板
        gamePanel = new Game(mode);
        // 将游戏面板添加到容器
        CardLayoutDemo.cardPanel.add(gamePanel,"game");
        // 切换到游戏面板
        //CardLayoutDemo.cardLayout.show(container, "game");
        CardLayoutDemo.cardLayout.show(CardLayoutDemo.cardPanel, "game");
        gamePanel.action();
    }



    public JPanel getMainPanel() {
        return mainPanel;
    }
}
