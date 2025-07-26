import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ArtworkPanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private JTextField titleField, artistField, priceField;
    private DBHelper dbHelper;

    public ArtworkPanel() {
        dbHelper = new DBHelper();
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new String[]{"ID", "Title", "Artist", "Price"}, 0);
        table = new JTable(model);
        loadArtworks();

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        titleField = new JTextField(10);
        artistField = new JTextField(10);
        priceField = new JTextField(5);
        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");

        inputPanel.add(new JLabel("Title:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Artist:"));
        inputPanel.add(artistField);
        inputPanel.add(new JLabel("Price:"));
        inputPanel.add(priceField);
        inputPanel.add(addButton);
        inputPanel.add(deleteButton);

        add(inputPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addArtwork());
        deleteButton.addActionListener(e -> deleteArtwork());
    }

    private void loadArtworks() {
        model.setRowCount(0);
        try (Connection conn = dbHelper.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM artworks")) {
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("artist"),
                        rs.getDouble("price")
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void addArtwork() {
        String title = titleField.getText();
        String artist = artistField.getText();
        double price = Double.parseDouble(priceField.getText());

        try (Connection conn = dbHelper.connect();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO artworks (title, artist, price) VALUES (?, ?, ?)")) {
            ps.setString(1, title);
            ps.setString(2, artist);
            ps.setDouble(3, price);
            ps.executeUpdate();
            loadArtworks();
            titleField.setText("");
            artistField.setText("");
            priceField.setText("");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void deleteArtwork() {
        int row = table.getSelectedRow();
        if (row == -1) return;
        int id = (int) model.getValueAt(row, 0);

        try (Connection conn = dbHelper.connect();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM artworks WHERE id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
            loadArtworks();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}