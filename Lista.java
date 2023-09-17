public class Lista {
    int[] array;
    int size;
    int n;

    Lista(){
        size = 6;
        array = new int[size];
        n = 0;
    }

    void inserirInicio(int x) throws Exception{
        if(n>=array.length){
            throw new Exception("Lista lotada!");
        }
        for(int i = n; i > 0; i--){
            array[i] = array[i-1];
        }
        n++;
        array[0] = x;
    }

    void inserirFim(int x) throws Exception{
        if(n>=array.length){
            throw new Exception("Lista lotada!");
        }
        array[n] = x;
        n++;
    }

    void inserir(int x, int pos) throws Exception{
        if(n>=array.length || pos < 0 || pos > n){
            throw new Exception("Lista lotada ou array out of bound!");
        }
        for(int i = n; i > pos; i--){
            array[i] = array[i - 1];
        }
        array[pos] = x;
        n++;
    }

    void removerInicio() throws Exception{
        if(n == 0){
            throw new Exception("Sem elementos para remover!");
        }
        n--;
        for(int i = 0; i < n; i++){
            array[i] = array[i + 1];
        }
    }

    void removerFim() throws Exception{
        if(n == 0){
            throw new Exception("Sem elementos para remover!");
        }
        n--;
    }

    void remover(int pos) throws Exception{
        if(n==0||pos<0||pos>=n){
            throw new Exception("Sem elementos para remover ou index out of bound");
        }     
        n--;
        for(int i = pos; i < n; i++){
            array[i] = array[i+1];
        }
    }

    void mostrar(){
        for(int i = 0 ; i < n; i++){
            System.out.print(array[i] + " ");
        }
    }
}