package Frontend;

import javax.swing.*;

public class InterfazPrincipal extends JFrame {
    private JPanel mainPanel;
    private JButton button1;
    private JButton button2;
    private JButton button3;

    public InterfazPrincipal(String title){
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

    }

    public static void main(String[] args) {
        JFrame frame = new InterfazPrincipal("Front-end TAP");
        frame.setVisible(true);
    }


}
