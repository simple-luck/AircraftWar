package edu.hitsz;

import edu.hitsz.application.Main;
import edu.hitsz.dao.Daoiml;
import edu.hitsz.dao.PlayerData;
import edu.hitsz.dao.ScoreRankingPrinter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public class ScoreTable {
    private DefaultTableModel model;
    private boolean inputReceived = false; // 标记是否已经接收过输入
    private JPanel mainPanel;
    private JTable scoreTable;
    private JScrollPane tabelScrollPanel;
    private JButton deleteButton;
    private JPanel bottomPanel;
    private JLabel headerLabel;
    private int score;


    public ScoreTable() {

        model = new DefaultTableModel();
        JTable table = new JTable(model);
        model.addColumn("名次");
        model.addColumn("玩家名");
        model.addColumn("分数");
        model.addColumn("时间");

        loadData();
        //JTable并不存储自己的数据，而是从表格模型那里获取它的数据
        scoreTable.setModel(model);
        tabelScrollPanel.setViewportView(scoreTable);


        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = scoreTable.getSelectedRow();
                //System.out.println(row);
                //System.out.println(tableData[row][1]);
                int result = JOptionPane.showConfirmDialog(deleteButton,
                        "是否确定中删除？");
                if (JOptionPane.YES_OPTION == result && row != -1) {
                    model.removeRow(row);
                }
            }
        });


        mainPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if (!inputReceived) {
                    // 创建输入对话框
                    String playerName = JOptionPane.showInputDialog("请输入名字以保存本轮数据");
                    if (playerName != null && !playerName.isEmpty()) {
                        Daoiml dao = new Daoiml();
                        dao.setFile("game.txt");
                        // 创建SimpleDateFormat对象，指定日期时间格式
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        // 使用format方法将Date对象转换为字符串
                        String formattedDate = formatter.format(new Date());
                        //创建数据对象实例
                        PlayerData playerdata = new PlayerData(playerName, score, formattedDate);
                        //System.out.println(playerdata);
                        dao.add(playerdata);
                        loadData();
                        inputReceived=true;
                    }
                }
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void loadData() {
        model.setRowCount(0); // 清空表格
        String[] columnName = {"名次", "玩家名", "得分", "时间"};
        Daoiml dao = new Daoiml();
        dao.setFile("game.txt");
        List<PlayerData> scoreRanking = ScoreRankingPrinter.generateScoreRanking(dao);
        int rows = scoreRanking.size();
        //String[][] tableData = new String[rows][4];
        for (int i = 0; i < rows; i++) {
            PlayerData playerData = scoreRanking.get(i);
            //System.out.println("第"+(i + 1) +"名"+" " + playerData.getUsername() + "," + playerData.getScore()+","+playerData.getDate());
            String[] newData = new String[4];
            newData[0] = Integer.toString(i);
            newData[1] = playerData.getUsername();
            newData[2] = Integer.toString(playerData.getScore());
            newData[3] = playerData.getDate();
            //tableData[i] = newData;
            model.addRow(newData);
        }
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


}
