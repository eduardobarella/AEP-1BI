package org.example.models;

import org.example.enums.Categoria;
import org.example.enums.Prioridade;
import org.example.enums.Status;

import java.util.ArrayList;
import java.util.List;

public class Solicitacao {
    private static int contador = 1;

    private int protocolo;
    private Categoria categoria;
    private String descricao;
    private String localizacao;
    private Prioridade prioridade;
    private Status status;
    private boolean anonimo;
    private List<HistoricoStatus> historico;

    public Solicitacao(Categoria categoria, String descricao, String localizacao, Prioridade prioridade, boolean anonimo) {
        this.protocolo = contador++;
        this.categoria = categoria;
        this.descricao = descricao;
        this.localizacao = localizacao;
        this.prioridade = prioridade;
        this.status = Status.ABERTO;
        this.anonimo = anonimo;
        this.historico = new ArrayList<>();

        adicionarHistorico(Status.ABERTO, "Solicitação criada");
    }

    public int getProtocolo() {
        return protocolo;
    }

    public Status getStatus() {
        return status;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void atualizarStatus(Status novoStatus, String comentario) {
        this.status = novoStatus;
        adicionarHistorico(novoStatus, comentario);
    }

    private void adicionarHistorico(Status status, String comentario) {
        historico.add(new HistoricoStatus(status, comentario));
    }

    public void exibirDetalhes() {
        System.out.println("Protocolo: " + protocolo);
        System.out.println("Categoria: " + categoria);
        System.out.println("Descrição: " + descricao);
        System.out.println("Localização: " + localizacao);
        System.out.println("Prioridade: " + prioridade);
        System.out.println("Status: " + status);
        System.out.println("Anônimo: " + (anonimo ? "Sim" : "Não"));

        System.out.println("Histórico:");
        for (HistoricoStatus h : historico) {
            System.out.println(h);
        }
    }
}