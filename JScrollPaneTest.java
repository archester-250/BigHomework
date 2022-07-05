import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class JScrollPaneTest extends JFrame{
    public JScrollPaneTest(){
        Container container = getContentPane();
        container.setLayout(new GridLayout(2,2,10,10));
        JPanel p2 = new JPanel(new BorderLayout());
        p2.add(new JButton("b2"),BorderLayout.SOUTH);
        JTextArea ta = new JTextArea(20,50);
        JScrollPane sp = new JScrollPane(ta);
        container.add(sp);
        container.add(p2);
        setTitle("带滚动条的文字编辑器");
        setSize(400,400);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new JScrollPaneTest();
    }
}
