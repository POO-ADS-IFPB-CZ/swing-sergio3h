package com.meuapp.dao;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private static final String ARQUIVO = "src/main/resources/produtos.txt";

    public static void adicionarProduto() {
        JTextField nomeField = new JTextField();
        JTextField precoField = new JTextField();

        JPanel panel = new JPanel(new java.awt.GridLayout(2, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("Nome do Produto:"));
        panel.add(nomeField);
        panel.add(new JLabel("Preço:"));
        panel.add(precoField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Adicionar Produto",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String nome = nomeField.getText().trim();
            String preco = precoField.getText().trim();
            if (!nome.isEmpty() && !preco.isEmpty()) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO, true))) {
                    writer.write(nome + " - R$ " + preco);
                    writer.newLine();
                    JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso!");
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao salvar o produto!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void removerProduto() {
        List<String> produtos = carregarProdutos();
        if (produtos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum produto para remover.");
            return;
        }

        String produtoParaRemover = (String) JOptionPane.showInputDialog(null, "Selecione o produto para remover:",
                "Remover Produto", JOptionPane.QUESTION_MESSAGE, null,
                produtos.toArray(), produtos.get(0));

        if (produtoParaRemover != null) {
            produtos.remove(produtoParaRemover);
            salvarProdutos(produtos);
            JOptionPane.showMessageDialog(null, "Produto removido com sucesso!");
        }
    }

    public static List<String> carregarProdutos() {
        List<String> produtos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                produtos.add(linha);
            }
        } catch (IOException e) {
            // Arquivo pode não existir na primeira execução, ignoramos esse erro
        }
        return produtos;
    }

    public static void salvarProdutos(List<String> produtos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (String produto : produtos) {
                writer.write(produto);
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar os produtos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
