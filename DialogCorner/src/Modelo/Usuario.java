package Modelo;

public class Usuario {
    private String nickname;
    private String clave;

    public Usuario(String nickname, String clave) {
        this.nickname = nickname;
        this.clave = clave;
    }

    public String getNickname() {
        return nickname;
    }

    public String getClave() {
        return clave;
    }
}
