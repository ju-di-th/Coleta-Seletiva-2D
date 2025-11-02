//Pacote contendo a classe Entity
package fallObject;

// Importações necessárias
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

// Classe Entity, representa uma entidade genérica no jogo
public class Entity {
	// Variáveis de posição e velocidade da entidade
	public int posX,posY;
	public int speed;
	// Imagem da entidade
	public BufferedImage morango;
	// Direção da entidade
	public String direction;
	// Contadores para animação de sprite
	public int spriteCounter=0;
	public int spriteNum =1;
	// Área sólida da entidade para detecção de colisão
	public Rectangle solidArea; 
	// Indica se a colisão está ativada
	public boolean collisionOn = false;
}
