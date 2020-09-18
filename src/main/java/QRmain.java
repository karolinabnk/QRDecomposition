import javax.swing.*;

public class QRmain {

    public static void main(String[] args) {

        final Gui gui = new Gui();

        SwingUtilities.invokeLater(() -> gui.setVisible(true));
    }
}