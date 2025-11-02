// Pacote contendo a classe GamePanel
package main;

// Importações necessárias
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
// import java.awt.event.MouseAdapter;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import fallObject.FallObjectManager;

// Claasse GamePanel, representa o painel principal do jogo
public class GamePanel extends JPanel implements Runnable{
	private BufferedImage backgroundImage;
    // Para efeito visual ao pressionar "R"
    private boolean showRestartEffect;
    private long restartEffectStartTime;
    
    public boolean isShowRestartEffect() {
        return showRestartEffect;
    }
    public void setShowRestartEffect(boolean showRestartEffect) {
        this.showRestartEffect = showRestartEffect;
    }
    public long getRestartEffectStartTime() {
        return restartEffectStartTime;
    }
    public void setRestartEffectStartTime(long restartEffectStartTime) {
        this.restartEffectStartTime = restartEffectStartTime;
    }

	// Enumerção para os estados do jogo
	public enum State
	{
	    START, RUNNING,  GAME_OVER;
	}

	//Configurações da tela (largura, altura, tamanho do tile, etc.)
	// Tamanho original do tile (16x16 pixels)
	final int originalTileSize = 16; 
	// Fator de escala para aumentar o tamanho do tile
	final int scale = 3;
	
	// Tamanho final do tile após a aplicação da escala (48x48 pixels)
	public final int tileSize = originalTileSize * scale; 
	// Número máximo de colunas na tela
	public final int maxScreenCol = 16;
	// Número máximo de linhas na tela
	public final int maxScreenRow = 12;

	// Largura total da tela em pixels (48 pixels por tile * 16 colunas = 768 pixels)
	public final int ScreenWidth = tileSize * maxScreenCol; 
	// Altura total da tela em pixels (48 pixels por tile * 12 linhas = 576 pixels)
	public final int ScreenHeight = tileSize * maxScreenRow;

	//Estado atual do jogo
	public State state = State.RUNNING;
	// Contadores de acertos, erros e vidas
	public int acertos=0;
	public int erros=0;
	public int vidas=3;
	
	// Configuração de FPS
	int FPS = 60;	

	// Manipuladores de eventos do mouse e teclado,e gerenciador de objetos que caem
	MouseHandler mouseH = new MouseHandler(this);
	KeyHandler keyH = new KeyHandler(this);
	FallObjectManager objectM = new FallObjectManager(this,keyH,mouseH);
	
	// Thread do jogo
	Thread gameThread;

	// Construtor da classe
	public GamePanel() {
    this.setPreferredSize(new Dimension(ScreenWidth, ScreenHeight));
    this.setDoubleBuffered(true);
    this.addKeyListener(keyH);
    this.addMouseListener(mouseH);
    this.addMouseMotionListener(mouseH);
    this.setFocusable(true);

    try {
        backgroundImage = ImageIO.read(getClass().getResourceAsStream("/background/tarde.png"));
        if (backgroundImage == null) {
            System.err.println("Imagem não encontrada: /background/tarde.png");
        }
    } catch (IOException | IllegalArgumentException e) {
        System.err.println("Erro ao carregar a imagem de fundo: " + e.getMessage());
    }
    
}

    // Inicia a thread do jogo
	public void StartGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	// Método principal da thread do jogo
	@Override
	public void run() { // Loop principal do jogo
		// Intervalo de tempo entre cada atualização do jogo, calculado com base nos FPS desejados
		double drawInterval = 1000000000/FPS; // Intervalo em nanosegundos 
		double delta = 0; // Variável para acumular o tempo decorrido
		long lastTime = System.nanoTime(); // Marca o tempo inicial
		long currentTime;
		
		// Loop que continuará rodando enquanto o gameThread não for nulo
		while(gameThread !=null) {
			// Atualizações de lógica e renderização da tela
			currentTime = System.nanoTime(); // Obtém o tempo atual
			// Calcula o tempo decorrido desde a última atualização e acumula em delta
			delta += (currentTime - lastTime) / drawInterval; 
			// Atualiza lastTime para o tempo atual
			lastTime = currentTime;

			if(delta>=1) { // Verifica se delta é maior ou igual a 1
				update();  // Se for, atualiza a lógica do jogo
				repaint(); // Solicita uma nova renderização do jogo
				delta--; // Decrementa delta para se preparar para a próxima atualização
			}
			}
		}
	
