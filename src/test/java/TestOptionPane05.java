import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestOptionPane05 {

    public static void main(String[] args) {
        new TestOptionPane05();
    }

    protected JOptionPane getOptionPane(JComponent parent) {
        JOptionPane pane = null;
        if (!(parent instanceof JOptionPane)) {
            pane = getOptionPane((JComponent)parent.getParent());
        } else {
            pane = (JOptionPane) parent;
        }
        return pane;
    }

    public TestOptionPane05() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                final JButton okay = new JButton("Ok");
                okay.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane pane = getOptionPane((JComponent) e.getSource());
                        pane.setValue(okay);
                    }
                });
                final JButton cancel = new JButton("Cancel");
                cancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane pane = getOptionPane((JComponent) e.getSource());
                        pane.setValue(cancel);
                    }
                });

                int string = JOptionPane.showOptionDialog(
                        null,
                        "text",
                        "Get",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        new JButton[]{okay, cancel},
                        okay
                );
                System.out.println(string);
            }
        });
    }
}