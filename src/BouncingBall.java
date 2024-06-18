import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BouncingBall extends JFrame {

    // Constants for the window size and ball size
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private static final int BALL_SIZE = 90;

    // Ball's current position and speed
    private int x = 0;
    private int y = 0;
    private int xSpeed = 1;
    private int ySpeed = 1;

    // Variables for drag functionality
    private boolean isDragging = false;
    private int mouseX;
    private int mouseY;

    // Constructor to set up the JFrame
    public BouncingBall() {
        setTitle("Bouncing Ball");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and add BallPanel to the JFrame
        BallPanel ballPanel = new BallPanel();
        add(ballPanel);

        // Add mouse listener to handle mouse press events
        ballPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent ev) {
                // Check if the mouse press is within the ball
                if (ev.getX() >= x && ev.getX() <= x + BALL_SIZE &&
                        ev.getY() >= y && ev.getY() <= y + BALL_SIZE) {
                    isDragging = true;
                    mouseX = ev.getX();
                    mouseY = ev.getY();
                }
            }

            @Override
            public void mouseReleased(MouseEvent ev) {
                // Stop dragging and adjust the ball's speed based on the drag
                isDragging = false;
                xSpeed = (ev.getX() - mouseX) / 10;
                ySpeed = (ev.getY() - mouseY) / 10;
            }
        });

        // Add mouse motion listener to handle dragging the ball
        ballPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent ev) {
                // Update ball's position while dragging
                if (isDragging) {
                    x = ev.getX() - BALL_SIZE / 2;
                    y = ev.getY() - BALL_SIZE / 2;
                    ballPanel.repaint();
                }
            }
        });

        // Timer to update ball's position and repaint the panel
        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                if (!isDragging) {
                    moveBall();
                    ballPanel.repaint();
                }
            }
        });
        timer.start();
    }

    // Method to update the ball's position and handle wall collisions
    private void moveBall() {
        x += xSpeed;
        y += ySpeed;

        // Reverse direction upon hitting the left or right edge
        if (x < 0 || x > WIDTH - BALL_SIZE) {
            xSpeed = -xSpeed;
        }

        // Reverse direction upon hitting the top or bottom edge
        if (y < 0 || y > HEIGHT - BALL_SIZE) {
            ySpeed = -ySpeed;
        }
    }

    // Inner class to handle the drawing of the ball
    private class BallPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.RED);
            g.fillOval(x, y, BALL_SIZE, BALL_SIZE);
        }
    }

    // Main function to run the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BouncingBall().setVisible(true);
            }
        });
    }
}
