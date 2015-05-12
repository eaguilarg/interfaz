package sudoku;

import java.awt.List;
import java.util.ArrayList;

public class Sudoku1 {
    public Sudoku1(){
        
    }
    
    
    //movimientos
   //int derecha=y+1, izquierda=y-1, arriba=x-1, abajo=x+1;
 
  //busca elementos negativos o mayores a Matrix.length()  
  public boolean buscarElemDistintos(int [][]matrix, int n){
    boolean resp=false;  
   int x=0, y=0;
           while(matrix[x][y]!=0 && matrix[x][y]<=n || matrix[x][y]!=0 && matrix[x][y]>0){
                y=y+1;
            if(y==n){
                y=0;
                x=x+1;
            }
            
            if(matrix[x][y]>n || matrix[x][y]<0)
                resp=true;
            }
    return resp;
  }

 //valida si contiene elemento n en matriz
  public boolean contains(int fila,int elem, int [][]matrix, int n, int y){
      boolean resp=true;
            while(matrix[fila][y]!=elem && y<n)
                y++;
            if(y==n)
               resp=false;
      return resp;
                   
  }

public void imprimeMatriz(int [][] matriz){
for (int x=0; x < matriz.length; x++) {

  for (int y=0; y < matriz[x].length; y++) {
    System.out.print (matriz[x][y]);
    if (y!=matriz[x].length-1) 
        System.out.print(" ");
     }
 System.out.println(" ");
}
}
 


    private boolean valida(int [][]A, int x,int y,int val){
        boolean resp=true; 
       
       int tam=(int)Math.sqrt(A.length);
       int q= x%(tam);
       int r= y%(tam);
       int inicioX=x-q;
       int inicioY=y-r;
       
                   
        if(!(x>=0 && x<A.length && y>=0 && y<A[0].length))
            return false;
        for(int i=0; i<A[0].length;i++){//fila
            if(val==A[x][i]){
                resp=false;
                break;
            }
        }
         for(int i=0; i<A[0].length;i++){//columna
            if(val==A[i][y]){
                resp=false;
                break;
            }
         }
        for(int a=inicioX; a<inicioX+(tam);a++)//cuadrante
          for(int b=inicioY;b<inicioY+(tam);b++)
              if(val==A[a][b]){
                  resp=false;
                   break;
           }
            
   
        return resp;
     
       }
    
  public boolean ElemRepetidos(int [][]A,int x,int y){
      ArrayList <Integer> lista = null, lista2 = null,lista3=null;
      //filas
      while(A[x][y]!=0 && y<A.length){
          y++; 
          if(lista.contains(A[x][y]))
             return true;
         lista.add(A[x][y]);
     if(y==A.length){
         lista=new ArrayList<Integer>();
         y=0;
         x++;
      }
     if(y==A.length-1 && x==A.length-1)
         return false;
      }
      
     //columnas 
     while(A[x][y]!=0 && x<A.length){
         x++;
       if(lista2.contains(A[x][y]))
             return true;
         lista2.add(A[x][y]);
     if(x==A.length){
         lista2=new ArrayList<Integer>();
         x=0;
         y++;
      }
     if(y==A.length-1 && x==A.length-1)
         return false;
      }
        return false;
     
     
  }
    
   
   public boolean ValidaInicio(int [][]A, int x,int y,int val){
       if(buscarElemDistintos(A,(int)Math.sqrt(A.length))==false)
           return true;
       if(ElemRepetidos(A,0,0)==false)
           return true;
        return false;
       
       
       
   }
    public boolean llena(int [][]A, int x,int y,int val) {
        int xAnt=x, yAnt=y;
        
        //si usuario puso numeros incorrectos
   /*   if(buscarElemDistintos(A,(int)Math.sqrt(A.length)))
          return false;
     */ 
      if(A[x][y]!=0)
          return llena(A,x,y+1,val);
      
   
    //validacion incorrecta  
        if(!valida(A,x,y,val))
            return false;
        A[x][y]=val;
        if(x==A.length-1 && y==A[0].length-1)//si ya acabaste
            return true;
        if(y<A[0].length-1)
            y++;
        else{
            y=0;
            x++;
        }
               
        
        //9 casos
        boolean done=false;
        int i=1;
        while(!done && i<=A.length)
            done=llena(A,x,y,i++);
        if(!done)
            A[xAnt][yAnt]=0;
        return done;
    
}
    
    
    
    
    
public static void main(String args[]){
        Sudoku1 p=new Sudoku1();
        int [][] c={ 
        {5, 5, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {3, 0, 0, 0, 0, 0, 0, 0, 0},
    
        };
       if(p.ValidaInicio(c, 0, 0, 1)==true){
         p.llena(c,0,0,1);
         p.imprimeMatriz(c);
       }
   

}


   
    
}
