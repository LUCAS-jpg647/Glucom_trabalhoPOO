package TrabPOO;

import java.util.ArrayList;

public class Sistema {
    private static Sistema instancia;

    private ArrayList<Marca> marcas;
    private ArrayList<Ferramenta> ferramentas;
    private ArrayList<Usuario> usuarios;

    // Construtor privado: ninguem de fora consegue dar "new Sistema()".
    private Sistema() {
        this.marcas = new ArrayList<Marca>();
        this.ferramentas = new ArrayList<Ferramenta>();
        this.usuarios = new ArrayList<Usuario>();
    }

    // Unico ponto de acesso a instancia do Sistema.
    public static Sistema getInstancia() {
        if (instancia == null) {
            instancia = new Sistema();
        }
        return instancia;
    }

    // ---------- CRUD MARCA ----------

    public Marca cadastrarMarca(String nomeFantasia, String fabricante, String cnpj) {
        Marca marca = Marca.criarMarca(nomeFantasia, fabricante, cnpj);
        if (marca != null) {
            this.marcas.add(marca);
        }
        return marca;
    }

    // Encapsulamento: devolve uma copia da lista, nao a lista original.
    // Assim quem chamar esse metodo nao consegue alterar a lista interna do Sistema.
    public ArrayList<Marca> listarMarcas() {
        return new ArrayList<Marca>(this.marcas);
    }

    public Marca buscarMarcaPorCodigo(int codigo) {
        for (int i = 0; i < this.marcas.size(); i++) {
            Marca m = this.marcas.get(i);
            if (m.getCodigo() == codigo) {
                return m;
            }
        }
        return null;
    }

    // ---------- CRUD FERRAMENTA ----------

    public Ferramenta cadastrarFerramenta(Marca marca, String nome, String caracteristicas) {
        Ferramenta f = Ferramenta.criarFerramenta(marca, nome, caracteristicas);
        if (f != null) {
            this.ferramentas.add(f);
        }
        return f;
    }

    public ArrayList<Ferramenta> listarFerramentas() {
        return new ArrayList<Ferramenta>(this.ferramentas);
    }

    public Ferramenta buscarFerramentaPorCodigo(int codigo) {
        for (int i = 0; i < this.ferramentas.size(); i++) {
            Ferramenta f = this.ferramentas.get(i);
            if (f.getCodigo() == codigo) {
                return f;
            }
        }
        return null;
    }

    public boolean atualizarFerramenta(int codigo, String nome, String caracteristicas, boolean status) {
        Ferramenta f = this.buscarFerramentaPorCodigo(codigo);
        if (f != null) {
            f.setNome(nome);
            f.setCaracteristicas(caracteristicas);
            f.setStatus(status);
            return true;
        } else {
            return false;
        }
    }

    public boolean removerFerramenta(int codigo) {
        Ferramenta f = this.buscarFerramentaPorCodigo(codigo);
        if (f != null) {
            this.ferramentas.remove(f);
            return true;
        } else {
            return false;
        }
    }

    // ---------- CRUD USUARIO (TECNICO E CLIENTE) ----------

    public Usuario cadastrarUsuario(String nome, String nomeUsuario, String senha, TipoUsuario tipo) {
        Usuario u = Usuario.criarUsuario(nome, nomeUsuario, senha, tipo);
        if (u != null) {
            this.usuarios.add(u);
        }
        return u;
    }

    public ArrayList<Usuario> listarUsuarios() {
        return new ArrayList<Usuario>(this.usuarios);
    }

    public Usuario buscarUsuarioPorId(int id) {
        for (int i = 0; i < this.usuarios.size(); i++) {
            Usuario u = this.usuarios.get(i);
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    public boolean atualizarUsuario(int id, String nome, String nomeUsuario, String senha) {
        Usuario u = this.buscarUsuarioPorId(id);
        if (u != null) {
            u.setNome(nome);
            u.setNomeUsuario(nomeUsuario);
            u.setSenha(senha);
            return true;
        } else {
            return false;
        }
    }

    public boolean removerUsuario(int id) {
        Usuario u = this.buscarUsuarioPorId(id);
        if (u != null) {
            this.usuarios.remove(u);
            return true;
        } else {
            return false;
        }
    }
}