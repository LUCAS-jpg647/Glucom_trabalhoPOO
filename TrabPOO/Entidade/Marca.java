package TrabPOO.Dados;

public class Marca {
    private int codigo;
    private String nomeFantasia;
    private String fabricante;
    private String cnpj;

    public Marca(int codigo, String nomeFantasia, String fabricante, String cnpj) {
        this.codigo = codigo;
        this.nomeFantasia = nomeFantasia;
        this.fabricante = fabricante;
        this.cnpj = cnpj;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public String getNomeFantasia() {
        return this.nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getFabricante() {
        return this.fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String toString() {
        return "Codigo: " + this.codigo
                + " | Nome fantasia: " + this.nomeFantasia
                + " | Fabricante: " + this.fabricante
                + " | CNPJ: " + this.cnpj;
    }
}
