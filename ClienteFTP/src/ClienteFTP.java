import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;

public class ClienteFTP {
    public static void main(String[] args) {
        FTPClient cliente = new FTPClient();

        String serverFTP = "localhost";
        try {
            cliente.connect(serverFTP,22);
            System.out.println(cliente.getReplyString());
            int codigo=cliente.getReplyCode();
            System.out.println("Código:"+codigo);
            if (!FTPReply.isPositiveCompletion(codigo)) {
                cliente.disconnect();
                System.out.println("Conexión rechazada");
                System.exit(0);
            }
            cliente.disconnect();
            System.out.println("fin de la conexión");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
