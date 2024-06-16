package org.example;

import javax.xml.bind.annotation.*;
import java.time.LocalDate;
import java.util.ArrayList;

@XmlRootElement(name = "RegistroTemp")
@XmlType(propOrder = {"fecha", "maxima","minima"})
@XmlAccessorType(XmlAccessType.FIELD)
public class RegTemperatura {
    @XmlAttribute(name = "Fecha")
    String fecha;
    @XmlElement(name = "TempMaxima")
    int maxima;
    @XmlElement(name = "TempMinima")
    int minima;

    public RegTemperatura() {
    }

    public RegTemperatura(int maxima, int mínima) {
        this.maxima = maxima;
        this.minima = mínima;
        this.fecha = LocalDate.now().toString();
    }

    public int getMaxima() {
        return maxima;
    }

    public void setMaxima(int maxima) {
        this.maxima = maxima;
    }

    public int getMinima() {
        return minima;
    }

    public void setMinima(int minima) {
        this.minima = minima;
    }

    public String getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "RegTemperatura{" +
                "fecha='" + fecha + '\'' +
                ", maxima=" + maxima +
                ", minima=" + minima +
                '}';
    }

    @XmlRootElement(name = "Registros")
    public static class RegTemperaturaWrapper{

        private ArrayList<RegTemperatura> temperaturas;

        public RegTemperaturaWrapper() {
        }

        public RegTemperaturaWrapper(ArrayList<RegTemperatura> temperaturas) {
            this.temperaturas = new ArrayList<>();
        }

        @XmlElement(name = "RegistroTemp")
        public ArrayList<RegTemperatura> getTemperaturas() {
            return temperaturas;
        }

        public void setTemperaturas(ArrayList<RegTemperatura> temperaturas) {
            this.temperaturas = temperaturas;
        }

        public void agregarTemperatura(RegTemperatura regTemperatura){
            temperaturas.add(regTemperatura);
        }

        @Override
        public String toString() {
            return "RegTemperaturaWrapper{" +
                    "temperaturas=" + temperaturas +
                    '}';
        }
    }
}
