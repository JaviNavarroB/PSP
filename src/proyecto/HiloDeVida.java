package proyecto;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;

//TENGO QUE CAMBIAR LAS VARIABLES GLOBALES POR UN MONITOR


public class HiloDeVida extends Thread {

    int vida;
    int contador;
    int i;
    int j;

    public HiloDeVida(int i, int j) {
        Random rn = new Random();
        this.vida = rn.nextInt(2);
        this.i=i;
        this.j=j;
    }

    @Override
    public void run() {
        try{
            while (true){

                evaluarVidaHilo(this.i, this.j);

                // Esperar a que todas las celdas hayan terminado de evaluar
                Principal2.cb.await();

                // Actualizar el estado de la celda
                actualizarVida();

                // Esperar a que todas las celdas actualicen su estado
                Principal2.cb.await();
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }

    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getContador() {
        return contador;
    }

    public void evaluarVidaHilo(int i, int j) {
        System.out.println(Principal2.tablero[0][0]);
        this.contador=0;
        for (int k = i - 1; k <= i + 1; k++) {
            if (k >= 0 && k < Principal2.tablero.length) {
                for (int l = j - 1; l <= j + 1; l++) {
                    if (l >= 0 && l < Principal2.tablero[k].length) { //checkear esto
                        if (Principal2.tablero[k][l].getVida() == 1) {
                            if (k == i && l == j) {
                                continue;  //checkear que esto siga sin hacer nada.
                            } else {
                                this.contador++;

                            }
                        }
                    }
                }
            }
        }
    }
    public void actualizarVida(){
        if (this.contador==2 || this.contador ==3){
            this.vida=1;
        }else{
            this.vida=0;
        }
    }
}