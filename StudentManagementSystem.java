import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class StudentManagementSystem extends JFrame {
    private List<Student> students;
    private JTextField nameTextField, rollNumberTextField, gradeTextField;

    public StudentManagementSystem() {
        students = new ArrayList<>();
        setupUI();
    }

    private void setupUI() {
        setTitle("Student Management System");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        JLabel nameLabel = new JLabel("Name:");
        nameTextField = new JTextField(20);
        JLabel rollNumberLabel = new JLabel("Roll Number:");
        rollNumberTextField = new JTextField(10);
        JLabel gradeLabel = new JLabel("Grade:");
        gradeTextField = new JTextField(5);

        inputPanel.add(nameLabel);
        inputPanel.add(nameTextField);
        inputPanel.add(rollNumberLabel);
        inputPanel.add(rollNumberTextField);
        inputPanel.add(gradeLabel);
        inputPanel.add(gradeTextField);

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        JButton removeButton = new JButton("Remove Student");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeStudent();
            }
        });

        JButton searchButton = new JButton("Search Student");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchStudent();
            }
        });

        JButton displayButton = new JButton("Display All Students");
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAllStudents();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(displayButton);

        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void addStudent() {
        String name = nameTextField.getText().trim();
        String rollNumberStr = rollNumberTextField.getText().trim();
        String grade = gradeTextField.getText().trim();

        if (name.isEmpty() || rollNumberStr.isEmpty() || grade.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int rollNumber;
        try {
            rollNumber = Integer.parseInt(rollNumberStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid Roll Number format!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Student student = new Student(name, rollNumber, grade);
        students.add(student);
        JOptionPane.showMessageDialog(this, "Student added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        // Clear input fields
        nameTextField.setText("");
        rollNumberTextField.setText("");
        gradeTextField.setText("");
    }

    private void removeStudent() {
        String rollNumberStr = rollNumberTextField.getText().trim();

        if (rollNumberStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the Roll Number to remove!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int rollNumber;
        try {
            rollNumber = Integer.parseInt(rollNumberStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid Roll Number format!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean found = false;
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                students.remove(student);
                found = true;
                break;
            }
        }

        if (found) {
            JOptionPane.showMessageDialog(this, "Student removed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Student not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Clear input fields
        rollNumberTextField.setText("");
    }

    private void searchStudent() {
        String rollNumberStr = rollNumberTextField.getText().trim();

        if (rollNumberStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the Roll Number to search!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int rollNumber;
        try {
            rollNumber = Integer.parseInt(rollNumberStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid Roll Number format!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean found = false;
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                JOptionPane.showMessageDialog(this, "Student found:\n" + student, "Search Result", JOptionPane.INFORMATION_MESSAGE);
                found = true;
                break;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(this, "Student not found!", "Search Result", JOptionPane.INFORMATION_MESSAGE);
        }

        // Clear input fields
        rollNumberTextField.setText("");
    }

    private void displayAllStudents() {
        if (students.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No students in the system!", "Student List", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Student student : students) {
            sb.append(student).append("\n");
        }

        JOptionPane.showMessageDialog(this, "All Students:\n" + sb.toString(), "Student List", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudentManagementSystem().setVisible(true);
            }
        });
    }
}
