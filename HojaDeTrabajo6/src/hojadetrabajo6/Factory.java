/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hojadetrabajo6;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Irene
 */
public class Factory {
    Set implementacion; 
    public Factory(){
    
    }
    
    public Set<String> getSet(int opcion){
           
        /*Si se selecciona el numero 1, entonces se devuelve la implementacion
        * hashSet*/
        if (opcion == 0)
        {
            implementacion =  new HashSet(); 
           
        }
        /*Si se selecciona el numero 2, entonces se devuelve la implementacion
        * linkedHasSet*/
        else if(opcion == 1)
        {
            implementacion = new LinkedHashSet();
        }
        /*Si se selecciona el numero 3, entonces se devuelve la implementacion
        * treeSet*/
        else if(opcion == 2)
        {
            implementacion = new TreeSet();
        } 
        /*Se devuelve la implementacion deseada*/
        return implementacion;
        
    }
}



