package Dados;

import Entidade.*;

import java.util.ArrayList;

public class UsuarioRepository {
    private ArrayList<Usuario> usuarios;
    private int proximoId;

    public UsuarioRepository() {
        this.usuarios = new ArrayList<Usuario>();
        this.proximoId = 1;
    }

    public Usuario salvar(String nome, String nomeUsuario, String senha, TipoUsuario tipo) {
        Usuario u = new Usuario(this.proximoId, nome, nomeUsuario, senha, tipo);
        this.usuarios.add(u);
        this.proximoId = this.proximoId + 1;
        return u;
    }

    public ArrayList<Usuario> listar() {
        return new ArrayList<Usuario>(this.usuarios);
    }

    public Usuario buscarPorId(int id) {
        for (int i = 0; i < this.usuarios.size(); i++) {
            Usuario u = this.usuarios.get(i);
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    public boolean remover(int id) {
        Usuario u = this.buscarPorId(id);
        if (u != null) {
            this.usuarios.remove(u);
            return true;
        }
        return false;
    }

    public Usuario buscarPorLoginSenha(String nomeUsuario, String senha) {
        for (int i = 0; i < this.usuarios.size(); i++) {
            Usuario u = this.usuarios.get(i);
            if (u.getNomeUsuario().equals(nomeUsuario) && u.getSenha().equals(senha)) {
                return u;
            }
        }
        return null;
    }
}