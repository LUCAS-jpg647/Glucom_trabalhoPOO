package UI;
//Login do Tecnico:Cleitom senha:123;
//Login do CLiente:Maria senha:123;
import java.util.ArrayList;
import java.util.Scanner;

import Entidade.Ferramenta;
import Entidade.OrdemServico;
import Entidade.Prioridade;
import Entidade.TipoUsuario;
import Entidade.Usuario;
import Negocios.FerramentaController;
import Negocios.OrdemServicoController;
import Negocios.UsuarioController;

public class Main {

    static Scanner scn = new Scanner(System.in);
    static FerramentaController ferramentaController = new FerramentaController();
    static UsuarioController usuarioController = new UsuarioController();
    static OrdemServicoController ordemServicoController = new OrdemServicoController();

    public static void main(String[] args) {
        inicializarUsuarios();
        inicializarFerramentas();

        boolean continuarSistema = true;
        while (continuarSistema) {
            Usuario usuarioLogado = telaLogin();
            if (usuarioLogado == null) {
                System.out.println("Encerrando o sistema...");
                continuarSistema = false;
            } else if (usuarioLogado.getTipo() == TipoUsuario.TECNICO) {
                menuTecnico(usuarioLogado);
            } else {
                menuCliente(usuarioLogado);
            }
        }
    }

    public static void inicializarUsuarios() {
        usuarioController.cadastrarUsuario("Cleitão dos Elevador", "Cleitom", "123", TipoUsuario.TECNICO);
        usuarioController.cadastrarUsuario("Maria", "Maria", "123", TipoUsuario.CLIENTE);
    }

    public static void inicializarFerramentas() {
        ferramentaController.cadastrarFerramenta("Bosch", "FuradeiraEletrica", "Furadeira de impacto 650W");
        ferramentaController.cadastrarFerramenta("Makita", "ParafusadeiraEletrica", "Parafusadeira sem fio 12V");
        ferramentaController.cadastrarFerramenta("Tramontina", "AlicateUniversal", "Alicate isolado 1000V");
        ferramentaController.cadastrarFerramenta("Vonder", "MarteloUnha", "Martelo unha 27mm cabo fibra");
        ferramentaController.cadastrarFerramenta("Stanley", "TrenaMetalica", "Trena 5 metros com trava");
    }

    // ==================== LOGIN / CRIACAO DE CONTA ====================

    public static Usuario telaLogin() {
        System.out.println();
        System.out.println("=== Tela de Login ===");
        System.out.println("1 - Entrar");
        System.out.println("2 - Criar Conta");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opcao: ");
        int opcao = scn.nextInt();

        if (opcao == 0) {
            return null;
        } else if (opcao == 1) {
            return fazerLogin();
        } else if (opcao == 2) {
            criarConta();
            return telaLogin();
        } else {
            System.out.println("Opcao invalida!");
            return telaLogin();
        }
    }

    public static Usuario fazerLogin() {
        System.out.print("Login: ");
        String login = scn.next();
        System.out.print("Senha: ");
        String senha = scn.next();

        Usuario usuario = usuarioController.autenticar(login, senha);
        if (usuario == null) {
            System.out.println("Login ou senha invalidos!");
            return telaLogin();
        }

        System.out.println("Login realizado com sucesso! Bem-vindo, " + usuario.getNome() + ".");
        return usuario;
    }

    public static void criarConta() {
        System.out.println();
        System.out.println("=== Criar Nova Conta ===");
        System.out.print("Nome completo (sem espaco): ");
        String nome = scn.next();
        System.out.print("Nome de usuario (sem espaco): ");
        String nomeUsuario = scn.next();
        System.out.print("Senha (sem espaco): ");
        String senha = scn.next();

        System.out.println("Tipo de usuario:");
        System.out.println("1 - Tecnico");
        System.out.println("2 - Cliente");
        System.out.print("Escolha: ");
        int opcaoTipo = scn.nextInt();

        TipoUsuario tipo;
        switch (opcaoTipo) {
            case 1 -> tipo = TipoUsuario.TECNICO;
            case 2 -> tipo = TipoUsuario.CLIENTE;
            default -> tipo = null;
        }

        if (tipo == null) {
            System.out.println("Opcao de tipo invalida!");
            return;
        }

        Usuario u = usuarioController.cadastrarUsuario(nome, nomeUsuario, senha, tipo);
        if (u != null) {
            System.out.println("Conta criada com sucesso! Voce ja pode fazer login.");
        } else {
            System.out.println("Erro ao criar conta. Verifique os dados ou se o nome de usuario ja esta em uso.");
        }
    }

    // ==================== MENU TECNICO ====================

