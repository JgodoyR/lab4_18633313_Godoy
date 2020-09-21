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

    public String nombre;
    public String autor;
    public ZonasTrabajo zonas = new ZonasTrabajo();
    public Repositorio repo;    
    
    public Repositorio Repo(String nombre, String autor){       
        Repositorio repositorio = null; 
        this.nombre = nombre;
        this.autor = autor;
        return repositorio;     
    }
   
    
    public Repositorio gitInit(String nombre, String autor){
        Repositorio repositorio = null;        
        Repo(nombre, autor);      
        this.repo = repositorio; 
        return repositorio; 
    }

    /*Metodo que permite crear un archivo de texto plano dandole un nombre, contenido
    y fecha de modificacion
    */
    public Repositorio crearArchivo(Repositorio repositorio, String nombre, String contenido){       
        String fechaModificacion;
        fechaModificacion = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
        ArchivoTexto archivoTexto = new ArchivoTexto();
        archivoTexto =  archivoTexto.ArchivoTexto(nombre, fechaModificacion, contenido);
        repositorio.zonas.archivosWorkspace.add(archivoTexto);      
        return repositorio;
    }
    
    public Repositorio gitAdd(Repositorio repositorio, ArrayList<ArchivoTexto> archivosWorkspace, String archivo){        
        for(ArchivoTexto archivoTexto : zonas.archivosWorkspace){
            if(archivoTexto.equals(archivo)){
                zonas.archivosIndex.add(archivoTexto);
            }
        }        
        return repositorio;
    }
    
    public Repositorio gitAddAll(Repositorio repositorio, ArrayList<ArchivoTexto> archivosWorkspace){
        zonas.archivosIndex.clear();
        zonas.archivosIndex.addAll(zonas.archivosWorkspace);
        return repositorio;
    }
    
    public ArrayList<String> gitCommit(Repositorio repositorio, String autor, String mensaje, ArrayList<ArchivoTexto> archivosIndex){
        autor = null;
        mensaje = null;
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
            if(commitsLR.size() == 0){
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
        else{
            System.out.println("Necesita tener archivos en el index para realizar un commit");
        }  
        
        return cambios;
    }
    
    public void gitPush(){
        if(zonas.commitsLocalRepository.size() > 0 && zonas.commitsLocalRepository.size() > zonas.archivosRemoteRepository.size()){
            zonas.archivosRemoteRepository = zonas.commitsLocalRepository;
            zonas.alDia = true;
        }
        else{
            System.out.println("\nNecesita tener commits en el Local Repository antes de enviarlos al Remote Repository.");
        }    
    }
    
    public void gitPull(){
        String nombre;
        String fecha;
        String contenido;
        if(zonas.archivosRemoteRepository.size() > 0){
            for(int i = 0; i < zonas.archivosWorkspace.size(); i++){
                for(int j = 0; j < zonas.archivosRemoteRepository.size(); j++){
                    if((zonas.archivosWorkspace.get(i).nombre).equals((zonas.archivosRemoteRepository.get(j).archivos).get(j).nombre)){
                        zonas.archivosWorkspace.remove(zonas.archivosWorkspace.get(i));
                        //nombre = null;
                        //fecha = null;
                        //contenido = null;
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
        else{
            System.out.println("\nNo hay archivos en el Remote Repository para copiarlos en el Workspace.");
        }
        
    }

}
