public class Equals {
	public static boolean stringEq(String testado, String testando) {
		for(int i = 0; i < testado.length();i++) {
			if(testado.charAt(i) != testando.charAt(i))
				return false;
		}
		return true;
	}
}