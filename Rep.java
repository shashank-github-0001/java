import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Rep extends JFrame {
  public JButton insert, show;
  public JTextField repno, repname, state, comission, rate;
  public Connection conn;
  public ResultSet rs;
  public JTextArea area;

  Rep() {
    // things
    setTitle("rep dbms");
    setSize(400, 300);
    Font font = new Font("Serif", Font.PLAIN, 30);
    setLayout(new GridLayout(7, 2));
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    // labels and input fields
    add(new JLabel("reono"));
    repno = new JTextField();
    repno.setFont(font);
    add(repno);

    // labels and input fields
    add(new JLabel("repname"));
    repname = new JTextField();
    add(repname);

    // labels and input fields
    add(new JLabel("state"));
    state = new JTextField();
    add(state);

    // labels and input fields
    add(new JLabel("comission"));
    comission = new JTextField();
    add(comission);

    // labels and input fields
    add(new JLabel("rate"));
    rate = new JTextField();
    add(rate);

    area = new JTextArea();
    area.setEditable(false);

    add(area);

    insert = new JButton("insert");
    show = new JButton("show");

    add(insert);
    add(show);

    insert.addActionListener(e -> insert());
    show.addActionListener(e -> display());
  }

  public void insert() {
    try {
      conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/testdb",
                                         "root", "sudoroot0");
      PreparedStatement st = conn.prepareStatement(
          "insert into representative values (?, ?, ?, ?, ?)");
      st.setString(1, repno.getText());
      st.setString(2, repname.getText());
      st.setString(3, state.getText());
      st.setString(4, comission.getText());
      st.setString(5, rate.getText());
      st.executeUpdate();
      JOptionPane.showMessageDialog(this, "table updated");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void display() {
    try {
      conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/testdb",
                                         "root", "sudoroot0");
      Statement st = conn.createStatement();
      rs = st.executeQuery("select * from representative");
      while (rs.next()) {
        String formatted = "repno: " + rs.getString("repno") +
                           " repname: " + rs.getString("repname") +
                           "state: " + rs.getString("state") +
                           "comission: " + rs.getString("comission") +
                           "rate: " + rs.getString("rate") + "\n";
        area.append(formatted);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new Rep().setVisible(true));
  }
}
