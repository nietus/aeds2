import java.util.*;

class teste{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N, K;

        Stack<motorista> q = new Stack<>();

        boolean resp = true;

        while((N = sc.nextInt()) != 0 && (K = sc.nextInt()) != 0){

            for(int i = 0; i < N; i++){
                int ci = sc.nextInt();
                int si = sc.nextInt();
                if(q.size() < K){
                    q.add(new motorista(ci, si));
                }else{
                    motorista m = q.peek();
                    if(m.getsi() < ci){
                        q.pop();
                        q.add(new motorista(ci, si));
                    }else{
                        resp = false;
                    }
                }
            }

            for(motorista m: q){
                for(int k = 1; k < q.size(); k++){
                    if(m.getsi() < q.get(q.size() - k).getsi()){
                        resp = false;
                    }
                }
            }
            
            System.out.println(resp ? "Sim" : "Nao");
        }
    }
}

class motorista{
    int ci;
    int si;

    public motorista(int ci, int si){
        this.ci = ci;
        this.si = si;
    }

    int getci(){
        return ci;
    }

    int getsi(){
        return si;
    }
}