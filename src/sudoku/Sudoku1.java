package sudoku;

import java.awt.List;
import java.util.ArrayList;

public class Sudoku1 {

    public Sudoku1() {
    }

    
    //busca elementos negativos o mayores a Matrix.length()  
    //true=sin o hay numeros incorrectos
    //false=si existen numeros incorrectos
    public boolean buscarElemDistintos(int[][] matrix, int n) {
        int x = 0, y = 0;

        while (matrix[x][y] <= n && matrix[x][y] >= 0) {
            y = y + 1;
            if (y == n) {
                y = 0;
                x = x + 1;
            }
            if (x == n) {
                return true;
            }
        }
        return false;
    }
//busca en matriz si hay texto    
//true=si no hay texto
//false=si si hay texto
public boolean buscaStrings(int [][]matrix, int n){//codigo ascii 48-57
    int x=0,  y=0;
    
    while(matrix[x][y]+"".charAt(0)>=48 && matrix[x][y]+"".charAt(0)<=57){
        y++;
    if (y == n) {
        y = 0;
        x = x + 1;
    }
    if (x == n) {
        return true;
    }
    }
      return false;
              
}
    //valida si contiene elemento n en matriz
    public boolean contains(int fila, int elem, int[][] matrix, int n, int y) {
        boolean resp = true;
        while (matrix[fila][y] != elem && y < n) {
            y++;
        }
        if (y == n) {
            resp = false;
        }
        return resp;

    }

    public void imprimeMatriz(int[][] matriz) {
        for (int x = 0; x < matriz.length; x++) {

            for (int y = 0; y < matriz[x].length; y++) {
                System.out.print(matriz[x][y]);
                if (y != matriz[x].length - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println(" ");
        }
    }
//validacion cuadrante, fila,columna
    private boolean valida(int[][] A, int x, int y, int val) {
        boolean resp = true;

        int tam = (int) Math.sqrt(A.length);
        int q = x % (tam);
        int r = y % (tam);
        int inicioX = x - q;
        int inicioY = y - r;

        if (!(x >= 0 && x < A.length && y >= 0 && y < A[0].length)) {
            return false;
        }
        for (int i = 0; i < A[0].length; i++) {//fila
            if (val == A[x][i] && i!=y) {//
                resp = false;
                break;
            }
        }
        for (int i = 0; i < A[0].length; i++) {//columna
            if (val == A[i][y] && i!=x) {
                resp = false;
                break;
            }
        }
        for (int a = inicioX; a < inicioX + (tam); a++)//cuadrante
        {
            for (int b = inicioY; b < inicioY + (tam); b++) {
                if (val == A[a][b] && a!=a && b!=b) {
                    resp = false;
                    break;
                }
            }
        }

        return resp;

    }
//valida si existen elementos repetidos
    //true=no hay repetidos
    //false=hay repetidos
    public boolean ElemRepetidos(int[][] A) {
        
         for (int x = 0; x < A.length; x++) {//moverse
            for (int y = 0; y < A.length; y++) {
                if (A[x][y] != 0 && valida(A, x, y, A[x][y])==false){ //si distinto de cero y no existe otro A[x][y]
                    return false;
                }            
            }
        }
        return true;
    }
    
    

    public boolean ValidaInicio(int[][] A) {
        if (buscarElemDistintos(A, A.length) == true && ElemRepetidos(A)==true) {
            return true;
        }
        
        return false;

    }

    public boolean llena(int[][] A, int x, int y, int val) {
        int xAnt = x, yAnt = y;
        if(val>A.length)
            return false;
        
        if (y == A.length) {
            y = 0;
            x++;
        }
        if(x==A.length){
            return true;
        }
        if (A[x][y] != 0) {
            return llena(A, x, y + 1, val);
        }

        //validacion incorrecta  
        if (!valida(A, x, y, val)) {
            return llena(A,x,y,val+1);
        }
        A[x][y] = val;
        if (x == A.length - 1 && y == A[0].length - 1)//si ya acabaste
        {
            return true;
        }

        if (y < A[0].length - 1) {
            y++;
        } else {
            y = 0;
            x++;
        }

        //9 casos
        boolean done = false;
        int i = 1;
        while (!done && i <= A.length) {
            done = llena(A, x, y, i++);
        }
        if (!done) {
            A[xAnt][yAnt] = 0;
        }
        return done;

    }
    
   
    public static void main(String args[]) {
        Sudoku1 p = new Sudoku1();
        int[][] c = new int [16][16]; 
                /*{
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},};
       */

       
       p.llena(c, 0, 0, 1);
        p.imprimeMatriz(c);
        
     
        
     
    }

}
