/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ubp;

import java.io.InputStream;
import weka.clusterers.SimpleKMeans;
import weka.core.converters.ConverterUtils;

/**
 *
 * @author agustin
 */
public class KMedias extends AprendizClustering {

    private KMedias() {

    }

    public KMedias(InputStream is) {
        super.setIs(is);
    }


    @Override
    public void cargarInstancias() throws Exception {
        ConverterUtils.DataSource source = new ConverterUtils.DataSource(getIs());
        setInstancias(source.getDataSet());
        getInstancias().setClassIndex(-1);
        System.out.println("Se cargaron " + getInstancias().numInstances() + " instancias");
    }
    
    @Override
    public void crearClustering() throws Exception {
        if (getInstancias() == null) {
            System.out.println("No fueron cargadas las instancias!");
            return;
        }
        if (getInstancias().classIndex() != -1) {
            System.out.println("No debe haber un atributo de clase para poder construir el modelo de clustering");
            return;
        }
        setClustering(new SimpleKMeans());
        getClustering().buildClusterer(getInstancias());
    }

    @Override
    public void metricas() throws Exception {
        if (getInstancias() == null || getClustering() == null) {
            System.err.println("No fueron cargadas las instancias o el clusterizador!");
            return;
        }
        System.out.println(((SimpleKMeans)getClustering()));
    }
    
}
