package jdbc1.src;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Main extends JFrame {

  JTextField custNo, custName, custState, custLimit;
  JButton insert, display;
  JTextArea area;
  String url;
  String username;
  String password;
  Connection conn;
  ResultSet rs;

  Main() {
    setSize(300, 400);
    setLayout(new GridLayout(6, 2));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    add(new JLabel("customer no"));
    custNo = new JTextField();
    add(custNo);

    add(new JLabel("customer name"));
    custName = new JTextField();
    add(custName);

    add(new JLabel("customer state"));
    custState = new JTextField();
    add(custState);

    add(new JLabel("customer credit limit"));
    custLimit = new JTextField();
    add(custLimit);

    area = new JTextArea();
    add(area);

    insert = new JButton("insert");
    add(insert);
    display = new JButton("display");
    add(display);

    try {
      conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/test");
    } catch (SQLException e) {
      e.printStackTrace();
    }

    insert.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) { insert(); };
    });

    display.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) { display(); }
    });
  }

  public void insert() {
    String name, no, state, creditLimit;
    no = custNo.getText();
    name = custName.getText();
    state = custState.getText();
    creditLimit = custLimit.getText();
    try {
      PreparedStatement ps =
          conn.prepareStatement("insert into customer values (?, ?, ?, ?)");
      ps.setString(1, no);
      ps.setString(2, name);
      ps.setString(3, state);
      ps.setString(4, creditLimit);
      ps.executeUpdate();
      display();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void display() {
    try {
      Statement st = conn.createStatement();
      rs = st.executeQuery("select * from customer");
      while (rs.next()) {
        String no, name, state, limit;
        no = rs.getString("custno");
        name = rs.getString("custname");
        state = rs.getString("state");
        limit = rs.getString("creditlimit");
        area.append("no: " + no + " name: " + name + " state: " + state +
                    " limit: " + limit + "\n");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> { new Main().setVisible(true); });
  }
}
