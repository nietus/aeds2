public class Fila {
    //implementacao circular
    int[] arr;
    int primeiro, ultimo;
    int size;
    Fila(){
        int size = 6;
        arr = new int[size+1];
        primeiro = ultimo = 0;
    }
    void inserir(int x) throws Exception{
        if((ultimo + 1) % arr.length == primeiro){
            throw new Exception("Fila cheia!");
        }
        arr[ultimo] = x;
        ultimo = (ultimo + 1) % arr.length;
    }
    void remover() throws Exception {
        if(primeiro == ultimo){
            throw new Exception("fila vazia!");
        }
        primeiro = (primeiro + 1) % arr.length;
    }
}