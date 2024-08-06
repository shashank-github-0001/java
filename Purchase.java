import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.*;

public class Purchase extends JFrame {

  private JTextField itemNameField, quantityField, itemPurchasedField,
      totalCostField;
  private JButton calculateButton, printButton;
  HashMap<String, Integer> costArray;

  Purchase() {
    setSize(300, 400);
    setLayout(new GridLayout(3, 4));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // cost array
    costArray = new HashMap<>();
    costArray.put("potato", 10);
    costArray.put("tomato", 20);
    costArray.put("cherries", 50);

    // item name field
    add(new JLabel("Item name"));
    itemNameField = new JTextField();
    add(itemNameField);

    // quantityField
    add(new JLabel("quantity"));
    quantityField = new JTextField();
    add(quantityField);

    // item purchased field
    add(new JLabel("item purchased"));
    itemPurchasedField = new JTextField();
    add(itemPurchasedField);

    // total cost
    add(new JLabel("total cost"));
    totalCostField = new JTextField();
    add(totalCostField);

    // buttons
    calculateButton = new JButton("calculate");
    add(calculateButton);
    printButton = new JButton("print");
    add(printButton);

    calculateButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) { calculateCost(); }
    });

    printButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) { printItem(); }
    });
  }

  public void calculateCost() {
    String name = itemNameField.getText();
    int quantity = Integer.parseInt(quantityField.getText());
    int price = costArray.get(name);
    Integer totalPrice = price * quantity;
    itemPurchasedField.setText(name);
    totalCostField.setText(totalPrice.toString());
  }

  public void printItem() {
    String[] option = {"No Discount", "5% discount", "10% discount"};
    int ans = JOptionPane.showOptionDialog(
        this, "select discount", "options", JOptionPane.DEFAULT_OPTION,
        JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);

    Double totalPrice = Double.parseDouble(totalCostField.getText());

    if (ans == 0) {
      totalPrice *= 1;
    } else if (ans == 1) {
      totalPrice *= 0.95;
    } else {
      totalPrice *= 0.9;
    }
    totalCostField.setText(totalPrice.toString());

    JOptionPane.showMessageDialog(
        this, "item name: " + itemNameField.getText() +
                  "\ntotalCost: " + totalCostField.getText());
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> { new Purchase().setVisible(true); });
  }
}
