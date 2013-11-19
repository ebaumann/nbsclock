package de.elmar_baumann.nb.slclock.alarmclock;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.util.NbBundle;

/**
 * @author Elmar Baumann
 */
public class AlarmEventsPanel extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;

    public AlarmEventsPanel() {
        initComponents();
        checkBoxShowIcon.setSelected(AlarmEventsModel.getInstance().isShowIcon());
        addEvents();
    }

    private void addEvents() {
        panelEvents.removeAll();
        List<AlarmEvent> events = new ArrayList<>(AlarmEventsModel.getInstance().getEvents());
        Collections.sort(events, new AlarmEvent.AlarmEventCmpAsc());
        for (AlarmEvent event : events) {
            addEvent(event);
        }
        addVerticalFillPanel();
        invalidate();
        validate();
        repaint();
    }

    private void addEvent(AlarmEvent event) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 5, 0, 5);
        panelEvents.add(new AlarmEventPanel(event), gbc);
    }

    private void addVerticalFillPanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelEvents.add(new JPanel(), gbc);
    }

    private final Action addAction = new AbstractAction() {

        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent e) {
            AlarmEventEditPanel alarmEventsEditPanel = new AlarmEventEditPanel();
                DialogDescriptor dd = new DialogDescriptor(
                       alarmEventsEditPanel, // innerPane
                        NbBundle.getMessage(AlarmEventsPanel.class, "AlarmEventsPanel.AddAction.Title"), // title
                        true, // modal
                        new Object[] {DialogDescriptor.OK_OPTION, DialogDescriptor.CANCEL_OPTION}, //options
                        DialogDescriptor.OK_OPTION, // initialValue
                        DialogDescriptor.DEFAULT_ALIGN, // optionsAlign
                        null, // helpCtx
                        null //bl
                );
            if (DialogDisplayer.getDefault().notify(dd) == DialogDescriptor.OK_OPTION) {
                AlarmEvent event = alarmEventsEditPanel.save();
                event.setRun(true);
                AlarmEventsModel.getInstance().addToEvents(event);
            }
        }
    };

    public void listenToModelChanges(boolean listen) {
        if (listen) {
            AlarmEventsModel.getInstance().addPropertyChangeListener(modelChangeListener);
        } else {
            AlarmEventsModel.getInstance().removePropertyChangeListener(modelChangeListener);
        }
    }

    private final PropertyChangeListener modelChangeListener = new PropertyChangeListener() {

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            String propertyName = evt.getPropertyName();
            if (AlarmEventsModel.PROPERTY_EVENTS.equals(propertyName)) {
                addEvents();
            } else if (AlarmEventsModel.PROPERTY_SHOW_ICON.equals(propertyName)) {
                checkBoxShowIcon.setSelected((boolean) evt.getNewValue());
            }
        }
    };

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        panelContent = new javax.swing.JPanel();
        scrollPaneAlarmEvents = new javax.swing.JScrollPane();
        panelEvents = new javax.swing.JPanel();
        checkBoxShowIcon = new javax.swing.JCheckBox();
        panelButtons = new javax.swing.JPanel();
        buttonAdd = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        panelContent.setLayout(new java.awt.GridBagLayout());

        scrollPaneAlarmEvents.setPreferredSize(new java.awt.Dimension(600, 300));

        panelEvents.setPreferredSize(new java.awt.Dimension(300, 200));
        panelEvents.setLayout(new java.awt.GridBagLayout());
        scrollPaneAlarmEvents.setViewportView(panelEvents);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        panelContent.add(scrollPaneAlarmEvents, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(checkBoxShowIcon, org.openide.util.NbBundle.getMessage(AlarmEventsPanel.class, "AlarmEventsPanel.checkBoxShowIcon.text")); // NOI18N
        checkBoxShowIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxShowIconActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 0, 0, 0);
        panelContent.add(checkBoxShowIcon, gridBagConstraints);

        panelButtons.setLayout(new java.awt.GridBagLayout());

        buttonAdd.setAction(addAction);
        org.openide.awt.Mnemonics.setLocalizedText(buttonAdd, org.openide.util.NbBundle.getMessage(AlarmEventsPanel.class, "AlarmEventsPanel.buttonAdd.text")); // NOI18N
        panelButtons.add(buttonAdd, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(7, 5, 0, 0);
        panelContent.add(panelButtons, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(panelContent, gridBagConstraints);
    }//GEN-END:initComponents

    private void checkBoxShowIconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxShowIconActionPerformed
        AlarmEventsModel.getInstance().setShowIcon(checkBoxShowIcon.isSelected());
    }//GEN-LAST:event_checkBoxShowIconActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAdd;
    private javax.swing.JCheckBox checkBoxShowIcon;
    private javax.swing.JPanel panelButtons;
    private javax.swing.JPanel panelContent;
    private javax.swing.JPanel panelEvents;
    private javax.swing.JScrollPane scrollPaneAlarmEvents;
    // End of variables declaration//GEN-END:variables

}
