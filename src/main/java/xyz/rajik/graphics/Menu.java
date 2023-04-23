package xyz.rajik.graphics;

import lombok.Data;
import xyz.rajik.FileType;

import javax.swing.*;
import java.awt.*;
import java.io.File;

@Data
public class Menu extends JPanel {
    private JButton resume;
    private JButton save;
    private JButton load;
    private JButton exit;
    private JComboBox<FileType> savingType;

    public Menu() {
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

        add(resume);
        add(save);
        add(load);
        FileType[] types = new FileType[] {FileType.JSON, FileType.TEXT};
        savingType = new JComboBox<>(types);
        savingType.setFont(font);
        savingType.setFocusable(false);
        savingType.setSelectedIndex(0);
        add(savingType);
        add(exit);
        setVisible(false);
        resume.setFocusable(false);
        save.setFocusable(false);
        load.setFocusable(false);
        exit.setFocusable(false);

    }

    public void showMenu() {
        setVisible(true);
    }

    public void hideMenu() {
        setVisible(false);
    }

    public FileType getType() {
        return (FileType) savingType.getSelectedItem();
    }
}
