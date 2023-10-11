public class Exceptions extends RuntimeException {
    //IOException also can be used. Runtime exceptions wont let the program compile
    //This is to create a NEW exception
    public Exceptions(String msg){
        super(msg);
    }

    public static void main(String[] args){
        Conta c = new Conta(100);
        c.getSaldo();
        try{
            c.saca(1000);
        }
        catch(Exceptions e){
            System.out.println(e.getMessage());
        }
    }
}