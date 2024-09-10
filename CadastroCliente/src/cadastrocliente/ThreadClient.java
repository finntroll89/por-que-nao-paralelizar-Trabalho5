
package cadastrocliente;

import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class ThreadClient extends Thread {

    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;

    private JTextArea textArea;
    private JFrame frame;
    private ArrayList<String> output;
    
    public ThreadClient() {
        
    }

    public ThreadClient(ObjectInputStream in, ObjectOutputStream out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public void run() {
        output = new ArrayList<>();
        try {
            Boolean validate = (Boolean) in.readObject();
            Integer idUsuario = (Integer) in.readObject();

            if (validate) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

                String comando;
                String idPessoa;
                String idProduto;
                String quantidade;
                String valor_unitario;

                do {
                    System.out.print("""
                                     ======= Comandos =======
                                     
                                      L - Listar 
                                      F - Finalizar 
                                      E - Entrada 
                                      S - Saida 
                                     
                                     Digite o comando: """);
                    comando = reader.readLine().toUpperCase();

                    switch (comando) {
                        case "E" -> {
                            out.writeObject("E");

                            System.out.println("======== Entrada ========");

                            System.out.print("ID Pessoa: ");
                            idPessoa = reader.readLine();
                            out.writeObject(idPessoa);

                            System.out.print("ID Produto: ");
                            idProduto = reader.readLine();
                            out.writeObject(idProduto);

                            System.out.print("ID Usuario: " + idUsuario);
                            out.writeObject(idUsuario);
                            System.out.println("");

                            System.out.print("Quantidade: ");
                            quantidade = reader.readLine();
                            out.writeObject(quantidade);

                            System.out.print("Valor Unitario: ");
                            valor_unitario = reader.readLine();
                            out.writeObject(valor_unitario);
                            
                            output.add("Entrada realizada com sucesso.\n");
                        }
                        
                        case "S" -> {
                            out.writeObject("S");

                            System.out.println("======== Saida ========");

                            System.out.print("ID Pessoa: ");
                            idPessoa = reader.readLine();
                            out.writeObject(idPessoa);

                            System.out.print("ID Produto: ");
                            idProduto = reader.readLine();
                            out.writeObject(idProduto);

                            System.out.print("ID Usuario: " + idUsuario);
                            out.writeObject(idUsuario);
                            System.out.println("");

                            System.out.print("Quantidade: ");
                            quantidade = reader.readLine();
                            out.writeObject(quantidade);

                            System.out.print("Valor Unitario: ");
                            valor_unitario = reader.readLine();
                            out.writeObject(valor_unitario);
                            
                            output.add("Saida realizada com sucesso.\n");
                        }

                        case "L" -> {
                            out.writeObject("L");
                            try {
                                ArrayList<String> produtoList = (ArrayList<String>) in.readObject();
                                ArrayList<Integer> produtoQuantidade = (ArrayList<Integer>) in.readObject();
                                if (frame == null || !frame.isVisible()) {
                                    frame = new JFrame("Retorno do Servidor");
                                    frame.setSize(400, 600);
                                    textArea = new JTextArea(20, 50);
                                    textArea.setEditable(false);
                                    frame.add(new JScrollPane(textArea));
                                    frame.pack();
                                    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                                    frame.setVisible(true);
                                    frame.setVisible(true);
                                    SwingUtilities.invokeLater(() -> {
                                        output.add("Lista de Produtos:\n");
                                        for (int i = 0; i < produtoList.size(); i++) {
                                            output.add(produtoList.get(i) + " " + produtoQuantidade.get(i) + "\n");
                                        }
                                        for (String line : output) {
                                            textArea.append(line);
                                        }
                                        textArea.setCaretPosition(textArea.getDocument().getLength());
                                    });
                                } else {
                                    frame.setVisible(false);
                                }
                                
                            } catch (ClassNotFoundException | IOException e) {
                                e.printStackTrace();
                            }
                        }

                        case "F" -> {
                            out.writeObject("F");
                            System.out.println("======== Programa finalizado ========");
                        }

                        default -> System.out.println("Opcao invalida. Escolha novamente.");
                    }

                } while (!"f".equalsIgnoreCase(comando));

            } else {
                System.out.println("Usuario ou senha nao conferem!");
            }

        } catch (HeadlessException | IOException | ClassNotFoundException e) {
            if (!(e instanceof java.io.EOFException)) {
                System.out.println("======== Thread Finalizada ========");
            }
        }
    }
}
