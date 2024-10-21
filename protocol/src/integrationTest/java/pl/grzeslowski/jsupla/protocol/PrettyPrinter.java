package pl.grzeslowski.jsupla.protocol;

import java.util.Arrays;
import java.util.List;

public class PrettyPrinter {

    public static final List<Integer> SUPLA_DS_CALL_REGISTER_DEVICE_G = Arrays.asList(
        // email
        32 + 32 + 32 + 32 + 32 + 32 + 32 + 32,
        //auth key,
        16,
        //guid
        16,
        //name
        32 + 32 + 32 + 32 + 32 + 32 + 9,
        // soft ver
        21,
        // server name
        32 + 32 + 1,
        //flags
        4,
        // manufacturerId
        2,
        //product id
        2,
        //channels count
        1
    );

    public static void main(String[] args) {
        printBytesPerLine(new byte[]{
            },
            SUPLA_DS_CALL_REGISTER_DEVICE_G);
    }

    public static void printBytesPerLine(byte[] byteArray, List<Integer> byteCountsPerLine) {
        int index = 0;

        for (int count : byteCountsPerLine) {
            // Ensure the count doesn't exceed the remaining bytes in the array
            int bytesToPrint = Math.min(count, byteArray.length - index);

            // Print each byte in the current line
            for (int i = 0; i < bytesToPrint; i++) {
//                System.out.printf("%02X ", byteArray[index + i]);
                System.out.printf("%3d, ", byteArray[index + i]);
            }
            System.out.println(); // New line after printing the required bytes

            // Move the index to the next part of the array
            index += count;

            // Stop if we reach the end of the array
            if (index >= byteArray.length) {
                break;
            }
        }

        for (; index < byteArray.length; index++) {
            System.out.printf("%3d, ", byteArray[index]);
        }
    }
}
