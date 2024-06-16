package org.example;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "deportista")
@XmlType(propOrder = {"nombre", "edad", "peso", "estatura"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Deportista {
    /*La anotación @XmlElement puede ser obviada si queremos
     * que el elemento del xml tenga el mismo nombre que el atributo de la clase*/
    @XmlElement(name = "nombre")
    private String nombre;
    @XmlElement(name = "edad")
    private int edad;
    @XmlElement(name = "peso")
    private double peso;
    //@XmlElement(name = "estatura")
    private double estatura;

    // Constructor vacío requerido por JAXB
    public Deportista() {
    }

    public Deportista(String nombre, int edad, double peso, double estatura) {
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.estatura = estatura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getEstatura() {
        return estatura;
    }

    public void setEstatura(double estatura) {
        this.estatura = estatura;
    }

    @Override
    public String toString() {
        return "Deportista{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", peso=" + peso +
                ", estatura=" + estatura +
                '}';
    }

    @XmlRootElement(name = "deportistas")
    public static class DeportistasWrapper {
        private List<Deportista> deportistas;

        @XmlElement(name = "deportista")
        public List<Deportista> getDeportistas() {
            return deportistas;
        }

        public void setDeportistas(List<Deportista> deportistas) {
            this.deportistas = deportistas;
        }

        @Override
        public String toString() {
            return "DeportistasWrapper{" +
                    "deportistas=" + deportistas +
                    '}';
        }
    }
}
