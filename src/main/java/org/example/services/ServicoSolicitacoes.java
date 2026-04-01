package org.example.services;

import org.example.models.Solicitacao;

import java.util.ArrayList;
import java.util.List;

public class ServicoSolicitacoes {
    private List<Solicitacao> lista = new ArrayList<>();

    public void criar(Solicitacao s) {
        lista.add(s);
    }

    public void listar() {
        for (Solicitacao s : lista) {
            System.out.println("Protocolo: " + s.getProtocolo() + " | Status: " + s.getStatus() + " | Categoria: " + s.getCategoria());
        }
    }

    public Solicitacao buscar(int protocolo) {
        for (Solicitacao s : lista) {
            if (s.getProtocolo() == protocolo) {
                return s;
            }
        }
        return null;
    }
}
