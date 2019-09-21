package modelos;

public class Usuarios {

       private int id;
    private String nickname;
    private String nombre;
    private String password;
    private String correo;
    private String direccion;


    //vacio por que no se conocen todos los elementos
    public Usuarios() {
    }

    //para realizar las consultas
    public Usuarios(int id, String nickname, String nombre, String password, String correo, String direccion) {
        this.id = id;
        this.nickname = nickname;
        this.nombre = nombre;
        this.password = password;
        this.correo = correo;
        this.direccion = direccion;
    }

    //para agregar un usuario
    public Usuarios(String nickname, String nombre, String password, String correo, String direccion) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.password = password;
        this.correo = correo;
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
