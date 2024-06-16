package org.example;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class LeerXml {
    public static void main(String[] args) {
        //Para leer un xml necesitmos un contexto de JAXBContext
        try {
            JAXBContext context = JAXBContext.newInstance(Deportista.class);

            // Para leer necesitamos un Unmarshaller
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Recuperamos los datos
            Deportista deportista = (Deportista) unmarshaller.unmarshal(new File("deportista.xml"));

            System.out.println(deportista);
            //--------------------------------
            //Ejemplo con varios deportistas y la clase Deportistas
            JAXBContext context1 = JAXBContext.newInstance(Deportistas.class);

            // Para leer necesitamos un Unmarshaller
            Unmarshaller unmarshaller2 = context1.createUnmarshaller();

            // Recuperamos los datos
            System.out.println("----------------------");
            Deportistas deportistas = (Deportistas) unmarshaller2.unmarshal(new File("deportistas1.xml"));
            System.out.println(deportistas);

            //--------------------------------
            //Ejemplo con varios deportistas y la clase DeportistasWrapper definida en la clase Deportista
            JAXBContext context2 = JAXBContext.newInstance(Deportista.DeportistasWrapper.class, Deportista.class);

            // Para leer necesitamos un Unmarshaller
            Unmarshaller unmarshaller3 = context2.createUnmarshaller();

            // Recuperamos los datos
            System.out.println("----------------------");
            Deportista.DeportistasWrapper result = (Deportista.DeportistasWrapper) unmarshaller3.unmarshal(new File("deportistas2.xml"));
            System.out.println(result);


        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
