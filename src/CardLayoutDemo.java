import javax.swing.*;
import javax.swing.plaf.SliderUI;
import java.awt.*;

public class CardLayoutDemo {

    static final CardLayout cardLayout = new CardLayout(0,0);
    static final JPanel cardPanel = new JPanel(cardLayout);


    public static void main(String[] args) {

        JFrame frame = new JFrame("CardLayout Demo");
        Container container = frame.getContentPane();
        frame.setSize(512, 768);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(cardPanel);

        Selection start = new Selection();
        cardPanel.add(start.getMainPanel());
        frame.setVisible(true);
    }
}
