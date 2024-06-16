package org.example;

import org.w3c.dom.Document;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class TemperaturaMain {
    public static void main(String[] args) {
        String menu = """
                1. Registrar nueva temperatura.
                2. Mosrar historial de registro.
                3. Salir.
                """;

        RegTemperatura.RegTemperaturaWrapper registros;
        Boolean salir = false;
        Scanner sc = new Scanner(System.in);
        int opcion;
        int max, min;
        File registrosFile = new File("temperaturas.xml");

        ArrayList<RegTemperatura> listTemperaturas = new ArrayList<>();

        try {
            JAXBContext context = JAXBContext.newInstance(RegTemperatura.RegTemperaturaWrapper.class, RegTemperatura.class);
            if (!isXMLFileEmpty(registrosFile)){
                Unmarshaller unmarshaller = context.createUnmarshaller();
                registros = (RegTemperatura.RegTemperaturaWrapper) unmarshaller.unmarshal(registrosFile);
                listTemperaturas = registros.getTemperaturas();
            }else{
                registros = new RegTemperatura.RegTemperaturaWrapper();
            }

            System.out.println("Introduce el número de la acción que quiere realizar");
            while (!salir){
                System.out.println(menu);
                opcion = sc.nextInt();
                switch (opcion){
                    case 1->{
                        System.out.println("Escribe la temperatura máxima");
                        max = sc.nextInt();
                        System.out.println("Escibre la temperatura mínima");
                        min = sc.nextInt();
                        listTemperaturas.add(new RegTemperatura(max,min));
                    }
                    case 2->{
                        if(listTemperaturas.isEmpty()){
                            System.out.println("No se han encontrado registros");
                        }else {
                            System.out.println(listTemperaturas);
                        }

                    }
                    case 3->{
                        System.out.println("Cerrando programa...");
                        try {
                            registros.setTemperaturas(listTemperaturas);
                            Marshaller marshaller = context.createMarshaller();
                            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                            marshaller.marshal(registros,registrosFile);
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                        salir = true;
                    }
                    default -> {
                        System.out.println("Introduce una opción válida");
                    }
                }
            }


        }catch (Exception e){
            System.out.println(e.getMessage());
            //e.printStackTrace();
        }



    }

    private static boolean isXMLFileEmpty(File xmlFile) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // Comprueba si el archivo contiene solo la etiqueta raíz vacía
            return doc.getDocumentElement().getChildNodes().getLength() == 0;
        } catch (Exception e) {
            //e.printStackTrace();
            return true; // En caso de excepción, asumimos que el archivo está vacío o mal formado
        }
    }
}