    public static void menuTecnico(Usuario tecnico) {
        int opcao;
        do {
            System.out.println();
            System.out.println("=== Menu Tecnico (" + tecnico.getNome() + ") ===");
            System.out.println("1 - Cadastrar Ferramenta");
            System.out.println("2 - Listar Ferramentas");
            System.out.println("3 - Atualizar Ferramenta");
            System.out.println("4 - Remover Ferramenta");
            System.out.println("5 - Ver Ordens de Servico Disponiveis");
            System.out.println("6 - Aceitar Ordem de Servico");
            System.out.println("7 - Ver Minhas Ordens em Andamento");
            System.out.println("8 - Concluir Ordem de Servico");
            System.out.println("0 - Logout");
            System.out.print("Escolha uma opcao: ");

            opcao = scn.nextInt();

            switch (opcao) {
                case 1 -> cadastrarFerramenta();
                case 2 -> listarFerramentas();
                case 3 -> atualizarFerramenta();
                case 4 -> removerFerramenta();
                case 5 -> listarOrdensDisponiveis();
                case 6 -> aceitarOrdem(tecnico);
                case 7 -> listarOrdensDoTecnico(tecnico);
                case 8 -> concluirOrdem(tecnico);
                case 0 -> System.out.println("Saindo do menu tecnico...");
                default -> System.out.println("Opcao invalida!");
            }
        } while (opcao != 0);
    }

    // ---------- Ferramenta ----------

