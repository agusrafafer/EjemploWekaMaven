/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ubp;

import weka.classifiers.Classifier;

/**
 *
 * @author Agustín Fernandez
 */
public abstract class AprendizClasificacion extends Aprendiz {


    private Classifier clasificador;//Pruede ser un árbol j48 o una regresión lineal o un árbol de regresión o etc

    
    public Classifier getClasificador() {
        return clasificador;
    }

    public void setClasificador(Classifier clasificador) {
        this.clasificador = clasificador;
    }  

    public abstract void crearClasificador() throws Exception;



}
