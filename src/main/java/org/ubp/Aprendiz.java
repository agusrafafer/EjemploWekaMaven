/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ubp;

import java.io.InputStream;
import weka.core.Instances;

/**
 *
 * @author agustin
 */
public abstract class Aprendiz {

    private InputStream is = null;
    private Instances instancias;

    public InputStream getIs() {
        return is;
    }

    public void setIs(InputStream is) {
        this.is = is;
    }

    public Instances getInstancias() {
        return this.instancias;
    }

    public void setInstancias(Instances instancias) {
        this.instancias = instancias;
    }
    
    public abstract void cargarInstancias() throws Exception;
    
        
    public abstract void metricas() throws Exception;
}
