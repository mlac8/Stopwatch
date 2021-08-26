/* Stopwatch.java
 * CSCI 112 - Summer 2021
 * last edited Aug. 16, 2021 by M. Lacanilao
 *
 * This class contains all the necessary design and functional components for
 * our desktop GUI application. This version of the project will contain
 * functionality, as last week's version only contained the design components.
 * This time around, we wil implement the java.awt.event.* package of classes in order
 * to utilize the ActionListener class, which allows our GUI JButtons to call the
 * three functions (START, STOP, RESET) that we programmed in this class.
 */
package Stopwatch;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

public class Stopwatch implements ActionListener {

    //  create JFrame object
    JFrame myJFrame = new JFrame ();


    // create functional JButtons for the stopwatch
    JButton jbtStart = new JButton ("START");
    JButton jbtStop = new JButton ("STOP");
    JButton jbtReset = new JButton ("RESET");

    // create JLabel that displays all three time integers declared below
    JLabel jlTime = new JLabel ();
    int timeElapsed = 0;
    int sec = 0;
    int min = 0;
    int hr = 0;
    boolean started = false;

    // create strings that will contain our time integers for formatting
    String secString = String.format ("%02d", sec);
    String minString = String.format ("%02d", min);
    String hrString = String.format ("%02d", hr);

    // set default Font for this application. Testing gaming fonts for future projects!
    Font myFont = new Font ("Pokemon GB", Font.PLAIN, 12);

    // implement Timer class, which can be scaled to any scheduled task in Java
    Timer timer = new Timer (1000, new ActionListener () {
        /*
         Following actionPerformed method contains the math and formatting required
         in order for the correct time to be displayed while the timer is running.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            timeElapsed = timeElapsed+1000;
            hr = (timeElapsed/3600000);
            min = (timeElapsed/60000);
            sec = (timeElapsed/1000) % 60;
            secString = String.format ("%02d", sec);
            hrString = String.format ("%02d", hr);
            minString = String.format ("%02d", min);
            jlTime.setText (hrString+":"+minString+":"+secString);


        }
    });
    Stopwatch() {
        jlTime.setText(hrString + ":" + minString + ":" + secString);
        jlTime.setBounds (50, 100, 300, 100);
        jlTime.setFont (new Font("Pokemon GB", Font.PLAIN, 30));
        jlTime.setBorder (BorderFactory.createRaisedBevelBorder ());
        jlTime.setOpaque (true);
        jlTime.setHorizontalAlignment (JTextField.CENTER);

        // setting properties of Start button
        jbtStart.setBounds (50, 200, 100, 50);
        jbtStart.setFont (myFont);
        jbtStart.setFocusable (false);
        jbtStart.addActionListener (this);

        // set properties of Stop button
        jbtStop.setBounds(150, 200, 100, 50);
        jbtStop.setFont (myFont);
        jbtStop.setFocusable (false);
        jbtStop.addActionListener (this);

        // setting properties of Reset button
        jbtReset.setBounds (250, 200, 100, 50);
        jbtReset.setFont (myFont);
        jbtReset.setFocusable (false);
        jbtReset.addActionListener (this);

        // add JButtons to the JFrame
        myJFrame.add(jbtStart);
        myJFrame.add(jbtStop);
        myJFrame.add(jbtReset);
        myJFrame.add(jlTime);

        // finishing JFrame properties
        myJFrame.setTitle ("Stopwatch");
        ImageIcon icon = new ImageIcon  ("./src/hourglass.png");
        myJFrame.setIconImage (icon.getImage ());
        myJFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        myJFrame.setSize(420, 420);
        myJFrame.setLayout (null);
        myJFrame.getContentPane().setBackground(Color.CYAN);
        myJFrame.setResizable (false);
        myJFrame.setVisible (true);
    } // end Stopwatch()

    /* Our actionPerformed method will allow us to call the functions that
     control our Stopwatch app. Each if() statement represents a different
     function that can be called by our three buttons thanks to the
      ActionListener class. */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbtStart) {
            start();
        }
        if (e.getSource() == jbtStop) {
            started = true;
            stop();
        }
        if (e.getSource() == jbtReset) {
            started = false;
            reset ();
        }
    } // end actionPerformed()

    // start method
    void start() {
        timer.start();
    } // end stop()

    // stop method
    void stop() {
        timer.stop();
    } //end stop()

    /* reset method. this one is a little more complex because we need all of our
    properties to be overwritten by resetting all of our variables back to zero. */
    void reset() {
        timer.stop();
        timeElapsed = 0;
        sec = 0;
        min = 0;
        hr = 0;
        secString = String.format ("%02d", sec);
        hrString = String.format ("%02d", hr);
        minString = String.format ("%02d", min);
        jlTime.setText (hrString+":"+minString+":"+secString);
    } // end reset()
} // end class Stopwatch
