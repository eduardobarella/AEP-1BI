package org.example;

import org.example.enums.Categoria;
import org.example.enums.Prioridade;
import org.example.enums.Status;
import org.example.models.Solicitacao;
import org.example.services.ServicoSolicitacoes;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ServicoSolicitacoes servico = new ServicoSolicitacoes();

        int perfil;

        do {
            System.out.println("\n===== ACESSO AO SISTEMA =====");
            System.out.println("1 - Gestor");
            System.out.println("2 - Solicitante");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            perfil = sc.nextInt();
            sc.nextLine();

            if (perfil == 1) {
                menuGestor(sc, servico);
            } else if (perfil == 2) {
                menuSolicitante(sc, servico);
            }

        } while (perfil != 0);

        System.out.println("Sistema encerrado.");
    }

    // ===========================
    // MENU SOLICITANTE
    // ===========================
    public static void menuSolicitante(Scanner sc, ServicoSolicitacoes servico) {

        int opcao;

        do {
            System.out.println("\n===== MENU SOLICITANTE =====");
            System.out.println("1 - Criar solicitação");
            System.out.println("2 - Listar solicitações");
            System.out.println("3 - Buscar por protocolo");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 1:
                    System.out.println("\nEscolha a categoria:");
                    System.out.println("1 - Iluminação");
                    System.out.println("2 - Buraco");
                    System.out.println("3 - Poda");
                    System.out.println("4 - Saúde");
                    System.out.println("5 - Zeladoria");
                    System.out.println("6 - Denúncia");

                    int opCategoria = sc.nextInt();
                    sc.nextLine();

                    Categoria categoria;

                    switch (opCategoria) {
                        case 1: categoria = Categoria.ILUMINACAO; break;
                        case 2: categoria = Categoria.BURACO; break;
                        case 3: categoria = Categoria.PODA; break;
                        case 4: categoria = Categoria.SAUDE; break;
                        case 5: categoria = Categoria.ZELADORIA; break;
                        case 6: categoria = Categoria.DENUNCIA; break;
                        default:
                            System.out.println("Categoria inválida!");
                            continue;
                    }

                    System.out.println("Descrição:");
                    String descricao = sc.nextLine();

                    if (descricao.isEmpty()) {
                        System.out.println("Descrição obrigatória!");
                        continue;
                    }

                    System.out.println("Localização:");
                    String local = sc.nextLine();

                    System.out.println("\nEscolha a prioridade:");
                    System.out.println("1 - Alta");
                    System.out.println("2 - Média");
                    System.out.println("3 - Baixa");

                    int opPrioridade = sc.nextInt();
                    sc.nextLine();

                    Prioridade prioridade;

                    switch (opPrioridade) {
                        case 1: prioridade = Prioridade.ALTA; break;
                        case 2: prioridade = Prioridade.MEDIA; break;
                        case 3: prioridade = Prioridade.BAIXA; break;
                        default:
                            System.out.println("Prioridade inválida!");
                            continue;
                    }

                    System.out.println("\nDeseja se identificar?");
                    System.out.println("1 - Sim");
                    System.out.println("2 - Não (Anônimo)");

                    int opAnonimo = sc.nextInt();
                    sc.nextLine();

                    boolean anonimo;
                    String nome = "";

                    switch (opAnonimo) {
                        case 1:
                            anonimo = false;
                            System.out.println("Digite seu nome:");
                            nome = sc.nextLine();

                            if (nome.isEmpty()) {
                                System.out.println("Nome não pode ser vazio!");
                                continue;
                            }
                            break;

                        case 2:
                            anonimo = true;
                            break;

                        default:
                            System.out.println("Opção inválida!");
                            continue;
                    }

                    Solicitacao s = new Solicitacao(categoria, descricao, local, prioridade, anonimo, nome);
                    servico.criarSolicitacao(s);

                    System.out.println("\n✅ Solicitação criada com protocolo: " + s.getProtocolo());
                    break;

                case 2:
                    servico.listarProtocolo();
                    break;

                case 3:
                    System.out.println("Digite o protocolo:");
                    int p = sc.nextInt();

                    Solicitacao buscada = servico.buscarProtocolo(p);

                    if (buscada != null) {
                        buscada.exibirDetalhes();
                    } else {
                        System.out.println("Solicitação não encontrada.");
                    }
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    // ===========================
    // MENU GESTOR
    // ===========================
    public static void menuGestor(Scanner sc, ServicoSolicitacoes servico) {

        int opcao;

        do {
            System.out.println("\n===== MENU GESTOR =====");
            System.out.println("1 - Listar solicitações");
            System.out.println("2 - Buscar por protocolo");
            System.out.println("3 - Atualizar status");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 1:
                    servico.listarProtocolo();
                    break;

                case 2:
                    System.out.println("Digite o protocolo:");
                    int p = sc.nextInt();

                    Solicitacao buscada = servico.buscarProtocolo(p);

                    if (buscada != null) {
                        buscada.exibirDetalhes();
                    } else {
                        System.out.println("Solicitação não encontrada.");
                    }
                    break;

                case 3:
                    System.out.println("Digite o protocolo:");
                    int id = sc.nextInt();
                    sc.nextLine();

                    Solicitacao sol = servico.buscarProtocolo(id);

                    if (sol != null) {

                        System.out.println("\nNovo status:");
                        System.out.println("1 - Triagem");
                        System.out.println("2 - Em execução");
                        System.out.println("3 - Resolvido");
                        System.out.println("4 - Encerrado");

                        int opStatus = sc.nextInt();
                        sc.nextLine();

                        Status status;

                        switch (opStatus) {
                            case 1: status = Status.TRIAGEM; break;
                            case 2: status = Status.EM_EXECUCAO; break;
                            case 3: status = Status.RESOLVIDO; break;
                            case 4: status = Status.ENCERRADO; break;
                            default:
                                System.out.println("Status inválido!");
                                continue;
                        }

                        System.out.println("Comentário:");
                        String comentario = sc.nextLine();

                        sol.atualizarStatus(status, comentario);

                        System.out.println("✅ Status atualizado com sucesso!");
                    } else {
                        System.out.println("Solicitação não encontrada.");
                    }
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }
}