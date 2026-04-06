package org.example.services;

import org.example.models.Solicitacao;

import java.util.ArrayList;
import java.util.List;

public class ServicoSolicitacoes {
    private List<Solicitacao> lista = new ArrayList<>();

    public void criarSolicitacao(Solicitacao s) {
        lista.add(s);
    }

    public void listarProtocolo() {
        for (Solicitacao s : lista) {
            System.out.println("Protocolo: " + s.getProtocolo() + " | Status: " + s.getStatus() + " | Categoria: " + s.getCategoria());
        }
    }

    public Solicitacao buscarProtocolo(int protocolo) {
        for (Solicitacao s : lista) {
            if (s.getProtocolo() == protocolo) {
                return s;
            }
        }
        return null;
    }
}
