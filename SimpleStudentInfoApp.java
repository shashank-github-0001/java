import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

class Student {

    String name, usn, address, category;
    int age;
    double sgpa1, sgpa2, sgpa3, sgpa4, cgpa;

    Student(
        String name,
        String usn,
        int age,
        String address,
        double sgpa1,
        double sgpa2,
        double sgpa3,
        double sgpa4,
        String category
    ) {
        this.name = name;
        this.usn = usn;
        this.age = age;
        this.address = address;
        this.sgpa1 = sgpa1;
        this.sgpa2 = sgpa2;
        this.sgpa3 = sgpa3;
        this.sgpa4 = sgpa4;
        this.cgpa = (sgpa1 + sgpa2 + sgpa3 + sgpa4) / 4;
        this.category = category;
    }

    @Override
    public String toString() {
        return (
            "Name: " +
            name +
            ", USN: " +
            usn +
            ", Age: " +
            age +
            ", Address: " +
            address +
            ", CGPA: " +
            cgpa +
            ", Category: " +
            category
        );
    }
}

public class SimpleStudentInfoApp extends JFrame implements ActionListener {

    private JTextField nameField, usnField, ageField, addressField, sgpa1Field, sgpa2Field, sgpa3Field, sgpa4Field, categoryField;
    private JTextArea textArea;
    private ArrayList<Student> studentList;

    public SimpleStudentInfoApp() {
        setTitle("Student Information");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        studentList = new ArrayList<>();

        JPanel inputPanel = new JPanel(new GridLayout(9, 2));
        JPanel buttonPanel = new JPanel(new FlowLayout());

        nameField = new JTextField();
        usnField = new JTextField();
        ageField = new JTextField();
        addressField = new JTextField();
        sgpa1Field = new JTextField();
        sgpa2Field = new JTextField();
        sgpa3Field = new JTextField();
        sgpa4Field = new JTextField();
        categoryField = new JTextField();
        textArea = new JTextArea(10, 30);
        textArea.setEditable(false);

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("USN:"));
        inputPanel.add(usnField);
        inputPanel.add(new JLabel("Age:"));
        inputPanel.add(ageField);
        inputPanel.add(new JLabel("Address:"));
        inputPanel.add(addressField);
        inputPanel.add(new JLabel("SGPA 1:"));
        inputPanel.add(sgpa1Field);
        inputPanel.add(new JLabel("SGPA 2:"));
        inputPanel.add(sgpa2Field);
        inputPanel.add(new JLabel("SGPA 3:"));
        inputPanel.add(sgpa3Field);
        inputPanel.add(new JLabel("SGPA 4:"));
        inputPanel.add(sgpa4Field);
        inputPanel.add(new JLabel("Category:"));
        inputPanel.add(categoryField);

        JButton computeButton = new JButton("Compute");
        JButton doneButton = new JButton("Done");
        JButton displayButton = new JButton("Display");

        computeButton.addActionListener(this);
        doneButton.addActionListener(this);
        displayButton.addActionListener(this);

        buttonPanel.add(computeButton);
        buttonPanel.add(doneButton);
        buttonPanel.add(displayButton);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(new JScrollPane(textArea), BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Compute")) {
            if (validateFields()) {
                double sgpa1 = Double.parseDouble(sgpa1Field.getText());
                double sgpa2 = Double.parseDouble(sgpa2Field.getText());
                double sgpa3 = Double.parseDouble(sgpa3Field.getText());
                double sgpa4 = Double.parseDouble(sgpa4Field.getText());
                double cgpa = (sgpa1 + sgpa2 + sgpa3 + sgpa4) / 4;
                JOptionPane.showMessageDialog(this, "CGPA: " + cgpa);
            }
        } else if (command.equals("Done")) {
            if (validateFields()) {
                String name = nameField.getText();
                String usn = usnField.getText();
                int age = Integer.parseInt(ageField.getText());
                String address = addressField.getText();
                double sgpa1 = Double.parseDouble(sgpa1Field.getText());
                double sgpa2 = Double.parseDouble(sgpa2Field.getText());
                double sgpa3 = Double.parseDouble(sgpa3Field.getText());
                double sgpa4 = Double.parseDouble(sgpa4Field.getText());
                String category = categoryField.getText();

                Student student = new Student(
                    name,
                    usn,
                    age,
                    address,
                    sgpa1,
                    sgpa2,
                    sgpa3,
                    sgpa4,
                    category
                );
                studentList.add(student);
            }
        } else if (command.equals("Display")) {
            displayStudents();
        }
    }

    private boolean validateFields() {
        if (
            nameField.getText().isEmpty() ||
            usnField.getText().isEmpty() ||
            ageField.getText().isEmpty() ||
            addressField.getText().isEmpty() ||
            sgpa1Field.getText().isEmpty() ||
            sgpa2Field.getText().isEmpty() ||
            sgpa3Field.getText().isEmpty() ||
            sgpa4Field.getText().isEmpty() ||
            categoryField.getText().isEmpty()
        ) {
            JOptionPane.showMessageDialog(
                this,
                "All fields are required",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE
            );
            return false;
        }

        try {
            int age = Integer.parseInt(ageField.getText());
            if (age <= 0) {
                JOptionPane.showMessageDialog(
                    this,
                    "Age must be a positive number",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
                );
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                this,
                "Age must be a number",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE
            );
            return false;
        }

        try {
            double sgpa1 = Double.parseDouble(sgpa1Field.getText());
            double sgpa2 = Double.parseDouble(sgpa2Field.getText());
            double sgpa3 = Double.parseDouble(sgpa3Field.getText());
            double sgpa4 = Double.parseDouble(sgpa4Field.getText());

            if (
                sgpa1 < 0 ||
                sgpa1 > 10 ||
                sgpa2 < 0 ||
                sgpa2 > 10 ||
                sgpa3 < 0 ||
                sgpa3 > 10 ||
                sgpa4 < 0 ||
                sgpa4 > 10
            ) {
                JOptionPane.showMessageDialog(
                    this,
                    "SGPA must be between 0 and 10",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
                );
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                this,
                "SGPA must be a number",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE
            );
            return false;
        }

        return true;
    }

    private void displayStudents() {
        textArea.setText("");
        for (Student student : studentList) {
            textArea.append(student.toString() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SimpleStudentInfoApp().setVisible(true);
        });
    }
}