    // Atualiza a lógica do jogo
	public void update() {
		// Atualiza o gerenciador de objetos que caem
		objectM.update();
	}
	
	// Desenha os elementos na tela
	// Dentro do método paintComponent
    @Override
    public void paintComponent(Graphics g) {
    super.paintComponent(g); // Limpa a área de desenho

    Graphics2D g2 = (Graphics2D) g;

    // Desenha a imagem de fundo
    try {
        if (backgroundImage != null) {
            g2.drawImage(backgroundImage, 0, 0, ScreenWidth, ScreenHeight, null);
        } else {
            System.out.println("Aviso: backgroundImage está nulo.");
        }
    } catch (Exception e) {
        System.out.println("Erro ao desenhar background:");
        e.printStackTrace();
    }

    // Verifica se o jogador perdeu todas as vidas
    if (vidas == 0) {
        state = State.GAME_OVER;
    }

    // Renderiza elementos de acordo com o estado do jogo
    try {
        switch (state) {
            case START:
                // Pode adicionar tela de início no futuro
                break;

            case RUNNING:
                try {
                    // Tenta carregar e desenhar o cesto
                    BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/background/cestual.png"));
                    if (image != null) {
                        g2.drawImage(image, 140, ScreenHeight - 100, 477, 117, null);
                    } else {
                        System.out.println("Erro: cestual.png não foi encontrado.");
                    }
                } catch (Exception e) {
                    System.out.println("Erro ao carregar/desenhar cestual.png:");
                    e.printStackTrace();
                }

                // Tenta desenhar objetos
                try {
                    objectM.draw(g2);
                } catch (Exception e) {
                    System.out.println("Erro ao desenhar os objetos:");
                    e.printStackTrace();
                }
                break;

            case GAME_OVER: 
                try {
                    // Texto "GAME OVER"
                    Font f = new Font("Comic Sans MS", Font.BOLD, 40);
                    g2.setFont(f);
                    g2.setColor(Color.DARK_GRAY);
                    g2.drawString("GAME OVER", 270, 220);

                    // Botão "Jogar Novamente"
                    g2.setColor(Color.DARK_GRAY);
                    g2.fillRect(280, 290, 210, 50);
                    f = new Font("Comic Sans MS", Font.BOLD, 20);
                    g2.setFont(f);
                    g2.setColor(Color.WHITE);
                    g2.drawString("Jogar Novamente", 300, 320);

                } catch (Exception e) {
                    System.out.println("Erro ao desenhar tela de GAME OVER:");
                    e.printStackTrace();
                }
                break;
        }
    } catch (Exception e) {
        System.out.println("Erro no switch(state):");
        e.printStackTrace();
    }

    // Efeito visual temporário ao pressionar "R"
    if (showRestartEffect) {
        long now = System.currentTimeMillis();
        if (now - restartEffectStartTime < 890) { // Dura 1 segundo
            g2.setColor(new Color(255, 255, 0)); 
            g2.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
            g2.drawString("Reiniciando...", ScreenWidth / 2 - 115, 35);
        } else {
            showRestartEffect = false;
        }
    }

    g2.dispose(); // Libera os recursos gráficos
}


	public void restartGame() { // Reinicia o estado do jogo para os valores iniciais
		// Reinicia a contagem de acertos e erros
		acertos = 0;
		erros = 0;
		// Reinicia o número de vidas
		vidas = 3;
		// Chama o método SetDefaultValues do gerenciador de objetos caindo para resetar suas posições
		objectM.SetDefaultValues(); 
		// Muda o estado do jogo para RUNNING para reiniciar o jogo
		state = State.RUNNING; 
	}
    
    public void resetGame() {
    vidas = 3;
    acertos = 0;
    erros = 0;
    objectM.SetDefaultValues();
    objectM.CriaObjeto();
    state = State.RUNNING;
}

}
