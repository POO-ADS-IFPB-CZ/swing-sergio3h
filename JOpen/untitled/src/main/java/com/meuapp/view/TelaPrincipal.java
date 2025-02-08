package com.meuapp.view;

import com.meuapp.dao.ProdutoDAO;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;

public class TelaPrincipal {
    public void exibir() {
        while (true) {
            List<String> produtos = ProdutoDAO.carregarProdutos();
            String listaProdutos = produtos.isEmpty() ? "Nenhum produto cadastrado." : String.join("\n", produtos);

            JTextArea textArea = new JTextArea(listaProdutos, 10, 30);
            textArea.setFont(new Font("Arial", Font.PLAIN, 14));
            textArea.setBackground(new Color(230, 230, 230));
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            panel.add(new JLabel("Produtos Cadastrados:"));
            panel.add(scrollPane);

            String[] options = {"Adicionar Produto", "Remover Produto", "Sair"};
            int escolha = JOptionPane.showOptionDialog(null, panel, "Gerenciador de Produtos",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            switch (escolha) {
                case 0:
                    ProdutoDAO.adicionarProduto();
                    break;
                case 1:
                    ProdutoDAO.removerProduto();
                    break;
                case 2:
                case -1:
                    System.exit(0);
                    break;
            }
        }
    }
}
