package view.viewport;

import controller.Intent;
import java.util.ArrayList;
import model.GameObject;
import model.entity.occupation.Smasher;
import model.entity.occupation.Sneak;
import model.entity.occupation.Summoner;
import controller.IntentMap.IntentMap;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import utility.ImageUtil;
import utility.decal.Decal;

public class NewGamePopupViewport extends ViewPort {

    /*Properties*/
    
    
    /*Constructor*/
    
    public NewGamePopupViewport() {
        initComponents();
        setBackgroundThemes();
        setVisible(true);
    }
    
    /*Methods*/
    private void setBackgroundThemes(){
        
        avatarCharacterButton1.setIcon( ImageUtil.getImage( ImageUtil.character_parrotmancer) );
        avatarCharacterButton2.setIcon( ImageUtil.getImage( ImageUtil.character_gankplanker) );
        avatarCharacterButton3.setIcon( ImageUtil.getImage( ImageUtil.character_turnhat ) );
        characterTextArea2.setFont(characterTextArea1.getFont());
        characterTextArea3.setFont(characterTextArea1.getFont());
    }
    
       @Override
    protected void paintComponent(Graphics g){
        
        super.paintComponent(g);
        Image image = ImageUtil.getImage( ImageUtil.new_game_background, this.getWidth(), this.getHeight()).getImage();
        g.drawImage( image , 0, 0, this.getWidth(), this.getHeight(), this);
    }
   
    
    @Override
    public void updateView(GameObject gameObject) {
        
        characterTextArea1.setText(Summoner.description);
        characterTextArea2.setText(Smasher.description);
        characterTextArea3.setText(Sneak.description);
    }

    @Override
    public ArrayList<IntentMap> generateIntentMapping() {
        ArrayList<IntentMap> ims = new ArrayList<IntentMap>(1);
        beginAdventureButton.setEnabled(false);
         ims.add(new IntentMap(mainMenuButton, Intent.GOTO_MAIN));
        ims.add(new IntentMap(beginAdventureButton, Intent.BEGIN));
        ims.add(new IntentMap(nicknameTextField, Intent.SET_NICKNAME));
        ims.add(new IntentMap(avatarCharacterButton1, new Summoner(), Intent.SET_OCCUPATION));
        ims.add(new IntentMap(avatarCharacterButton2, new Smasher(), Intent.SET_OCCUPATION));
        ims.add(new IntentMap(avatarCharacterButton3, new Sneak(), Intent.SET_OCCUPATION));
        
        return ims;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        presentationLabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        avatarCharacterButton1 = new javax.swing.JButton();
        avatarCharaterPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        characterTextArea1 = new javax.swing.JTextArea();
        avatarCharacterButton2 = new javax.swing.JButton();
        avatarCharacterPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        characterTextArea2 = new javax.swing.JTextArea();
        avatarCharacterButton3 = new javax.swing.JButton();
        avatarCharacterPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        characterTextArea3 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        nicknameTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        beginAdventureButton = new javax.swing.JButton(){

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
        mainMenuButton = new javax.swing.JButton(){

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);
                Image image = ImageUtil.getImage( ImageUtil.go_to_mainscreen_pic, this.getWidth(), this.getHeight()).getImage();
                g.drawImage( image , 0, 0, this);

                this.setOpaque(false);
                this.setContentAreaFilled(false);
                this.setBorderPainted(false);

            }

        };

        jPanel3.setBackground(new java.awt.Color(230, 154, 78));
        jPanel3.setOpaque(false);

