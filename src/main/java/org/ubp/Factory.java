/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ubp;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author Agustín Fernandez
 */
public class Factory {
    
    private Factory(){
        
    }
    
    public static Aprendiz crear(String nombreClase, InputStream is){
        Aprendiz aprendiz = null;
        try {
            //Reflection de Java
            aprendiz = (Aprendiz) Class.forName(Aprendiz.class.getPackageName() + "." + nombreClase).getDeclaredConstructor(InputStream.class).newInstance(is);
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            System.err.println(e);
        }
        return aprendiz;
    }
    
}
