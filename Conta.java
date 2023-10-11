public class Conta {
    private int saldo;
    //for the Exceptions.java

    public Conta(){
        this.saldo = 0;
    }

    public Conta(int saldo){
        this.saldo = saldo;
    }
    
    public int getSaldo(){
        return this.saldo;
    }

    public void saca(double valor) {
        if (saldo < valor) {
            throw new Exceptions("Saldo Insuficiente, tente um valor menor");
        } else {
            saldo-=valor;      
        }       
    }
}