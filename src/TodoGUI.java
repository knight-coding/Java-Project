import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class TodoGUI extends JFrame {
    private JTextField taskField;
    private JButton addButton, deleteButton, completeButton, refreshButton;
    private JTable taskTable;
    private DefaultTableModel model;

    private String url = "jdbc:mysql://localhost:3306/todolist";
    private String user = "root";
    private String pass = "your_password";

    public TodoGUI() {
        setTitle("ToDo List App");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Top panel
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        taskField = new JTextField(30);
        addButton = new JButton("Add Task");
        completeButton = new JButton("Mark Completed");
        deleteButton = new JButton("Delete Task");
        refreshButton = new JButton("Refresh");

        panel.add(taskField);
        panel.add(addButton);
        panel.add(completeButton);
        panel.add(deleteButton);
        panel.add(refreshButton);

        // Table
        model = new DefaultTableModel(new String[]{"ID", "Task", "Status"}, 0);
        taskTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(taskTable);

        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        loadTasks();

        // Button Actions
        addButton.addActionListener(e -> addTask());
        refreshButton.addActionListener(e -> loadTasks());
        completeButton.addActionListener(e -> markCompleted());
        deleteButton.addActionListener(e -> deleteTask());

        setVisible(true);
    }

    private void addTask() {
        String task = taskField.getText().trim();
        if (task.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Task cannot be empty!");
            return;
        }

        String sql = "INSERT INTO todos(task) VALUES(?)";

        try (Connection con = DriverManager.getConnection(url, user, pass);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, task);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Task added!");
            taskField.setText("");
            loadTasks();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadTasks() {
        model.setRowCount(0); // Clear table
        String sql = "SELECT * FROM todos";

        try (Connection con = DriverManager.getConnection(url, user, pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("task"),
                        rs.getString("status")
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void markCompleted() {
        int row = taskTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a task first!");
            return;
        }
        int id = (int) model.getValueAt(row, 0);

        String sql = "UPDATE todos SET status='Completed' WHERE id=?";

        try (Connection con = DriverManager.getConnection(url, user, pass);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Task marked completed!");
            loadTasks();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteTask() {
        int row = taskTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a task first!");
            return;
        }
        int id = (int) model.getValueAt(row, 0);

        String sql = "DELETE FROM todos WHERE id=?";

        try (Connection con = DriverManager.getConnection(url, user, pass);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Task deleted!");
            loadTasks();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TodoGUI::new);
    }
}
