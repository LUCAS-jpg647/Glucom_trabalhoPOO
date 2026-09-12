package Entidade;

import java.util.ArrayList;

public class OrdemServico {
    private int codigo;
    private Usuario cliente;
    private Usuario tecnico;
    private String descricao;
    private Prioridade prioridade;
    private StatusOrdem status;
    private ArrayList<Ferramenta> ferramentasUtilizadas;

    public OrdemServico(int codigo, Usuario cliente, String descricao, Prioridade prioridade) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.status = StatusOrdem.ABERTA;
        this.tecnico = null;
        this.ferramentasUtilizadas = new ArrayList<Ferramenta>();
    }

    public int getCodigo() {
        return this.codigo;
    }

    public Usuario getCliente() {
        return this.cliente;
    }

    public Usuario getTecnico() {
        return this.tecnico;
    }

    public void setTecnico(Usuario tecnico) {
        this.tecnico = tecnico;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Prioridade getPrioridade() {
        return this.prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public StatusOrdem getStatus() {
        return this.status;
    }

    public void setStatus(StatusOrdem status) {
        this.status = status;
    }

    public ArrayList<Ferramenta> getFerramentasUtilizadas() {
        return this.ferramentasUtilizadas;
    }

    public void setFerramentasUtilizadas(ArrayList<Ferramenta> ferramentasUtilizadas) {
        this.ferramentasUtilizadas = ferramentasUtilizadas;
    }

    public String toString() {
        String nomeTecnico = "Nenhum";
        if (this.tecnico != null) {
            nomeTecnico = this.tecnico.getNome();
        }
        return "Codigo: " + this.codigo
                + " | Cliente: " + this.cliente.getNome()
                + " | Descricao: " + this.descricao
                + " | Prioridade: " + this.prioridade
                + " | Status: " + this.status
                + " | Tecnico: " + nomeTecnico;
    }
}