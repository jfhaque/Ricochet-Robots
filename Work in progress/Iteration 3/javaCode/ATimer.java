import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDateTime;

public class ATimer {
    private LocalDateTime startTime;
    private JLabel label;
    private javax.swing.Timer timer;
    private Duration duration;
    private JPanel testPane;
    private boolean hasBiddingTimeStopped;

    public ATimer() {
        testPane = setupActualTestPane();
        JFrame frame = new JFrame("Testing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(testPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);



        this.hasBiddingTimeStopped = false;
    }


    private JPanel setupActualTestPane(){

        JPanel testPaneToReturn = new JPanel();

        testPaneToReturn.setLayout(new GridLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        label = new JLabel("...");
        testPaneToReturn.add(label, gbc);



        duration = Duration.ofSeconds(17);

        JButton button = new JButton("Start");

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDateTime now = LocalDateTime.now();
                Duration runningTime = Duration.between(startTime, now);
                Duration timeLeft = duration.minus(runningTime);
                if (timeLeft.isZero() || timeLeft.isNegative()) {
                    timeLeft = Duration.ZERO;
                    button.doClick();
                }
                label.setText(format(timeLeft));
            }
        };

        timer = new javax.swing.Timer(500, listener);


        button.addActionListener( (ActionEvent e) -> {
            if (timer.isRunning()) {
                timer.stop();
                startTime = null;
                button.setText("Start");
            } else {
                startTime = LocalDateTime.now();
                timer.start();
                button.setText("Stop");
            }
        });

        testPaneToReturn.add(button, gbc);


        return testPaneToReturn;
    }

    protected String format(Duration duration) {
        long hours = duration.toHours();
        long mins = duration.minusHours(hours).toMinutes();
        long seconds = duration.minusMinutes(mins).toMillis() / 1000;
        return String.format("%02dm %02ds", mins, seconds);
    }

    /*public void startTimer(){
        timer.start
    }*/

    public void startTimer(){
        timer.start();
    }

    public boolean hasBiddingTimeStopped(){
        if (timer.isRunning()){
            return false;
        }
        else{
            return true;
        }
    }
}
