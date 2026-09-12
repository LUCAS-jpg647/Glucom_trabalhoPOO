package UI;

import java.util.ArrayList;
import java.util.Scanner;

import Entidade.Ferramenta;
import Entidade.Marca;
import Entidade.OrdemServico;
import Entidade.Prioridade;
import Entidade.TipoUsuario;
import Entidade.Usuario;
import Negocios.FerramentaController;
import Negocios.MarcaController;
import Negocios.OrdemServicoController;
import Negocios.UsuarioController;

public class Main {

    static Scanner scn = new Scanner(System.in);
    static MarcaController marcaController = new MarcaController();
    static FerramentaController ferramentaController = new FerramentaController();
    static UsuarioController usuarioController = new UsuarioController();
    static OrdemServicoController ordemServicoController = new OrdemServicoController();

    public static void main(String[] args) {
        inicializarUsuarios();

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
        usuarioController.cadastrarUsuario("Tecnico Padrao", "tecnico", "123", TipoUsuario.TECNICO);
        usuarioController.cadastrarUsuario("Cliente Padrao", "cliente", "123", TipoUsuario.CLIENTE);
    }

    public static Usuario telaLogin() {
        System.out.println();
        System.out.println("=== Tela de Login ===");
        System.out.print("Login (0 para sair): ");
        String login = scn.next();

        if (login.equals("0")) {
            return null;
        }

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

    // ==================== MENU TECNICO ====================

    public static void menuTecnico(Usuario tecnico) {
        int opcao;
        do {
            System.out.println();
            System.out.println("=== Menu Tecnico (" + tecnico.getNome() + ") ===");
            System.out.println("1  - Cadastrar Marca");
            System.out.println("2  - Listar Marcas");
            System.out.println("3  - Atualizar Marca");
            System.out.println("4  - Remover Marca");
            System.out.println("5  - Cadastrar Ferramenta");
            System.out.println("6  - Listar Ferramentas");
            System.out.println("7  - Atualizar Ferramenta");
            System.out.println("8  - Remover Ferramenta");
            System.out.println("9  - Ver Ordens de Servico Disponiveis");
            System.out.println("10 - Aceitar Ordem de Servico");
            System.out.println("11 - Ver Minhas Ordens em Andamento");
            System.out.println("12 - Concluir Ordem de Servico");
            System.out.println("0  - Logout");
            System.out.print("Escolha uma opcao: ");

            opcao = scn.nextInt();

            switch (opcao) {
                case 1 -> cadastrarMarca();
                case 2 -> listarMarcas();
                case 3 -> atualizarMarca();
                case 4 -> removerMarca();
                case 5 -> cadastrarFerramenta();
                case 6 -> listarFerramentas();
                case 7 -> atualizarFerramenta();
                case 8 -> removerFerramenta();
                case 9 -> listarOrdensDisponiveis();
                case 10 -> aceitarOrdem(tecnico);
                case 11 -> listarOrdensDoTecnico(tecnico);
                case 12 -> concluirOrdem(tecnico);
                case 0 -> System.out.println("Saindo do menu tecnico...");
                default -> System.out.println("Opcao invalida!");
            }
        } while (opcao != 0);
    }

    // ---------- Marca ----------

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

    public static void listarMarcas() {
        System.out.println("--- Lista de Marcas ---");
        ArrayList<Marca> marcas = marcaController.listarMarcas();
        if (marcas.isEmpty()) {
            System.out.println("Nenhuma marca cadastrada.");
        } else {
            for (int i = 0; i < marcas.size(); i++) {
                System.out.println(marcas.get(i).toString());
            }
        }
    }

    public static void atualizarMarca() {
        System.out.println("--- Atualizar Marca ---");
        System.out.print("Digite o codigo da marca: ");
        int codigo = scn.nextInt();

        Marca marca = marcaController.buscarMarcaPorCodigo(codigo);
        if (marca == null) {
            System.out.println("Marca nao encontrada!");
            return;
        }

        System.out.print("Novo nome fantasia (" + marca.getNomeFantasia() + "): ");
        String nomeFantasia = scn.next();
        System.out.print("Novo fabricante (" + marca.getFabricante() + "): ");
        String fabricante = scn.next();
        System.out.print("Novo CNPJ (" + marca.getCnpj() + "): ");
        String cnpj = scn.next();

        boolean ok = marcaController.atualizarMarca(codigo, nomeFantasia, fabricante, cnpj);
        if (ok) {
            System.out.println("Marca atualizada com sucesso!");
        } else {
            System.out.println("Erro ao atualizar marca.");
        }
    }

    public static void removerMarca() {
        System.out.println("--- Remover Marca ---");
        System.out.print("Digite o codigo da marca: ");
        int codigo = scn.nextInt();

        boolean ok = marcaController.removerMarca(codigo);
        if (ok) {
            System.out.println("Marca removida com sucesso!");
        } else {
            System.out.println("Marca nao encontrada!");
        }
    }

    // ---------- Ferramenta ----------

    public static void cadastrarFerramenta() {
        System.out.println("--- Cadastro de Ferramenta ---");

        if (marcaController.listarMarcas().isEmpty()) {
            System.out.println("Nenhuma marca cadastrada. Cadastre uma marca primeiro.");
            return;
        }

        System.out.println("Marcas cadastradas:");
        ArrayList<Marca> marcas = marcaController.listarMarcas();
        for (int i = 0; i < marcas.size(); i++) {
            System.out.println(marcas.get(i).getCodigo() + " - " + marcas.get(i).getNomeFantasia());
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
        if (ok) {
            System.out.println("Ordem aceita com sucesso! Ela agora esta em andamento.");
        } else {
            System.out.println("Nao foi possivel aceitar essa ordem. Verifique o codigo e o status dela.");
        }
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
            }

            System.out.print("Digite o codigo da ferramenta para pegar (0 para parar de pegar ferramentas): ");
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
                    System.out.println("Ferramenta '" + f.getNome() + "' retirada.");
                }
            }
        }

        System.out.println("Executando o servico...");

        for (int i = 0; i < ferramentasRetiradas.size(); i++) {
            Ferramenta f = ferramentasRetiradas.get(i);
            ferramentaController.alterarStatus(f.getCodigo(), true);
            System.out.println("Ferramenta '" + f.getNome() + "' devolvida.");
        }

        boolean ok = ordemServicoController.concluirOrdem(codigoOrdem, tecnico, ferramentasRetiradas);
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