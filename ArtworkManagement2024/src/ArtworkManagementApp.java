import javax.swing.*;
import java.awt.*;

public class ArtworkManagementApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Artwork Management 2024");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setLayout(new BorderLayout());

            ArtworkPanel panel = new ArtworkPanel();
            frame.add(panel, BorderLayout.CENTER);

            frame.setVisible(true);
        });
    }
}