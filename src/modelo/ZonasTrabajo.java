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

/** Clase que representa las cuatro zonas de trabajo de git
 *  Atributos: archivosWorkspace ArrayList que representa los archivos en la zona workspace de la zona de trabajo
 *             archivosIndex ArrayList que representa los archivos en la zona index de la zona de trabajo
 *             commitsLocalRepository ArrayList que representa los commits en la zona local repository de la zona de trabajo
 *             archivosRemoteRepository ArrayList que representa los archivos en la zona remote repository de la zona de trabajo
 *             
 */

public class ZonasTrabajo{
    
    public ArrayList<ArchivoTexto> archivosWorkspace = new ArrayList<>();
    public ArrayList<ArchivoTexto> archivosIndex = new ArrayList<>();
    public ArrayList<Commit> commitsLocalRepository = new ArrayList<>();
    public ArrayList<Commit> archivosRemoteRepository = new ArrayList<>();
    public boolean alDia = false;

    public ZonasTrabajo ZonasTrabajo(ArrayList<ArchivoTexto> workspace, ArrayList<ArchivoTexto> index, ArrayList<Commit> localRepository, ArrayList<Commit> remoteRepository){
          
        ZonasTrabajo zonas = new ZonasTrabajo();
        
        zonas = zonas.ZonasTrabajo(workspace, index, localRepository, remoteRepository);
        
        return zonas;
    }

    /*Metodo que retorna el atributo archivosWorkspace de la zona de trabajo
	@return atributo archivosWorkspace*/
    public ArrayList<ArchivoTexto> getWorkspace(){
        return this.archivosWorkspace;
    }

    /*Metodo que asigna el valor de workspace en el atributo archivosWorkspace de la zona de trabajo
	@param workspace representa el nuevo valor de archivosWorkspace de la zona de trabajo*/
    public void setWorkspace(ArrayList<ArchivoTexto> workspace){
        this.archivosWorkspace = workspace;
    }

    /*Metodo que retorna el atributo archivosIndex de la zona de trabajo
	@return atributo archivosIndex*/
    public ArrayList<ArchivoTexto> getIndex(){
        return this.archivosIndex;
    }

    /*Metodo que asigna el valor de index en el atributo archivosIndex de la zona de trabajo
	@param index representa el nuevo valor de archivosIndex de la zona de trabajo*/
    public void setIndex(ArrayList<ArchivoTexto> index){
        this.archivosIndex = index;
    }

    /*Metodo que retorna el atributo commitsLocalRepository de la zona de trabajo
	@return atributo commitsLocalRepository*/
    public ArrayList<Commit> getLocalRepository(){
        return this.commitsLocalRepository;
    }

    /*Metodo que asigna el valor de localRepository en el atributo commitsLocalRepository de la zona de trabajo
	@param localRepository representa el nuevo valor de commitsLocalRepository de la zona de trabajo*/
    public void setLocalRepository(ArrayList<Commit> localRepository){
        this.commitsLocalRepository = localRepository;
    }

    /*Metodo que retorna el atributo archivosRemoteRepository de la zona de trabajo
	@return atributo archivosRemoteRepository*/
    public ArrayList<Commit> getRemoteRepository(){
        return this.archivosRemoteRepository;
    }

    /*Metodo que asigna el valor de remoteRepository en el atributo archivosRemoteRepository de la zona de trabajo
	@param remoteRepository representa el nuevo valor de archivosRemoteRepository de la zona de trabajo*/
    public void setRemoteRepository(ArrayList<Commit> remoteRepository){
        this.archivosRemoteRepository = remoteRepository;
    }

}