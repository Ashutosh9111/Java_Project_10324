import java.util.Scanner;

class Compressor {
    private String original;
    private String compressed;

    public String compress(String input) {
        original = input;
        StringBuilder result = new StringBuilder();
        int count = 1;
        
        for (int i = 0; i < input.length(); i++) {
            if (i + 1 < input.length() && input.charAt(i) == input.charAt(i + 1)) {
                count++;
            } else {
                result.append(input.charAt(i)).append(count);
                count = 1;
            }
        }
        compressed = result.toString();
        return compressed;
    }

    public String decompress() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < compressed.length(); i += 2) {
            char ch = compressed.charAt(i);
            int count = compressed.charAt(i + 1) - '0';
            for (int j = 0; j < count; j++) {
                result.append(ch);
            }
        }
        return result.toString();
    }

    public boolean verify(String original, String decompressed) {
        return original.equals(decompressed);
    }
}

public class CompressionDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter String: ");
        String input = scanner.nextLine();

        Compressor compressor = new Compressor();
        String compressed = compressor.compress(input);
        String decompressed = compressor.decompress();

        System.out.println("Compressed String: " + compressed);
        System.out.println("Decompressed String: " + decompressed);
        System.out.println("Verification: " + compressor.verify(input, decompressed));
        
        scanner.close();
    }
}