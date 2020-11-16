package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUItest implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JButton button;

    public GUItest() {
        frame = new JFrame();
        panel = new JPanel();

        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Time to Tic Tac Toe!");
        frame.add(panel);

//        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new FlowLayout());


        button = new JButton("Click this");
        button.addActionListener(this);
        panel.add(button);

        label = new JLabel("Unclicked");
        panel.add(label);

        frame.setVisible(true);
//        frame.setResizable(false);

    }


    public static void main(String[] args) {
        new GUItest();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        label.setText("Clicked");
    }
}
