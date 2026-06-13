/**
 * ============================================================
 *  File Compression Simulator - Using Core Java & OOP Concepts
 * ============================================================
 *  Course      : Object Oriented Programming With Java
 *  Course Code : RU-100-01-00012
 *  University  : Rungta International Skills University, Bhilai CG
 *  Session     : 2025-26
 *  Guide       : 
 * ============================================================
 *
 *  CLASS: Compressor
 *  -----------------
 *  This class is the core engine of the File Compression Simulator.
 *  It provides three public methods:
 *
 *    1. compress()         - Compresses a string using Run-Length Encoding (RLE)
 *    2. decompress()       - Decompresses an RLE-encoded string back to original
 *    3. compressionRatio() - Calculates how much the string was compressed (%)
 *
 *  Algorithm Used: Run-Length Encoding (RLE)
 *  -----------------------------------------
 *  RLE replaces sequences of the same character with the character
 *  followed by the count of its repetitions.
 *
 *  Example:
 *    Input  -> "aaaabbc"
 *    Output -> "a4b2c1"
 *
 *  OOP Concepts Demonstrated:
 *    - Class and Object creation
 *    - Method design and encapsulation
 *    - Use of StringBuilder for efficient string handling
 *    - Loops (while, for) and conditional statements (if)
 * ============================================================
 */
public class Compressor {

    // ─────────────────────────────────────────────────────────
    // METHOD 1: compress()
    // ─────────────────────────────────────────────────────────

    /**
     * Compresses the input string using Run-Length Encoding (RLE).
     *
     * How it works:
     *   - Traverse the string from left to right
     *   - For each character, count how many times it repeats consecutively
     *   - Append the character and its count to the result
     *   - Move the pointer forward by the count
     *
     * Example:
     *   Input  : "aaaabbc"
     *   Output : "a4b2c1"
     *
     * @param input The original string to compress
     * @return      The RLE-compressed string
     */
    public String compress(String input) {

        // Handle null or empty string edge cases
        if (input == null || input.isEmpty()) {
            return "";
        }

        // StringBuilder is used for efficient string concatenation
        StringBuilder compressed = new StringBuilder();

        // Pointer to traverse the string
        int i = 0;

        while (i < input.length()) {

            // Get the current character at position i
            char currentChar = input.charAt(i);

            // Initialize count for this character
            int count = 1;

            // Count how many times this character repeats consecutively
            while (i + count < input.length() && input.charAt(i + count) == currentChar) {
                count++;
            }

            // Append the character followed by its count to the result
            compressed.append(currentChar);
            compressed.append(count);

            // Move pointer forward by the count (skip all repeated chars)
            i += count;
        }

        // Return the final compressed string
        return compressed.toString();
    }

    // ─────────────────────────────────────────────────────────
    // METHOD 2: decompress()
    // ─────────────────────────────────────────────────────────

    /**
     * Decompresses an RLE-encoded string back to the original string.
     *
     * How it works:
     *   - Read the compressed string two parts at a time: character + digit(s)
     *   - Repeat the character the specified number of times
     *   - Append all repeated characters to reconstruct the original string
     *
     * Example:
     *   Input  : "a4b2c1"
     *   Output : "aaaabbc"
     *
     * @param compressed The RLE-compressed string to decompress
     * @return           The reconstructed original string
     */
    public String decompress(String compressed) {

        // Handle null or empty string edge cases
        if (compressed == null || compressed.isEmpty()) {
            return "";
        }

        // StringBuilder to build the decompressed result
        StringBuilder result = new StringBuilder();

        // Pointer to traverse the compressed string
        int i = 0;

        while (i < compressed.length()) {

            // Step 1: Read the character (letter)
            char ch = compressed.charAt(i);
            i++; // Move pointer past the character

            // Step 2: Read the count (one or more digits follow the character)
            StringBuilder numStr = new StringBuilder();
            while (i < compressed.length() && Character.isDigit(compressed.charAt(i))) {
                numStr.append(compressed.charAt(i));
                i++; // Move pointer past each digit
            }

            // Convert the digit string to an integer count
            int count = Integer.parseInt(numStr.toString());

            // Step 3: Repeat the character 'count' times and append to result
            for (int j = 0; j < count; j++) {
                result.append(ch);
            }
        }

        // Return the fully reconstructed original string
        return result.toString();
    }

    // ─────────────────────────────────────────────────────────
    // METHOD 3: compressionRatio()
    // ─────────────────────────────────────────────────────────

    /**
     * Calculates the compression ratio as a percentage.
     *
     * Formula:
     *   ratio = (1 - compressedLength / originalLength) * 100
     *
     * A positive ratio means the string got smaller (good compression).
     * A negative ratio means the compressed string is longer than the original
     * (RLE is inefficient for non-repetitive strings like "helloworld").
     *
     * @param original   The original input string
     * @param compressed The compressed version of the string
     * @return           A formatted string showing the compression ratio (e.g., "33.33%")
     */
    public String compressionRatio(String original, String compressed) {

        // Avoid division by zero
        if (original == null || original.isEmpty()) {
            return "N/A";
        }

        // Calculate the ratio
        double ratio = (1.0 - (double) compressed.length() / original.length()) * 100;

        // Return formatted result with 2 decimal places
        return String.format("%.2f%%", ratio);
    }
}