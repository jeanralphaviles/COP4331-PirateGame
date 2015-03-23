 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.viewport;

import java.awt.Color;
import java.util.ArrayList;

import model.GameObject;
import model.entity.Statistics;
import controller.IntentMap.IntentMap;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.MatteBorder;
import utility.decal.Decal;

/**
 *
 * @author comcc_000
 */
public class StatusViewport extends ViewPort {

    private int lastLivesLeft = -1;
    /**
     * Creates new form StatusViewport
     */
    public StatusViewport() {
    	setBackground(Color.BLACK);
        initComponents();
        initPanels();
        initProgressBars(10, 10); 
    }
    
    public void initPanels() {    
        mainStatsPanel.setBackground(new Color(204, 204, 153));
        otherStatsPanel.setBackground(new Color(204, 204, 153));
        healthManaPanel.setBackground(new Color(204, 204, 153));
    }
    
    @Override
    public void updateView(GameObject gameObject) {
        Statistics statistics = gameObject.getAvatar().getDerivedStatistics();
    
        int health = statistics.getCurrentHealth();
        int maxHealth = statistics.getMaxHealth();
        int mana = statistics.getCurrentMana();
        int maxMana = statistics.getMaxMana();
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
        
        if (lastLivesLeft != livesLeft) {
            lastLivesLeft = livesLeft;
            updateLives(livesLeft);
        }
    }

    @Override
    public ArrayList<IntentMap> generateIntentMapping() {
        ArrayList<IntentMap> ims = new ArrayList<IntentMap>(1);
        return ims;
    }
    
    public void initProgressBars(int maxHealth, int maxMana) {
        healthBar.setMaximum(maxHealth);
        healthBar.setMinimum(0);
        healthBar.setStringPainted(true);
        healthBar.setForeground(Color.red);
        healthBar.setBackground(new Color(200, 200, 200));
        
        manaBar.setMaximum(maxMana);
        manaBar.setMinimum(0);
        manaBar.setStringPainted(true);
        manaBar.setForeground(Color.blue);
        manaBar.setBackground(new Color(200, 200, 200));
        
        ImageIcon image = new ImageIcon(Decal.life);
        
        lifeOneLabel.setIcon(image);
        lifeTwoLabel.setIcon(image);
        lifeThreeLabel.setIcon(image);
        lifeFourLabel.setIcon(image);
        lifeFiveLabel.setIcon(image);
    }
    
    public void updateProgressBars (int health, int mana, int maxHealth, int maxMana) {
    	healthBar.setStringPainted(true);
    	manaBar.setStringPainted(true);
        healthBar.setMaximum(maxHealth);
        manaBar.setMaximum(maxMana);
        healthBar.setValue(health);
        manaBar.setValue(mana);
    }
    
    public void updateLives(int livesLeft) {
        lifeOneLabel.setVisible(false);
        lifeTwoLabel.setVisible(false);
        lifeThreeLabel.setVisible(false);
        lifeFourLabel.setVisible(false);
        lifeFiveLabel.setVisible(false);
        
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

        setSize(new Dimension(443, 375));

        mainStatsPanel.setBorder(new MatteBorder(4, 4, 1, 1, new Color(153, 51, 0)));

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

        healthManaPanel.setBorder(new MatteBorder(1, 4, 4, 4, new Color(153, 51, 0)));

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
        healthManaPanelLayout.setHorizontalGroup(
        	healthManaPanelLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(healthManaPanelLayout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(healthManaPanelLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(healthManaPanelLayout.createSequentialGroup()
        					.addComponent(manaLabel)
        					.addGap(73)
        					.addComponent(manaBar, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE))
        				.addGroup(healthManaPanelLayout.createSequentialGroup()
        					.addComponent(healthLabel)
        					.addGap(67)
        					.addComponent(healthBar, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE))
        				.addGroup(healthManaPanelLayout.createSequentialGroup()
        					.addGroup(healthManaPanelLayout.createParallelGroup(Alignment.LEADING)
        						.addGroup(healthManaPanelLayout.createSequentialGroup()
        							.addComponent(livesLabel)
        							.addGap(18)
        							.addComponent(lifeOneLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        							.addGap(18)
        							.addComponent(lifeTwoLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        							.addGap(18)
        							.addComponent(lifeThreeLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
        						.addGroup(healthManaPanelLayout.createSequentialGroup()
        							.addComponent(experienceLabel)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(experienceValueLabel)))
        					.addGap(18)
        					.addGroup(healthManaPanelLayout.createParallelGroup(Alignment.LEADING)
        						.addGroup(healthManaPanelLayout.createSequentialGroup()
        							.addComponent(levelLabel)
        							.addPreferredGap(ComponentPlacement.UNRELATED)
        							.addComponent(levelValueLabel))
        						.addGroup(healthManaPanelLayout.createSequentialGroup()
        							.addComponent(lifeFourLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        							.addGap(18)
        							.addComponent(lifeFiveLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
        					.addGap(0, 96, Short.MAX_VALUE)))
        			.addGap(18))
        );
        healthManaPanelLayout.setVerticalGroup(
        	healthManaPanelLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(healthManaPanelLayout.createSequentialGroup()
        			.addContainerGap(9, Short.MAX_VALUE)
        			.addGroup(healthManaPanelLayout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(healthManaPanelLayout.createSequentialGroup()
        					.addComponent(healthBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(manaBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addGap(12))
        				.addGroup(healthManaPanelLayout.createSequentialGroup()
        					.addComponent(healthLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(manaLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)))
        			.addGroup(healthManaPanelLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(healthManaPanelLayout.createParallelGroup(Alignment.BASELINE)
        					.addComponent(experienceLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        					.addComponent(experienceValueLabel))
        				.addGroup(healthManaPanelLayout.createParallelGroup(Alignment.BASELINE)
        					.addComponent(levelLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        					.addComponent(levelValueLabel)))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(healthManaPanelLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(livesLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lifeOneLabel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lifeFiveLabel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lifeFourLabel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lifeThreeLabel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lifeTwoLabel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
        			.addGap(9))
        );
        healthManaPanel.setLayout(healthManaPanelLayout);

        otherStatsPanel.setBorder(new MatteBorder(4, 1, 1, 4, new Color(153, 51, 0)));

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
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addComponent(mainStatsPanel, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(otherStatsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        				.addComponent(healthManaPanel, GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE))
        			.addGap(1))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(mainStatsPanel, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
        				.addComponent(otherStatsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(healthManaPanel, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
        			.addGap(3))
        );
        setLayout(groupLayout);
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
