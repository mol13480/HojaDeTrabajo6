/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hojadetrabajo6;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.*; 
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Irene
 */
public class Control {
    public int opcion; 
    
    //los 3 diferentes sets
    public Set<String> SetJava;
    public Set<String> SetWeb;
    public Set<String> SetCelulares;
    
     
    
    //se crea un factory para la implementacion
    public Factory factory = new Factory();
    
    //se crean los diferentes conjuntos
    public Set<String> interseccion_todos; 
    public Set<String> interseccion_web_celulares; 
    public Set<String> java_noWeb; 
    public Set<String> union_web_celulares; 
    
    //consructor
    public Control(){
        boolean bandera = false; 
       
        //el usuario elige la implementacion
        opcion = JOptionPane.showOptionDialog(
			   null,
			   "Que tipo Set desea utilizar", 
			   "Selector de opciones",
			   JOptionPane.YES_NO_CANCEL_OPTION,
			   JOptionPane.QUESTION_MESSAGE,
			   null,
			   new Object[] { "HashSet", "Hashtree","LinkedHashSet"},   // null para YES, NO y CANCEL
			   "opcion 0");
        
        switch (opcion){
            case 0: 
            case 1: 
            case 2:
            {
                SetJava = factory.getSet(opcion);
                SetWeb = factory.getSet(opcion);
                SetCelulares = factory.getSet(opcion);
                break;
            }
            default:{
                //si el usuario ingresa algo incorrecto
                JOptionPane.showMessageDialog(null, "Ingrese datos correctos");
                //se cierra el programa 
                System.exit(0); 
            }     
        }
        
        //se le pregunta al usuario que desarrolladores quiere ingresar
        int set = JOptionPane.showOptionDialog(
			   null,
			   "Seleccione", 
			   "Selector de opciones",
			   JOptionPane.YES_NO_CANCEL_OPTION,
			   JOptionPane.QUESTION_MESSAGE,
			   null,
			   new Object[] { "Ingresar Desarrolladores Java", "Ingresar Desarrolladores de Web","Ingresar Desarrolladores de Celulares","Mostrar Resultados","Finalizar"},   // null para YES, NO y CANCEL
			   "opcion 0");
        
        int dialogButton = JOptionPane.YES_NO_OPTION; 
       
        while (dialogButton == JOptionPane.YES_OPTION){
            //si el usuario eligio desarrolladores de java 
            if (set==0){
                System.out.println("javaa"); 
                String nombre = JOptionPane.showInputDialog("Ingrese el nombre"); 
                SetJava.add(nombre);  
               
            }
            //si el usuario eligio desarrolladores web
            if (set==1){
                System.out.println("weeb"); 
                String nombre = JOptionPane.showInputDialog("Ingrese el nombre"); 
                SetWeb.add(nombre);  
            }
            //si el usuario eligio desarrolladores de celulares
            if (set==2){
                System.out.println("celulaes"); 
                String nombre = JOptionPane.showInputDialog("Ingrese el nombre"); 
                SetCelulares.add(nombre);  
            }
            //si el usuario eligio mostrar resultados
            if (set==3){
                
                //Se llevan a cabo las tareas 
                System.out.println(SetJava);
                interseccion_web_celulares=Interseccion(SetWeb,SetCelulares); 
                java_noWeb=Diferencia(SetJava,SetWeb); 
                union_web_celulares=Union(SetWeb,SetCelulares);
                interseccion_todos=Interseccion(SetJava,interseccion_web_celulares);
                
                //se muestran los resultados de los conjuntos de intersecciones y uniones
                System.out.println("Desarrolladores con exeriencia en Java, web y  celulares:");
                System.out.println(interseccion_todos);
                System.out.println("Desarrolladores con exeriencia en Java, pero no web:");
                System.out.println(java_noWeb);
                System.out.println("Desarrolladores con exeriencia en web  y celulares:");
                System.out.println(interseccion_web_celulares);
                System.out.println("Desarrolladores con exeriencia en web o celulares:");
                System.out.println(union_web_celulares);
                
                //SetJava subconjunto de SetWeb?
                boolean sub_conjunto = Subconjunto(SetWeb,SetJava);
                if(sub_conjunto==true){
                    System.out.println("Los desarrolladores de java son un subconjunto de los desarrolladores Web");
                }
                else
                    System.out.println("Los desarrolladores de java no es un subconjunt de los desarrolladores Web");
                
                //El conjunto con mayor cantidad de desarrolladores
                int cantSetJava = SetJava.size();
                int cantSetWeb = SetWeb.size();
                int cantSetCelulares = SetCelulares.size();
                if(cantSetJava>cantSetWeb && cantSetJava>cantSetCelulares){
                    System.out.println("El conjunto de desarrolladores de Java es el mayor");
                    System.out.println(SetJava); 
                    ordenar(SetJava);
                }
                if(cantSetWeb>cantSetJava && cantSetWeb>cantSetCelulares){
                    System.out.println("El conjunto de desarrolladores Web es el mayor");
                    System.out.println(SetWeb); 
                    ordenar(SetWeb);
                }
                if(cantSetCelulares>cantSetJava && cantSetCelulares>cantSetWeb){
                    System.out.println("El conjunto de desarrolladores de Celulares es el mayor");
                    System.out.println(SetCelulares);  
                    ordenar(SetCelulares);
                }

            }
            //si el usuario elige finalizar el programa
            if (set==4){
               System.exit(0);
            }
            set = JOptionPane.showOptionDialog(
			   null,
			   "Seleccione", 
			   "Selector de opciones",
			   JOptionPane.YES_NO_CANCEL_OPTION,
			   JOptionPane.QUESTION_MESSAGE,
			   null,
			   new Object[] { "Ingresar Desarrolladores Java", "Ingresar Desarrolladores de Web","Ingresar Desarrolladores de Celulares","Mostrar Resultados","Finalizar"},   // null para YES, NO y CANCEL
			   "opcion 0");
            
        }
    }
    
    
     //metodo para calcular la interseccion
    private Set Interseccion(Set set1, Set set2){
        Set<String> interseccion = factory.getSet(opcion);
        Iterator iterator = set1.iterator();
        while(iterator.hasNext()){
            interseccion.add((String) iterator.next());
        }
        interseccion.retainAll(set2);
        return interseccion; 
    }
    //metodo para saber cuales elementos estan en el set1 y no estan en el set2
    private Set Diferencia(Set set1, Set set2){
        Set<String> diferencia = factory.getSet(opcion);
        Iterator iterator = set1.iterator();
        while(iterator.hasNext()){
            diferencia.add((String) iterator.next());
        }
        diferencia.removeAll(set2);
        return diferencia; 
    }
    //metodo para la union de dos sets
    private Set Union(Set set1, Set set2){
        Set<String> union = factory.getSet(opcion);
        Iterator iterator = set1.iterator();
        while (iterator.hasNext()){
            union.add((String) iterator.next());
        }
        union.addAll(set2);
        return union; 
    }
    //metodo para si set2 es subconjunto de set1
    private boolean Subconjunto(Set set1, Set set2){
        boolean esSubconjunto; 
        esSubconjunto = set1.containsAll(set2);
        return esSubconjunto;
    }
    //meotodo para ordenar alfabeticamente
    private void ordenar(Set set){
        ArrayList<String> arraylist = new ArrayList<>();
        Set<String> union = factory.getSet(opcion); 
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            arraylist.add((String) iterator.next());
        }
        Collections.sort(arraylist);
        System.out.println("Conjunto ordenado:"); 
        System.out.println(arraylist);
    }
   
}
