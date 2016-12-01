package br.com.helpdev.mapaalerta.objetos.xml;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Guilherme Biff Zarelli on 25/11/16.
 */
@Root(name = "map_alert")
public class XmlMapAlert implements Serializable {

    @Attribute(name = "title")
    private String title;
    @Attribute(name = "descricao")
    private String descricao;
    @Element(name = "file_gpx", required = false)
    private String fileGpx;
    @Element(name = "file_imagem", required = false)
    private String fileImagem;
    @ElementList(name = "notifications", type = ObMapNotification.class, required = false)
    private List<ObMapNotification> obMapLocations;

    public XmlMapAlert(String title) {
        this(title, null, null, null, null);
    }

    public XmlMapAlert(String title, String descricao, String gpx, List<ObMapNotification> obMapLocations, String fileImagem) {
        this.title = title;
        this.descricao = descricao;
        this.fileGpx = gpx;
        this.obMapLocations = obMapLocations;
        this.fileImagem = fileImagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ObMapNotification> getObMapLocations() {
        return obMapLocations;
    }

    public void setObMapLocations(List<ObMapNotification> obMapLocations) {
        this.obMapLocations = obMapLocations;
    }

    public String getFileGpx() {
        return fileGpx;
    }

    public void setFileGpx(String fileGpx) {
        this.fileGpx = fileGpx;
    }

    public String getFileImagem() {
        return fileImagem;
    }

    public void setFileImagem(String fileImagem) {
        this.fileImagem = fileImagem;
    }

    @Override
    public String toString() {
        return "XmlMapAlert{" +
                "title='" + title + '\'' +
                ", descricao='" + descricao + '\'' +
                ", fileGpx='" + fileGpx + '\'' +
                ", fileImagem='" + fileImagem + '\'' +
                ", obMapLocations=" + obMapLocations +
                '}';
    }
}
