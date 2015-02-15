package demo;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JList;


public class EventObjectEx extends JFrame {

    private JList list;
    private DefaultListModel model;
    

    public EventObjectEx() {
        
        initUI();
    }
    
    private void initUI() {

        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);   

        model = new DefaultListModel();
        list = new JList(model);
        list.setMinimumSize(new Dimension(250, 150));
        list.setBorder(BorderFactory.createEtchedBorder());

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ClickAction());

        gl.setAutoCreateContainerGaps(true);
        
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(okButton)
                .addGap(20)
                .addComponent(list)
        );

        gl.setVerticalGroup(gl.createParallelGroup()
                .addComponent(okButton)
                .addComponent(list)
        );
        
        pack();

        setTitle("PAUSE SCREEN");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private class ClickAction extends AbstractAction {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            Locale locale = Locale.getDefault();
            Date date = new Date(e.getWhen());
            String tm = DateFormat.getTimeInstance(DateFormat.SHORT,
                    locale).format(date);

            if (!model.isEmpty()) {
                model.clear();
            }

            if (e.getID() == ActionEvent.ACTION_PERFORMED) {
                model.addElement("GAME IS BEEN PAUSED");
            }

            model.addElement("Time: " + tm);

            /*String source = e.getSource().getClass().getName();
            model.addElement("Source: " + source);*/

            int mod = e.getModifiers();

            StringBuffer buffer = new StringBuffer(" ");

            if ((mod & ActionEvent.ALT_MASK) > 0) {
                buffer.append("Alt ");
            }

            if ((mod & ActionEvent.SHIFT_MASK) > 0) {
                buffer.append("Shift ");
            }

            if ((mod & ActionEvent.META_MASK) > 0) {
                buffer.append("Meta ");
            }

            if ((mod & ActionEvent.CTRL_MASK) > 0) {
                buffer.append("Ctrl ");
            }

            model.addElement(buffer);
        }
    }        

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                EventObjectEx ex = new EventObjectEx();
                ex.setVisible(true);
            }
        });
    }
}