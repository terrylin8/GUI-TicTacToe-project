package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetNameWindow extends JDialog implements ActionListener {
//    private JFrame nameFrame;
//    private JPanel namePanel;
//    private JLabel label;
//    private JTextField box1;
//    private JTextField box2;

    private JTextField input1;
    private JTextField input2;

    private JButton confirmName;

    public SetNameWindow(JFrame frame) {
        super(frame, "Set player names!", true);
        setLayout(new FlowLayout());

//        nameFrame = new JFrame();
//        namePanel = new JPanel();

        JLabel nameLabel1 = new JLabel("Enter name of player 1 in box 1");
        JLabel nameLabel2 = new JLabel("Enter name of player 2 in box 2");
        add(nameLabel1);
        add(nameLabel2);

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setTitle("Set player names!");

        input1 = new JTextField(20);
        add(input1);
        input2 = new JTextField(20);
        add(input2);

        confirmName = new JButton("Confirm");
        confirmName.setActionCommand("name");
        confirmName.addActionListener(this);
        add(confirmName);

        setLayout(new FlowLayout());
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
