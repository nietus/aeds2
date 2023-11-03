public class No {
    // for binary trees
    int elemento;
    No esq;
    No dir;
    public No(int elemento){
        this(elemento,null,null);
    }
    private No(int elemento, No esq, No dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}
