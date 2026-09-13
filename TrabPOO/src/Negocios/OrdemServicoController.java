package Negocios;

import java.util.ArrayList;

import Entidade.Ferramenta;
import Entidade.OrdemServico;
import Entidade.Prioridade;
import Entidade.StatusOrdem;
import Entidade.Usuario;
import Dados.OrdemServicoRepository;

public class OrdemServicoController {
    private OrdemServicoRepository repository;

    public OrdemServicoController() {
        this.repository = new OrdemServicoRepository();
    }

    public OrdemServico solicitarOrdem(Usuario cliente, String descricao, Prioridade prioridade) {
        if (cliente == null) {
            return null;
        }
        if (descricao == null || descricao.isEmpty()) {
            return null;
        }
        if (prioridade == null) {
            return null;
        }
        return this.repository.salvar(cliente, descricao, prioridade);
    }

    public ArrayList<OrdemServico> listarOrdensAbertas() {
        return this.repository.listarPorStatus(StatusOrdem.ABERTA);
    }

    public ArrayList<OrdemServico> listarOrdensEmAndamentoDoTecnico(Usuario tecnico) {
        if (tecnico == null) {
            return new ArrayList<OrdemServico>();
        }
        return this.repository.listarPorTecnico(tecnico.getId(), StatusOrdem.EM_ANDAMENTO);
    }

    public ArrayList<OrdemServico> listarOrdensDoCliente(Usuario cliente) {
        if (cliente == null) {
            return new ArrayList<OrdemServico>();
        }
        return this.repository.listarPorCliente(cliente.getId());
    }

    public OrdemServico buscarOrdemPorCodigo(int codigo) {
        return this.repository.buscarPorCodigo(codigo);
    }

    public boolean aceitarOrdem(int codigo, Usuario tecnico) {
        if (tecnico == null) {
            return false;
        }
        OrdemServico o = this.repository.buscarPorCodigo(codigo);
        if (o == null) {
            return false;
        }
        if (o.getStatus() != StatusOrdem.ABERTA) {
            return false;
        }
        o.setTecnico(tecnico);
        o.setStatus(StatusOrdem.EM_ANDAMENTO);
        return true;
    }

    public boolean registrarFerramentasUtilizadas(int codigo, Usuario tecnico, ArrayList<Ferramenta> ferramentasUtilizadas) {
        if (tecnico == null) {
            return false;
        }
        OrdemServico o = this.repository.buscarPorCodigo(codigo);
        if (o == null) {
            return false;
        }
        if (o.getTecnico() == null || o.getTecnico().getId() != tecnico.getId()) {
            return false;
        }
        o.setFerramentasUtilizadas(ferramentasUtilizadas);
        return true;
    }

    public boolean concluirOrdem(int codigo, Usuario tecnico) {
        if (tecnico == null) {
            return false;
        }
        OrdemServico o = this.repository.buscarPorCodigo(codigo);
        if (o == null) {
            return false;
        }
        if (o.getStatus() != StatusOrdem.EM_ANDAMENTO) {
            return false;
        }
        if (o.getTecnico() == null || o.getTecnico().getId() != tecnico.getId()) {
            return false;
        }
        o.setStatus(StatusOrdem.CONCLUIDA);
        return true;
    }
}