package data;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.Objects;

/**
 * Class for study group coordinates
 */
public class Coordinates implements Serializable {
    private Integer x;
    private Double y;

    /**
     * Class constructor
     *
     * @param aX - first coordinate
     * @param aY - second coordinate
     */
    public Coordinates(Integer aX, Double aY) {
        x = aX;
        y = aY;
    }

    /**
     * Class constructor for Xml parser
     */
    public Coordinates() {
        x = null;
        y = null;
    }

    @XmlElement(name = "CoordinateX")
    public void setX(Integer aX) {
        x = aX;
    }

    @XmlElement(name = "CoordinateY")
    public void setY(Double aY) {
        y = aY;
    }

    public Integer getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    @Override
    public String toString() {
        return getX() + ", " + getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}