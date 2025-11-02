// Pacote contendo a classe FalObjectManager
package fallObject;

// Importações necessárias
import java.awt.Color; // usado para encapsular as cores no sistema
import java.awt.Font; // define estilos e tamanhos de fonte no sistema
import java.awt.Graphics2D; // fornece métodos para desenhar formas, textos e imagens
import java.io.IOException; // captura e trata exceções que ocorrem durante operações de entrada e saída
import java.util.Random; // gera números pseudoaleatórios

import javax.imageio.ImageIO; // fornece métodos para ler e escrever imagens 
import main.GamePanel; // classe principal do painel do jogo
import main.KeyHandler; // trata eventos de teclado
import main.MouseHandler; // trata eventos do mouse

import java.awt.image.BufferedImage;


// Classe FallObjectManager, gerencia os objetos que caem na tela
public class FallObjectManager extends Entity {
	private BufferedImage heartImage;
	// Referência para o painel do jogo
	GamePanel gp;
	// Array de objetos que caem
	public FallObject[] object;
	// Números do mapa para os objetos
	public int mapTileNum[][];
	// Objeto de geração de números aleatórios
	Random random = new Random();
	// Objeto atual
	int currentObject;
	
	// Manipuladores de eventos e teclados e mouse
	KeyHandler keyH;
	MouseHandler mouseH;
	
	// Construtor da classe
	public FallObjectManager(GamePanel gp,KeyHandler keyH,MouseHandler mouseH) {
		// Inicializa referências
		this.gp=gp;
		this.keyH = keyH;
		this.mouseH = mouseH;
		// Inicializa o array de objetos
		object = new FallObject[20];
		// Define valores padrão
		SetDefaultValues();
		// Carrega as imagens do objeto
		getObjectImage();
		try {
			heartImage = ImageIO.read(getClass().getResourceAsStream("/background/heart.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// Carrega as imagens dos objetos
	private void getObjectImage() {
    String[][] objetos = {
        {"morango.png", "O"},
        {"banana.png", "O"},
        {"pizza.png", "O"},
        {"queijo.png", "O"},
        {"cadeado.png", "M"},
        {"parafuso.png", "M"},
        {"lata.png", "M"},
        {"ferro.png", "M"},
        {"tupperware.png", "L"},
        {"toy.png", "L"},
        {"garrafaP.png", "L"},
        {"molho.png", "L"},
        {"espelho.png", "V"},
        {"lampada.png", "V"},
        {"copo.png", "V"},
        {"garrafa.png", "V"},
        {"jornal.png", "P"},
        {"pasta.png", "P"},
        {"toilet-paper.png", "P"},
        {"magazine.png", "P"}
    };

    for (int i = 0; i < objetos.length; i++) {
        try {
            object[i] = new FallObject();
            String nomeArquivo = objetos[i][0];
            String tipo = objetos[i][1];

            // Carrega a imagem do recurso
            object[i].image = ImageIO.read(getClass().getResourceAsStream("/objects/" + nomeArquivo));
            object[i].tipo = tipo;

        } catch (IOException | NullPointerException e) {
            System.err.println("Erro ao carregar imagem: /objects/" + objetos[i][0]);
            e.printStackTrace();
        }
    }
}

	
	// Define valores padrão para a posição e velocidade dos objetos
	public void SetDefaultValues() {
		posX = 300;
		speed = 2;
		direction="down";
		posY=-30;
	}
	
	// Cria um novo objeto
	public void CriaObjeto() {
		currentObject = random.nextInt(20);
	}
	
	// Atualiza a lógica dos objetos
	public void update() {
		// Lógica de movimentação dos objectos de acordo com a movimentação do mouse e teclas pressionadas
		if (mouseH.mouseUsed) {
            posX = mouseH.posX - gp.tileSize / 2;
		} else if(keyH.upPressed==true || keyH.downPressed==true || keyH.leftPressed==true || keyH.rightPressed==true) {
			if (keyH.leftPressed==true) {
				direction="left";
				posX-=5;
			} else if(keyH.rightPressed==true) {
				direction="right";
				posX+=5;
			}
		}
	}
	
	// Desenha objetos na tela
	public void draw(Graphics2D g2) {
		// Desenha informações na tela, como acertos, vidas, etc.
		// Desenha os objetos na posição atual
		// Verifica se algum objeto atingiu o fundo da tela e trata de acordo com o tipo de objeto
		// Cria um novo objeto após ele atingir o fundo da tela
		int fontSize = 20;
        Font f = new Font("Comic Sans MS", Font.BOLD, fontSize);
        g2.setFont(f);
        g2.setColor(Color.DARK_GRAY);
		g2.drawString("Tente de novo com R", 50, 60);
        g2.drawString("Acertos: " + gp.acertos, 50, 30);
        g2.setColor(Color.red);
      	g2.drawString("Vidas", 600, 60);
		

		for (int t = 0; t < gp.vidas; t++) {
            if (heartImage != null) {
                g2.drawImage(heartImage, 600 + (t * 26), 25, 18, 18, null);
            }
		}

		int largura = 64;
		int altura = 64;
		g2.drawImage(object[currentObject].image, posX, posY, largura, altura, null);
		posY+=speed;
		if(posY>=430) {
			
			switch (object[currentObject].tipo) {
				case "V": {//
					if(posX>=130 && posX<=200) {
						marcaAcerto();
					}else {
						
						marcaErro();
					}
					break;
				}
				case "M": {//
					if(posX>=226 && posX<=300) {
						marcaAcerto();
					}else {
						
						marcaErro();
					}
					break;
				}
				case "O": {//
					if(posX>=334 && posX<=380) {
						marcaAcerto();
					}else {
						
						marcaErro();
					}
					break;
				}
				case "P": {//
					if(posX>=430 && posX<=480) {
						marcaAcerto();
					}else {
						
						marcaErro();
					}
					break;
				}
				case "L": {//
					if(posX>=530 && posX<=570) {
						marcaAcerto();
					}else {
						
						marcaErro();
					}
					break;
				}
				
			}	

			CriaObjeto();
			posY=-30;
		}
	}
    // Marca acerto
	public void marcaAcerto() {
		gp.acertos++;

	}
	
	// Marca um erro e remove uma vida
	public void marcaErro() {
		gp.erros++;
		gp.vidas--;
	
	}
	
}
