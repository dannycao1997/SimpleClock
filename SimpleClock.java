//package SimpleClock;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;


public class SimpleClock extends JFrame {
    
        Calendar calendar;
        SimpleDateFormat timeFormat;
        SimpleDateFormat dayFormat;
        SimpleDateFormat dateFormat;
    
        JLabel timeLabel;
        JLabel dayLabel;
        JLabel dateLabel;
        JButton formatButton; // button to switch between 12hour and 24hour format
        JButton timeZoneButton; // button to switch between local time and GMT
        boolean is24HourFormat = false; // flag to track if 24hour format is on
        String time;
        String day;
        String date;


        SimpleClock() {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Digital Clock");
            this.setLayout(new FlowLayout());
            this.setSize(370, 260); // changed width to 370 and height to 260 to fit new buttons
            this.setResizable(false);
    
            timeFormat = new SimpleDateFormat("hh:mm:ss a");
            dayFormat=new SimpleDateFormat("EEEE");
            dateFormat=new SimpleDateFormat("dd MMMMM, yyyy");

            timeLabel = new JLabel();
            timeLabel.setFont(new Font("SANS_SERIF", Font.PLAIN, 50)); // changed size to 50
            timeLabel.setBackground(Color.WHITE); // changed background to white
            timeLabel.setForeground(Color.PINK); // changed foreground to pink
            timeLabel.setOpaque(false); // changed to false

            dayLabel=new JLabel();
            dayLabel.setFont(new Font("Ink Free",Font.BOLD,30)); // changed size to 30
    
            dateLabel=new JLabel();
            dateLabel.setFont(new Font("Ink Free",Font.BOLD,25)); // changed size to 25

            // initialize switch format button
            formatButton = new JButton("12/24HR FORMAT");
            formatButton.addActionListener(e -> toggleFormat());

            // initialize local/gmt button
            timeZoneButton = new JButton("LocalTime/GMT");
            timeZoneButton.addActionListener(e -> toggleTimeZone());
    
    
            this.add(timeLabel);
            this.add(dayLabel);
            this.add(dateLabel);
            this.setVisible(true);
            this.add(formatButton); // added for Jframe
            this.add(timeZoneButton); // added for Jframe
    
            setTimer();
        }
    
        public void setTimer() {
            Thread clockThread = new Thread(() -> { // using a thread for updating the clock
                while (true) {
                    time = timeFormat.format(Calendar.getInstance().getTime());
                    timeLabel.setText(time);

                    day = dayFormat.format(Calendar.getInstance().getTime());
                    dayLabel.setText(day);

                    date = dateFormat.format(Calendar.getInstance().getTime());
                    dateLabel.setText(date);

                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.getStackTrace();
                    }
                }
            });
            clockThread.start(); // start the clock update thread
        }

        // toggle between 12hour and 24hour format
        private void toggleFormat(){
            is24HourFormat = !is24HourFormat;
            if (is24HourFormat) {
                timeFormat = new SimpleDateFormat("HH:mmss");
            } else {
                timeFormat = new SimpleDateFormat("hh:mm:ss a");
            }
        }

        // toggle between local time and GMT
        private void toggleTimeZone(){
            if(timeFormat.getTimeZone().getID().equals("GMT")) {
                timeFormat.setTimeZone(TimeZone.getDefault());
            } else {
                timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            }
        }

        public static void main(String[] args) {
            new SimpleClock();
        }
    }
