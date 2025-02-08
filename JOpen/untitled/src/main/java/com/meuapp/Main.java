package com.meuapp;

import com.meuapp.view.TelaPrincipal;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaPrincipal().exibir());
    }
}
