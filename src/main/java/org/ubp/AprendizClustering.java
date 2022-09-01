/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ubp;

import weka.clusterers.Clusterer;

/**
 *
 * @author Agustín Fernandez
 */
public abstract class AprendizClustering extends Aprendiz {

    private Clusterer clustering;//Pruede ser un árbol j48 o una regresión lineal o un árbol de regresión o etc

    
    public Clusterer getClustering() {
        return clustering;
    }

    public void setClustering(Clusterer clustering) {
        this.clustering = clustering;
    }  

    public abstract void crearClustering() throws Exception;



}
