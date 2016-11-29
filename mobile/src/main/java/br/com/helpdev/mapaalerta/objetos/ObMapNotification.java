package br.com.helpdev.mapaalerta.objetos;


import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Guilherme Biff Zarelli on 25/11/16.
 */
@Root(name = "notification")
public class ObMapNotification {

    public static final int TRANSITION_ENTER = 1 << 1;
    public static final int TRANSITION_EXIT = 1 << 2;
    public static final int TRANSITION_DWELL = 1 << 3;

    @Attribute(name = "flags")
    private int transationNotificationFlags;

    @Element(name = "text_reproduction", required = false)
    private String textNotificationReproduction;
    @Element(name = "file_notification", required = false)
    private String nameFileNotification;

    @Element(name = "lat")
    private double latitude;
    @Element(name = "lng")
    private double longitude;
    @Element(name = "radius")
    private float radius;

    public String getTextNotificationReproduction() {
        return textNotificationReproduction;
    }

    public void setTextNotificationReproduction(String textNotificationReproduction) {
        this.textNotificationReproduction = textNotificationReproduction;
    }

    public String getNameFileNotification() {
        return nameFileNotification;
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
        return "ObMapNotification{" +
                "textNotificationReproduction='" + textNotificationReproduction + '\'' +
                ", nameFileNotification='" + nameFileNotification + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", radius=" + radius +
                ", transationNotificationFlags=" + transationNotificationFlags +
                '}';
    }
}
