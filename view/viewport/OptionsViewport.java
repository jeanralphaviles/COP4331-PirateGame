package view.viewport;

import java.util.ArrayList;
import model.GameObject;
import controller.Intent;
import controller.IntentMap.IntentMap;
import controller.physicalController.RebindInfo;
import javax.swing.JButton;
import model.entity.occupation.ability.instantAbility.Talk;
import utility.Course;

/**
 *
 * @author Doherty
 */
public class OptionsViewport extends ViewPort {
    
    private static final int ARRAY_LENGTH = 14;
    private static String[] labels;
    
    public OptionsViewport() {
        initComponents();
        setLabels(getButtons());
        setVisible(true);
    }
    
    @Override
    public void updateView(GameObject gameObject) {
        
    }
    
    @Override
    public ArrayList<IntentMap> generateIntentMapping() {
        ArrayList<IntentMap> ims = new ArrayList<>(1);
        
        //Reassign movement keys
        ims.add(new IntentMap(null, upButton, new RebindInfo(Intent.MOVE, new Course(Course.up), upButton) , Intent.LISTEN, ""));   
        ims.add(new IntentMap(null, upLeftButton, new RebindInfo(Intent.MOVE, new Course(Course.left_up), upLeftButton), Intent.LISTEN, ""));
        ims.add(new IntentMap(null, leftButton, new RebindInfo(Intent.MOVE, new Course(Course.left), leftButton), Intent.LISTEN, ""));
        ims.add(new IntentMap(null, leftDownButton, new RebindInfo(Intent.MOVE, new Course(Course.left_down), leftDownButton), Intent.LISTEN, ""));
        ims.add(new IntentMap(null, downButton, new RebindInfo(Intent.MOVE, new Course(Course.down), downButton), Intent.LISTEN, ""));
        ims.add(new IntentMap(null, downRightButton, new RebindInfo(Intent.MOVE, new Course(Course.right_down), downRightButton), Intent.LISTEN, ""));
        ims.add(new IntentMap(null, rightButton, new RebindInfo(Intent.MOVE, new Course(Course.right), rightButton), Intent.LISTEN, ""));
        ims.add(new IntentMap(null, rightUpButton, new RebindInfo(Intent.MOVE, new Course(Course.right_up), rightUpButton), Intent.LISTEN, ""));
        
        //Reassign other keys (Intents need to be fixed)
        ims.add(new IntentMap(null, talkButton, new RebindInfo(Intent.TALK, new Talk(), talkButton), Intent.LISTEN, ""));
        ims.add(new IntentMap(null, meleeButton, new RebindInfo(Intent.ACTIVATE_ABILITY, null, meleeButton), Intent.LISTEN, ""));
        ims.add(new IntentMap(null, macro1Button, new RebindInfo(Intent.ACTIVATE_ABILITY, null, macro1Button), Intent.LISTEN, ""));
        ims.add(new IntentMap(null, macro2Button, new RebindInfo(Intent.ACTIVATE_ABILITY, null, macro2Button), Intent.LISTEN, ""));
        ims.add(new IntentMap(null, macro3Button, new RebindInfo(Intent.ACTIVATE_ABILITY, null, macro3Button), Intent.LISTEN, ""));
        ims.add(new IntentMap(null, saveButton, new RebindInfo(Intent.SAVE, null, saveButton), Intent.LISTEN, ""));
        
        ims.add(new IntentMap(backButton, Intent.GOTO_GAME));
        ims.add(new IntentMap(resetDefaultsButton, Intent.RESET_DEFAULT_CONTROLS));
        
        return ims;
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
            "Space",
            "Q",
            "1",
            "2",
            "3",
            "\\"
        };
        return labelString;
    }
    
    public JButton[] getButtons(){
        JButton[] buttons = {
            upButton,
            upLeftButton,
            leftButton,
            leftDownButton,
            downButton,
            downRightButton,
            rightButton,
            rightUpButton,
            talkButton,
            meleeButton,
            macro1Button,
            macro2Button,
            macro3Button,
            saveButton
        };
        return buttons;
    }
    
    private static void setLabels(JButton[] buttons) {
        if (labels == null) labels = getDefaultLabels();
        for (int i = 0; i < ARRAY_LENGTH; ++i) {
            buttons[i].setText(labels[i]);           
        }
    }

    private static String[] getLabels() {
        if (labels == null) 
            getDefaultLabels();
        return labels;
    }
    
    private void saveCurrentLabels(JButton[] buttons) {
        for (int i = 0; i < ARRAY_LENGTH; ++i) {
            buttons[i].setText(labels[i]);
            labels[i] = buttons[i].getText();
        }
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
        jLabel11 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        resetDefaultsButton = new javax.swing.JButton();
        upButton = new javax.swing.JButton();
        upLeftButton = new javax.swing.JButton();
        leftDownButton = new javax.swing.JButton();
        leftButton = new javax.swing.JButton();
        downLabel = new javax.swing.JLabel();
        downRightLabel = new javax.swing.JLabel();
        rightLabel = new javax.swing.JLabel();
        rightUpLabel = new javax.swing.JLabel();
        downButton = new javax.swing.JButton();
        downRightButton = new javax.swing.JButton();
        rightUpButton = new javax.swing.JButton();
        rightButton = new javax.swing.JButton();
        meleeButton = new javax.swing.JButton();
        talkButton = new javax.swing.JButton();
        macro1Button = new javax.swing.JButton();
        macro2Button = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        macro3Button = new javax.swing.JButton();

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Up Arrow");
        jButton1.setPreferredSize(new java.awt.Dimension(125, 35));

        setBackground(new java.awt.Color(255, 255, 255));

        upLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        upLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        upLabel.setText("Up");
        upLabel.setBorder(new javax.swing.border.MatteBorder(null));
        upLabel.setName("upLabel"); // NOI18N
        upLabel.setOpaque(true);
        upLabel.setPreferredSize(new java.awt.Dimension(150, 35));

        upLeftLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        upLeftLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        upLeftLabel.setText("Up-Left");
        upLeftLabel.setBorder(new javax.swing.border.MatteBorder(null));
        upLeftLabel.setName("upLeftLabel"); // NOI18N
        upLeftLabel.setOpaque(true);
        upLeftLabel.setPreferredSize(new java.awt.Dimension(150, 35));

        leftLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        leftLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        leftLabel.setText("Left");
        leftLabel.setBorder(new javax.swing.border.MatteBorder(null));
        leftLabel.setName("leftLabel"); // NOI18N
        leftLabel.setOpaque(true);
        leftLabel.setPreferredSize(new java.awt.Dimension(150, 35));

        leftDownLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        leftDownLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        leftDownLabel.setText("Left-Down");
        leftDownLabel.setBorder(new javax.swing.border.MatteBorder(null));
        leftDownLabel.setName("leftDownLabel"); // NOI18N
        leftDownLabel.setOpaque(true);
        leftDownLabel.setPreferredSize(new java.awt.Dimension(150, 35));

        talkLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        talkLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        talkLabel.setText("Talk");
        talkLabel.setBorder(new javax.swing.border.MatteBorder(null));
        talkLabel.setName("talkLabel"); // NOI18N
        talkLabel.setOpaque(true);
        talkLabel.setPreferredSize(new java.awt.Dimension(150, 35));

        meleeLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        meleeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        meleeLabel.setText("Melee");
        meleeLabel.setBorder(new javax.swing.border.MatteBorder(null));
        meleeLabel.setName("meleeLabel"); // NOI18N
        meleeLabel.setOpaque(true);
        meleeLabel.setPreferredSize(new java.awt.Dimension(150, 35));

        macro1Label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        macro1Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        macro1Label.setText("Macro 1");
        macro1Label.setBorder(new javax.swing.border.MatteBorder(null));
        macro1Label.setName("macro1Label"); // NOI18N
        macro1Label.setOpaque(true);
        macro1Label.setPreferredSize(new java.awt.Dimension(150, 35));

        macro2Label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        macro2Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        macro2Label.setText("Macro 2");
        macro2Label.setBorder(new javax.swing.border.MatteBorder(null));
        macro2Label.setName("macro2Label"); // NOI18N
        macro2Label.setOpaque(true);
        macro2Label.setPreferredSize(new java.awt.Dimension(150, 35));

        macro3Label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        macro3Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        macro3Label.setText("Macro 3");
        macro3Label.setBorder(new javax.swing.border.MatteBorder(null));
        macro3Label.setName("macro3Label"); // NOI18N
        macro3Label.setOpaque(true);
        macro3Label.setPreferredSize(new java.awt.Dimension(150, 35));

        saveLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        saveLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        saveLabel.setText("Save Game");
        saveLabel.setBorder(new javax.swing.border.MatteBorder(null));
        saveLabel.setName("saveGameLabel"); // NOI18N
        saveLabel.setOpaque(true);
        saveLabel.setPreferredSize(new java.awt.Dimension(150, 35));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Key Action");
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel11.setName("labelLabel"); // NOI18N
        jLabel11.setPreferredSize(new java.awt.Dimension(150, 35));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Key Mapping");
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel16.setName("labelLabel"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(150, 35));

        backButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        backButton.setText("Back");
        backButton.setName("backButton"); // NOI18N
        backButton.setPreferredSize(new java.awt.Dimension(125, 35));
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        resetDefaultsButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        resetDefaultsButton.setText("Reset Defaults");
        resetDefaultsButton.setName("resetDefaultsButton"); // NOI18N
        resetDefaultsButton.setPreferredSize(new java.awt.Dimension(125, 35));
        resetDefaultsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetDefaultsButtonActionPerformed(evt);
            }
        });

        upButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        upButton.setPreferredSize(new java.awt.Dimension(150, 35));
        upButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upButtonActionPerformed(evt);
            }
        });

        upLeftButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        upLeftButton.setPreferredSize(new java.awt.Dimension(150, 35));
        upLeftButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upLeftButtonActionPerformed(evt);
            }
        });

        leftDownButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        leftDownButton.setPreferredSize(new java.awt.Dimension(150, 35));
        leftDownButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leftDownButtonActionPerformed(evt);
            }
        });

        leftButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        leftButton.setPreferredSize(new java.awt.Dimension(150, 35));
        leftButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leftButtonActionPerformed(evt);
            }
        });

        downLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        downLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        downLabel.setText("Down");
        downLabel.setBorder(new javax.swing.border.MatteBorder(null));
        downLabel.setName("downLabel"); // NOI18N
        downLabel.setOpaque(true);
        downLabel.setPreferredSize(new java.awt.Dimension(150, 35));

        downRightLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        downRightLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        downRightLabel.setText("Down-Right");
        downRightLabel.setBorder(new javax.swing.border.MatteBorder(null));
        downRightLabel.setName("downRightLabel"); // NOI18N
        downRightLabel.setOpaque(true);
        downRightLabel.setPreferredSize(new java.awt.Dimension(150, 35));

        rightLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rightLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rightLabel.setText("Right");
        rightLabel.setBorder(new javax.swing.border.MatteBorder(null));
        rightLabel.setName("rightLabel"); // NOI18N
        rightLabel.setOpaque(true);
        rightLabel.setPreferredSize(new java.awt.Dimension(150, 35));

        rightUpLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rightUpLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rightUpLabel.setText("Right-Up");
        rightUpLabel.setToolTipText("");
        rightUpLabel.setBorder(new javax.swing.border.MatteBorder(null));
        rightUpLabel.setName("rightUpLabel"); // NOI18N
        rightUpLabel.setOpaque(true);
        rightUpLabel.setPreferredSize(new java.awt.Dimension(150, 35));

        downButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        downButton.setPreferredSize(new java.awt.Dimension(150, 35));
        downButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downButtonActionPerformed(evt);
            }
        });

        downRightButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        downRightButton.setPreferredSize(new java.awt.Dimension(150, 35));
        downRightButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downRightButtonActionPerformed(evt);
            }
        });

        rightUpButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rightUpButton.setPreferredSize(new java.awt.Dimension(150, 35));
        rightUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rightUpButtonActionPerformed(evt);
            }
        });

        rightButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rightButton.setPreferredSize(new java.awt.Dimension(150, 35));
        rightButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rightButtonActionPerformed(evt);
            }
        });

        meleeButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        meleeButton.setText("Q");
        meleeButton.setPreferredSize(new java.awt.Dimension(150, 35));
        meleeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meleeButtonActionPerformed(evt);
            }
        });

        talkButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        talkButton.setText("Space");
        talkButton.setPreferredSize(new java.awt.Dimension(150, 35));
        talkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                talkButtonActionPerformed(evt);
            }
        });

        macro1Button.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        macro1Button.setText("1");
        macro1Button.setPreferredSize(new java.awt.Dimension(150, 35));
        macro1Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                macro1ButtonActionPerformed(evt);
            }
        });

        macro2Button.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        macro2Button.setText("2");
        macro2Button.setPreferredSize(new java.awt.Dimension(150, 35));
        macro2Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                macro2ButtonActionPerformed(evt);
            }
        });

        saveButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        saveButton.setText("\\");
            saveButton.setPreferredSize(new java.awt.Dimension(150, 35));
            saveButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    saveButtonActionPerformed(evt);
                }
            });

            macro3Button.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            macro3Button.setText("3");
            macro3Button.setPreferredSize(new java.awt.Dimension(150, 35));
            macro3Button.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    macro3ButtonActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(downLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(rightUpLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(downRightLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rightLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(leftDownLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(upLeftLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(leftLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(upLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(saveLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(macro3Label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(macro2Label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(macro1Label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(meleeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(talkLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(100, 100, 100)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(upButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(upLeftButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(leftDownButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(leftButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(downButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(downRightButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(rightUpButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(rightButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(meleeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(talkButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(macro1Button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(macro2Button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(macro3Button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(75, 75, 75)
                            .addComponent(resetDefaultsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(8, 8, 8)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(upLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(upButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(43, 43, 43)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
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
                            .addComponent(saveLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(upLeftButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(leftButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(leftDownButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(downButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(downRightButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(rightButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(rightUpButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(talkButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(meleeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(macro1Button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(macro2Button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(macro3Button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(resetDefaultsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
        }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        saveCurrentLabels(getButtons());
    }//GEN-LAST:event_backButtonActionPerformed

    private void upButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_upButtonActionPerformed

    private void upLeftButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upLeftButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_upLeftButtonActionPerformed

    private void resetDefaultsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetDefaultsButtonActionPerformed
        labels = getDefaultLabels();
        setLabels(getButtons());
    }//GEN-LAST:event_resetDefaultsButtonActionPerformed

    private void leftDownButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leftDownButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_leftDownButtonActionPerformed

    private void leftButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leftButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_leftButtonActionPerformed

    private void downButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_downButtonActionPerformed

    private void downRightButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downRightButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_downRightButtonActionPerformed

    private void rightUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rightUpButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rightUpButtonActionPerformed

    private void rightButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rightButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rightButtonActionPerformed

    private void meleeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meleeButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_meleeButtonActionPerformed

    private void talkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_talkButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_talkButtonActionPerformed

    private void macro1ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_macro1ButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_macro1ButtonActionPerformed

    private void macro2ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_macro2ButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_macro2ButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveButtonActionPerformed

    private void macro3ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_macro3ButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_macro3ButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton downButton;
    private javax.swing.JLabel downLabel;
    private javax.swing.JButton downRightButton;
    private javax.swing.JLabel downRightLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JButton leftButton;
    private javax.swing.JButton leftDownButton;
    private javax.swing.JLabel leftDownLabel;
    private javax.swing.JLabel leftLabel;
    private javax.swing.JButton macro1Button;
    private javax.swing.JLabel macro1Label;
    private javax.swing.JButton macro2Button;
    private javax.swing.JLabel macro2Label;
    private javax.swing.JButton macro3Button;
    private javax.swing.JLabel macro3Label;
    private javax.swing.JButton meleeButton;
    private javax.swing.JLabel meleeLabel;
    private javax.swing.JButton resetDefaultsButton;
    private javax.swing.JButton rightButton;
    private javax.swing.JLabel rightLabel;
    private javax.swing.JButton rightUpButton;
    private javax.swing.JLabel rightUpLabel;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel saveLabel;
    private javax.swing.JButton talkButton;
    private javax.swing.JLabel talkLabel;
    private javax.swing.JButton upButton;
    private javax.swing.JLabel upLabel;
    private javax.swing.JButton upLeftButton;
    private javax.swing.JLabel upLeftLabel;
    // End of variables declaration//GEN-END:variables
}