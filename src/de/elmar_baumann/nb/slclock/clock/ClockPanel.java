package de.elmar_baumann.nb.slclock.clock;

import de.elmar_baumann.nb.slclock.util.NamedThreadFactory;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import javax.swing.SwingUtilities;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.util.NbBundle;

/**
 * Displays date and time in the status line.
 *
 * @author Elmar Baumann
 */
public class ClockPanel extends javax.swing.JPanel {

    private static final int REFRESH_INTERVAL_MILLISECONDS = 1000;
    private static final long serialVersionUID = 1L;
    private final ThreadFactory threadFactory = new NamedThreadFactory("StatusLineClock: Clock Updater");
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(threadFactory);

    public ClockPanel() {
        initComponents();
        DateFormatArray dateFormatArray = ClockPreferences.restoreDateFormatArray();
        labelClock.setText(dateFormatArray.format(new Date()));
        scheduler.scheduleWithFixedDelay(
                new ClockLabelUpdater(dateFormatArray),
                0, // refresh immediately
                REFRESH_INTERVAL_MILLISECONDS,
                TimeUnit.MILLISECONDS);
        labelClock.addMouseListener(preferencesDialogDisplayer);
        labelClock.setToolTipText(getToolTipText());
    }

    private final MouseListener preferencesDialogDisplayer = new MouseAdapter() {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (SwingUtilities.isLeftMouseButton(e)) {
                ClockPreferencesPanel clockPreferencesPanel = new ClockPreferencesPanel();
                clockPreferencesPanel.setShowAlarmClockSettingsButton(true);
                DialogDescriptor dd = new DialogDescriptor(
                       clockPreferencesPanel, // innerPane
                        NbBundle.getMessage(ClockPanel.class, "ClockPanel.PreferencesDialog.Title"), // title
                        true, // modal
                        new Object[] {DialogDescriptor.OK_OPTION}, //options
                        DialogDescriptor.OK_OPTION, // initialValue
                        DialogDescriptor.DEFAULT_ALIGN, // optionsAlign
                        null, // helpCtx
                        null //bl
                );
                DialogDisplayer.getDefault().notify(dd);
            }
        }
    };

    private class ClockLabelUpdater implements Runnable, ClockPreferencesListener {

        private DateFormatArray dateFormatArray;

        private ClockLabelUpdater(DateFormatArray dateFormatArray) {
            this.dateFormatArray = dateFormatArray;
            listen();
        }

        private void listen() {
            ClockPreferences.addListener(this);
        }

        @Override
        public void run() {
            Date now = new Date();
            labelClock.setText(dateFormatArray.format(now));
        }

        @Override
        public void dateFormatChanged(DateFormatArray newFormat) {
            dateFormatArray = newFormat;
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {//GEN-BEGIN:initComponents

        labelClock = new javax.swing.JLabel();

        setToolTipText(org.openide.util.NbBundle.getMessage(ClockPanel.class, "ClockPanel.toolTipText")); // NOI18N
        setLayout(new java.awt.GridBagLayout());
        add(labelClock, new java.awt.GridBagConstraints());
    }//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelClock;
    // End of variables declaration//GEN-END:variables

}
