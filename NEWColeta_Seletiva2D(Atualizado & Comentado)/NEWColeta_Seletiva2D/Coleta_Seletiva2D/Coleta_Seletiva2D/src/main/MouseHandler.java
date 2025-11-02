package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

// A classe MouseHandler implementa as interfaces MouseListener e MouseMotionListener para lidar com eventos do mouse
public class MouseHandler implements MouseListener, MouseMotionListener {

    private GamePanel gp; // Referência ao painel do jogo
    public int posX; // Posição X atual do mouse
    public boolean mouseUsed = false; // Flag para indicar se o mouse está sendo usado

    // Construtor que inicializa a referência ao painel do jogo
    public MouseHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    // Método chamado quando um botão do mouse é pressionado
    @Override
    public void mousePressed(MouseEvent e) {
        mouseUsed = true; // Marca que o mouse está sendo usado

        if (gp.state == GamePanel.State.GAME_OVER) {
            int mouseX = e.getX();
            int mouseY = e.getY();
            int btnW = 220;
            int btnH = 60;
            int btnX = gp.ScreenWidth / 2 - btnW / 2;
            int btnY = gp.ScreenHeight / 2 + 20;
            if (mouseX >= btnX && mouseX <= btnX + btnW &&
            mouseY >= btnY && mouseY <= btnY + btnH) {
                gp.resetGame();
                gp.setShowRestartEffect(true);
                gp.setRestartEffectStartTime(System.currentTimeMillis());
            }
        }
    }

    // Método chamado quando um botão do mouse é liberado
    @Override
    public void mouseReleased(MouseEvent e) {
        mouseUsed = false; // Marca que o mouse não está mais sendo usado
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    // Método chamado quando o mouse é arrastado com um botão pressionado
    @Override
    public void mouseDragged(MouseEvent e) {
        posX = e.getX(); // Atualiza a posição X do mouse
        // Ajusta a posição do objeto no jogo com base na posição do mouse
        gp.objectM.posX = posX - gp.tileSize / 2; 
    }

    // Método chamado quando o mouse é movido
    @Override
    public void mouseMoved(MouseEvent e) {
        posX = e.getX(); // Atualiza a posição X do mouse
        if (mouseUsed) { // Se o mouse está sendo usado
            // Ajusta a posição do objeto no jogo com base na posição do mouse
            gp.objectM.posX = posX - gp.tileSize / 2; 
        }
    }
}
