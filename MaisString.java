
public class MaisString {
	
	public static void main(String[] args) {
		String s = "OlA, Mundo!";
		System.out.println("Maiusculas: " + numMaiusculos(s) + " Caracteres: " + s.length());
		System.out.println("Primeira aparicao de A: " + primeiroA(s)+1 + " Num de vogais: " + numVogais(s));
		//System.out.println(CharToIntNumber('A'));
	}

    public static boolean isVowel(char c) {
        c = Character.toUpperCase(c);
        return c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }
	
	public static int numVogais(String s) {
		int num = 0;
		for(int i = 0; i < s.length();i++) {
			if(isVowel(s.charAt(i))) {
				num++;
			}
		}
		return num;
	}
	
	public static int primeiroA(String s) {
		for(int i = 0; i < s.length();i++) {
			if(charToIntNumber(s.charAt(i)) == 65) {
				return i;
			}
		}
		return -1;
	}
	
	public static int numMaiusculos(String s) {
		int contaMaiusculo = 0;
		for(int i = 0; i < s.length();i++) {
			if(charToIntNumber(s.charAt(i)) >= 65 && charToIntNumber(s.charAt(i)) <= 90) {
				contaMaiusculo++;
			}
		}
		return contaMaiusculo;
	}
	
	public static int charToIntNumber(char s) {
		return (int) s;
	}
	
	public static boolean palindromo(String s) {
		boolean resp = true;
		for(int i = 0; i < s.length()/2;i++) {
			if(s.charAt(i) != s.charAt(s.length() - i - 1)){
				resp = false;
				i = s.length();
			}
		}
		return resp;
	}
	
}
