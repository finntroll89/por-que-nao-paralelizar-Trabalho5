
package cadastrocliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;
import java.util.logging.Level;

public class CadastroCliente {

    private final String ADDRESS = "localhost"; 
    private final int PORT = 4321;
    
    public CadastroCliente() {
        
    }
    
    private void run() {
        try (Socket socket = new Socket(ADDRESS, PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Conectado ao servidor CadastroServer.");
            System.out.print("Digite seu nome de usuario: ");
            String username = consoleIn.readLine();
            System.out.print("Digite sua senha: ");
            String password = consoleIn.readLine();
            out.println(username);
            out.println(password);
            String response = in.readLine();
            System.out.println(response);
            if (response.equals("Autenticacao bem-sucedida. Aguardando comandos...")) {
                boolean exitChoice = false;
                while (!exitChoice) {
                    System.out.print("Digite 'L' para listar produtos ou 'S' para sair: ");
                    String command = consoleIn.readLine().toUpperCase();
                    out.println(command);
                    switch (command) {
                        case "S" -> exitChoice = true;
                        case "L" -> receiveAndDisplayProductList(in);
                        default -> System.out.println("Opcao invalida!");
                    }
                }
            }
        } catch (IOException e) {
            Logger.getLogger(CadastroCliente.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    private void receiveAndDisplayProductList(BufferedReader in) throws IOException {
        String line;
        while ((line = in.readLine()) != null && !line.isEmpty()) {
            System.out.println(line);
        }
    }

    public static void main(String[] args) {
        new CadastroCliente().run();
    }
   
}
