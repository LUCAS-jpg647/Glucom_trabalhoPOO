package Dados;

import Entidade.*;

import java.util.ArrayList;

public class FerramentaRepository {
    private ArrayList<Ferramenta> ferramentas;
    private int proximoCodigo;

    public FerramentaRepository() {
        this.ferramentas = new ArrayList<Ferramenta>();
        this.proximoCodigo = 1;
    }

    public Ferramenta salvar(Marca marca, String nome, String caracteristicas) {
        Ferramenta f = new Ferramenta(this.proximoCodigo, marca, nome, caracteristicas);
        this.ferramentas.add(f);
        this.proximoCodigo = this.proximoCodigo + 1;
        return f;
    }

    public ArrayList<Ferramenta> listar() {
        return new ArrayList<Ferramenta>(this.ferramentas);
    }

    public Ferramenta buscarPorCodigo(int codigo) {
        for (int i = 0; i < this.ferramentas.size(); i++) {
            Ferramenta f = this.ferramentas.get(i);
            if (f.getCodigo() == codigo) {
                return f;
            }
        }
        return null;
    }

    public boolean remover(int codigo) {
        Ferramenta f = this.buscarPorCodigo(codigo);
        if (f != null) {
            this.ferramentas.remove(f);
            return true;
        }
        return false;
    }
}