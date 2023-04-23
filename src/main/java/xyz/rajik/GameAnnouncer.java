package xyz.rajik;

import javax.swing.*;

public class GameAnnouncer {
    public static void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Info"  , JOptionPane.INFORMATION_MESSAGE);
    }
}
