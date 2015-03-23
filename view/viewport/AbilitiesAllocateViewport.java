package view.viewport;

import java.util.ArrayList;
import model.GameObject;
import controller.Intent;
import controller.IntentMap.IntentMap;
import controller.physicalController.RebindInfo;
import controller.virtualController.AllocateAbilityPointsParams;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import model.entity.Avatar;
import model.entity.occupation.ability.Ability;
import utility.Course;
import utility.ImageUtil;

/**
 *
 * @author Doherty
 */
public class AbilitiesAllocateViewport extends ViewPort {
    
    private static final int ARRAY_LENGTH = 14;
    private static String[] labels;
    //
    private boolean firstRender = true;
    //
    private ArrayList<IntentMap> ims = new ArrayList<IntentMap>(1);
    //
    private int[] oldAbilityLevelValues;
    
    public AbilitiesAllocateViewport() {
        initComponents();
        setVisible(true);
        initGUIProperites();
    }
    
    private void initGUIProperites() {
        JTextField[] textfields = getTextFields();
        for (int i=0; i<textfields.length; i++) {
            textfields[i].setHorizontalAlignment(JTextField.CENTER);
        }
    }
    
    @Override
    public void updateView(GameObject gameObject) {
        Avatar avatar = gameObject.getAvatar();
        ArrayList<Ability> abilities = avatar.getAbilities();
        if (firstRender) {
            setLabels(abilities);
            updateIntentMappings(gameObject);
            firstRender = false;
            refreshControllerNeeded = true;
        } else {
            refreshControllerNeeded = false;
        }
        
        if (abilityValuesChanged(abilities) ) {
            updateAbilityValues(abilities);
        }
        
        updatePoints(avatar);
    }
    
    private void updatePoints(Avatar avatar) {
        int points = avatar.getBooty();
        pointsTextField.setText("Points: " + points);
    }
    
    private void updateIntentMappings(GameObject gameObject) {
        ims = new ArrayList<IntentMap>(1); //clear
        
        //ability point allocation textfields
        Avatar avatar = gameObject.getAvatar();
        ArrayList<Ability> abilities = avatar.getAbilities();
        JTextField[] textFields = getTextFields();
        
        Ability ability;
        JTextField textField;
        //AllocateAbilityPointsParams params; // = new AllocateAbilityPointsParams()
        for (int i = 0; i<abilities.size() && i<textFields.length; ++i) {
            ability = abilities.get(i);
            textField = textFields[i];
//            params = new AllocateAbilityPointsParams(ability); 
            
            ims.add(new IntentMap(textField, ability, Intent.ALLOCATE_ABILITY_POINTS));
        }
        
        //back
        ims.add(new IntentMap(backButton, Intent.GOTO_GAME));
    }
    
    private boolean abilityValuesChanged(ArrayList<Ability> abilities) {
        boolean abilityValuesChanged = false;
        if (oldAbilityLevelValues == null) { //first time
            abilityValuesChanged = true;
            oldAbilityLevelValues = new int[abilities.size()];
            for (int i=0; i<abilities.size(); i++) {
                oldAbilityLevelValues[i] = abilities.get(i).getAbilityLevel();
            }
        } else {
            int[] newAbilityLevelValues = new int[abilities.size()];
            for (int i=0; i<abilities.size(); i++) {
                newAbilityLevelValues[i] = abilities.get(i).getAbilityLevel();
                if (newAbilityLevelValues[i] != oldAbilityLevelValues[i]) {
                    oldAbilityLevelValues[i] = newAbilityLevelValues[i];
                    abilityValuesChanged = true;
                }
            }
        }
        return abilityValuesChanged;
    }
    
    @Override
    public ArrayList<IntentMap> generateIntentMapping() {
        return ims;
    }
    
