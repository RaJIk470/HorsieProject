package xyz.rajik.graphics;

import lombok.Data;
import xyz.rajik.FileType;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import xyz.rajik.Dimension;
import xyz.rajik.Game;

@Data
public class Menu extends JPanel {
    private JButton resume;
    private JButton save;
    private JButton load;
    private JButton exit;
    private JComboBox<FileType> savingType;
    private JComboBox<Dimension> screenSize;
    private JComboBox<String> difficulty;
    private JButton confirmName;
    private JTextField nameField;
    private transient JPanel p;
    private JButton settingsButton;
    private JPanel settingsPanel;

    public Menu(JPanel p) {
        setFocusable(false);
        Font font = new Font("Sans", Font.PLAIN, 22);
        resume = new JButton("Resume");
        resume.setFont(font);

        save = new JButton("Save");
        save.setFont(font);

        load = new JButton("Load");
        load.setFont(font);

        exit = new JButton("Exit");
        exit.setFont(font);

        settingsButton = new JButton("Settings");
        settingsButton.setFont(font);
        settingsButton.setFocusable(false);

        confirmName = new JButton("Confirm");
        nameField = new JTextField("Player");


        FileType[] types = new FileType[] {FileType.JSON, FileType.TEXT};
        Dimension[] screenSizes = new Dimension[] {
                new Dimension(2560, 1600),
                new Dimension(1920, 1080),
                new Dimension(1650, 950),
                new Dimension(1024, 720),
                new Dimension(800, 600),
        };
        String[] difficulties = new String[] {
            "Easy",
            "Medium",
            "Hard",
            "Extreme",
            "Impossible"
        };
        difficulty = new JComboBox<>(difficulties);
        difficulty.setFont(font);
        screenSize = new JComboBox<>(screenSizes);
        screenSize.setFont(font);
        confirmName.setFont(font);
        nameField.setFont(font);


        savingType = new JComboBox<>(types);
        savingType.setFont(font);
        savingType.setFocusable(false);
        savingType.setSelectedIndex(0);
        setVisible(false);
        resume.setFocusable(false);
        save.setFocusable(false);
        difficulty.setFocusable(false);
        load.setFocusable(false);
        exit.setFocusable(false);
        screenSize.setFocusable(false);
        confirmName.setFocusable(false);
        //nameField.setFocusable(false);


        GridLayout gridLayout = new GridLayout(6, 1, 0, 20);




        difficulty.addActionListener((e) -> {
            Dimension d = (Dimension) screenSize.getSelectedItem();
            int diff = 0;
            JComboBox<String> jcb = (JComboBox<String>) e.getSource();
            String dif = (String) jcb.getSelectedItem();
            switch (d.getWidth()) {
                case 2560 -> {
                    diff = 10;
                }
                case 1920 -> {
                    diff = 9;
                }
                case 1650 -> {
                    diff = 8;
                }
                case 1024 -> {
                    diff = 7;
                }
                case 800 -> {
                    diff = 6;
                }


            }
            switch (dif) {
                case "Easy" -> {
                    diff += 0;
                }
                case "Medium" -> {
                    diff += 4;
                }
                case "Hard" -> {
                    diff += 6;
                }
                case "Extreme" -> {
                    diff += 8;
                }
                case "Impossible" -> {
                    diff += 10;
                }
            }

            int resultDiff = diff;
            System.out.println(resultDiff);
            Game.game.getGameField().setDifficulty(resultDiff);
            Game.game.getGameField().getBalls().getBalls().stream().forEach(ball -> ball.setSpeed(resultDiff));
        });

        JPanel panel = new JPanel();

        GridLayout settingsGrid = new GridLayout(6, 1, 0, 20);
        settingsPanel = new JPanel();
        settingsPanel.setLayout(settingsGrid);
        JButton back = new JButton("Back");
        back.setFont(font);
        settingsPanel.add(screenSize);
        settingsPanel.add(difficulty);
        settingsPanel.add(nameField);
        settingsPanel.add(confirmName);
        settingsPanel.add(back);
        panel.setLayout(gridLayout);

        back.setFocusable(false);
        back.addActionListener((e) -> {
            p.remove(settingsPanel);
            p.add(panel, BorderLayout.CENTER);
            settingsPanel.repaint();
            p.repaint();
            panel.repaint();
            repaint();
        });

        confirmName.addActionListener((e) -> {
            Game.game.playerName = nameField.getText();
            nameField.setFocusable(false);
            nameField.setFocusable(true);
            Game.game.requestFocus();
        });

        panel.add(resume);
        panel.add(save);
        panel.add(load);
        panel.add(savingType);
        panel.add(settingsButton);
        //panel.add(screenSize);
        //panel.add(difficulty);
        //panel.add(nameField);
        //panel.add(confirmName);
        panel.add(exit);

        //BorderLayout borderLayout = new BorderLayout(1100, 500);
        //setLayout(borderLayout);
        //add(panel, BorderLayout.CENTER);

        BorderLayout borderLayout = new BorderLayout(1100, 500);
        p.setLayout(borderLayout);
        //setVisible(false);
        //panel.setVisible(false);
        this.p = panel;
        panel.setBackground(Color.WHITE);
        panel.setVisible(false);
        JPanel menuBtnP = new JPanel();
        GridLayout gridLayout1 = new GridLayout(1, 5);
        menuBtnP.setLayout(gridLayout1);
        JButton jButton = new JButton("Menu");
        Font jbtnfont = new Font("Sans", Font.PLAIN, 32);
        jButton.setFont(jbtnfont);
        jButton.setFocusable(false);
        jButton.addActionListener((e) -> {
            toggleMenu();
        });
        for (int i = 0; i < 4; i++) {
            JPanel jPanel = new JPanel();
            jPanel.setBackground(Color.white);
            menuBtnP.add(jPanel);
        }
        menuBtnP.add(jButton);
        menuBtnP.setBackground(Color.WHITE);
        settingsPanel.setBackground(Color.WHITE);
        p.add(new JPanel(), BorderLayout.EAST);
        p.add(new JPanel(), BorderLayout.WEST);
        p.add(menuBtnP, BorderLayout.NORTH);
        p.add(new JPanel(), BorderLayout.SOUTH);
        p.add(panel, BorderLayout.CENTER);
        //p.add(settingsPanel, BorderLayout.CENTER);

        settingsButton.addActionListener((e) -> {
            p.remove(panel);
            p.repaint();
            p.add(settingsPanel, BorderLayout.CENTER);
            //p.add(panel);
            //settingsPanel.setVisible(true);
            p.repaint();
            settingsPanel.repaint();
            repaint();
        });

        //p.add(panel);
        //this.add(panel);

        screenSize.addActionListener((e) -> {
            JComboBox<Dimension> jcb = (JComboBox<Dimension>) e.getSource();
            Dimension d = (Dimension) jcb.getSelectedItem();
            Game.game.setScreenSize(d.getWidth(), d.getHeight());
            Game.game.getGameField().changeScreenSize(d.getWidth(), d.getHeight());
            Game.game.setLocation(0, 0);
            switch (d.getWidth()) {
                case 2560 -> {
                    borderLayout.setHgap(1100);
                    borderLayout.setVgap(500);
                    Game.game.getGameField().setDifficulty(10);
                }
                case 1920 -> {
                    borderLayout.setHgap(700);
                    borderLayout.setVgap(250);
                    Game.game.getGameField().setDifficulty(9);
                }
                case 1650 -> {
                    borderLayout.setHgap(500);
                    borderLayout.setVgap(200);
                    Game.game.getGameField().setDifficulty(8);
                }
                case 1024 -> {
                    borderLayout.setHgap(300);
                    borderLayout.setVgap(150);
                    Game.game.getGameField().setDifficulty(7);
                }
                case 800 -> {
                    borderLayout.setHgap(200);
                    borderLayout.setVgap(100);
                    Game.game.getGameField().setDifficulty(6);
                }
            }
            p.repaint();
            settingsPanel.repaint();
            panel.repaint();
            repaint();
        });
    }

    public void showMenu() {
        p.setVisible(true);
        settingsPanel.setVisible(true);
        repaint();
    }

    public void hideMenu() {
        p.setVisible(false);
        settingsPanel.setVisible(false);
        repaint();
    }

    public void toggleMenu() {
        if (p.isVisible()) {
            Game.game.resumeGame();
        } else {
            Game.game.pauseGame();
        }
        repaint();
    }

    public FileType getType() {
        return (FileType) savingType.getSelectedItem();
    }

    @Override
    public void repaint() {
        //super.repaint();
        if (resume == null) return;
        resume.repaint();
        save.repaint();
        load.repaint();
        exit.repaint();
        savingType.repaint();
        screenSize.repaint();
        difficulty.repaint();
        confirmName.repaint();
        nameField.repaint();
        settingsPanel.repaint();
        p.repaint();
    }
}
