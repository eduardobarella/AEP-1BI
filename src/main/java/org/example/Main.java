package org.example;

import org.example.models.*;
import org.example.services.ServicoSolicitacoes;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ServicoSolicitacoes servico = new ServicoSolicitacoes();

        int opcao;

        do {
            System.out.println("\n===== SISTEMA DE SOLICITAÇÕES =====");
            System.out.println("1 - Criar solicitação");
            System.out.println("2 - Listar solicitações");
            System.out.println("3 - Buscar por protocolo");
            System.out.println("4 - Atualizar status");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 1:
                    // CATEGORIA
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

                    // DESCRIÇÃO
                    System.out.println("Descrição:");
                    String descricao = sc.nextLine();

                    if (descricao.isEmpty()) {
                        System.out.println("Descrição obrigatória!");
                        continue;
                    }

                    // LOCAL
                    System.out.println("Localização:");
                    String local = sc.nextLine();

                    // PRIORIDADE
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

                    // ANÔNIMO
                    System.out.println("\nDeseja se identificar?");
                    System.out.println("1 - Sim");
                    System.out.println("2 - Não (Anônimo)");

                    int opAnonimo = sc.nextInt();
                    sc.nextLine();

                    boolean anonimo;

                    switch (opAnonimo) {
                        case 1: anonimo = false; break;
                        case 2: anonimo = true; break;
                        default:
                            System.out.println("Opção inválida!");
                            continue;
                    }

                    // CRIAR
                    Solicitacao s = new Solicitacao(categoria, descricao, local, prioridade, anonimo);
                    servico.criar(s);

                    System.out.println("\n✅ Solicitação criada com protocolo: " + s.getProtocolo());
                    break;

                case 2:
                    servico.listar();
                    break;

                case 3:
                    System.out.println("Digite o protocolo:");
                    int p = sc.nextInt();

                    Solicitacao buscada = servico.buscar(p);

                    if (buscada != null) {
                        buscada.exibirDetalhes();
                    } else {
                        System.out.println("Solicitação não encontrada.");
                    }
                    break;

                case 4:
                    System.out.println("Digite o protocolo:");
                    int id = sc.nextInt();
                    sc.nextLine();

                    Solicitacao sol = servico.buscar(id);

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
                    System.out.println("Encerrando sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }
}