    private void updateAbilityValues(ArrayList<Ability> abilities) {
        Ability ability;
        int abilityLevel; // = ability.getAbilityLevel();
        JTextField[] textFields = getTextFields();
        
        for (int i = 0; i<abilities.size() && i<textFields.length; ++i) {
            ability = abilities.get(i);
            abilityLevel = ability.getAbilityLevel();
            
            textFields[i].setText(abilityLevel + "");    
        }
    }
    
    public static String[] getDefaultLabels() {
        String[] labelString = { 
            "Numpad-8",
            "Numpad-7",
            "Numpad-4",
            "Numpad-1",
            "Numpad-2",
            "Numpad-3",
            "Numpad-6",
            "Numpad-9",
            "E",
            "Q",
            "1",
            "2",
            "3",
            "\\"
        };
        return labelString;
    }
    
    public JTextField[] getTextFields(){
        JTextField[] textFields = {
            jTextField1,
            jTextField2,
            jTextField3,
            jTextField3,
            jTextField4,
            jTextField5,
            jTextField6,
            jTextField7,
            jTextField8,
            jTextField9,
            jTextField10,
            jTextField11,
            jTextField12,
            jTextField13
        };
        return textFields;
    }
    
    public JLabel[] getLabels() {
        JLabel[] labels = {
            upLabel,
            upLeftLabel,
            leftLabel,
            leftLabel,
            downLabel,
            downRightLabel,
            rightLabel,
            rightLabel,
            macro1Label,
            macro2Label,
            macro3Label,
            saveLabel
        };
        return labels;
    }
    
