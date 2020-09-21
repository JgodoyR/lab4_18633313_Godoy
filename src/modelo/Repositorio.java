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
import java.util.Date;
import java.text.SimpleDateFormat;

/** Clase que representa un repositorio que contiene todos los elementos de la zona de trabajo
 *  Atributos: nombre string que representa el nombre del repositorio
 *             autor string que representa el autor del repositorio
 */

public class Repositorio {

    public String nombre; //nombre del repositorio
    public String autor; //nombre del autor del repositorio
    public ZonasTrabajo zonas = new ZonasTrabajo(); 
    public Repositorio repo;    
    
    /** 
     * Crea un repositorio a partir de un nombre y autor.
     * @param nombre El nombre del repositorio.
     * @param autor El nombre del autor del repositorio.
     * @return El repositorio
     */
    public Repositorio Repo(String nombre, String autor){       
        Repositorio repositorio = null; 
        this.nombre = nombre;
        this.autor = autor;
        return repositorio;     
    }
   
    /** 
     * Obtiene los atributos nombre y autor, y los pasa al metodo Repo.
     * @param nombre El nombre del repositorio.
     * @param autor El nombre del autor del repositorio.
     * @return El repositorio
     */
    public Repositorio gitInit(String nombre, String autor){
        Repositorio repositorio = null;        
        Repo(nombre, autor);      
        this.repo = repositorio; 
        return repositorio; 
    }

    /** 
     * Crea un archivo de texto nuevo a partir de un nombre y contenido.
     * @param repositorio El repositorio 
     * @param nombre El nombre del archivo de texto
     * @param contenido El contenido del archivo de texto
     * @return El repositorio
     */
    public Repositorio crearArchivo(Repositorio repositorio, String nombre, String contenido){       
        String fechaModificacion;
        fechaModificacion = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
        ArchivoTexto archivoTexto = new ArchivoTexto();
        archivoTexto =  archivoTexto.ArchivoTexto(nombre, fechaModificacion, contenido);
        repositorio.zonas.archivosWorkspace.add(archivoTexto);      
        return repositorio;
    }
    
    /** 
     * Recorre el Workspace buscando coincidencias de elementos con el nombre del archivo, en caso de encontrarlas agrega
     * el archivo al Index, y si no, no realiza ninguna accion.
     * @param repositorio El repositorio..¿
     * @param archivosWorkspace Archivos que se encuentran en la zona de trabajo Workspace.
     * @param archivo El nombre del archivo de texto que se debe buscar en el Workspace.
     * @return El repositorio.
     */
    public Repositorio gitAdd(Repositorio repositorio, ArrayList<ArchivoTexto> archivosWorkspace, String archivo){        
        ArrayList<Integer> lista = new ArrayList<>();
        ArrayList<String> nombreArchivo = new ArrayList<>();
       
        if(zonas.archivosWorkspace.size() > 0){
            nombreArchivo.add(archivo);
            int largoNombreArchivo = nombreArchivo.size();
            int tamanoWorkspace = zonas.archivosWorkspace.size();
            
            for(int i = 0; i < largoNombreArchivo; i++){
                for(int j = 0; j < tamanoWorkspace; j++){
                    if((zonas.archivosWorkspace.get(j).nombre.equals(nombreArchivo.get(i)))){
                        lista.add(j);
                    }
                }
            }

            if(lista.size() > 0){
                for(int i : lista){
                    zonas.archivosIndex.add(zonas.archivosWorkspace.get(i));
                }
            }
        }       
        return repositorio;
    }
    
    /** 
     * Agrega todos los archivos del Workspace al Index.
     * @param repositorio El repositorio.
     * @param archivosWorkspace Archivos que se encuentran en la zona de trabajo Workspace.
     * @return El repositorio.
     */
    public Repositorio gitAddAll(Repositorio repositorio, ArrayList<ArchivoTexto> archivosWorkspace){
        if(zonas.archivosWorkspace.size() > 0){
            zonas.archivosIndex.clear();
            zonas.archivosIndex.addAll(zonas.archivosWorkspace);
        }
        return repositorio;
    }
    
