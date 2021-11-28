import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) throws IOException {
        
        InetAddress addr;
        if (args.length == 0)
            addr = InetAddress.getByName(null);

        else
            addr = InetAddress.getByName(args[0]);
       
            Socket socket = null;
       
            BufferedReader in = null, stdIn = null;
       
            PrintWriter out = null;

        try {

            // creazione socket
            socket = new Socket(addr, Server.PORT);
            System.out.println("Client connesso");
            


            // creazione stream di input da socket
            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            in = new BufferedReader(isr);


            // creazione stream di output su socket
            OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
            BufferedWriter bw = new BufferedWriter(osw);
            out = new PrintWriter(bw, true);


            // creazione stream di input da tastiera
            stdIn = new BufferedReader(new InputStreamReader(System.in));
            String userInput;


            // ciclo di lettura da tastiera, invio al server e stampa risposta
            while (true) {
                userInput = stdIn.readLine();
                out.println(userInput);

                if (userInput.equals("stop")){
                    
                    break;
                }


                System.out.println("Messaggio inviato: " + in.readLine());
            }
        } catch (UnknownHostException e) {

            System.err.println("errore");
            System.exit(1);

        } catch (IOException e) {

            System.err.println("errore");
            System.exit(1);
        }

        System.out.println("fine comunicazione");
        out.close();
        in.close();
        stdIn.close();
        socket.close();
    }
}