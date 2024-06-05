import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BouncingBall extends JFrame {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private static final int BALL_SIZE = 90;

    private int x = 0;
    private int y = 0;
    private int xSpeed = 1;
    private int ySpeed = 1;

    private boolean isDragging = false;
    private int mouseX;
    private int mouseY;

    public BouncingBall() {
        setTitle("Bouncing Ball");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BallPanel ballPanel = new BallPanel();
        add(ballPanel);

        ballPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent ev) {
                if (ev.getX() >= x && ev.getX() <= x + BALL_SIZE &&
                        ev.getY() >= y && ev.getY() <= y + BALL_SIZE) {
                    isDragging = true;
                    mouseX = ev.getX();
                    mouseY = ev.getY();
                }
            }

            @Override
            public void mouseReleased(MouseEvent ev) {
                isDragging = false;
                xSpeed = (ev.getX() - mouseX) / 10;
                ySpeed = (ev.getY() - mouseY) / 10;
            }
        });

        ballPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent ev) {
                if (isDragging) {
                    x = ev.getX() - BALL_SIZE / 2;
                    y = ev.getY() - BALL_SIZE / 2;
                    ballPanel.repaint();
                }
            }
        });

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

    private void moveBall() {
        x += xSpeed;
        y += ySpeed;

        if (x < 0 || x > WIDTH - BALL_SIZE) {
            xSpeed = -xSpeed;
        }

        if (y < 0 || y > HEIGHT - BALL_SIZE) {
            ySpeed = -ySpeed;
        }
    }

    //
    private class BallPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.RED);
            g.fillOval(x, y, BALL_SIZE, BALL_SIZE);
        }
    }

    //Main function
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BouncingBall().setVisible(true);
            }
        });
    }
}
