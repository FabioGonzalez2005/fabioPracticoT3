package fabiopracticot3servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class FabioPracticoT3Servidor {

    public static void main(String[] args) {
        int port = 22343;
        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("Servidor iniciado en el puerto " + port + ".");

            while (true) {
                try (Socket client = server.accept();
                     BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                     PrintWriter output = new PrintWriter(client.getOutputStream(), true)) {

                    System.out.println("Cliente conectado desde " + client.getInetAddress());

                    String direccion1 = input.readLine();
                    String direccion2 = input.readLine();

                    if ("0.0.0.0/0".equals(direccion1)) {
                        break;
                    }

                    System.out.println("Dirección 1: " + direccion1);
                    System.out.println("Dirección 2: " + direccion2);

                    String resultado;
                    if (mismaRed(direccion1, direccion2)) {
                        resultado = "Las direcciones pertenecen a la misma red.";
                    } else {
                        resultado = "Las direcciones NO pertenecen a la misma red.";
                    }

                    output.println(resultado);
                    System.out.println("Procesado: " + direccion1 + " y " + direccion2);

                } catch (IOException e) {
                }
            }
        } catch (IOException e) {
        }
    }

    private static boolean mismaRed(String direccion1, String direccion2) {
        String[] partes1 = direccion1.split("/");
        String[] partes2 = direccion2.split("/");

        String ip1 = partes1[0];
        String ip2 = partes2[0];

        int mascara1 = Integer.parseInt(partes1[1]);
        int mascara2 = Integer.parseInt(partes2[1]);

        if (mascara1 != mascara2) {
            return false;
        }

        String binario1 = IPAddressToBinary(ip1);
        String binario2 = IPAddressToBinary(ip2);

        String subred1 = binario1.substring(0, mascara1) + "0".repeat(32 - mascara1);
        String subred2 = binario2.substring(0, mascara2) + "0".repeat(32 - mascara2);

        return subred1.equals(subred2);
    }

    private static String IPAddressToBinary(String IPAddress) {
        String result = "";
        String[] octets = IPAddress.split("\\.");
        for (String octet : octets) {
            result += String.format("%8s", Integer.toBinaryString(Integer.valueOf(octet))).replace(" ", "0");
        }
        return result;
    }
}
