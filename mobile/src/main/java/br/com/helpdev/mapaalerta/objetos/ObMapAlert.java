package br.com.helpdev.mapaalerta.objetos;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;

import br.com.helpdev.supportlib_maps.gpx.objetos.Gpx;

/**
 * Created by Guilherme Biff Zarelli on 25/11/16.
 */
@Root(name = "map_alert")
public class ObMapAlert implements Serializable {

    @Attribute(name = "title")
    private String title;
    @Element(name = "gpx", type = Gpx.class, required = false)
    private Gpx gpx;
    @ElementList(name = "notifications", type = ObMapNotification.class, required = false)
    private List<ObMapNotification> obMapLocations;

    public ObMapAlert(String title) {
        this(title, null, null);
    }

    public ObMapAlert(String title, Gpx gpx, List<ObMapNotification> obMapLocations) {
        this.title = title;
        this.gpx = gpx;
        this.obMapLocations = obMapLocations;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Gpx getGpx() {
        return gpx;
    }

    public void setGpx(Gpx gpx) {
        this.gpx = gpx;
    }

    public List<ObMapNotification> getObMapLocations() {
        return obMapLocations;
    }

    public void setObMapLocations(List<ObMapNotification> obMapLocations) {
        this.obMapLocations = obMapLocations;
    }

    @Override
    public String toString() {
        return "ObMapAlert{" +
                "title='" + title + '\'' +
                ", gpx=" + gpx +
                ", obMapLocations=" + obMapLocations +
                '}';
    }
}
