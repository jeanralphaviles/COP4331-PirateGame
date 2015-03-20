/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.viewport;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import model.GameObject;
import model.entity.Statistics;
import controller.controllerMap.IntentComponentMap;

/**
 *
 * @author comcc_000
 */
public class StatusViewport extends ViewPort {

    /**
     * Creates new form StatusViewport
     */
    public StatusViewport() {
        initComponents();
        initPanels();
        initProgressBars(10, 10);
        
        lifeOneLabel.setVisible(false);
        lifeTwoLabel.setVisible(false);
        lifeThreeLabel.setVisible(false);
        lifeFourLabel.setVisible(false);
        lifeFiveLabel.setVisible(false); 
    }
    
    public void initPanels() {    
        mainStatsPanel.setBackground(Color.LIGHT_GRAY);
        otherStatsPanel.setBackground(Color.LIGHT_GRAY);
        healthManaPanel.setBackground(Color.LIGHT_GRAY);
    }
    
    @Override
    public void updateView(GameObject gameObject) {
        Statistics statistics = gameObject.getAvatar().getDerivedStatistics();
    
        int health = statistics.getCurrentHealth();
        int maxHealth = statistics.getMaxHealth();
        int mana = statistics.getCurrentMana();
        int maxMana = 100;
        int level = statistics.getLevel();
        int livesLeft = statistics.getLivesLeft(); 
        int offense = statistics.getOffensiveRating();
        int defense = statistics.getDefensiveRating();
        int armor = statistics.getArmorRating();
        int strength = statistics.getStrength();
        int agility = statistics.getAgility();
        int intellect = statistics.getIntellect();
        int hardiness = statistics.getHardiness();
        int experience = statistics.getExperience();
        int experienceNeeded = 100 * level;
        int movement = statistics.getMovement();
        
        strengthLabel1.setText(Integer.toString(strength));
        agilityLabel1.setText(Integer.toString(agility));
        intellectLabel1.setText(Integer.toString(intellect));
        hardinessLabel1.setText(Integer.toString(hardiness));
        
        offensiveRatingLabel1.setText(Integer.toString(offense));
        defensiveRatingLabel1.setText(Integer.toString(defense));
        armorRatingLabel1.setText(Integer.toString(armor));
        movementLabel1.setText(Integer.toString(movement));
        
        updateProgressBars(health, mana, maxHealth, maxMana);
        
        String experienceString = Integer.toString(experience) + " / " + Integer.toString(experienceNeeded);
        experienceValueLabel.setText(experienceString);
        
        levelValueLabel.setText(Integer.toString(level));
        
        updateLives(livesLeft);
    }

    @Override
    public ArrayList<IntentComponentMap> generateIntentComponentMapping() {
        ArrayList<IntentComponentMap> icms = new ArrayList<IntentComponentMap>(1);
        return icms;
    }
    
    public void initProgressBars(int maxHealth, int maxMana) {
        healthBar.setMaximum(maxHealth);
        healthBar.setMinimum(0);
        healthBar.setStringPainted(true);
        healthBar.setForeground(Color.red);
        healthBar.setBackground(Color.red);
        
        manaBar.setMaximum(maxMana);
        manaBar.setMinimum(0);
        manaBar.setStringPainted(true);
        manaBar.setForeground(Color.blue);
        manaBar.setBackground(Color.red);
    }
    
    public void updateProgressBars (int health, int mana, int maxHealth, int maxMana) {
        healthBar.setMaximum(maxHealth);
        manaBar.setMaximum(maxMana);
        healthBar.setValue(health);
        manaBar.setValue(mana);
    }
    
