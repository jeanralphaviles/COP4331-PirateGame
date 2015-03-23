/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.viewport;

import controller.Intent;

import java.util.ArrayList;

import model.GameObject;
import controller.IntentMap.IntentMap;


import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import utility.ImageUtil;

/**
 *
 * @author comcc_000
 */
public class TradeViewport extends ViewPort {

    /**
     * Creates new form FlavorImageViewport
     */
    
    
    public TradeViewport() {
        initComponents();
        setGUIsProperties();
        generateView();
    }
    
    @Override
    public void updateView(GameObject gameObject) {
        
    }

    @Override
    public ArrayList<IntentMap> generateIntentMapping() {
        ArrayList<IntentMap> ims = new ArrayList<>(1);
        
        ims.add(new IntentMap(continueAdventureButton, Intent.GOTO_GAME));
        
        return ims;
    }
    
      public static void main(String[] args){
        
        JFrame frame = new JFrame();
        frame.setSize(2500,2500);
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.add( new TradeViewport() );
     
        frame.setVisible(true);
    }
      
    
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Image image = ImageUtil.getImage( ImageUtil.trader_view_port_background, this.getWidth(), this.getHeight()).getImage();
        g.drawImage( image , 0, 0, this);
    }
    
    private void generateView(){
        
        DialogueViewport dialogueViewport = new DialogueViewport();
        this.addViewport( dialogueViewport );
        dialoguePanel.setLayout( new BorderLayout() );
        dialoguePanel.add( dialogueViewport , BorderLayout.CENTER );
    }

    /*
        By selecting the visual setting for tadeItemsPanel1's components
        this function will set the same properties to the rest of the panel's
        components.
    */
    private void setGUIsProperties(){
        // Set Items Name
        itemLabel1.setText("Blue Potions");
        itemLabel2.setText("Red Potions");
        itemLabel3.setText("Fuits");
        itemLabel4.setText("Books");
        itemLabel5.setText("Swords");
        
        // Set Items Values
        itemTextField1.setText("5");
        itemTextField2.setText("15");
        itemTextField3.setText("3");
        itemTextField4.setText("25");
        itemTextField5.setText("20");
        
        for (Component itemPanel : itemsPanel.getComponents() ){
            
            if( itemPanel instanceof JPanel){
                
                JPanel panels = (JPanel)itemPanel;
                for (  Component comp : panels.getComponents() ){

                    if ( comp instanceof JLabel ){
                        
                        System.out.println("Setting Label properties");
                        JLabel label = (JLabel)comp;
                        label.setBackground( itemLabel1.getBackground() );
                        label.setFont( itemLabel1.getFont()  );
                        label.setForeground( itemLabel1.getForeground() );
                        label.setHorizontalAlignment( itemLabel1.getHorizontalAlignment() );
                        label.setBorder( itemLabel1.getBorder() );
                        label.setOpaque(false);
                    }
                    else if ( comp instanceof JPanel){

                         JPanel insidePanels = (JPanel)comp;
                         
                         for (Component panelsComp : insidePanels.getComponents() ){
                             
                             if ( panelsComp instanceof JButton ){
                                 
                                 JButton button = (JButton)panelsComp;
                                 button.setBackground( bluePotionButton1.getBackground() );
                             }
                         }
                    }
                    else if ( comp instanceof JTextField){
                        
                        JTextField textField = (JTextField)comp;
                        
                        textField.setFont(itemTextField1.getFont() ); // NOI18N
                        textField.setForeground( itemTextField1.getBackground() );
                        textField.setHorizontalAlignment( itemTextField1.getHorizontalAlignment() );
                        textField.setBorder( itemTextField1.getBorder());
                        textField.setEditable(false);

                        textField.setOpaque(false);
                    }
                }
                
            }
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel(){

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                // Draw The Trader
                Image image = ImageUtil.getImage( ImageUtil.trader_pic, this.getWidth(), this.getHeight()).getImage();
                g.drawImage( image , 0, 0, this);

            }
        };
        itemsPanel = new javax.swing.JPanel();
        itemPanel1 = new javax.swing.JPanel();
        itemLabel1 = new javax.swing.JLabel();
        itemTextField1 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        bluePotionButton1 = new javax.swing.JButton(){
            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);
                Image image = ImageUtil.getImage( ImageUtil.blue_potion_1, this.getWidth(), this.getHeight()).getImage();
                g.drawImage( image , 0, 0, this);

            }

        };
        jButton2 = new javax.swing.JButton(){

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);
                Image image = ImageUtil.getImage( ImageUtil.blue_potion_4, this.getWidth(), this.getHeight()).getImage();
                g.drawImage( image , 0, 0, this);

            }
        };
        jButton3 = new javax.swing.JButton(){

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);
                Image image = ImageUtil.getImage( ImageUtil.blue_potion_2, this.getWidth(), this.getHeight()).getImage();
                g.drawImage( image , 0, 0, this);

            }

        };
        jButton4 = new javax.swing.JButton(){

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);
                Image image = ImageUtil.getImage( ImageUtil.blue_potion_3, this.getWidth(), this.getHeight()).getImage();
                g.drawImage( image , 0, 0, this);

            }

        };
        itemPanel2 = new javax.swing.JPanel();
        itemLabel2 = new javax.swing.JLabel();
        itemTextField2 = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton(){

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);
                Image image = ImageUtil.getImage( ImageUtil.red_potion_1, this.getWidth(), this.getHeight()).getImage();
                g.drawImage( image , 0, 0, this);

            }

        };
        jButton10 = new javax.swing.JButton(){

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);
                Image image = ImageUtil.getImage( ImageUtil.red_potion_4, this.getWidth(), this.getHeight()).getImage();
                g.drawImage( image , 0, 0, this);

            }

        };
        jButton11 = new javax.swing.JButton(){

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);
                Image image = ImageUtil.getImage( ImageUtil.red_potion_2, this.getWidth(), this.getHeight()).getImage();
                g.drawImage( image , 0, 0, this);

            }

        };
        jButton12 = new javax.swing.JButton(){

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);
                Image image = ImageUtil.getImage( ImageUtil.red_potion_3, this.getWidth(), this.getHeight()).getImage();
                g.drawImage( image , 0, 0, this);

            }

        };
        itemPanel3 = new javax.swing.JPanel();
        itemLabel3 = new javax.swing.JLabel();
        itemTextField3 = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jButton13 = new javax.swing.JButton(){

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);
                Image image = ImageUtil.getImage( ImageUtil.fruit_1, this.getWidth(), this.getHeight()).getImage();
                g.drawImage( image , 0, 0, this);

            }

        };
        jButton14 = new javax.swing.JButton(){

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);
                Image image = ImageUtil.getImage( ImageUtil.fruit_4, this.getWidth(), this.getHeight()).getImage();
                g.drawImage( image , 0, 0, this);

            }

        };
        jButton15 = new javax.swing.JButton(){

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);
                Image image = ImageUtil.getImage( ImageUtil.fruit_2, this.getWidth(), this.getHeight()).getImage();
                g.drawImage( image , 0, 0, this);

            }

        };
        jButton16 = new javax.swing.JButton(){

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);
                Image image = ImageUtil.getImage( ImageUtil.fruit_3, this.getWidth(), this.getHeight()).getImage();
                g.drawImage( image , 0, 0, this);

            }

        };
        itemPanel4 = new javax.swing.JPanel();
        itemLabel4 = new javax.swing.JLabel();
        itemTextField4 = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jButton17 = new javax.swing.JButton(){

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);
                Image image = ImageUtil.getImage( ImageUtil.book_1, this.getWidth(), this.getHeight()).getImage();
                g.drawImage( image , 0, 0, this);

            }

        };
        jButton18 = new javax.swing.JButton(){

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);
                Image image = ImageUtil.getImage( ImageUtil.book_4, this.getWidth(), this.getHeight()).getImage();
                g.drawImage( image , 0, 0, this);

            }

        };
        jButton19 = new javax.swing.JButton(){

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);
                Image image = ImageUtil.getImage( ImageUtil.book_2, this.getWidth(), this.getHeight()).getImage();
                g.drawImage( image , 0, 0, this);

            }

        };
        jButton20 = new javax.swing.JButton(){

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);
                Image image = ImageUtil.getImage( ImageUtil.book_3, this.getWidth(), this.getHeight()).getImage();
                g.drawImage( image , 0, 0, this);

            }

        };
        itemPanel5 = new javax.swing.JPanel();
        itemLabel5 = new javax.swing.JLabel();
        itemTextField5 = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jButton21 = new javax.swing.JButton(){

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);
                Image image = ImageUtil.getImage( ImageUtil.sword_1, this.getWidth(), this.getHeight()).getImage();
                g.drawImage( image , 0, 0, this);

            }

        };
        jButton22 = new javax.swing.JButton(){

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);
                Image image = ImageUtil.getImage( ImageUtil.sword_4, this.getWidth(), this.getHeight()).getImage();
                g.drawImage( image , 0, 0, this);

            }

        };
        jButton23 = new javax.swing.JButton(){

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);
                Image image = ImageUtil.getImage( ImageUtil.sword_2, this.getWidth(), this.getHeight()).getImage();
                g.drawImage( image , 0, 0, this);

            }

        };
        jButton24 = new javax.swing.JButton(){

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);
                Image image = ImageUtil.getImage( ImageUtil.sword_3, this.getWidth(), this.getHeight()).getImage();
                g.drawImage( image , 0, 0, this);

            }

        };
        selectionPanel = new javax.swing.JPanel();
        continueAdventureButton = new javax.swing.JButton(){

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);
                Image image = ImageUtil.getImage( ImageUtil.go_to_gamescreen_pic, this.getWidth(), this.getHeight()).getImage();
                g.drawImage( image , 0, 0, this);

                this.setOpaque(false);
                this.setContentAreaFilled(false);
                this.setBorderPainted(false);

            }

        };
        jPanel4 = new javax.swing.JPanel();
        bootyPanel = new javax.swing.JPanel(){

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);
                Image image = ImageUtil.getImage( ImageUtil.booty_pic, 75, 50).getImage();
                g.drawImage( image , bootValueLabel.getX()+ bootValueLabel.getWidth(), this.getHeight()/2 - image.getHeight(this)/2, this);

            }

        };
        bootValueLabel = new javax.swing.JLabel();
        bootyNameLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        dialoguePanel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(151, 102, 52));

        jPanel1.setBackground(new java.awt.Color(209, 125, 40));
        jPanel1.setOpaque(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 225, Short.MAX_VALUE)
        );

        itemsPanel.setBackground(new java.awt.Color(201, 168, 135));
        itemsPanel.setOpaque(false);

        itemPanel1.setOpaque(false);

        itemLabel1.setBackground(new java.awt.Color(255, 254, 254));
        itemLabel1.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        itemLabel1.setForeground(new java.awt.Color(150, 105, 150));
        itemLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        itemLabel1.setText("Name");
        itemLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 1, 1, new java.awt.Color(0, 0, 0)));
        itemTextField1.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        itemTextField1.setForeground(new java.awt.Color(172, 227, 25));
        itemTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        itemTextField1.setText("Price");
        itemTextField1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 4, 4, new java.awt.Color(0, 0, 0)));
        itemTextField1.setOpaque(false);

        jPanel3.setBackground(new java.awt.Color(149, 101, 54));
        jPanel3.setOpaque(false);

        bluePotionButton1.setBackground(new java.awt.Color(57, 57, 30));

        jButton2.setText("jButton1");

        jButton3.setBackground(new java.awt.Color(57, 57, 30));
        jButton3.setBorder(new javax.swing.border.MatteBorder(null));

        jButton4.setText("jButton1");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bluePotionButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bluePotionButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout itemPanel1Layout = new javax.swing.GroupLayout(itemPanel1);
        itemPanel1.setLayout(itemPanel1Layout);
        itemPanel1Layout.setHorizontalGroup(
            itemPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(itemPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(itemLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(itemTextField1)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        itemPanel1Layout.setVerticalGroup(
            itemPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(itemLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(itemTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        itemPanel2.setOpaque(false);

        itemLabel2.setBackground(new java.awt.Color(159, 120, 80));
        itemLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        itemLabel2.setText("Name");

        itemTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        itemTextField2.setText("Price");

        jPanel9.setBackground(new java.awt.Color(149, 101, 54));
        jPanel9.setOpaque(false);

        jButton9.setText("jButton1");

        jButton10.setText("jButton1");

        jButton11.setText("jButton1");

        jButton12.setText("jButton1");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel9.setBackground(new java.awt.Color(149, 101, 54));

        jButton9.setText("jButton1");

        jButton10.setText("jButton1");

        jButton11.setText("jButton1");

        jButton12.setText("jButton1");

        javax.swing.GroupLayout itemPanel2Layout = new javax.swing.GroupLayout(itemPanel2);
        itemPanel2.setLayout(itemPanel2Layout);
        itemPanel2Layout.setHorizontalGroup(
            itemPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, itemPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(itemPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(itemLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(itemTextField2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(itemPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(itemPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 146, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        itemPanel2Layout.setVerticalGroup(
            itemPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(itemLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(itemTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(itemPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(itemPanel2Layout.createSequentialGroup()
                    .addGap(58, 58, 58)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(60, 60, 60)))
        );

        itemPanel3.setOpaque(false);

        itemLabel3.setBackground(new java.awt.Color(175, 129, 83));
        itemLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        itemLabel3.setText("Name");
        itemLabel3.setOpaque(true);

        itemTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        itemTextField3.setText("Price");

        jPanel10.setBackground(new java.awt.Color(149, 101, 54));
        jPanel10.setOpaque(false);

        jButton13.setText("jButton1");

        jButton14.setText("jButton1");

        jButton15.setText("jButton1");

        jButton16.setText("jButton1");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                    .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                    .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel10.setBackground(new java.awt.Color(149, 101, 54));

        jButton13.setText("jButton1");

        jButton14.setText("jButton1");

        jButton15.setText("jButton1");

        jButton16.setText("jButton1");

        javax.swing.GroupLayout itemPanel3Layout = new javax.swing.GroupLayout(itemPanel3);
        itemPanel3.setLayout(itemPanel3Layout);
        itemPanel3Layout.setHorizontalGroup(
            itemPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, itemPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(itemPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(itemLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(itemTextField3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(itemPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(itemPanel3Layout.createSequentialGroup()
                    .addGap(11, 11, 11)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 146, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        itemPanel3Layout.setVerticalGroup(
            itemPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(itemLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 177, Short.MAX_VALUE)
                .addComponent(itemTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(itemPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(itemPanel3Layout.createSequentialGroup()
                    .addGap(59, 59, 59)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(59, 59, 59)))
        );

        itemPanel4.setOpaque(false);

        itemLabel4.setBackground(new java.awt.Color(159, 120, 80));
        itemLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        itemLabel4.setText("Name");
        itemLabel4.setOpaque(true);

        itemTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        itemTextField4.setText("Price");

        jPanel11.setBackground(new java.awt.Color(149, 101, 54));
        jPanel11.setOpaque(false);

        jButton17.setText("jButton1");

        jButton18.setText("jButton1");

        jButton19.setText("jButton1");

        jButton20.setText("jButton1");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jButton17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addComponent(jButton20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton17, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                    .addComponent(jButton19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                    .addComponent(jButton20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel11.setBackground(new java.awt.Color(149, 101, 54));

        jButton17.setText("jButton1");

        jButton18.setText("jButton1");

        jButton19.setText("jButton1");

        jButton20.setText("jButton1");

        javax.swing.GroupLayout itemPanel4Layout = new javax.swing.GroupLayout(itemPanel4);
        itemPanel4.setLayout(itemPanel4Layout);
        itemPanel4Layout.setHorizontalGroup(
            itemPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, itemPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(itemPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(itemLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(itemTextField4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(itemPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(itemPanel4Layout.createSequentialGroup()
                    .addGap(11, 11, 11)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 146, Short.MAX_VALUE)
                    .addGap(11, 11, 11)))
        );
        itemPanel4Layout.setVerticalGroup(
            itemPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(itemLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 178, Short.MAX_VALUE)
                .addComponent(itemTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(itemPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(itemPanel4Layout.createSequentialGroup()
                    .addGap(59, 59, 59)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(59, 59, 59)))
        );

        itemPanel5.setOpaque(false);

        itemLabel5.setBackground(new java.awt.Color(175, 129, 83));
        itemLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        itemLabel5.setText("Name");
        itemLabel5.setOpaque(true);

        itemTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        itemTextField5.setText("Price");

        jPanel12.setBackground(new java.awt.Color(149, 101, 54));
        jPanel12.setOpaque(false);

        jButton21.setText("jButton1");

        jButton22.setText("jButton1");

        jButton23.setText("jButton1");

        jButton24.setText("jButton1");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jButton21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addComponent(jButton24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton21, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                    .addComponent(jButton23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton22, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                    .addComponent(jButton24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel12.setBackground(new java.awt.Color(149, 101, 54));

        jButton21.setText("jButton1");

        jButton22.setText("jButton1");

        jButton23.setText("jButton1");

        jButton24.setText("jButton1");

        javax.swing.GroupLayout itemPanel5Layout = new javax.swing.GroupLayout(itemPanel5);
        itemPanel5.setLayout(itemPanel5Layout);
        itemPanel5Layout.setHorizontalGroup(
            itemPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, itemPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(itemPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(itemLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(itemTextField5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(itemPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(itemPanel5Layout.createSequentialGroup()
                    .addGap(11, 11, 11)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 146, Short.MAX_VALUE)
                    .addGap(11, 11, 11)))
        );
        itemPanel5Layout.setVerticalGroup(
            itemPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(itemLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 178, Short.MAX_VALUE)
                .addComponent(itemTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(itemPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(itemPanel5Layout.createSequentialGroup()
                    .addGap(59, 59, 59)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(59, 59, 59)))
        );

        javax.swing.GroupLayout itemsPanelLayout = new javax.swing.GroupLayout(itemsPanel);
        itemsPanel.setLayout(itemsPanelLayout);
        itemsPanelLayout.setHorizontalGroup(
            itemsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, itemsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(itemPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(itemPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(itemPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(itemPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(itemPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        itemsPanelLayout.setVerticalGroup(
            itemsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, itemsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(itemsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(itemPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(itemPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(itemPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(itemPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(itemPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        selectionPanel.setBackground(new java.awt.Color(138, 83, 28));
        selectionPanel.setOpaque(false);

        continueAdventureButton.setText("Continue");

        jPanel4.setOpaque(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        bootyPanel.setOpaque(false);

        bootValueLabel.setBackground(new java.awt.Color(205, 151, 97));
        bootValueLabel.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        bootValueLabel.setForeground(new java.awt.Color(172, 227, 25));
        bootValueLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bootValueLabel.setText("5");

        bootyNameLabel.setBackground(new java.awt.Color(97, 71, 45));
        bootyNameLabel.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        bootyNameLabel.setForeground(new java.awt.Color(172, 227, 25));
        bootyNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bootyNameLabel.setText("Booty:");

        javax.swing.GroupLayout bootyPanelLayout = new javax.swing.GroupLayout(bootyPanel);
        bootyPanel.setLayout(bootyPanelLayout);
        bootyPanelLayout.setHorizontalGroup(
            bootyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bootyPanelLayout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addComponent(bootValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(bootyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bootyPanelLayout.createSequentialGroup()
                    .addComponent(bootyNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 257, Short.MAX_VALUE)))
        );
        bootyPanelLayout.setVerticalGroup(
            bootyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bootValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(bootyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(bootyNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout selectionPanelLayout = new javax.swing.GroupLayout(selectionPanel);
        selectionPanel.setLayout(selectionPanelLayout);
        selectionPanelLayout.setHorizontalGroup(
            selectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, selectionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bootyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(continueAdventureButton, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        selectionPanelLayout.setVerticalGroup(
            selectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, selectionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(selectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(continueAdventureButton, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(bootyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );

        jPanel2.setOpaque(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        dialoguePanel.setOpaque(false);

        javax.swing.GroupLayout dialoguePanelLayout = new javax.swing.GroupLayout(dialoguePanel);
        dialoguePanel.setLayout(dialoguePanelLayout);
        dialoguePanelLayout.setHorizontalGroup(
            dialoguePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        dialoguePanelLayout.setVerticalGroup(
            dialoguePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.setOpaque(false);

        dialoguePanel.setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(itemsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(selectionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dialoguePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dialoguePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(itemsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(selectionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bluePotionButton1;
    private javax.swing.JLabel bootValueLabel;
    private javax.swing.JLabel bootyNameLabel;
    private javax.swing.JPanel bootyPanel;
    private javax.swing.JButton continueAdventureButton;
    private javax.swing.JPanel dialoguePanel;
    private javax.swing.JLabel itemLabel1;
    private javax.swing.JLabel itemLabel2;
    private javax.swing.JLabel itemLabel3;
    private javax.swing.JLabel itemLabel4;
    private javax.swing.JLabel itemLabel5;
    private javax.swing.JPanel itemPanel1;
    private javax.swing.JPanel itemPanel2;
    private javax.swing.JPanel itemPanel3;
    private javax.swing.JPanel itemPanel4;
    private javax.swing.JPanel itemPanel5;
    private javax.swing.JTextField itemTextField1;
    private javax.swing.JTextField itemTextField2;
    private javax.swing.JTextField itemTextField3;
    private javax.swing.JTextField itemTextField4;
    private javax.swing.JTextField itemTextField5;
    private javax.swing.JPanel itemsPanel;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel selectionPanel;
    // End of variables declaration//GEN-END:variables
   
}
