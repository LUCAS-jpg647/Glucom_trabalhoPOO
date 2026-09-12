package Dados;

import Entidade.*;

import java.util.ArrayList;

public class MarcaRepository {
    private ArrayList<Marca> marcas;
    private int proximoCodigo;

    public MarcaRepository() {
        this.marcas = new ArrayList<Marca>();
        this.proximoCodigo = 1;
    }

    public Marca salvar(String nomeFantasia, String fabricante, String cnpj) {
        Marca marca = new Marca(this.proximoCodigo, nomeFantasia, fabricante, cnpj);
        this.marcas.add(marca);
        this.proximoCodigo = this.proximoCodigo + 1;
        return marca;
    }

    public ArrayList<Marca> listar() {
        return new ArrayList<Marca>(this.marcas);
    }

    public Marca buscarPorCodigo(int codigo) {
        for (int i = 0; i < this.marcas.size(); i++) {
            Marca m = this.marcas.get(i);
            if (m.getCodigo() == codigo) {
                return m;
            }
        }
        return null;
    }

    public boolean remover(int codigo) {
        Marca m = this.buscarPorCodigo(codigo);
        if (m != null) {
            this.marcas.remove(m);
            return true;
        }
        return false;
    }
}