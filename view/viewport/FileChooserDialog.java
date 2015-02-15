package demo;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooserDialog extends JFrame {

    private JPanel panel;
    private JTextArea area;

    public FileChooserDialog() {

        initUI();
    }

    public final void initUI() {

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        ImageIcon open = new ImageIcon("open.png");

        JToolBar toolbar = new JToolBar();
        JButton openb = new JButton("LOAD GAME");
        JButton newnb = new JButton("NEW GAME");
        JButton exit = new JButton("EXIT");
        JButton pause = new JButton("PAUSE");
        

        openb.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                JFileChooser fileopen = new JFileChooser();
                FileFilter filter = new FileNameExtensionFilter("c files", "c");
                fileopen.addChoosableFileFilter(filter);

                int ret = fileopen.showDialog(panel, "Open file");

                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    String text = readFile(file);
                    area.setText(text);
                }

            }
        });
        newnb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            
            	
     
            test1 a = new test1()	;
            a.setVisible(true);
            
            }
        });
        
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	JOptionPane.showMessageDialog(panel, "Are you sure to quit?",
                        "Question", JOptionPane.QUESTION_MESSAGE);
            	System.exit(0);
            }
        });
        pause.addActionListener(new ActionListener(){
        	 @Override
             public void actionPerformed(ActionEvent event) {
        		 JOptionPane.showMessageDialog(panel, "GAME PAUSED",
                         "PAUSE SCREEN", JOptionPane.INFORMATION_MESSAGE);
        		
        		/* EventObjectEx pause= new EventObjectEx();
        		 pause.setVisible(true);*/
        		 
        	 }
        	
        });
                
        toolbar.add(newnb);
        toolbar.add(openb);
        toolbar.add(pause);
        
        
        toolbar.add(exit);
        

        area = new JTextArea();
        area.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane pane = new JScrollPane();
        pane.getViewport().add(area);

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(pane);
        add(panel);

        add(toolbar, BorderLayout.NORTH);

        setTitle("FileChooserDialog");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public String readFile(File file) {

        StringBuffer fileBuffer = null;
        String fileString = null;
        String line = null;

        try {
            FileReader in = new FileReader(file);
            BufferedReader brd = new BufferedReader(in);
            fileBuffer = new StringBuffer();

            while ((line = brd.readLine()) != null) {
                fileBuffer.append(line).append(
                        System.getProperty("line.separator"));
            }

            in.close();
            fileString = fileBuffer.toString();
        } catch (IOException e) {
            return null;
        }
        return fileString;
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                FileChooserDialog fcd = new FileChooserDialog();
                fcd.setVisible(true);
            }
        });
    }
}