    public void updateLives(int livesLeft) {
        if (livesLeft >= 1) {
            lifeOneLabel.setVisible(true);
        }
        if (livesLeft >= 2) {
            lifeTwoLabel.setVisible(true);
        }
        if (livesLeft >= 3) {
            lifeThreeLabel.setVisible(true);
        }
        if (livesLeft >= 4) {
            lifeFourLabel.setVisible(true);
        }
        if (livesLeft >= 5) {
            lifeFiveLabel.setVisible(true);
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

        jFrame1 = new javax.swing.JFrame();
        jFrame2 = new javax.swing.JFrame();
        jFrame3 = new javax.swing.JFrame();
        jFrame4 = new javax.swing.JFrame();
        jPanel3 = new javax.swing.JPanel();
        mainStatsPanel = new javax.swing.JPanel();
        strengthLabel = new javax.swing.JLabel();
        agilityLabel = new javax.swing.JLabel();
        intellectLabel = new javax.swing.JLabel();
        hardinessLabel = new javax.swing.JLabel();
        strengthLabel1 = new javax.swing.JLabel();
        agilityLabel1 = new javax.swing.JLabel();
        intellectLabel1 = new javax.swing.JLabel();
        hardinessLabel1 = new javax.swing.JLabel();
        healthManaPanel = new javax.swing.JPanel();
        healthLabel = new javax.swing.JLabel();
        manaLabel = new javax.swing.JLabel();
        levelLabel = new javax.swing.JLabel();
        livesLabel = new javax.swing.JLabel();
        experienceLabel = new javax.swing.JLabel();
        healthBar = new javax.swing.JProgressBar();
        manaBar = new javax.swing.JProgressBar();
        experienceValueLabel = new javax.swing.JLabel();
        levelValueLabel = new javax.swing.JLabel();
        lifeOneLabel = new javax.swing.JLabel();
        lifeTwoLabel = new javax.swing.JLabel();
        lifeFiveLabel = new javax.swing.JLabel();
        lifeFourLabel = new javax.swing.JLabel();
        lifeThreeLabel = new javax.swing.JLabel();
        otherStatsPanel = new javax.swing.JPanel();
        offensiveRatingLabel = new javax.swing.JLabel();
        defensiveRatingLabel = new javax.swing.JLabel();
        armorRatingLabel = new javax.swing.JLabel();
        movementLabel = new javax.swing.JLabel();
        defensiveRatingLabel1 = new javax.swing.JLabel();
        offensiveRatingLabel1 = new javax.swing.JLabel();
        armorRatingLabel1 = new javax.swing.JLabel();
        movementLabel1 = new javax.swing.JLabel();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame3Layout = new javax.swing.GroupLayout(jFrame3.getContentPane());
        jFrame3.getContentPane().setLayout(jFrame3Layout);
        jFrame3Layout.setHorizontalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame3Layout.setVerticalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame4Layout = new javax.swing.GroupLayout(jFrame4.getContentPane());
        jFrame4.getContentPane().setLayout(jFrame4Layout);
        jFrame4Layout.setHorizontalGroup(
            jFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame4Layout.setVerticalGroup(
            jFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 128, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 174, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(250, 250));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mainStatsPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        strengthLabel.setFont(new java.awt.Font("Luminari", 1, 20)); // NOI18N
        strengthLabel.setText("Strength");

        agilityLabel.setFont(new java.awt.Font("Luminari", 1, 20)); // NOI18N
        agilityLabel.setText("Agility");

        intellectLabel.setFont(new java.awt.Font("Luminari", 1, 20)); // NOI18N
        intellectLabel.setText("Intellect");

        hardinessLabel.setFont(new java.awt.Font("Luminari", 1, 20)); // NOI18N
        hardinessLabel.setText("Hardiness");

        strengthLabel1.setFont(new java.awt.Font("Luminari", 1, 20)); // NOI18N
        strengthLabel1.setText("10");

        agilityLabel1.setFont(new java.awt.Font("Luminari", 1, 20)); // NOI18N
        agilityLabel1.setText("10");

        intellectLabel1.setFont(new java.awt.Font("Luminari", 1, 20)); // NOI18N
        intellectLabel1.setText("10");

        hardinessLabel1.setFont(new java.awt.Font("Luminari", 1, 20)); // NOI18N
        hardinessLabel1.setText("10");

        javax.swing.GroupLayout mainStatsPanelLayout = new javax.swing.GroupLayout(mainStatsPanel);
        mainStatsPanel.setLayout(mainStatsPanelLayout);
        mainStatsPanelLayout.setHorizontalGroup(
            mainStatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainStatsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainStatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(strengthLabel)
                    .addComponent(agilityLabel)
                    .addComponent(intellectLabel)
                    .addComponent(hardinessLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(mainStatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(intellectLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(agilityLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(strengthLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hardinessLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        mainStatsPanelLayout.setVerticalGroup(
            mainStatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainStatsPanelLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(mainStatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(strengthLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(strengthLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(mainStatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agilityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(agilityLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(mainStatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(intellectLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(intellectLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(mainStatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hardinessLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hardinessLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        add(mainStatsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 180, 190));

        healthManaPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        healthLabel.setFont(new java.awt.Font("Luminari", 3, 22)); // NOI18N
        healthLabel.setText("Health : ");

        manaLabel.setFont(new java.awt.Font("Luminari", 3, 22)); // NOI18N
        manaLabel.setText("Mana : ");

        levelLabel.setFont(new java.awt.Font("Luminari", 0, 14)); // NOI18N
        levelLabel.setText("Level : ");

        livesLabel.setFont(new java.awt.Font("Luminari", 1, 14)); // NOI18N
        livesLabel.setText("Lives Left : ");

        experienceLabel.setFont(new java.awt.Font("Luminari", 0, 14)); // NOI18N
        experienceLabel.setText("Experience : ");

        healthBar.setForeground(new java.awt.Color(204, 0, 0));

        manaBar.setForeground(new java.awt.Color(0, 0, 153));

        experienceValueLabel.setFont(new java.awt.Font("Luminari", 1, 24)); // NOI18N
        experienceValueLabel.setText("100 / 100");

        levelValueLabel.setFont(new java.awt.Font("Luminari", 1, 24)); // NOI18N
        levelValueLabel.setText("2   ");

        lifeOneLabel.setText("<3");

        lifeTwoLabel.setText("<3");

        lifeFiveLabel.setText("<3");

        lifeFourLabel.setText("<3");

        lifeThreeLabel.setText("<3");

        javax.swing.GroupLayout healthManaPanelLayout = new javax.swing.GroupLayout(healthManaPanel);
        healthManaPanel.setLayout(healthManaPanelLayout);
        healthManaPanelLayout.setHorizontalGroup(
            healthManaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(healthManaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(healthManaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(healthManaPanelLayout.createSequentialGroup()
                        .addComponent(manaLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(manaBar, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(healthManaPanelLayout.createSequentialGroup()
                        .addComponent(healthLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                        .addComponent(healthBar, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(healthManaPanelLayout.createSequentialGroup()
                        .addGroup(healthManaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(healthManaPanelLayout.createSequentialGroup()
                                .addComponent(livesLabel)
                                .addGap(18, 18, 18)
                                .addComponent(lifeOneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lifeTwoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lifeThreeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(healthManaPanelLayout.createSequentialGroup()
                                .addComponent(experienceLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(experienceValueLabel)))
                        .addGap(18, 18, 18)
                        .addGroup(healthManaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(healthManaPanelLayout.createSequentialGroup()
                                .addComponent(levelLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(levelValueLabel))
                            .addGroup(healthManaPanelLayout.createSequentialGroup()
                                .addComponent(lifeFourLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lifeFiveLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        healthManaPanelLayout.setVerticalGroup(
            healthManaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(healthManaPanelLayout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(healthManaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, healthManaPanelLayout.createSequentialGroup()
                        .addComponent(healthBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(manaBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(healthManaPanelLayout.createSequentialGroup()
                        .addComponent(healthLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(manaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(healthManaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(healthManaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(experienceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(experienceValueLabel))
                    .addGroup(healthManaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(levelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(levelValueLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(healthManaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(livesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lifeOneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lifeFiveLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lifeFourLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lifeThreeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lifeTwoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9))
        );

        add(healthManaPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 430, 170));

        otherStatsPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        offensiveRatingLabel.setFont(new java.awt.Font("Luminari", 1, 18)); // NOI18N
        offensiveRatingLabel.setText("Offensive Rating");

        defensiveRatingLabel.setFont(new java.awt.Font("Luminari", 1, 18)); // NOI18N
        defensiveRatingLabel.setText("Defensive Rating");

        armorRatingLabel.setFont(new java.awt.Font("Luminari", 1, 18)); // NOI18N
        armorRatingLabel.setText("Armor Rating");

        movementLabel.setFont(new java.awt.Font("Luminari", 1, 18)); // NOI18N
        movementLabel.setText("Movement");

        defensiveRatingLabel1.setFont(new java.awt.Font("Luminari", 1, 18)); // NOI18N
        defensiveRatingLabel1.setText("10");

        offensiveRatingLabel1.setFont(new java.awt.Font("Luminari", 1, 18)); // NOI18N
        offensiveRatingLabel1.setText("10");

        armorRatingLabel1.setFont(new java.awt.Font("Luminari", 1, 18)); // NOI18N
        armorRatingLabel1.setText("10");

        movementLabel1.setFont(new java.awt.Font("Luminari", 1, 18)); // NOI18N
        movementLabel1.setText("10");

        javax.swing.GroupLayout otherStatsPanelLayout = new javax.swing.GroupLayout(otherStatsPanel);
        otherStatsPanel.setLayout(otherStatsPanelLayout);
        otherStatsPanelLayout.setHorizontalGroup(
            otherStatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(otherStatsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(otherStatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(offensiveRatingLabel)
                    .addComponent(armorRatingLabel)
                    .addComponent(movementLabel)
                    .addComponent(defensiveRatingLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addGroup(otherStatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(armorRatingLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(movementLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(defensiveRatingLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(offensiveRatingLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        otherStatsPanelLayout.setVerticalGroup(
            otherStatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(otherStatsPanelLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(otherStatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(offensiveRatingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(offensiveRatingLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(otherStatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(defensiveRatingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(defensiveRatingLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(otherStatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(armorRatingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(armorRatingLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(otherStatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(movementLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(movementLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        add(otherStatsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 250, 190));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel agilityLabel;
    private javax.swing.JLabel agilityLabel1;
    private javax.swing.JLabel armorRatingLabel;
    private javax.swing.JLabel armorRatingLabel1;
    private javax.swing.JLabel defensiveRatingLabel;
    private javax.swing.JLabel defensiveRatingLabel1;
    private javax.swing.JLabel experienceLabel;
    private javax.swing.JLabel experienceValueLabel;
    private javax.swing.JLabel hardinessLabel;
    private javax.swing.JLabel hardinessLabel1;
    private javax.swing.JProgressBar healthBar;
    private javax.swing.JLabel healthLabel;
    private javax.swing.JPanel healthManaPanel;
    private javax.swing.JLabel intellectLabel;
    private javax.swing.JLabel intellectLabel1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JFrame jFrame3;
    private javax.swing.JFrame jFrame4;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel levelLabel;
    private javax.swing.JLabel levelValueLabel;
    private javax.swing.JLabel lifeFiveLabel;
    private javax.swing.JLabel lifeFourLabel;
    private javax.swing.JLabel lifeOneLabel;
    private javax.swing.JLabel lifeThreeLabel;
    private javax.swing.JLabel lifeTwoLabel;
    private javax.swing.JLabel livesLabel;
    private javax.swing.JPanel mainStatsPanel;
    private javax.swing.JProgressBar manaBar;
    private javax.swing.JLabel manaLabel;
    private javax.swing.JLabel movementLabel;
    private javax.swing.JLabel movementLabel1;
    private javax.swing.JLabel offensiveRatingLabel;
    private javax.swing.JLabel offensiveRatingLabel1;
    private javax.swing.JPanel otherStatsPanel;
    private javax.swing.JLabel strengthLabel;
    private javax.swing.JLabel strengthLabel1;
    // End of variables declaration//GEN-END:variables
}
