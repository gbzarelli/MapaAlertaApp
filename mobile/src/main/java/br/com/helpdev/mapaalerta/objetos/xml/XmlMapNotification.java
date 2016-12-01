package br.com.helpdev.mapaalerta.objetos.xml;


import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

/**
 * Created by Guilherme Biff Zarelli on 25/11/16.
 */
@Root(name = "notification")
public class XmlMapNotification implements Serializable {

    public static final int TRANSITION_ENTER = 1 << 1;
    public static final int TRANSITION_EXIT = 1 << 2;
    public static final int TRANSITION_DWELL = 1 << 3;

    @Attribute(name = "transation_notification_flags")
    private int transationNotificationFlags;

    @Element(name = "text_reproduction", required = false)
    private String textReproduction;
    @Element(name = "file_notification", required = false)
    private String fileNotification;

    @Element(name = "lat")
    private double latitude;
    @Element(name = "lng")
    private double longitude;
    @Element(name = "radius")
    private float radius;

    public String getTextReproduction() {
        return textReproduction;
    }

    public void setTextReproduction(String textReproduction) {
        this.textReproduction = textReproduction;
    }

    public String getFileNotification() {
        return fileNotification;
    }

    public void setFileNotification(String fileNotification) {
        this.fileNotification = fileNotification;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public int getTransationNotificationFlags() {
        return transationNotificationFlags;
    }

    public void setTransationNotificationFlags(int transationNotificationFlags) {
        this.transationNotificationFlags = transationNotificationFlags;
    }

    @Override
    public String toString() {
        return "XmlMapNotification{" +
                "transationNotificationFlags=" + transationNotificationFlags +
                ", textReproduction='" + textReproduction + '\'' +
                ", fileNotification='" + fileNotification + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", radius=" + radius +
                '}';
    }
}
