
package cadastroserver;

import cadastroserver.controller.UsuarioJpaController;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CadastroServerV2 {
    
    private final int PORT = 4321;
    
    public CadastroServerV2() {
        
    }
    
    private void run() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CadastroServerPU");
        UsuarioJpaController ctrlUsu = new UsuarioJpaController(emf);
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("======== SERVIDOR CONECTADO - PORTA " + PORT + " ========");
            while (true) {
                Socket socket = serverSocket.accept();
                CadastroThread teste = new CadastroThread(ctrlUsu, socket);
                teste.start();
                System.out.println("======== Thread iniciada ========");
            }
        } catch (IOException ex) {
            Logger.getLogger(CadastroServerV2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        new CadastroServerV2().run();
    }

}
