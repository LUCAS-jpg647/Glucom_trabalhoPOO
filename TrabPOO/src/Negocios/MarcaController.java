package Negocios;

import java.util.ArrayList;

import Entidade.Marca;
import Dados.MarcaRepository;

public class MarcaController {
    private MarcaRepository repository;

    public MarcaController() {
        this.repository = new MarcaRepository();
    }

    public Marca cadastrarMarca(String nomeFantasia, String fabricante, String cnpj) {
        if (nomeFantasia == null || nomeFantasia.isEmpty()) {
            return null;
        }
        if (fabricante == null || fabricante.isEmpty()) {
            return null;
        }
        if (cnpj == null || cnpj.isEmpty()) {
            return null;
        }
        return this.repository.salvar(nomeFantasia, fabricante, cnpj);
    }

    public ArrayList<Marca> listarMarcas() {
        return this.repository.listar();
    }

    public Marca buscarMarcaPorCodigo(int codigo) {
        return this.repository.buscarPorCodigo(codigo);
    }

    public boolean removerMarca(int codigo) {
        return this.repository.remover(codigo);
    }
}