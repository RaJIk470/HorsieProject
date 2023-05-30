package xyz.rajik;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

public class GameAnnouncer {
    public static void showMessage(String message) {
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 26)));
        JOptionPane.showMessageDialog(null, message, "Info"  , JOptionPane.INFORMATION_MESSAGE);
    }
}
