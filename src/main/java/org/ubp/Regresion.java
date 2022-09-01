/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ubp;

import java.io.InputStream;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.core.Attribute;
import weka.core.Debug;
import weka.core.converters.ConverterUtils;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

/**
 *
 * @author Agustín Fernandez
 */
public class Regresion extends AprendizClasificacion{
    
    private Regresion(){
        
    }
    
    public Regresion(InputStream is){
        super.setIs(is);
    }
    
    @Override
    public void cargarInstancias() throws Exception {
        ConverterUtils.DataSource source = new ConverterUtils.DataSource(getIs());
        setInstancias(source.getDataSet());
//        Remove rm = new Remove();
//        String [] opt = new String[]{"-R", "1"};
//        rm.setOptions(opt);
//        rm.setInputFormat(getInstancias());
//        setInstancias(Filter.useFilter(getInstancias(), rm));
        getInstancias().setClassIndex(getInstancias().numAttributes() - 1);
        System.out.println("Se cargaron "+ getInstancias().numInstances() + " instancias");
    }

    @Override
    public void crearClasificador() throws Exception {
        if(getInstancias() == null){
            System.err.println("No fueron cargadas las instancias!");
            return;
        }
        Attribute attr = getInstancias().attribute(getInstancias().numAttributes() - 1);
        if(!attr.isNumeric()){
            System.err.println("El último atributo debe ser numérico para poder construir el modelo de regresión lineal");
            return;
        } 
        setClasificador(new LinearRegression());
        getClasificador().buildClassifier(getInstancias());
        System.out.println(getClasificador());
    }

    @Override
    public void metricas() throws Exception {
        if(getInstancias() == null || getClasificador() == null){
            System.err.println("No fueron cargadas las instancias o el clasificador!");
            return;
        }
        Evaluation eval = new Evaluation(getInstancias());
        int numConjuntos = 10;
        Debug.Random random = new Debug.Random(1);
        eval.crossValidateModel(getClasificador(), getInstancias(), numConjuntos, random);
        System.out.println(eval.toSummaryString());
    }
    
    
    
}
