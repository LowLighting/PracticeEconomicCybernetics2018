package com.company;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;

class Segment {
    private static double x = 0;
    private final double radius = 20;
    private double length;
    private BufferedImage segment;
    private Color segmentColor;
    private Color pointColor;
    private Color backGroundColor;

    private Rectangle2D.Double rectangle;
    private Ellipse2D.Double point;
    private double rotationX;

    private Point2D.Double center;
    private double shift;

    private int angle;// угол поворота отрезка

    Segment(double length, double posPointRotating, int angle,
            Color segmentColor, Color pointColor, Color backGroundColor)
            throws Exception {
        if (length <= 0 || posPointRotating < 0 || length < posPointRotating)
            throw new Exception("Error: invalid parameter class Segment"); // проверяем параметры

        this.angle = angle;
        this.segmentColor = segmentColor;
        this.pointColor = pointColor;
        this.backGroundColor = backGroundColor;
        this.length = length;

        segment = new BufferedImage((int) length * 3, (int) length * 3,
                BufferedImage.TYPE_INT_ARGB);// создаем рисунок

        center = new Point2D.Double(length, 3 * length / 2);
        rotationX = center.x + posPointRotating - radius / 2;
        rectangle = new Rectangle2D.Double(center.x, center.y, length, radius);
        point = new Ellipse2D.Double(rotationX, center.y, radius, radius);

        Graphics2D graphics2 = segment.createGraphics();

        graphics2.setBackground(backGroundColor);//закрашиваем
        graphics2.clearRect(0, 0, (int) (3 * length), (int) (3 * length));

        graphics2.setColor(segmentColor);
        graphics2.fill(rectangle);

        graphics2.setColor(pointColor);
        graphics2.fill(point);
    }

    void Position(double shiftX, double shiftY) {

        this.shift = shiftX;
        center.y = center.y + shiftY * Math.sin(Math.toRadians(angle * x));
        rectangle = new Rectangle2D.Double(center.x, center.y, length + radius, radius);
        point = new Ellipse2D.Double(rotationX + shift + radius / 2, center.y, radius, radius);
    }

    void rotate() {
        AffineTransform affinetransform = AffineTransform.getRotateInstance(
                Math.toRadians(angle * (++x)), rotationX + radius / 2 + shift,
                center.y);

        Graphics2D graphics2 = segment.createGraphics();

        graphics2.setBackground(backGroundColor);
        graphics2.clearRect(0, 0, (int) (3 * length), (int) (3 * length));

        graphics2.setTransform(affinetransform);
        graphics2.setColor(segmentColor);
        graphics2.fill(rectangle);

        graphics2.setColor(pointColor);
        graphics2.fill(point);

    }

    BufferedImage getSegment() {
        return segment;
    }
}