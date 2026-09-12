package UI;

import java.util.Scanner;

import Entidade.Ferramenta;
import Entidade.Marca;
import Entidade.TipoUsuario;
import Entidade.Usuario;
import Negocios.FerramentaController;
import Negocios.MarcaController;
import Negocios.UsuarioController;

public class Main {

    static Scanner scn = new Scanner(System.in);
    static MarcaController marcaController = new MarcaController();
    static FerramentaController ferramentaController = new FerramentaController();
    static UsuarioController usuarioController = new UsuarioController();

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.println("Tela de login");
            System.out.println("1 - Cadastrar Marca");
            System.out.println("2 - Cadastrar Ferramenta");
            System.out.println("3 - Listar Ferramentas");
            System.out.println("4 - Atualizar Ferramenta");
            System.out.println("5 - Remover Ferramenta");
            System.out.println("6 - Cadastrar Usuario (Tecnico ou Cliente)");
            System.out.println("7 - Listar Usuarios");
            System.out.println("8 - Atualizar Usuario");
            System.out.println("9 - Remover Usuario");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opcao: ");

            opcao = scn.nextInt();

            switch (opcao) {
                case 1 -> cadastrarMarca();
                case 2 -> cadastrarFerramenta();
                case 3 -> listarFerramentas();
                case 4 -> atualizarFerramenta();
                case 5 -> removerFerramenta();
                case 6 -> cadastrarUsuario();
                case 7 -> listarUsuarios();
                case 8 -> atualizarUsuario();
                case 9 -> removerUsuario();
                case 0 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opcao invalida!");
            }
        } while (opcao != 0);
    }

    public static void cadastrarMarca() {
        System.out.println("--- Cadastro de Marca ---");
        System.out.print("Nome fantasia : ");
        String nomeFantasia = scn.next();
        System.out.print("Fabricante : ");
        String fabricante = scn.next();
        System.out.print("CNPJ: ");
        String cnpj = scn.next();

        Marca marca = marcaController.cadastrarMarca(nomeFantasia, fabricante, cnpj);
        if (marca != null) {
            System.out.println("Marca cadastrada com sucesso! Codigo: " + marca.getCodigo());
        } else {
            System.out.println("Erro ao cadastrar marca. Verifique os dados.");
        }
    }

    public static void cadastrarFerramenta() {
        System.out.println("--- Cadastro de Ferramenta ---");

        if (marcaController.listarMarcas().isEmpty()) {
            System.out.println("Nenhuma marca cadastrada. Cadastre uma marca primeiro (opcao 1).");
            return;
        }

        System.out.println("Marcas cadastradas:");
        for (int i = 0; i < marcaController.listarMarcas().size(); i++) {
            System.out.println(marcaController.listarMarcas().get(i).getCodigo() + " - " + marcaController.listarMarcas().get(i).getNomeFantasia());
        }

        System.out.print("Digite o codigo da marca: ");
        int codMarca = scn.nextInt();

        Marca marca = marcaController.buscarMarcaPorCodigo(codMarca);
        if (marca == null) {
            System.out.println("Marca nao encontrada!");
            return;
        }

        System.out.print("Nome da ferramenta (sem espaco): ");
        String nome = scn.next();
        System.out.print("Caracteristicas (sem espaco): ");
        String caracteristicas = scn.next();

        Ferramenta f = ferramentaController.cadastrarFerramenta(marca, nome, caracteristicas);
        if (f != null) {
            System.out.println("Ferramenta cadastrada com sucesso! Codigo: " + f.getCodigo());
        } else {
            System.out.println("Erro ao cadastrar ferramenta.");
        }
    }

    public static void listarFerramentas() {
        System.out.println("--- Lista de Ferramentas ---");
        if (ferramentaController.listarFerramentas().isEmpty()) {
            System.out.println("Nenhuma ferramenta cadastrada.");
        } else {
            for (int i = 0; i < ferramentaController.listarFerramentas().size(); i++) {
                System.out.println(ferramentaController.listarFerramentas().get(i).toString());
            }
        }
    }

    public static void atualizarFerramenta() {
        System.out.println("--- Atualizar Ferramenta ---");
        System.out.print("Digite o codigo da ferramenta: ");
        int codigo = scn.nextInt();

        Ferramenta f = ferramentaController.buscarFerramentaPorCodigo(codigo);
        if (f == null) {
            System.out.println("Ferramenta nao encontrada!");
            return;
        }

        System.out.print("Novo nome (sem espaco) (" + f.getNome() + "): ");
        String nome = scn.next();
        System.out.print("Novas caracteristicas (sem espaco) (" + f.getCaracteristicas() + "): ");
        String caracteristicas = scn.next();
        System.out.print("Disponivel? (s/n): ");
        String resp = scn.next();
        boolean status = resp.equalsIgnoreCase("s");

        boolean ok = ferramentaController.atualizarFerramenta(codigo, nome, caracteristicas, status);
        if (ok) {
            System.out.println("Ferramenta atualizada com sucesso!");
        } else {
            System.out.println("Erro ao atualizar ferramenta.");
        }
    }

    public static void removerFerramenta() {
        System.out.println("--- Remover Ferramenta ---");
        System.out.print("Digite o codigo da ferramenta: ");
        int codigo = scn.nextInt();

        boolean ok = ferramentaController.removerFerramenta(codigo);
        if (ok) {
            System.out.println("Ferramenta removida com sucesso!");
        } else {
            System.out.println("Ferramenta nao encontrada!");
        }
    }

    public static void cadastrarUsuario() {
        System.out.println("--- Cadastro de Usuario ---");
        System.out.print("Nome (sem espaco): ");
        String nome = scn.next();
        System.out.print("Nome de usuario: ");
        String nomeUsuario = scn.next();
        System.out.print("Senha: ");
        String senha = scn.next();

        System.out.println("Tipo de usuario:");
        System.out.println("1 - Tecnico");
        System.out.println("2 - Cliente");
        System.out.print("Escolha: ");
        int opcaoTipo = scn.nextInt();

        TipoUsuario tipo;
        if (opcaoTipo == 1) {
            tipo = TipoUsuario.TECNICO;
        } else if (opcaoTipo == 2) {
            tipo = TipoUsuario.CLIENTE;
        } else {
            System.out.println("Opcao invalida!");
            return;
        }

        Usuario u = usuarioController.cadastrarUsuario(nome, nomeUsuario, senha, tipo);
        if (u != null) {
            System.out.println("Usuario cadastrado com sucesso! Id: " + u.getId());
        } else {
            System.out.println("Erro ao cadastrar usuario.");
        }
    }

    public static void listarUsuarios() {
        System.out.println("--- Lista de Usuarios ---");
        if (usuarioController.listarUsuarios().isEmpty()) {
            System.out.println("Nenhum usuario cadastrado.");
        } else {
            for (int i = 0; i < usuarioController.listarUsuarios().size(); i++) {
                System.out.println(usuarioController.listarUsuarios().get(i).toString());
            }
        }
    }

    public static void atualizarUsuario() {
        System.out.println("--- Atualizar Usuario ---");
        System.out.print("Digite o id do usuario: ");
        int id = scn.nextInt();

        Usuario u = usuarioController.buscarUsuarioPorId(id);
        if (u == null) {
            System.out.println("Usuario nao encontrado!");
            return;
        }

        System.out.print("Novo nome (sem espaco) (" + u.getNome() + "): ");
        String nome = scn.next();
        System.out.print("Novo nome de usuario (" + u.getNomeUsuario() + "): ");
        String nomeUsuario = scn.next();
        System.out.print("Nova senha: ");
        String senha = scn.next();

        boolean ok = usuarioController.atualizarUsuario(id, nome, nomeUsuario, senha);
        if (ok) {
            System.out.println("Usuario atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar usuario.");
        }
    }

    public static void removerUsuario() {
        System.out.println("--- Remover Usuario ---");
        System.out.print("Digite o id do usuario: ");
        int id = scn.nextInt();

        boolean ok = usuarioController.removerUsuario(id);
        if (ok) {
            System.out.println("Usuario removido com sucesso!");
        } else {
            System.out.println("Usuario nao encontrado!");
        }
    }
}