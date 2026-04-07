package org.example.models;

import java.time.LocalDateTime;
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
    private String nomeSolicitante; // novo (para controle de anonimato)

    private LocalDateTime dataCriacao;
    private List<HistoricoStatus> historico;

    public Solicitacao(Categoria categoria, String descricao, String localizacao,
                       Prioridade prioridade, boolean anonimo, String nomeSolicitante) {

        // 🔒 Validações obrigatórias
        if (categoria == null) {
            throw new IllegalArgumentException("Categoria é obrigatória");
        }

        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("Descrição é obrigatória");
        }

        if (localizacao == null || localizacao.isBlank()) {
            throw new IllegalArgumentException("Localização é obrigatória");
        }

        if (prioridade == null) {
            throw new IllegalArgumentException("Prioridade é obrigatória");
        }

        // 🔒 Regra de anonimato
        if (!anonimo && (nomeSolicitante == null || nomeSolicitante.isBlank())) {
            throw new IllegalArgumentException("Nome do solicitante é obrigatório quando não for anônimo");
        }

        this.protocolo = contador++;
        this.categoria = categoria;
        this.descricao = descricao;
        this.localizacao = localizacao;
        this.prioridade = prioridade;
        this.status = Status.ABERTO;
        this.anonimo = anonimo;
        this.nomeSolicitante = anonimo ? "ANÔNIMO" : nomeSolicitante;

        this.dataCriacao = LocalDateTime.now();
        this.historico = new ArrayList<>();

        adicionarHistorico(Status.ABERTO, "Solicitação criada");
    }

    // 🔎 SLA baseado na prioridade
    public int getPrazoDias() {
        switch (prioridade) {
            case ALTA: return 1;
            case MEDIA: return 3;
            case BAIXA: return 7;
            default: return 0;
        }
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

    // 🔄 Atualizar status (com log)
    public void atualizarStatus(Status novoStatus, String comentario) {
        if (novoStatus == null) {
            throw new IllegalArgumentException("Status não pode ser nulo");
        }
        this.status = novoStatus;
        adicionarHistorico(novoStatus, comentario);
    }

    // 💬 NOVO: adicionar comentário independente
    public void adicionarComentario(String comentario) {
        if (comentario == null || comentario.isBlank()) {
            throw new IllegalArgumentException("Comentário não pode ser vazio");
        }
        historico.add(new HistoricoStatus(this.status, comentario));
    }

    private void adicionarHistorico(Status status, String comentario) {
        historico.add(new HistoricoStatus(status, comentario));
    }

    // 📊 Exibir detalhes completos
    public void exibirDetalhes() {
        System.out.println("Protocolo: " + protocolo);
        System.out.println("Categoria: " + categoria);
        System.out.println("Descrição: " + descricao);
        System.out.println("Localização: " + localizacao);
        System.out.println("Prioridade: " + prioridade);
        System.out.println("Prazo (dias): " + getPrazoDias());
        System.out.println("Status: " + status);
        System.out.println("Solicitante: " + nomeSolicitante);
        System.out.println("Data criação: " + dataCriacao);

        System.out.println("Histórico:");
        for (HistoricoStatus h : historico) {
            System.out.println(h);
        }
    }
}