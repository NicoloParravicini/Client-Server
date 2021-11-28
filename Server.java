import java.io.*;
import java.net.*;

public class Server {
    public static final int PORT = 1050;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server avviato! ");
        System.out.println("Rimango in attesa di una connessione... ");
        Socket clientSocket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            // rimane in attesa finchè non avviene una connessione
            clientSocket = serverSocket.accept();
            System.out.println("Connessione accettata, scrivi un messaggio!" );


            // creazione stream di input da clientSocket
            InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream());
            in = new BufferedReader(isr);


            // creazione stream di output su clientSocket
            OutputStreamWriter osw = new OutputStreamWriter(clientSocket.getOutputStream());
            BufferedWriter bw = new BufferedWriter(osw);
            out = new PrintWriter(bw, true);


            // ciclo di ricezione dal client e invio di risposta
            while (true) {
                String str = in.readLine();

                if (str.equals("stop"))
                    break;

                System.out.println("messaggio ricevuto: " + str);
                out.println(str);
            }
        } catch (IOException e) {
            System.err.println("connessione fallita");
            System.exit(1);
        }


        // chiusura di stream e socket
        System.out.println("chiusura...");
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}