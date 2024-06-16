package org.example;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlRootElement(name = "Deportista")
@XmlType(propOrder = {"deportista"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Deportistas {
    @XmlElement(name = "deportista")
    private ArrayList<Deportista> deportista;

    public Deportistas() {
    }

    public Deportistas(ArrayList<Deportista> deportista) {
        this.deportista = deportista;
    }

    public void agregarDeportista(Deportista deportista){
        this.deportista.add(deportista);
    }

    public ArrayList<Deportista> getDeportista() {
        return deportista;
    }

    public void setDeportista(ArrayList<Deportista> deportista) {
        this.deportista = deportista;
    }

    @Override
    public String toString() {
        return "Deportistas{" +
                "deportista=" + deportista +
                '}';
    }
}
