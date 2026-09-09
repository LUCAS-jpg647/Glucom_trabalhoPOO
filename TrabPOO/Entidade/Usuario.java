package TrabPOO.Dados;

public class Usuario {
    private int id;
    private String nome;
    private String nomeUsuario;
    private String senha;
    private TipoUsuario tipo;

    public Usuario(int id, String nome, String nomeUsuario, String senha, TipoUsuario tipo) {
        this.id = id;
        this.nome = nome;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.tipo = tipo;
    }

    public int getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeUsuario() {
        return this.nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipo() {
        return this.tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public String toString() {
        return "Id: " + this.id
                + " | Nome: " + this.nome
                + " | Usuario: " + this.nomeUsuario
                + " | Tipo: " + this.tipo;
    }
}