    /** 
     * Crea un commit con todos los elementos del Index, asignandole un autor, mensaje descriptivo,
     * y fecha de creacion.
     * @param repositorio El repositorio.
     * @param autor Nombre del autor del commit.
     * @param mensaje Mensaje que describe los cambios realizados.
     * @param archivosIndex Archivos que se encuentran en la zona de trabajo Index.
     * @return Los cambios que tuvieron los archivos.
     */
    public ArrayList<String> gitCommit(Repositorio repositorio, String autor, String mensaje, ArrayList<ArchivoTexto> archivosIndex){       
        String marcaTiempo;
        String aux;
        zonas.alDia = false;
        marcaTiempo = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
        Commit commit = new Commit();
        ArrayList<String> cambios = new ArrayList<>();
        ArrayList<ArchivoTexto> archivosI = zonas.archivosIndex;
        ArrayList<Commit> commitsLR = zonas.commitsLocalRepository;
        ArrayList<ArchivoTexto> indexAux = zonas.archivosIndex;

        if(zonas.archivosIndex.size() > 0 && zonas.commitsLocalRepository.size() <= zonas.archivosIndex.size()){
            if(commitsLR.isEmpty()){
                for(ArchivoTexto archivo : archivosI){
                    aux = null;
                    aux = "+ " + archivo.nombre;
                    cambios.add(aux);
                }
            }
            else{
                ArrayList<ArchivoTexto> ultimoCommit = commitsLR.get(commitsLR.size()-1).archivos;
                ArrayList<ArchivoTexto> commitAux = ultimoCommit;
    
                for(int i = 0; i < archivosI.size(); i++){
                    for(int j = 0; j < ultimoCommit.size(); j++){
                        if(indexAux.get(i).nombre.equals(commitAux.get(j).nombre)){
                            archivosI.remove(indexAux.get(i).nombre);
                            ultimoCommit.remove(commitAux.get(i).nombre);
                        }
                    }
                }
    
                for(ArchivoTexto archivo : archivosI){
                    aux = null;
                    aux = "+ " + archivo.nombre;
                    cambios.add(aux);
                }
    
                for(ArchivoTexto archivo: ultimoCommit){
                    aux = null;
                    aux = "- " + archivo.nombre;
                    cambios.add(aux);
                }
            }
            commit = commit.Commit(autor, marcaTiempo, mensaje, cambios, zonas.archivosIndex);

            zonas.commitsLocalRepository.add(commit);           
        }     
        return cambios;
    }
    
    /** 
     * Agrega todos los commits del Local Repository al Remote Repository y cambia el estado del 
     * Remote Repository para dejarlo al dia
     * @param no posee debido a que es un void
     * @return no posee debido a que es un void
     */
    public void gitPush(){
        if(zonas.commitsLocalRepository.size() > 0 && zonas.commitsLocalRepository.size() > zonas.archivosRemoteRepository.size()){
            zonas.archivosRemoteRepository = zonas.commitsLocalRepository;
            zonas.alDia = true;
        }         
    }
    
    /** 
     * Copia los archivos del Remote Repository al Workspace, en caso de que existan en el Workspace
     * los elimina y añade al del Remote Repository, y cambia el estado del Remote Repository a 
     * desactualizado.
     * @param no posee debido a que es un void
     * @return no posee debido a que es un void
     */
    public void gitPull(){
        String nombre;
        String fecha;
        String contenido;
        if(zonas.archivosRemoteRepository.size() > 0){
            for(int i = 0; i < zonas.archivosWorkspace.size(); i++){
                for(int j = 0; j < zonas.archivosRemoteRepository.size(); j++){
                    if((zonas.archivosWorkspace.get(i).nombre).equals((zonas.archivosRemoteRepository.get(j).archivos).get(j).nombre)){
                        zonas.archivosWorkspace.remove(zonas.archivosWorkspace.get(i));                      
                        nombre = (zonas.commitsLocalRepository.get(j).archivos).get(j).nombre;
                        fecha = (zonas.commitsLocalRepository.get(j).archivos).get(j).fechaModificacion;
                        contenido = (zonas.commitsLocalRepository.get(j).archivos).get(j).contenido;
                        ArchivoTexto archivoTexto = new ArchivoTexto();
                        archivoTexto =  archivoTexto.ArchivoTexto(nombre, fecha, contenido);
                        zonas.archivosWorkspace.add(archivoTexto);
                    }
                }
            }
            zonas.alDia = false;
        }              
    }

}
