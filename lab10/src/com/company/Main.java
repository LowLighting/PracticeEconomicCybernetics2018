package com.company;


import java.awt.*;
import java.awt.image.BufferedImage;


public class Main extends java.applet.Applet implements Runnable {
    final private int width = 800, height = 800;
    private BufferedImage background;
    private Graphics2D picture;
    private boolean stop = false;
    private Thread timer = null;

    private Color fonColor = Color.WHITE;
    private Color segmentColor = Color.RED;
    private Color pointColor = Color.GREEN;
    private Segment segment;

    private double lengthSegment;

    // направление смещения оси вращения
    private double movePoint = -1;
    private double shift = 0;
    final private double speedPoint = 1;

    public void init() {
        try {

            lengthSegment = (double) Math.min(width, height) / 4;
            int displacement = 15;// угол смещения
            segment = new Segment(lengthSegment, lengthSegment / 2, displacement,
                    segmentColor, pointColor, fonColor);

            background = (BufferedImage) createImage(width, height);
            picture = background.createGraphics();
            picture.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            timer = new Thread(this);
            timer.start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void update(Graphics graphics) {
        try {

            Graphics2D graphics2D = (Graphics2D) graphics;
            drawSegment();

            graphics2D.drawImage(background, 0, 0, this);

        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
    }

    private void drawSegment() {
        shift += movePoint * speedPoint;
        if (shift < -lengthSegment / 2) {
            movePoint *= -1;
            shift = -lengthSegment / 2;
        } else if (shift > lengthSegment / 2) {
            movePoint *= -1;
            shift = lengthSegment / 2;
        }
        segment.Position(shift, speedPoint);
        segment.rotate();
        picture.drawImage(segment.getSegment(), null, 0, 0);
    }

    public void run() {
        while (!stop) {
            try {
                repaint();
                Thread.currentThread();
                int speedRepaint = 70;
                Thread.sleep(speedRepaint);
            } catch (Exception ignored) {
            }
        }
    }

    public void stop() {
        super.stop();
        stop = true;
    }


    public void start() {
        super.start();
        stop = false;
        if (timer == null) {
            timer = new Thread(this);
            timer.start();
        }
    }

    public void destroy() {
        super.destroy();
        stop = true;
        Thread.currentThread();
        Thread.yield();
    }
}

