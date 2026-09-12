package Dados;

import Entidade.*;

import java.util.ArrayList;

public class OrdemServicoRepository {
    private ArrayList<OrdemServico> ordens;
    private int proximoCodigo;

    public OrdemServicoRepository() {
        this.ordens = new ArrayList<OrdemServico>();
        this.proximoCodigo = 1;
    }

    public OrdemServico salvar(Usuario cliente, String descricao, Prioridade prioridade) {
        OrdemServico o = new OrdemServico(this.proximoCodigo, cliente, descricao, prioridade);
        this.ordens.add(o);
        this.proximoCodigo = this.proximoCodigo + 1;
        return o;
    }

    public ArrayList<OrdemServico> listar() {
        return new ArrayList<OrdemServico>(this.ordens);
    }

    public OrdemServico buscarPorCodigo(int codigo) {
        for (int i = 0; i < this.ordens.size(); i++) {
            OrdemServico o = this.ordens.get(i);
            if (o.getCodigo() == codigo) {
                return o;
            }
        }
        return null;
    }

    public ArrayList<OrdemServico> listarPorStatus(StatusOrdem status) {
        ArrayList<OrdemServico> resultado = new ArrayList<OrdemServico>();
        for (int i = 0; i < this.ordens.size(); i++) {
            OrdemServico o = this.ordens.get(i);
            if (o.getStatus() == status) {
                resultado.add(o);
            }
        }
        return resultado;
    }

    public ArrayList<OrdemServico> listarPorTecnico(int idTecnico, StatusOrdem status) {
        ArrayList<OrdemServico> resultado = new ArrayList<OrdemServico>();
        for (int i = 0; i < this.ordens.size(); i++) {
            OrdemServico o = this.ordens.get(i);
            if (o.getTecnico() != null && o.getTecnico().getId() == idTecnico && o.getStatus() == status) {
                resultado.add(o);
            }
        }
        return resultado;
    }

    public ArrayList<OrdemServico> listarPorCliente(int idCliente) {
        ArrayList<OrdemServico> resultado = new ArrayList<OrdemServico>();
        for (int i = 0; i < this.ordens.size(); i++) {
            OrdemServico o = this.ordens.get(i);
            if (o.getCliente().getId() == idCliente) {
                resultado.add(o);
            }
        }
        return resultado;
    }
}