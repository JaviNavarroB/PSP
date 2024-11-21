package proyecto;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;

public class Principal2 {

    public static HiloDeVida[][] tablero;
    public static CyclicBarrier cb;

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

        Scanner sc = new Scanner(System.in);
        System.out.println("0. Crear tablero random \n1. Crear tablero personalizado");
        int opcion = sc.nextInt();
        if (opcion == 0) random = true;
        else random = false;
        tablero = crearTablero(random);
        tablero = rellenarTableroRandom(tablero);
        iniciarHilos();//aqui se inician los hilos
        mostrarTablero(tablero);

        //los hilos deberian haber sido inicializados ya //check
        //los hilos esperan
        int numHilos = tablero.length * tablero[0].length;
        cb = new CyclicBarrier(numHilos, () -> {
            System.out.println("Todos los hilos han llegado a la barrera. Continuando...");
        });
        //se evalua el tablero
        evaluarVida(tablero);

        //los hilos siguen
        //repeat


    }

    public static HiloDeVida[][] crearTablero(boolean random) {
        HiloDeVida[][] tablero;
        if (random) {
            tablero = tableroRandom();
        } else {
            tablero = tableroDado();
        }

        return tablero;
    }

    public static HiloDeVida[][] tableroRandom() {
        Random rn = new Random();
        int alto = rn.nextInt(1, 10);
        int ancho = rn.nextInt(1, 10);

        System.out.println(alto + ", " + ancho);

        HiloDeVida[][] tablero = new HiloDeVida[alto][ancho];
        return tablero;
    }

    public static HiloDeVida[][] tableroDado() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ancho del tablero: ");
        int ancho = sc.nextInt();
        System.out.println("Alto del tablero: ");
        int alto = sc.nextInt();

        HiloDeVida[][] tablero = new HiloDeVida[ancho][alto];
        return tablero;
    }

    public static HiloDeVida[][] rellenarTableroRandom(HiloDeVida[][] tablero) {
        //Random rn = new Random();

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = new HiloDeVida(i, j);
                //tablero[i][j].setVida( rn.nextInt(2)); //aqui en verdad hay que iniciar los hilos
                 //entonces ahora tengo que usar lo anterior en el constructor
            }
        }

        return tablero;
    }
    public static void iniciarHilos(){
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = new HiloDeVida(i, j);
                //tablero[i][j].setVida( rn.nextInt(2)); //aqui en verdad hay que iniciar los hilos
                //entonces ahora tengo que usar lo anterior en el constructor
                tablero[i][j].start();
            }
        }

    }

    public static void mostrarTablero(HiloDeVida[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(tablero[i][j].getVida() + " ");
            }
            System.out.println();
        }
    }

    public static void evaluarVida(HiloDeVida[][] tablero) {
        int contador = 0;
        int contador2 = 0; //checkear si sigue habiendo algun hilo con vida, si no se acaba el juego.
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                //hay que evaluar las posiciones circundantes, es decir i,j -1,0,1 en las distintas direcciones
                // contador = 0;
                /*
                for (int k = i-1; k<=i+1;k++){
                    if (k>=0&&k<tablero.length){
                        for(int l = j-1;l <= j+1;l++){
                            if(l>=0&& l<tablero[k].length){ //checkear esto
                                if (tablero[k][l].getVida()==1){
                                    if(k==i && l==j){
                                        continue;  //checkear que esto siga sin hacer nada.
                                    }else{
                                        contador++;

                                    }
                                }
                            }
                        }
                    }
                }
                */
                tablero[i][j].run();//run??
                contador2 += tablero[i][j].getVida();
                //contador= tablero[i][j].getContador();
                /*
                if (contador==2 || contador ==3){
                    tablero[i][j].setVida(1);
                    contador2++;
                }else{
                    tablero[i][j].setVida(0);
                }
            }
            System.out.println();

                 */
            }
            mostrarTablero(tablero);
            if (contador2 == 0) {
                System.out.println("No quedan hilos con vida");
                System.exit(0);
            }
        }
    }
}
