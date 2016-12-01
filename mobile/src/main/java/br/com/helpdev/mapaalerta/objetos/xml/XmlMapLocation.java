package br.com.helpdev.mapaalerta.objetos.xml;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

/**
 * Created by Guilherme Biff Zarelli on 25/11/16.
 */
@Root(name = "location")
public class XmlMapLocation implements Serializable {

    @Element(name = "lat")
    private double latitude;
    @Element(name = "lng")
    private double longitude;
    @Element(name = "alt")
    private double alt;

    public double getAlt() {
        return alt;
    }

    public void setAlt(double alt) {
        this.alt = alt;
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

    @Override
    public String toString() {
        return "XmlMapLocation{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", alt=" + alt +
                '}';
    }
}