        presentationLabel.setFont(new java.awt.Font("Ubuntu", 3, 48)); // NOI18N
        presentationLabel.setForeground(new java.awt.Color(229, 196, 56));
        presentationLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        presentationLabel.setText("Welcome, select a character and nickname:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(presentationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(presentationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(158, 87, 17));
        jPanel4.setOpaque(false);

        avatarCharacterButton1.setBackground(new java.awt.Color(107, 25, 25));

        avatarCharaterPanel1.setOpaque(false);

        jScrollPane1.setOpaque(false);

        characterTextArea1.setColumns(20);
        characterTextArea1.setFont(new java.awt.Font("Ubuntu", 3, 18)); // NOI18N
        characterTextArea1.setLineWrap(true);
        characterTextArea1.setRows(5);
        characterTextArea1.setTabSize(15);
        characterTextArea1.setWrapStyleWord(true);
        characterTextArea1.setOpaque(false);
        jScrollPane1.setViewportView(characterTextArea1);

        javax.swing.GroupLayout avatarCharaterPanel1Layout = new javax.swing.GroupLayout(avatarCharaterPanel1);
        avatarCharaterPanel1.setLayout(avatarCharaterPanel1Layout);
        avatarCharaterPanel1Layout.setHorizontalGroup(
            avatarCharaterPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(avatarCharaterPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(avatarCharaterPanel1Layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                    .addGap(6, 6, 6)))
        );
        avatarCharaterPanel1Layout.setVerticalGroup(
            avatarCharaterPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 92, Short.MAX_VALUE)
            .addGroup(avatarCharaterPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(avatarCharaterPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane1)
                    .addGap(0, 0, 0)))
        );

        avatarCharacterButton2.setBackground(new java.awt.Color(107, 25, 25));

        jScrollPane3.setOpaque(false);

        characterTextArea2.setColumns(20);
        characterTextArea2.setRows(5);
        characterTextArea2.setOpaque(false);
        jScrollPane3.setViewportView(characterTextArea2);

        javax.swing.GroupLayout avatarCharacterPanel2Layout = new javax.swing.GroupLayout(avatarCharacterPanel2);
        avatarCharacterPanel2.setLayout(avatarCharacterPanel2Layout);
        avatarCharacterPanel2Layout.setHorizontalGroup(
            avatarCharacterPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(avatarCharacterPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(avatarCharacterPanel2Layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                    .addGap(6, 6, 6)))
        );
        avatarCharacterPanel2Layout.setVerticalGroup(
            avatarCharacterPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(avatarCharacterPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(avatarCharacterPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane3)
                    .addGap(0, 0, 0)))
        );

        avatarCharacterButton3.setBackground(new java.awt.Color(107, 25, 25));

        characterTextArea3.setColumns(20);
        characterTextArea3.setRows(5);
        characterTextArea3.setOpaque(false);
        jScrollPane4.setViewportView(characterTextArea3);

        javax.swing.GroupLayout avatarCharacterPanel3Layout = new javax.swing.GroupLayout(avatarCharacterPanel3);
        avatarCharacterPanel3.setLayout(avatarCharacterPanel3Layout);
        avatarCharacterPanel3Layout.setHorizontalGroup(
            avatarCharacterPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(avatarCharacterPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(avatarCharacterPanel3Layout.createSequentialGroup()
                    .addGap(6, 6, 6)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                    .addGap(7, 7, 7)))
        );
        avatarCharacterPanel3Layout.setVerticalGroup(
            avatarCharacterPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(avatarCharacterPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(avatarCharacterPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane4)
                    .addGap(0, 0, 0)))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(avatarCharacterButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(75, 75, 75))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(avatarCharaterPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(60, 60, 60)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(avatarCharacterButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(15, 15, 15))
                    .addComponent(avatarCharacterPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(63, 63, 63)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(avatarCharacterButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(15, 15, 15))
                    .addComponent(avatarCharacterPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(avatarCharacterButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(avatarCharacterButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(avatarCharacterButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(avatarCharaterPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(avatarCharacterPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(avatarCharacterPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(83, 80, 76));
        jPanel1.setOpaque(false);

        nicknameTextField.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        nicknameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nicknameTextField.setText("Woodman");
        nicknameTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 4, 4, new java.awt.Color(158, 87, 17)));

        jLabel1.setBackground(new java.awt.Color(158, 87, 17));
        jLabel1.setFont(new java.awt.Font("Ubuntu", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(195, 105, 105));
        jLabel1.setText("Nickname: ");

        beginAdventureButton.setBackground(new java.awt.Color(208, 208, 61));
        beginAdventureButton.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        beginAdventureButton.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 8, 8, new java.awt.Color(70, 35, 1)));
        beginAdventureButton.setOpaque(true);

        mainMenuButton.setBackground(new java.awt.Color(76, 76, 76));
        mainMenuButton.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        mainMenuButton.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 8, 8, new java.awt.Color(70, 35, 1)));
        mainMenuButton.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(nicknameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(beginAdventureButton, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(mainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(beginAdventureButton, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                        .addComponent(mainMenuButton, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(19, 19, 19)
                        .addComponent(nicknameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton avatarCharacterButton1;
    private javax.swing.JButton avatarCharacterButton2;
    private javax.swing.JButton avatarCharacterButton3;
    private javax.swing.JPanel avatarCharacterPanel2;
    private javax.swing.JPanel avatarCharacterPanel3;
    private javax.swing.JPanel avatarCharaterPanel1;
    private javax.swing.JButton beginAdventureButton;
    private javax.swing.JTextArea characterTextArea1;
    private javax.swing.JTextArea characterTextArea2;
    private javax.swing.JTextArea characterTextArea3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton mainMenuButton;
    private javax.swing.JTextField nicknameTextField;
    private javax.swing.JLabel presentationLabel;
    // End of variables declaration//GEN-END:variables

    
}
