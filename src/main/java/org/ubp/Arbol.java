/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ubp;

import java.awt.BorderLayout;
import java.io.InputStream;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.Debug.Random;
import weka.core.DenseInstance;
import weka.core.converters.ConverterUtils;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.AddExpression;
import weka.gui.treevisualizer.PlaceNode1;
import weka.gui.treevisualizer.TreeVisualizer;

/**
 *
 * @author Agustín Fernandez
 */
public class Arbol extends AprendizClasificacion {

    private Arbol() {

    }

    public Arbol(InputStream is) {
        super.setIs(is);
    }

    @Override
    public void cargarInstancias() throws Exception {
        ConverterUtils.DataSource source = new ConverterUtils.DataSource(getIs());
        setInstancias(source.getDataSet());
//        De esta manera puedo quitar un atributo del DataSet        
//        Remove rm = new Remove();
//        String [] opt = new String[]{"-R", "1"};
//        rm.setOptions(opt);
//        rm.setInputFormat(instancias);
//        setInstancias(Filter.useFilter(instancias, rm));
        //De esta manera puedo combinar dos atributos del DataSet
        AddExpression combinados = new AddExpression();
        combinados.setExpression("a5/a6");
        combinados.setName("Na/K");
        combinados.setInputFormat(getInstancias());
        setInstancias(Filter.useFilter(getInstancias(), combinados));
        getInstancias().setClassIndex(getInstancias().numAttributes() - 2);//La forma de indicar cual es el atributo de clasificación
//        getInstancias().setClassIndex(getInstancias().numAttributes() - 1);
        System.out.println("Se cargaron " + getInstancias().numInstances() + " instancias");
    }

    @Override
    public void crearClasificador() throws Exception {
        if (getInstancias() == null) {
            System.out.println("No fueron cargadas las instancias!");
            return;
        }
        Attribute attr = getInstancias().attribute(getInstancias().numAttributes() - 2);
        //Attribute attr = instancias.attribute(instancias.numAttributes() - 1);
        if (!attr.isNominal()) {
            System.out.println("El último atributo debe ser nominal para poder construir el modelo de clasificación");
            return;
        }
        setClasificador(new J48());
        getClasificador().buildClassifier(getInstancias());
        System.out.println("--------------------------------------");
        System.out.println("El árbol de clasificación resultante: ");
        System.out.println(getClasificador());
        System.out.println("--------------------------------------");

        DenseInstance inst = new DenseInstance(8);
        inst.setValue(0, 80d);
        inst.setValue(1, 0);//Es la posición de {F,M} (atributo sexo)
        inst.setValue(2, 1);//Es la posición de {HIGH,LOW,NORMAL} (atributo BP=presión sangre)
        inst.setValue(3, 0);//Es la posición de {HIGH,NORMAL} (atributo colesterol)
        inst.setValue(4, 0.84569d);// atributo Na
        inst.setValue(5, 0.0698d);//atributo K
        inst.setValue(6, 0);//Es la posición de {drugA,drugB,drugC,drugX,drugY} (atributo de clase)
        //Se puede obviar no seteando la clase y evitando usar setClassMissing
        //para probar la clasificación y funciona igual
        inst.setValue(7, 0.65458d);//El nuevo atributo agregado para mejorar el árbol llamado Na/K
        inst.setDataset(getInstancias());
        inst.setClassMissing();//Hacemos que el atributo de clase no sea tenido en cuenta para que nuestro árbol nos de la clasificación "correcta"

        System.out.println("---------------------------------");
        System.out.println("La instancia aún sin clasificar: ");
        System.out.println(inst);
        System.out.println("---------------------------------");
        System.out.println("---------------------------------");
        System.out.println("La instancia ya clasificada: ");
        double result = getClasificador().classifyInstance(inst);
        System.out.println(result);
        System.out.println("---------------------------------");
    }

    @Override
    public void metricas() throws Exception {
        if (getInstancias() == null || getClasificador() == null) {
            System.err.println("No fueron cargadas las instancias o el clasificador!");
            return;
        }
        Evaluation eval = new Evaluation(getInstancias());
        int numConjuntos = 5;
        Random random = new Random(1);
        eval.crossValidateModel(getClasificador(), getInstancias(), numConjuntos, random);
        System.out.println(eval.toSummaryString());
        System.out.println(eval.toMatrixString());

        //mostramos el árbol
        final javax.swing.JFrame jf = new javax.swing.JFrame("Vista del árbol J48");
        jf.setSize(500, 400);
        jf.getContentPane().setLayout(new BorderLayout());
        TreeVisualizer tv = new TreeVisualizer(null,
                ((J48)getClasificador()).graph(),
                new PlaceNode1());
        jf.getContentPane().add(tv, BorderLayout.CENTER);
        jf.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                jf.dispose();
            }
        });

        jf.setVisible(true);
        tv.fitToScreen();

    }

}
