package module4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CampusCalendarUI extends JFrame {
    private EventList eventList;
    private JTextField titleField, dateField;
    private JTextArea descriptionArea, eventDisplay;
    private JButton addButton;

    public CampusCalendarUI() {
        eventList = new EventList();

        setTitle("Campus Calendar");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));

        inputPanel.add(new JLabel("Title:"));
        titleField = new JTextField();
        inputPanel.add(titleField);

        inputPanel.add(new JLabel("Date (dd-MM-yyyy):"));
        dateField = new JTextField();
        inputPanel.add(dateField);

        inputPanel.add(new JLabel("Description:"));
        descriptionArea = new JTextArea(3, 20);
        inputPanel.add(new JScrollPane(descriptionArea));

        addButton = new JButton("Add Event");
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.NORTH);

        eventDisplay = new JTextArea();
        eventDisplay.setEditable(false);
        add(new JScrollPane(eventDisplay), BorderLayout.CENTER);

        // Button Action
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addEvent();
            }
        });
    }

    private void addEvent() {
        String title = titleField.getText().trim();
        String description = descriptionArea.getText().trim();
        String dateText = dateField.getText().trim();

        try {
            LocalDate date = LocalDate.parse(dateText, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            Event newEvent = new Event(title, description, date);
            eventList.add(newEvent);

            eventDisplay.append(newEvent.toString() + "\n");

            titleField.setText("");
            descriptionArea.setText("");
            dateField.setText("");
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Use dd-MM-yyyy.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CampusCalendarUI().setVisible(true);
        });
    }
}