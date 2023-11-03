public class arvorepesquisa {
    No raiz;
    arvorepesquisa(){
        raiz = null;
    }
    
    public void inserir(int x) throws Exception{
        raiz = inserir(x,raiz);
    }
    private No inserir(int x, No i) throws Exception{
        if(i == null){
            i = new No(x);
        }
        else if(x < i.elemento){
            i.esq = inserir(x,i.esq);
        }
        else if(x > i.elemento){
            i.dir = inserir(x, i.dir);
        }
        else{
            throw new Exception("Error");
        }
        return i;
    }
}