    private void setLabels(ArrayList<Ability> abilities) {
        JLabel[] labels = getLabels();
        String abilityName;
        for (int i = 0; i<abilities.size() && i<labels.length; ++i) {
            abilityName = abilities.get(i).getName();
            labels[i].setText(abilityName);    
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image image = ImageUtil.getImage( ImageUtil.options_viewport_background, this.getWidth(), this.getHeight()).getImage();
        g.drawImage( image, 0, 0, this);
    }
    
    public static void main(String[] args){
        
        JFrame frame = new JFrame();
        frame.setSize(2500,2500);
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.add(new AbilitiesAllocateViewport() );
     
        frame.setVisible(true);
    }
    
    /* Load/Save TODO (not priority) - MOVE TO APPROPRIATE CLASS (most likely controller) 
    @Override
    public String toString() {
	return "[" + "]"; //TODO
    }

    public static OptionsViewport fromString(String string) {
	String stripped = string.substring(1, string.length() - 1);
        //TODO
	return new OptionsViewport();
    }
    */
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the FormEditor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        upLabel = new javax.swing.JLabel();
        upLeftLabel = new javax.swing.JLabel();
        leftLabel = new javax.swing.JLabel();
        leftDownLabel = new javax.swing.JLabel();
        talkLabel = new javax.swing.JLabel();
        meleeLabel = new javax.swing.JLabel();
        macro1Label = new javax.swing.JLabel();
        macro2Label = new javax.swing.JLabel();
        macro3Label = new javax.swing.JLabel();
        saveLabel = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        downLabel = new javax.swing.JLabel();
        downRightLabel = new javax.swing.JLabel();
        rightLabel = new javax.swing.JLabel();
        rightUpLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        pointsTextField = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Up Arrow");
        jButton1.setPreferredSize(new java.awt.Dimension(125, 35));

        setBackground(new java.awt.Color(146, 61, 78));

        jPanel1.setBackground(new java.awt.Color(70, 52, 35));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 1, 1, new java.awt.Color(0, 0, 0)));
        jPanel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        jPanel1.setAlignmentY(Component.CENTER_ALIGNMENT);

        upLabel.setBackground(new java.awt.Color(151, 99, 47));
        upLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        upLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        upLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 1, 1, new java.awt.Color(0, 0, 0)));
        upLabel.setName("upLabel"); // NOI18N
        upLabel.setOpaque(true);
        upLabel.setPreferredSize(new java.awt.Dimension(150, 35));

        upLeftLabel.setBackground(new java.awt.Color(151, 99, 47));
        upLeftLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        upLeftLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        upLeftLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 1, 1, new java.awt.Color(0, 0, 0)));
        upLeftLabel.setName("upLeftLabel"); // NOI18N
        upLeftLabel.setOpaque(true);
        upLeftLabel.setPreferredSize(new java.awt.Dimension(150, 35));

        leftLabel.setBackground(new java.awt.Color(151, 99, 47));
        leftLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        leftLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        leftLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 1, 1, new java.awt.Color(0, 0, 0)));
        leftLabel.setName("leftLabel"); // NOI18N
        leftLabel.setOpaque(true);
        leftLabel.setPreferredSize(new java.awt.Dimension(150, 35));

        leftDownLabel.setBackground(new java.awt.Color(151, 99, 47));
        leftDownLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        leftDownLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        leftDownLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 1, 1, new java.awt.Color(0, 0, 0)));
        leftDownLabel.setName("leftDownLabel"); // NOI18N
        leftDownLabel.setOpaque(true);
        leftDownLabel.setPreferredSize(new java.awt.Dimension(150, 35));

        talkLabel.setBackground(new java.awt.Color(151, 99, 47));
        talkLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        talkLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        talkLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 1, 1, new java.awt.Color(0, 0, 0)));
        talkLabel.setName("talkLabel"); // NOI18N
        talkLabel.setOpaque(true);
        talkLabel.setPreferredSize(new java.awt.Dimension(150, 35));

        meleeLabel.setBackground(new java.awt.Color(151, 99, 47));
        meleeLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        meleeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        meleeLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 1, 1, new java.awt.Color(0, 0, 0)));
        meleeLabel.setName("meleeLabel"); // NOI18N
        meleeLabel.setOpaque(true);
        meleeLabel.setPreferredSize(new java.awt.Dimension(150, 35));

        macro1Label.setBackground(new java.awt.Color(151, 99, 47));
        macro1Label.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        macro1Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        macro1Label.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 1, 1, new java.awt.Color(0, 0, 0)));
        macro1Label.setName("macro1Label"); // NOI18N
        macro1Label.setOpaque(true);
        macro1Label.setPreferredSize(new java.awt.Dimension(150, 35));

        macro2Label.setBackground(new java.awt.Color(151, 99, 47));
        macro2Label.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        macro2Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        macro2Label.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 1, 1, new java.awt.Color(0, 0, 0)));
        macro2Label.setName("macro2Label"); // NOI18N
        macro2Label.setOpaque(true);
        macro2Label.setPreferredSize(new java.awt.Dimension(150, 35));

        macro3Label.setBackground(new java.awt.Color(151, 99, 47));
        macro3Label.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        macro3Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        macro3Label.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 1, 1, new java.awt.Color(0, 0, 0)));
        macro3Label.setName("macro3Label"); // NOI18N
        macro3Label.setOpaque(true);
        macro3Label.setPreferredSize(new java.awt.Dimension(150, 35));

        saveLabel.setBackground(new java.awt.Color(151, 99, 47));
        saveLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        saveLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        saveLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 1, 1, new java.awt.Color(0, 0, 0)));
        saveLabel.setName("saveGameLabel"); // NOI18N
        saveLabel.setOpaque(true);
        saveLabel.setPreferredSize(new java.awt.Dimension(150, 35));

        backButton.setBackground(new java.awt.Color(151, 99, 47));
        backButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        backButton.setText("Back");
        backButton.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 4, 4, new java.awt.Color(0, 0, 0)));
        backButton.setName("backButton"); // NOI18N
        backButton.setPreferredSize(new java.awt.Dimension(125, 35));
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        downLabel.setBackground(new java.awt.Color(151, 99, 47));
        downLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        downLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        downLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 1, 1, new java.awt.Color(0, 0, 0)));
        downLabel.setName("downLabel"); // NOI18N
        downLabel.setOpaque(true);
        downLabel.setPreferredSize(new java.awt.Dimension(150, 35));

        downRightLabel.setBackground(new java.awt.Color(151, 99, 47));
        downRightLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        downRightLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        downRightLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 1, 1, new java.awt.Color(0, 0, 0)));
        downRightLabel.setName("downRightLabel"); // NOI18N
        downRightLabel.setOpaque(true);
        downRightLabel.setPreferredSize(new java.awt.Dimension(150, 35));

        rightLabel.setBackground(new java.awt.Color(151, 99, 47));
        rightLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rightLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rightLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 1, 1, new java.awt.Color(0, 0, 0)));
        rightLabel.setName("rightLabel"); // NOI18N
        rightLabel.setOpaque(true);
        rightLabel.setPreferredSize(new java.awt.Dimension(150, 35));

        rightUpLabel.setBackground(new java.awt.Color(151, 99, 47));
        rightUpLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rightUpLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rightUpLabel.setToolTipText("");
        rightUpLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 1, 1, new java.awt.Color(0, 0, 0)));
        rightUpLabel.setName("rightUpLabel"); // NOI18N
        rightUpLabel.setOpaque(true);
        rightUpLabel.setPreferredSize(new java.awt.Dimension(150, 35));

        jPanel2.setBackground(new java.awt.Color(226, 177, 127));

        jLabel11.setBackground(new java.awt.Color(127, 75, 53));
        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Ability");
        jLabel11.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 1, 1, new java.awt.Color(0, 0, 0)));
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel11.setName("labelLabel"); // NOI18N
        jLabel11.setOpaque(true);
        jLabel11.setPreferredSize(new java.awt.Dimension(150, 35));

        jLabel16.setBackground(new java.awt.Color(127, 75, 53));
        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Points");
        jLabel16.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 1, 1, new java.awt.Color(0, 0, 0)));
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel16.setName("labelLabel"); // NOI18N
        jLabel16.setOpaque(true);
        jLabel16.setPreferredSize(new java.awt.Dimension(150, 35));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pointsTextField.setText("Points: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(upLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(downLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rightUpLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(downRightLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rightLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(leftDownLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(upLeftLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(leftLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(saveLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(macro3Label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(macro2Label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(macro1Label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(meleeLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(talkLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(100, 100, 100)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pointsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1)
                    .addComponent(jTextField2)
                    .addComponent(jTextField3)
                    .addComponent(jTextField4)
                    .addComponent(jTextField5)
                    .addComponent(jTextField6)
                    .addComponent(jTextField7)
                    .addComponent(jTextField8)
                    .addComponent(jTextField9)
                    .addComponent(jTextField10)
                    .addComponent(jTextField11)
                    .addComponent(jTextField12)
                    .addComponent(jTextField13)
                    .addComponent(jTextField14, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                .addGap(66, 66, 66))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(upLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(upLeftLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(leftLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(leftDownLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(downLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(downRightLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rightLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rightUpLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(talkLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(meleeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(macro1Label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(macro2Label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(macro3Label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pointsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed

    }//GEN-LAST:event_backButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JLabel downLabel;
    private javax.swing.JLabel downRightLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel leftDownLabel;
    private javax.swing.JLabel leftLabel;
    private javax.swing.JLabel macro1Label;
    private javax.swing.JLabel macro2Label;
    private javax.swing.JLabel macro3Label;
    private javax.swing.JLabel meleeLabel;
    private javax.swing.JTextField pointsTextField;
    private javax.swing.JLabel rightLabel;
    private javax.swing.JLabel rightUpLabel;
    private javax.swing.JLabel saveLabel;
    private javax.swing.JLabel talkLabel;
    private javax.swing.JLabel upLabel;
    private javax.swing.JLabel upLeftLabel;
    // End of variables declaration//GEN-END:variables
}