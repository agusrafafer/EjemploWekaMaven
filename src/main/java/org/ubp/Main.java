/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ubp;

import java.io.InputStream;

/**
 *
 * @author Agustín Fernandez
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
//        InputStream is = Main.class.getResourceAsStream("/dataset/drug1n.arff");//para árbol
//        InputStream is = Main.class.getResourceAsStream("/dataset/Behavior_of_the_urban_traffic.arff");//para regresión
//        InputStream is = Main.class.getResourceAsStream("/dataset/housing.arff");//para arbolregresión

//Al factory debe pasarse el nombre de la clase que se quiera instanciar
//        Aprendiz aprendedor = Factory.crear("ArbolRegresion", is);
//        Aprendiz aprendedor = Factory.crear("Arbol", is);
//        aprendedor.cargarInstancias();
//        ((AprendizClasificacion)aprendedor).crearClasificador();
//        aprendedor.metricas();
        

        //Para Clustering
        InputStream is = Main.class.getResourceAsStream("/dataset/housing.arff");
        Aprendiz aprendedor = Factory.crear("ExpMaxim", is);
        aprendedor.cargarInstancias();
        ((AprendizClustering)aprendedor).crearClustering();
        aprendedor.metricas();
        
        
    }

}
