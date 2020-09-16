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
import java.util.ArrayList;
/** Clase que representa un commit en el programa Git
 *  Atributos: autor string que representa el nombre del autor del commit
 *             marcaTiempo string que representa la fecha cuando se realizo el commit
 *             mensaje string que representa la descripcion de que cambios se realizaron en el commit
 *             cambios ArrayList que representa los cambios generados por el commit
 *             archivos ArrayList que contiene los archivos
 */

public class Commit 
{
    public String autor;
    public String marcaTiempo;
    public String mensaje;
    public ArrayList<String> cambios;
    public ArrayList<ArchivoTexto> archivos;
    
    public Commit Commit(String autor, String marcaTiempo, String mensaje, ArrayList<String> cambios, ArrayList<ArchivoTexto> archivos)
    {
        Commit commit = new Commit();
        commit.setAutor(autor);
        commit.setMarcaTiempo(marcaTiempo);
        commit.setMensaje(mensaje);
        commit.setCambios(cambios);
        commit.setArchivos(archivos);
        return commit;
        
    }

    /*Metodo que retorna el atributo autor del commit
    @return atributo autor*/
    public String getAutor(){
        return this.autor;
    }

    /*Metodo que retorna el atributo marca de tiempo del commit
    @return atributo marca de tiempo*/
    public String getMarcaTiempo(){
        return this.marcaTiempo;
    }

    /*Metodo que retorna el atributo mensaje del commit
    @return atributo mensaje*/
    public String getMensaje(){
        return this.mensaje;
    }

    /*Metodo que retorna el atributo cambios del commit
    @return atributo cambios*/
    public ArrayList<String> getCambios(){
        return this.cambios;
    }

    /*Metodo que retorna el atributo archivos del commit
    @return atributo archivos*/
    public ArrayList<ArchivoTexto> getArchivos(){
        return this.archivos;
    }

    /*Metodo que asigna el valor de autor en el atributo autor del commit
	@param autor representa el nuevo valor autor del commit*/
    public void setAutor(String autor){
        this.autor = autor;
    }

    /*Metodo que asigna el valor de marca de tiempo en el atributo marca de tiempo del commit
	@param marca de tiempo representa el nuevo valor marca de tiempo del commit*/
    public void setMarcaTiempo(String marcaTiempo){
        this.marcaTiempo = marcaTiempo;
    }

    /*Metodo que asigna el valor de mensaje en el atributo mensaje del commit
	@param mensaje representa el nuevo valor mensaje del commit*/
    public void setMensaje(String mensaje){
        this.mensaje = mensaje;
    }

    /*Metodo que asigna el valor de cambios en el atributo cambios del commit
	@param cambios representa el nuevo valor cambios del commit*/
    public void setCambios(ArrayList<String> cambios){
        this.cambios = cambios;
    }

    /*Metodo que asigna el valor de archivos en el atributo archivos del commit
	@param archivos representa el nuevo valor archivos del commit*/
    public void setArchivos(ArrayList<ArchivoTexto> archivos){
        this.archivos = archivos;
    }
}
