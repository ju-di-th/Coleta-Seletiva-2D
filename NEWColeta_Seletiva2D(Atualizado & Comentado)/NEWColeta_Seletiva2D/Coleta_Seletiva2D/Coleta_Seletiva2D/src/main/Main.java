package main;

import javax.swing.JFrame;

// Classe Main: ponto de entrada do programa
public class Main {
    public static void main(String[] args) {
        // Cria a janela principal do jogo
        JFrame window = new JFrame("Coleta Seletiva 2D");

        // Configurações da janela
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha o programa ao clicar no X
        window.setResizable(false);                            // Impede redimensionamento da janela

        // Cria e adiciona o painel do jogo
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack(); // Ajusta o tamanho da janela de acordo com o painel

        window.setLocationRelativeTo(null); // Centraliza na tela
        window.setVisible(true);            // Torna a janela visível

        // Inicia o loop do jogo (thread)
        gamePanel.StartGameThread();
    } 
}
