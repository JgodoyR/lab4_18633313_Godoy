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
    Scanner sc = new Scanner(System.in);
    
    public Repositorio Repositorio(String nombre, String autor){

        Repositorio repositorio = new Repositorio();
        repositorio.setNombre(nombre);
        repositorio.setAutor(autor);
        repositorio.gitInit();
        return repositorio;

    }

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

    /*Metodo que inicializa el repositorio pidiendole al usuario el nombre y autor
    de este
    */
    public void gitInit(){
        
        System.out.print("Ingrese un nombre para el repositorio: ");
        nombre = sc.nextLine();
        
        System.out.print("Ingrese el nombre del autor del repositorio: ");
        autor = sc.nextLine();

    }

    /*Metodo que permite crear un archivo de texto plano dandole un nombre, contenido
    y fecha de modificacion
    */
    public void crearArchivo(){

        String nombre = null;
        String contenido = null;
        String fechaModificacion;

        System.out.print("\nIngrese el nombre del archivo que desea crear: ");
        nombre = sc.nextLine();
        
        System.out.print("Ingrese el contenido del archivo creado: ");
        contenido = sc.nextLine();

        fechaModificacion = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());

        ArchivoTexto archivoTexto = new ArchivoTexto();
        archivoTexto =  archivoTexto.ArchivoTexto(nombre, fechaModificacion, contenido);

        this.zonas.archivosWorkspace.add(archivoTexto);

    }

    /*Metodo que agrega los archivos de texto del Workspace al Index, pidiendole al usuario que
    escoja entre pasar 1 o todos los archivos, en caso de que sea 1 se debe especificar el nombre
    del archivo para buscarlo en el Workspace, y si existe se agrega al Index
    */
    public void gitAdd(){

        String opcion;
        String archivoABuscar;
        ArrayList<Integer> lista = new ArrayList<>();
        ArrayList<String> nombreArchivo = new ArrayList<>();
        int op;
        int aux = 0;
        
        if(zonas.archivosWorkspace.size() > 0){
            while(aux == 0){
                try{
                    do{
                        System.out.println("\nEscoja una opcion: ");
                        System.out.println("1) Agregar un archivo desde el Workspace especificando su nombre");
                        System.out.println("2) Agregar todos los archivos del Workspace");
                        System.out.print("Su eleccion: ");
                        opcion = sc.nextLine();
                        op = Integer.parseInt(opcion);
                    }while(op < 1 || op > 2);
                    
                    switch(op){
                        case 1: 
                            try{
                                System.out.print("\nIngrese el nombre del archivo que desea agregar al Index: ");
                                archivoABuscar = sc.nextLine();
                                nombreArchivo.add(archivoABuscar);
                            }
                            catch(Exception e){
                                System.out.println("El nombre que ha ingresado no es valido");
                            }
                    
                            int largoNombreArchivo = nombreArchivo.size();
                            int tamanoWorkspace = zonas.archivosWorkspace.size();
                    
                            for(int i = 0; i < largoNombreArchivo; i++){
                                for(int j = 0; j < tamanoWorkspace; j++){
                                    if((zonas.archivosWorkspace.get(j).nombre.equals(nombreArchivo.get(i)))){
                                        lista.add(j);
                                    }
                                }
                            }
                    
                            if(lista.size() == 0){
                                System.out.println("\nEl archivo que busca en el Workspace no existe");
                                System.out.println("Asegurse de ingresar el nombre correcto del archivo o archivos que se encuentren en el Workspace");
                            }
                            else{
                                for(int i : lista){
                                    zonas.archivosIndex.add(zonas.archivosWorkspace.get(i));
                                }
                            }
                            break;
                        case 2:
                            zonas.archivosIndex.clear();
                            zonas.archivosIndex.addAll(zonas.archivosWorkspace);
                            break;
                        default:
                            System.out.println("\nNo ha introducido ninguna opcion valida.");
                            break;
                    }
                    aux = 1;
                }
                catch(NumberFormatException e){
                    System.out.println("Ingrese una opcion valida.");
                }
    
            }
        }
        else{
            System.out.println("\nEl Workspace esta vacio, no se pueden agregar archivos al Index");
        }
        
    
    }

    /*Metodo que crea un nuevo commit en el Local Repository con los contenidos del Index,
    solicitando al usuario que ingrese el nombre del autor y un mensaje que describa los 
    cambios realizados en dicho commit.
    */
    public void gitCommit(){
        String autor = null;
        String mensaje = null;
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
            System.out.print("Ingrese el nombre del autor del commit: ");
            autor = sc.nextLine();
            System.out.print("Ingrese un mensaje que describa el commit: ");
            mensaje = sc.nextLine();

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

    }

    /*Metodo que agrega los commits del Local Repository al Remote Repository y cambia el valor 
    boolean del estado del Remote Repository a true siempre y cuando el Local Repository tenga commits 
    para enviar al Remote Repository
    */
    public void gitPush(){
        if(zonas.commitsLocalRepository.size() > 0 && zonas.commitsLocalRepository.size() > zonas.archivosRemoteRepository.size()){
            zonas.archivosRemoteRepository = zonas.commitsLocalRepository;
            zonas.alDia = true;
        }
        else{
            System.out.println("\nNecesita tener commits en el Local Repository antes de enviarlos al Remote Repository.");
        }      
    }

    /*Metodo que copia los archivos que se encuentran en el Remote Repository al Workspace, siempre y cuando existan archivos 
    en el Remote Repository. Este metodo si encuentra un elemento igual en el Workspace lo elimina y lo reemplaza con el archivo 
    del Remote Repository
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
                        nombre = null;
                        fecha = null;
                        contenido = null;
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

    /*Metodo que permite ver por pantalla el estado actual del Repositorio
    */
    public void gitStatus(){

        System.out.println("\nEstado actual del repositorio git\n");
        System.out.println("Nombre del repositorio: " + nombre + "\nAutor del repositorio: " + autor );
        System.out.println("Numero de archivos en el Workspace: " + zonas.archivosWorkspace.size());
        System.out.println("Numero de archivos en el Index: " + zonas.archivosIndex.size());
        System.out.println("Numero de commmits en el Local Repository: " + zonas.commitsLocalRepository.size());
        System.out.print("Estado Remote Repository: ");
        if(zonas.alDia){
            System.out.print("Esta al dia\n");
        }
        else{
            System.out.print("No esta al dia\n");
        }
        mostrarZonasTrabajo();
        
    }

    /*Metodo que permite ver por pantalla el contenido de las zonas de trabajo del Repositorio,
    mostrando los archivos que posee el Workspace con el Index, y los commits que se encuentran en el 
    Local Repository y el Remote Repository
    */
    public void mostrarZonasTrabajo(){
        System.out.print("\nArchivos en el Workspace: ");
        for(int i = 0; i < zonas.archivosWorkspace.size(); i++){
            System.out.print("[");
            System.out.print(zonas.archivosWorkspace.get(i).nombre);
            System.out.print("]");
            System.out.print(" ");
        }
        System.out.print("\nArchivos en el Index: ");
        for(int i = 0; i < zonas.archivosIndex.size(); i++){
            System.out.print("[");
            System.out.print(zonas.archivosIndex.get(i).nombre);
            System.out.print("]");
            System.out.print(" ");
        }
        System.out.print("\nCommits en el Local Repository: ");
        for(int i = 0; i < zonas.commitsLocalRepository.size(); i++){
            System.out.print("[");
            System.out.print(zonas.commitsLocalRepository.get(i).autor + ", ");
            System.out.print(zonas.commitsLocalRepository.get(i).marcaTiempo + ", ");
            System.out.print(zonas.commitsLocalRepository.get(i).mensaje);
            System.out.print("]");
            System.out.print(" ");
        }
        System.out.print("\nArchivos en el Remote Repository: ");
        for(int i = 0; i < zonas.archivosRemoteRepository.size(); i++){
            System.out.print("[");
            System.out.print(zonas.archivosRemoteRepository.get(i).autor + ", ");
            System.out.print(zonas.archivosRemoteRepository.get(i).marcaTiempo + ", ");
            System.out.print(zonas.archivosRemoteRepository.get(i).mensaje);
            System.out.print("]");
            System.out.print(" ");
        }
        System.out.println("\n");
    }

}
