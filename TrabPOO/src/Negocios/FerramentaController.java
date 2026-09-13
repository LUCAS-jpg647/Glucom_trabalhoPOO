package Negocios;

import java.util.ArrayList;

import Entidade.Ferramenta;
import Dados.FerramentaRepository;

public class FerramentaController {
    private FerramentaRepository repository;

    public FerramentaController() {
        this.repository = new FerramentaRepository();
    }

    public Ferramenta cadastrarFerramenta(String marca, String nome, String caracteristicas) {
        if (marca == null || marca.isEmpty()) {
            return null;
        }
        if (nome == null || nome.isEmpty()) {
            return null;
        }
        if (caracteristicas == null || caracteristicas.isEmpty()) {
            return null;
        }
        return this.repository.salvar(marca, nome, caracteristicas);
    }

    public ArrayList<Ferramenta> listarFerramentas() {
        return this.repository.listar();
    }

    public Ferramenta buscarFerramentaPorCodigo(int codigo) {
        return this.repository.buscarPorCodigo(codigo);
    }

    public boolean atualizarFerramenta(int codigo, String nome, String caracteristicas, boolean status) {
        Ferramenta f = this.repository.buscarPorCodigo(codigo);
        if (f == null) {
            return false;
        }
        if (nome == null || nome.isEmpty()) {
            return false;
        }
        if (caracteristicas == null || caracteristicas.isEmpty()) {
            return false;
        }
        f.setNome(nome);
        f.setCaracteristicas(caracteristicas);
        f.setStatus(status);
        return true;
    }

    public boolean removerFerramenta(int codigo) {
        return this.repository.remover(codigo);
    }

    public boolean alterarStatus(int codigo, boolean status) {
        Ferramenta f = this.repository.buscarPorCodigo(codigo);
        if (f == null) {
            return false;
        }
        f.setStatus(status);
        return true;
    }
}