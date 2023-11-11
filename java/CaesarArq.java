public class CaesarArq {

    public static void main(String[] args) {
        convertToUppercaseAndSave("input.txt", "output_uppercase.txt");
        reverseAndSave("input.txt", "output_reverse.txt");
        caesarEncryptAndSave("input.txt", "output_encrypt.txt", 3);
        caesarDecryptAndSave("output_encrypt.txt", "output_decrypt.txt", 3);
    }

    public static void convertToUppercaseAndSave(String inputFile, String outputFile) {
        String content = Arq.openReadClose(inputFile);
        String uppercaseContent = content.toUpperCase();
        Arq.openWriteClose(outputFile, uppercaseContent);
    }

    public static void reverseAndSave(String inputFile, String outputFile) {
        String content = Arq.openReadClose(inputFile);
        StringBuilder reversedContent = new StringBuilder(content).reverse();
        Arq.openWriteClose(outputFile, reversedContent.toString());
    }

    public static void caesarEncryptAndSave(String inputFile, String outputFile, int shift) {
        String content = Arq.openReadClose(inputFile);
        String encryptedContent = caesarEncrypt(content, shift);
        Arq.openWriteClose(outputFile, encryptedContent);
    }

    public static void caesarDecryptAndSave(String inputFile, String outputFile, int shift) {
        String content = Arq.openReadClose(inputFile);
        String decryptedContent = caesarDecrypt(content, shift);
        Arq.openWriteClose(outputFile, decryptedContent);
    }

    public static String caesarEncrypt(String message, int shift) {
        StringBuilder result = new StringBuilder();
        for (char ch : message.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                ch = (char) (((ch - base + shift) % 26) + base);
            }
            result.append(ch);
        }
        return result.toString();
    }

    public static String caesarDecrypt(String message, int shift) {
        return caesarEncrypt(message, 26 - shift);
    }
}