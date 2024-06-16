package org.example;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EscribirXml {



    public static void main(String[] args) throws JAXBException {
        /* Para escribir un xml tenemos que crear un contexto con JAXBContext, no tiene constructor sino de un método estático
            al que se le pasa la clase raíz y puede lanzar un error del tipo JAXBException
         */
        JAXBContext contexto = JAXBContext.newInstance(Deportista.class);

        // Creamos un objeto Marshaller
        Marshaller m = contexto.createMarshaller();
        Deportista deportista = new Deportista("Pedro" , 23, 89.3,1.89);

        // Dar formato al xml
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);


        // Si queremos imprimir por pantalla
        m.marshal(deportista,System.out);
        // Si queremos guardar en un archivo
        m.marshal(deportista,new File("deportista.xml"));

        /* Este ejemplo solo escribe un elemento raiz deportista,
        si quermeos varios elementos deportista temenos que crear una clase que lo envuelva,
        para este ejemplo utilizaremos la clase Deportista y clase  DeportistasWrapper definida en la clase Deportista
         */
        //Ejemplo con la clase Deportista----------------
        JAXBContext contexto2 = JAXBContext.newInstance(Deportistas.class);

        // Creamos un objeto Marshaller
        Marshaller m2 = contexto2.createMarshaller();

        // Crear una lista de deportistas
        ArrayList<Deportista> listaDeportistas = new ArrayList<>();
        listaDeportistas.add(new Deportista("Pedro", 23, 89.3, 1.89));
        listaDeportistas.add(new Deportista("Juan", 30, 70.2, 1.75));
        listaDeportistas.add(new Deportista("Ana", 25, 60.5, 1.68));

        // Crear el objeto raíz Deportistas
        Deportistas deportistas = new Deportistas(listaDeportistas);

        // Dar formato al xml
        m2.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Marshall el objeto Deportistas
        System.out.println("-------------------------");
        System.out.println("Ejemplo con la clase Deportistas");
        m2.marshal(deportistas, System.out);
        m2.marshal(deportistas,new File("deportistas1.xml"));

        //Ejemplo con la clase DeportistaWrapper----------------
        JAXBContext contexto3 = JAXBContext.newInstance(Deportista.DeportistasWrapper.class, Deportista.class);

        // Creamos un objeto Marshaller
        Marshaller m3 = contexto3.createMarshaller();

        // Crear una lista de deportistas
        List<Deportista> listaDeportistas2 = new ArrayList<>();
        listaDeportistas2.add(new Deportista("Pedro", 23, 89.3, 1.89));
        listaDeportistas2.add(new Deportista("Juan", 30, 70.2, 1.75));
        listaDeportistas2.add(new Deportista("Ana", 25, 60.5, 1.68));

        // Crear el objeto raíz DeportistasWrapper
        Deportista.DeportistasWrapper wrapper = new Deportista.DeportistasWrapper();
        wrapper.setDeportistas(listaDeportistas2);

        // Dar formato al xml
        m3.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Marshall el objeto DeportistasWrapper
        System.out.println("-------------------------");
        System.out.println("Ejemplo con la clase DeportistaWrapper");
        m3.marshal(wrapper, System.out);
        m3.marshal(wrapper,new File("deportistas2.xml"));
    }
}