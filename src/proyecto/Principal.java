package proyecto;

import java.util.Random;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
/*
        1. **Implementación en Java**:
    - Cada celda debe ser un hilo que revise y actualice su estado en función de sus celdas vecinas.
    - Un hilo controlador debe gestionar el avance de generaciones sincronizando las celdas para actualizar el tablero de forma coordinada.

2. **Configuración del Tablero**:
    - El tablero debe poder inicializarse con un tamaño fijo especificado en el código y un estado inicial de celdas vivas o muertas generadas aleatoriamente o predeterminadas.

3. **Sincronización**:
    - Las celdas deben sincronizarse para evitar condiciones de carrera y asegurar que todas revisen su estado antes de avanzar a la siguiente generación.

4. **Salida en Consola**:
    - Mostrar el estado del tablero en cada generación.

    */
        boolean random;
        int[][] tablero;
        Scanner sc = new Scanner(System.in);
        System.out.println("0. Crear tablero random \n1. Crear tablero personalizado");
        int opcion = sc.nextInt();
        if (opcion==0) random=true; else random=false;
        tablero = crearTablero(random);
        tablero = rellenarTableroRandom(tablero);
        mostrarTablero(tablero);



    }

    public static int[][] crearTablero(boolean random) {
        int[][] tablero;
        if (random) {
            tablero = tableroRandom();
        } else {
            tablero = tableroDado();
        }

        return tablero;
    }

    public static int[][] tableroRandom() {
        Random rn = new Random();
        int alto = rn.nextInt(1,10);
        int ancho = rn.nextInt(1,10);

        System.out.println(alto+", "+ancho);

        int[][] tablero = new int[alto][ancho];
        return tablero;
    }

    public static int[][] tableroDado() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ancho del tablero: ");
        int ancho = sc.nextInt();
        System.out.println("Alto del tablero: ");
        int alto = sc.nextInt();

        int[][] tablero = new int[ancho][alto];
        return tablero;
    }

    public static int [][] rellenarTableroRandom(int [][] tablero){
        Random rn = new Random();
        for (int i = 0; i<tablero.length;i++){
            for (int j = 0; j<tablero[i].length;j++){
                tablero[i][j]= rn.nextInt(2); //aqui en verdad hay que iniciar los hilos
            }
        }

        return tablero;
    }
    public static void mostrarTablero(int [][] tablero){
        for (int i = 0; i<tablero.length;i++){
            for (int j = 0; j<tablero[i].length;j++){
                System.out.print(tablero[i][j]+" ");
            }
            System.out.println();
        }
    }

}

