package Client;

import java.net.InetAddress;

public class Server {
    private int puerto = 12345;

    private InetAddress ip;

    public Server(int puert, InetAddress dir) {
        puerto = puert;
        try {
            ip = dir;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int getPuerto() {
        return puerto;
    }
    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }
    public InetAddress getIp() {
        return ip;
    }
    public void setIp(InetAddress ip) {
        this.ip = ip;
    }
}
