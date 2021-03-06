package de.elmar_baumann.nb.slclock.timer;

import de.elmar_baumann.nb.slclock.util.ArrayUtil;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @author Elmar Baumann
 */
public class TimerEventEditPanel extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;
    private final TimerEvent event;

    public TimerEventEditPanel() {
        this(new TimerEvent());
    }

    public TimerEventEditPanel(TimerEvent event) {
        if (event == null) {
            throw new NullPointerException("event == null");
        }
        this.event = event;
        initComponents();
        comboBoxHours.setModel(new DefaultComboBoxModel<>(ArrayUtil.createIntRegionArray(0, 24)));
        comboBoxMinutes.setModel(new DefaultComboBoxModel<>(ArrayUtil.createIntRegionArray(0, 59)));
        comboBoxSeconds.setModel(new DefaultComboBoxModel<>(ArrayUtil.createIntRegionArray(0, 59)));
        eventToGui();
        sliderHours.addChangeListener(new SliderChangeListener(comboBoxHours));
        sliderMinutes.addChangeListener(new SliderChangeListener(comboBoxMinutes));
        sliderSeconds.addChangeListener(new SliderChangeListener(comboBoxSeconds));
        comboBoxHours.addActionListener(new ComboBoxActionListener(sliderHours));
        comboBoxMinutes.addActionListener(new ComboBoxActionListener(sliderMinutes));
        comboBoxSeconds.addActionListener(new ComboBoxActionListener(sliderSeconds));
    }

    private void eventToGui() {
        textFieldDisplayName.setText(event.getDisplayName() == null ? "" : event.getDisplayName());
        comboBoxHours.setSelectedItem(event.getHours());
        comboBoxMinutes.setSelectedItem(event.getMinutesPerHour());
        comboBoxSeconds.setSelectedItem(event.getSecondsPerMinute());
        sliderHours.setValue(event.getHours());
        sliderMinutes.setValue(event.getMinutesPerHour());
        sliderSeconds.setValue(event.getSecondsPerMinute());
        checkBoxPersistent.setSelected(event.isPersistent());
        checkBoxSound.setSelected(event.isSound());
        checkBoxVerbose.setSelected(event.isVerbose());
    }

    public TimerEvent save() {
        int seconds = getSeconds();
        event.setSeconds(seconds);
        event.setRemainingSeconds(seconds);
        event.setDisplayName(textFieldDisplayName.getText().trim());
        event.setPersistent(checkBoxPersistent.isSelected());
        event.setSound(checkBoxSound.isSelected());
        event.setVerbose(checkBoxVerbose.isSelected());
        return event;
    }

    private int getSeconds() {
        int seconds = getValue(comboBoxHours) * 3600
                + getValue(comboBoxMinutes) * 60
                + getValue(comboBoxSeconds);
        return seconds > 0
                ? seconds
                : 1;
    }

    private int getValue(JComboBox<Integer> cb) {
        Object value = cb.getSelectedItem();
        return value instanceof Number
                ? ((Number) value).intValue()
                : 0;
    }

    private final class SliderChangeListener implements ChangeListener {

        private final JComboBox<Integer> comboBox;

        private SliderChangeListener(JComboBox<Integer> comboBox) {
            this.comboBox = comboBox;
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            JSlider slider = (JSlider) e.getSource();
            comboBox.setSelectedItem(slider.getValue());
        }
    }

    private final class ComboBoxActionListener implements ActionListener {

        private final JSlider slider;

        private ComboBoxActionListener(JSlider slider) {
            this.slider = slider;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox<?> cb = (JComboBox<?>) e.getSource();
            slider.setValue((int) cb.getSelectedItem());
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {//GEN-BEGIN:initComponents

        labelDisplayName = new javax.swing.JLabel();
        textFieldDisplayName = new javax.swing.JTextField();
        labelHours = new javax.swing.JLabel();
        sliderHours = new javax.swing.JSlider(0, 24)
        ;
        comboBoxHours = new javax.swing.JComboBox<>();
        labelMinutes = new javax.swing.JLabel();
        sliderMinutes = new javax.swing.JSlider(0, 59)
        ;
        comboBoxMinutes = new javax.swing.JComboBox<>();
        labelSeconds = new javax.swing.JLabel();
        sliderSeconds = new javax.swing.JSlider(0, 59)
        ;
        comboBoxSeconds = new javax.swing.JComboBox<>();
        checkBoxPersistent = new javax.swing.JCheckBox();
        checkBoxSound = new javax.swing.JCheckBox();
        checkBoxVerbose = new javax.swing.JCheckBox();

        labelDisplayName.setLabelFor(textFieldDisplayName);
        org.openide.awt.Mnemonics.setLocalizedText(labelDisplayName, org.openide.util.NbBundle.getMessage(TimerEventEditPanel.class, "TimerEventEditPanel.labelDisplayName.text")); // NOI18N

        textFieldDisplayName.setColumns(30);

        labelHours.setLabelFor(comboBoxHours);
        org.openide.awt.Mnemonics.setLocalizedText(labelHours, org.openide.util.NbBundle.getMessage(TimerEventEditPanel.class, "TimerEventEditPanel.labelHours.text")); // NOI18N

        labelMinutes.setLabelFor(comboBoxMinutes);
        org.openide.awt.Mnemonics.setLocalizedText(labelMinutes, org.openide.util.NbBundle.getMessage(TimerEventEditPanel.class, "TimerEventEditPanel.labelMinutes.text")); // NOI18N

        labelSeconds.setLabelFor(comboBoxSeconds);
        org.openide.awt.Mnemonics.setLocalizedText(labelSeconds, org.openide.util.NbBundle.getMessage(TimerEventEditPanel.class, "TimerEventEditPanel.labelSeconds.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(checkBoxPersistent, org.openide.util.NbBundle.getMessage(TimerEventEditPanel.class, "TimerEventEditPanel.checkBoxPersistent.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(checkBoxSound, org.openide.util.NbBundle.getMessage(TimerEventEditPanel.class, "TimerEventEditPanel.checkBoxSound.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(checkBoxVerbose, org.openide.util.NbBundle.getMessage(TimerEventEditPanel.class, "TimerEventEditPanel.checkBoxVerbose.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelHours)
                            .addComponent(labelMinutes)
                            .addComponent(labelSeconds))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sliderMinutes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sliderSeconds, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sliderHours, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboBoxSeconds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(labelDisplayName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldDisplayName, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkBoxPersistent)
                    .addComponent(checkBoxVerbose)
                    .addComponent(checkBoxSound))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDisplayName)
                    .addComponent(textFieldDisplayName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelHours)
                    .addComponent(sliderHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelMinutes)
                    .addComponent(sliderMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelSeconds)
                    .addComponent(sliderSeconds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxSeconds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(checkBoxPersistent)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkBoxSound)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkBoxVerbose)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkBoxPersistent;
    private javax.swing.JCheckBox checkBoxSound;
    private javax.swing.JCheckBox checkBoxVerbose;
    private javax.swing.JComboBox<Integer> comboBoxHours;
    private javax.swing.JComboBox<Integer> comboBoxMinutes;
    private javax.swing.JComboBox<Integer> comboBoxSeconds;
    private javax.swing.JLabel labelDisplayName;
    private javax.swing.JLabel labelHours;
    private javax.swing.JLabel labelMinutes;
    private javax.swing.JLabel labelSeconds;
    private javax.swing.JSlider sliderHours;
    private javax.swing.JSlider sliderMinutes;
    private javax.swing.JSlider sliderSeconds;
    private javax.swing.JTextField textFieldDisplayName;
    // End of variables declaration//GEN-END:variables

}
