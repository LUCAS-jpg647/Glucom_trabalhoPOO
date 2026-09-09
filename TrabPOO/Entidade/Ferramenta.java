package TrabPOO.Dados;

public class Ferramenta {
    private int codigo;
    private Marca marca;
    private boolean status;
    private String nome;
    private String caracteristicas;

    public Ferramenta(int codigo, Marca marca, String nome, String caracteristicas) {
        this.codigo = codigo;
        this.marca = marca;
        this.nome = nome;
        this.caracteristicas = caracteristicas;
        this.status = true;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public String getCaracteristicas() {
        return this.caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Marca getMarca() {
        return this.marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public String toString() {
        String situacao;
        if (this.status) {
            situacao = "Disponivel";
        } else {
            situacao = "Indisponivel";
        }
        return "Codigo: " + this.codigo
                + " | Nome: " + this.nome
                + " | Marca: " + this.marca.getNomeFantasia()
                + " | Caracteristicas: " + this.caracteristicas
                + " | Status: " + situacao;
    }
}
