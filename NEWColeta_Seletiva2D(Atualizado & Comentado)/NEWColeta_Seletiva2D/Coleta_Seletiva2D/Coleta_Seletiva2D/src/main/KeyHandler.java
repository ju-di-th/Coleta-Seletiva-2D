package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// A classe KeyHandler implementa a interface KeyListener para lidar com eventos de teclado
public class KeyHandler implements KeyListener {
	private GamePanel gp; // Referência ao painel do jogo
	
	// Construtor que inicializa a referência ao painel do jogo
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}

    // Variáveis booleanas para verificar quais teclas estão pressionadas
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	
	// Método chamado quando uma tecla é digitada (não utilizado aqui)
	@Override
	public void keyTyped(KeyEvent e) {
	}

	// Método chamado quando uma tecla é pressionada
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		// Verifica qual tecla foi pressionada e atualiza as variáveis correspondentes
		if (keyCode == KeyEvent.VK_W) {
			upPressed = true;
		}
		if (keyCode == KeyEvent.VK_S) {
			downPressed = true;
		}
		if (keyCode == KeyEvent.VK_A) {
			leftPressed = true;
		}
		if (keyCode == KeyEvent.VK_D) {
			rightPressed = true;
		}
		
		if (keyCode == KeyEvent.VK_R) {
			gp.setShowRestartEffect(true);
			gp.setRestartEffectStartTime(System.currentTimeMillis());
			new Thread(() -> {
				try {
					Thread.sleep(800); // Mostra o efeito por 800ms
					} catch (InterruptedException ex) {
						ex.printStackTrace();
        }
		gp.resetGame(); // Só reinicia depois do delay
		}).start();
	}

		// Reinicia o jogo ao pressionar R quando estiver no estado GAME_OVER
		if (keyCode == KeyEvent.VK_R && gp.state == GamePanel.State.GAME_OVER) {
			gp.resetGame();
			gp.setShowRestartEffect(true);
			gp.setRestartEffectStartTime(System.currentTimeMillis());
		}
	}

    // Método chamado quando uma tecla é liberada
	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();

		// Verifica qual tecla foi liberada e atualiza as variáveis correspondentes
		if (keyCode == KeyEvent.VK_W) {
			upPressed = false;
		}
		if (keyCode == KeyEvent.VK_S) {
			downPressed = false;
		}
		if (keyCode == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if (keyCode == KeyEvent.VK_D) {
			rightPressed = false;
		}
	}
}
