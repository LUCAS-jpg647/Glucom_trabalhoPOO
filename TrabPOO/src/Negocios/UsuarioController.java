package Negocios;

import java.util.ArrayList;

import Entidade.TipoUsuario;
import Entidade.Usuario;
import Dados.UsuarioRepository;

public class UsuarioController {
    private UsuarioRepository repository;

    public UsuarioController() {
        this.repository = new UsuarioRepository();
    }

    public Usuario cadastrarUsuario(String nome, String nomeUsuario, String senha, TipoUsuario tipo) {
        if (nome == null || nome.isEmpty()) {
            return null;
        }
        if (nomeUsuario == null || nomeUsuario.isEmpty()) {
            return null;
        }
        if (senha == null || senha.isEmpty()) {
            return null;
        }
        if (tipo == null) {
            return null;
        }
        return this.repository.salvar(nome, nomeUsuario, senha, tipo);
    }

    public ArrayList<Usuario> listarUsuarios() {
        return this.repository.listar();
    }

    public Usuario buscarUsuarioPorId(int id) {
        return this.repository.buscarPorId(id);
    }

    public boolean atualizarUsuario(int id, String nome, String nomeUsuario, String senha) {
        Usuario u = this.repository.buscarPorId(id);
        if (u == null) {
            return false;
        }
        if (nome == null || nome.isEmpty()) {
            return false;
        }
        if (nomeUsuario == null || nomeUsuario.isEmpty()) {
            return false;
        }
        if (senha == null || senha.isEmpty()) {
            return false;
        }
        u.setNome(nome);
        u.setNomeUsuario(nomeUsuario);
        u.setSenha(senha);
        return true;
    }

    public boolean removerUsuario(int id) {
        return this.repository.remover(id);
    }
}