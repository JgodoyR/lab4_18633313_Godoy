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
/*
 * Laboratorio 3 Paradigmas de Programacion
 * Profesor: Daniel Gacitua / Seccion: C - 3
 * Alumno: Jordan Godoy Rojas / 18.633.313-6
 */

import java.util.Scanner;

public class Main 
{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int opcion;
        String op;
        int aux = 0; 
        Repositorio repo = new Repositorio();
        repo.gitInit();
        while(aux == 0){
            try{
                do{
                    System.out.println("\n### SIMULACION DE GIT ###");
                    System.out.println("Escoja su opcion:");
                    System.out.println("1. add");
                    System.out.println("2. commit");
                    System.out.println("3. push");
                    System.out.println("4. pull");
                    System.out.println("5. status");
                    System.out.println("6. Crear nuevo archivo");
                    System.out.println("7. Salir del programa");
                    System.out.print("INTRODUZCA SU OPCION: ");
                    op = input.nextLine();
                    opcion = Integer.parseInt(op);
                }while(opcion < 1 || opcion > 7);
                switch(opcion){
                    case 1:
                        System.out.println("\nEscogio la funcionalidad ADD");
                        repo.gitAdd();
                        break;
                    case 2:
                        System.out.println("\nEscogio la funcionalidad COMMIT");
                        repo.gitCommit();
                        break;
                    case 3:
                        System.out.println("\nEscogio la funcionalidad PUSH");
                        repo.gitPush();
                        break;
                    case 4:
                        System.out.println("\nEscogio la funcionalidad PULL");
                        repo.gitPull();
                        break;
                    case 5:
                        System.out.println("\nEscogio la funcionalidad STATUS");
                        repo.gitStatus();
                        break;
                    case 6:
                        System.out.println("\nEscogio la funcionalidad CREAR NUEVO ARCHIVO");
                        repo.crearArchivo();
                        break;
                    case 7:
                        System.out.println("\nSaliendo del programa");
                        aux = 1;
                        break;
                    default:
                        System.out.println("\nNo ha introducido ninguna opcion valida.");
                        break;
                }
            }
            catch(NumberFormatException e){
                System.out.println("\nIngrese una opcion valida.");
            }
        }
       
    }
     
}
