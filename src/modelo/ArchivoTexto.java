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
/** Clase que representa un archivo de texto plano
 * Atributos: nombre string que representa el nombre del archivo,
 *            fechaModificacion string que representa la fecha en que fue modificado el archivo
 *            contenido string que representa el contenido del archivo 
 */

public class ArchivoTexto 
{
    String nombre;
    String fechaModificacion;
    String contenido;
    
    public ArchivoTexto ArchivoTexto(String nombre, String fechaModificacion, String contenido){

        ArchivoTexto archivo = new ArchivoTexto();
        archivo.setNombre(nombre);
        archivo.setFechaModificion(fechaModificacion);
        archivo.setContenido(contenido);
        return archivo;     
        
    }

    /*Metodo que retorna el atributo nombre del archivo de texto
    @return atributo nombre*/
    public String getNombre(){
        return this.nombre;
    }

    /*Metodo que retorna el atributo fechaModificacion del archivo de texto
    @return atributo fechaModificacion*/
    public String getFechaModificacion(){
        return this.fechaModificacion;
    }

    /*Metodo que retorna el atributo contenido del archivo de texto
    @return atributo contenido*/
    public String getContenido(){
        return this.contenido;
    }

    /*Metodo que asigna el valor de nombre en el atributo nombre del archivo de texto
	@param nombre representa el nuevo valor nombre del archivo de texto*/
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    /*Metodo que asigna el valor de fechaModificacion en el atributo fechaModificacion del archivo de texto
	@param fechaModificacion representa el nuevo valor fechaModificacion del archivo de texto*/
    public void setFechaModificion(String fechaModificacion){
        this.fechaModificacion = fechaModificacion;
    }

    /*Metodo que asigna el valor de contenido en el atributo contenido del archivo de texto
	@param contenido representa el nuevo valor contenido del archivo de texto*/
    public void setContenido(String contenido){
        this.contenido = contenido;
    }
    
}
