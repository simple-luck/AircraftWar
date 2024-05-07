import edu.hitsz.application.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GameFrame extends JFrame{
    private JPanel Selection;
    public static CardLayout cardLayout;
    public static Container container;
    private Game gamePanel;

    public GameFrame() {
        // 设置窗口标题和默认关闭操作
        setTitle("游戏主界面");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // 窗口居中

        // 初始化容器和布局
        container = getContentPane();
        cardLayout = new CardLayout();
        container.setLayout(cardLayout);
        container.add(Selection, "modeSelection");
        // 显示窗口
        setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameFrame();
            }
        });
    }

}
