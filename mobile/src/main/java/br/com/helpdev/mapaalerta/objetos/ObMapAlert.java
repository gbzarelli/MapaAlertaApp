package br.com.helpdev.mapaalerta.objetos;

import java.io.Serializable;

import br.com.helpdev.mapaalerta.objetos.xml.XmlMapAlert;
import br.com.helpdev.supportlib_maps.gpx.objetos.Gpx;

/**
 * Created by Guilherme Biff Zarelli on 30/11/16.
 */

public class ObMapAlert implements Serializable {

    private XmlMapAlert obMapAlertXml;
    private Gpx gpx;

    public ObMapAlert(XmlMapAlert obMapAlertXml) {
        this(obMapAlertXml, null);
    }

    public ObMapAlert(XmlMapAlert obMapAlertXml, Gpx gpx) {
        this.obMapAlertXml = obMapAlertXml;
        this.gpx = gpx;
    }

    public XmlMapAlert getObMapAlertXml() {
        return obMapAlertXml;
    }

    public void setObMapAlertXml(XmlMapAlert obMapAlertXml) {
        this.obMapAlertXml = obMapAlertXml;
    }

    public Gpx getGpx() {
        return gpx;
    }

    public void setGpx(Gpx gpx) {
        this.gpx = gpx;
    }

    @Override
    public String toString() {
        return "ObMapAlert{" +
                "obMapAlertXml=" + obMapAlertXml +
                ", gpx=" + gpx +
                '}';
    }
}
