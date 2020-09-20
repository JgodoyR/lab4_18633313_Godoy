/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Nico
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

/** Clase que representa un repositorio que contiene todos los elementos de la zona de trabajo
 *  Atributos: nombre string que representa el nombre del repositorio
 *             autor string que representa el autor del repositorio
 */

public class Repositorio {

    String nombre;
    String autor;
    ZonasTrabajo zonas = new ZonasTrabajo();
    public Repositorio repo;    
    
     /*Metodo que inicializa el repositorio pidiendole al usuario el nombre y autor
    de este
    */
    public Repositorio gitInit(String nombre, String autor){
        
        Repositorio repositorio = null;
        
        repo.setNombre(nombre);
        repo.setAutor(autor);
        
        this.repo = repositorio;
        
        return repositorio;

    }
    
    /*
    public Repositorio getRepositorio(){
        return this.repo;
    }
    
    public void setRepositorio(Repositorio repositorio){
        this.repo = repositorio;
    }
    */
    
    /*Metodo que retorna el atributo nombre del repositorio
    @return atributo nombre*/
    public String getNombre(){
        return this.nombre;
    }

    /*Metodo que retorna el atributo autor del repositorio
    @return atributo autor*/
    public String getAutor(){
        return this.autor;
    }

    /*Metodo que asigna el valor de nombre en el atributo nombre del repositorio
	@param nombre representa el nuevo valor nombre del repositorio*/
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    /*Metodo que asigna el valor de autor en el atributo autor del repositorio
	@param autor representa el nuevo valor autor del repositorio*/
    public void setAutor(String autor){
        this.autor = autor;
    }

    /*Metodo que permite crear un archivo de texto plano dandole un nombre, contenido
    y fecha de modificacion
    */
    public Repositorio crearArchivo(Repositorio repositorio, String nombre, String contenido){

        nombre = null;
        contenido = null;
        String fechaModificacion;

        fechaModificacion = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());

        ArchivoTexto archivoTexto = new ArchivoTexto();
        archivoTexto =  archivoTexto.ArchivoTexto(nombre, fechaModificacion, contenido);

        this.zonas.archivosWorkspace.add(archivoTexto);
        
        return repositorio;

    }

}