    public static void cadastrarFerramenta() {
        System.out.println("--- Cadastro de Ferramenta ---");

        System.out.print("Nome da marca (sem espaco): ");
        String marca = scn.next();
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
        ArrayList<Ferramenta> ferramentas = ferramentaController.listarFerramentas();
        if (ferramentas.isEmpty()) {
            System.out.println("Nenhuma ferramenta cadastrada.");
        } else {
            for (int i = 0; i < ferramentas.size(); i++) {
                System.out.println(ferramentas.get(i).toString());
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

    // ---------- Ordem de Servico (Tecnico) ----------

    public static void listarOrdensDisponiveis() {
        System.out.println("--- Ordens de Servico Disponiveis ---");
        ArrayList<OrdemServico> ordens = ordemServicoController.listarOrdensAbertas();
        if (ordens.isEmpty()) {
            System.out.println("Nenhuma ordem disponivel no momento.");
        } else {
            for (int i = 0; i < ordens.size(); i++) {
                System.out.println(ordens.get(i).toString());
            }
        }
    }

    public static void aceitarOrdem(Usuario tecnico) {
        listarOrdensDisponiveis();
        if (ordemServicoController.listarOrdensAbertas().isEmpty()) {
            return;
        }

        System.out.print("Digite o codigo da ordem que deseja aceitar: ");
        int codigo = scn.nextInt();

        boolean ok = ordemServicoController.aceitarOrdem(codigo, tecnico);
        if (!ok) {
            System.out.println("Nao foi possivel aceitar essa ordem. Verifique o codigo e o status dela.");
            return;
        }

        System.out.println("Ordem aceita com sucesso! Ela agora esta em andamento.");
        ArrayList<Ferramenta> ferramentasRetiradas = retirarFerramentas();
        ordemServicoController.registrarFerramentasUtilizadas(codigo, tecnico, ferramentasRetiradas);
    }

    // ---------- Ferramentas (retirada) ----------

    public static ArrayList<Ferramenta> retirarFerramentas() {
        ArrayList<Ferramenta> ferramentasRetiradas = new ArrayList<Ferramenta>();

        boolean retirandoFerramentas = true;
        while (retirandoFerramentas) {
            System.out.println("--- Ferramentas Disponiveis ---");
            ArrayList<Ferramenta> ferramentas = ferramentaController.listarFerramentas();
            boolean existeDisponivel = false;
            for (int i = 0; i < ferramentas.size(); i++) {
                if (ferramentas.get(i).isStatus()) {
                    System.out.println(ferramentas.get(i).toString());
                    existeDisponivel = true;
                }
            }
            if (!existeDisponivel) {
                System.out.println("Nenhuma ferramenta disponivel no momento.");
                retirandoFerramentas = false;
                continue;
            }

            System.out.print("Digite o codigo da ferramenta para levar (0 para parar de levar ferramentas): ");
            int codigoFerramenta = scn.nextInt();

            if (codigoFerramenta == 0) {
                retirandoFerramentas = false;
            } else {
                Ferramenta f = ferramentaController.buscarFerramentaPorCodigo(codigoFerramenta);
                if (f == null || !f.isStatus()) {
                    System.out.println("Ferramenta invalida ou indisponivel.");
                } else {
                    ferramentaController.alterarStatus(codigoFerramenta, false);
                    ferramentasRetiradas.add(f);
                    System.out.println("Ferramenta '" + f.getNome() + "' levada.");
                }
            }
        }

        return ferramentasRetiradas;
    }

    public static void listarOrdensDoTecnico(Usuario tecnico) {
        System.out.println("--- Minhas Ordens em Andamento ---");
        ArrayList<OrdemServico> ordens = ordemServicoController.listarOrdensEmAndamentoDoTecnico(tecnico);
        if (ordens.isEmpty()) {
            System.out.println("Voce nao possui ordens em andamento.");
        } else {
            for (int i = 0; i < ordens.size(); i++) {
                System.out.println(ordens.get(i).toString());
            }
        }
    }

    public static void concluirOrdem(Usuario tecnico) {
        listarOrdensDoTecnico(tecnico);
        ArrayList<OrdemServico> minhasOrdens = ordemServicoController.listarOrdensEmAndamentoDoTecnico(tecnico);
        if (minhasOrdens.isEmpty()) {
            return;
        }

        System.out.print("Digite o codigo da ordem que deseja concluir: ");
        int codigoOrdem = scn.nextInt();

        OrdemServico ordem = ordemServicoController.buscarOrdemPorCodigo(codigoOrdem);
        if (ordem == null || ordem.getTecnico() == null || ordem.getTecnico().getId() != tecnico.getId()) {
            System.out.println("Ordem invalida ou nao pertence a voce.");
            return;
        }

        System.out.println("Executando o servico...");

        ArrayList<Ferramenta> ferramentasUtilizadas = ordem.getFerramentasUtilizadas();
        for (int i = 0; i < ferramentasUtilizadas.size(); i++) {
            Ferramenta f = ferramentasUtilizadas.get(i);
            ferramentaController.alterarStatus(f.getCodigo(), true);
            System.out.println("Ferramenta '" + f.getNome() + "' devolvida.");
        }

        boolean ok = ordemServicoController.concluirOrdem(codigoOrdem, tecnico);
        if (ok) {
            System.out.println("Ordem de servico concluida com sucesso!");
        } else {
            System.out.println("Erro ao concluir a ordem de servico.");
        }
    }

    // ==================== MENU CLIENTE ====================

    public static void menuCliente(Usuario cliente) {
        int opcao;
        do {
            System.out.println();
            System.out.println("=== Menu Cliente (" + cliente.getNome() + ") ===");
            System.out.println("1 - Solicitar Ordem de Servico");
            System.out.println("2 - Ver Minhas Ordens de Servico");
            System.out.println("0 - Logout");
            System.out.print("Escolha uma opcao: ");

            opcao = scn.nextInt();

            switch (opcao) {
                case 1 -> solicitarOrdem(cliente);
                case 2 -> listarOrdensDoCliente(cliente);
                case 0 -> System.out.println("Saindo do menu cliente...");
                default -> System.out.println("Opcao invalida!");
            }
        } while (opcao != 0);
    }

    public static void solicitarOrdem(Usuario cliente) {
        System.out.println("--- Solicitar Ordem de Servico ---");
        System.out.print("Descreva o servico desejado (sem espaco): ");
        String descricao = scn.next();

        System.out.println("Prioridade:");
        System.out.println("1 - Baixa");
        System.out.println("2 - Media");
        System.out.println("3 - Alta");
        System.out.println("4 - Urgente");
        System.out.print("Escolha: ");
        int opcaoPrioridade = scn.nextInt();

        Prioridade prioridade;
        switch (opcaoPrioridade) {
            case 1 -> prioridade = Prioridade.BAIXA;
            case 2 -> prioridade = Prioridade.MEDIA;
            case 3 -> prioridade = Prioridade.ALTA;
            case 4 -> prioridade = Prioridade.URGENTE;
            default -> prioridade = null;
        }

        if (prioridade == null) {
            System.out.println("Opcao de prioridade invalida!");
            return;
        }

        OrdemServico ordem = ordemServicoController.solicitarOrdem(cliente, descricao, prioridade);
        if (ordem != null) {
            System.out.println("Ordem de servico criada com sucesso! Codigo: " + ordem.getCodigo());
        } else {
            System.out.println("Erro ao criar ordem de servico.");
        }
    }

    public static void listarOrdensDoCliente(Usuario cliente) {
        System.out.println("--- Minhas Ordens de Servico ---");
        ArrayList<OrdemServico> ordens = ordemServicoController.listarOrdensDoCliente(cliente);
        if (ordens.isEmpty()) {
            System.out.println("Voce ainda nao solicitou nenhuma ordem de servico.");
        } else {
            for (int i = 0; i < ordens.size(); i++) {
                System.out.println(ordens.get(i).toString());
            }
        }
    }
}