package fabiopracticot3cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class FabioPracticoT3Cliente {

    public static void main(String[] args) {
        String host = "localhost";
        int port = 22343;

        try (Socket socket = new Socket(host, port);
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Conectado al servidor " + host + " en el puerto " + port + ".");

            System.out.print("Mándame la primera dirección: ");
            String direccion1 = consoleInput.readLine();

            System.out.print("Mándame la segunda dirección: ");
            String direccion2 = consoleInput.readLine();

            output.println(direccion1);
            output.println(direccion2);

            String respuesta = input.readLine();
            System.out.println("Respuesta del servidor: " + respuesta);

        } catch (IOException e) {
        }
    }
}

// Ejemplos de Ips

// 192.168.1.8/24
// 192.168.1.10/24

// 192.168.1.8/24
// 192.129.1.10/24