# Coleta Seletiva 2D

## Descrição do Projeto

O **Coleta Seletiva 2D** é um jogo educativo desenvolvido em Java com a biblioteca Swing/AWT, que visa conscientizar e ensinar os jogadores sobre a importância e as regras da **coleta seletiva de lixo**. O objetivo é simples: o jogador deve mover o cesto de lixo para capturar os resíduos que caem, direcionando-os para a lixeira correta de acordo com o tipo de material (Vidro, Metal, Orgânico, Papel e Plástico).

O jogo utiliza uma mecânica de *arcade* com pontuação por acertos e um sistema de vidas, tornando o aprendizado interativo e divertido.

## Tecnologias Utilizadas

*   **Linguagem:** Java
*   **Framework/Biblioteca:** Swing e AWT (para a interface gráfica e *game loop*)

## Como Executar o Projeto

Este projeto foi desenvolvido em Java e provavelmente utiliza um ambiente de desenvolvimento integrado (IDE) como o Eclipse (indicado pelos arquivos `.project` e `.classpath` originais).

### Pré-requisitos

*   **Java Development Kit (JDK):** Versão 8 ou superior.
*   **IDE:** Eclipse, IntelliJ IDEA ou similar.

### Passos para Execução

1.  **Clone ou Baixe o Projeto:**
    ```bash
    git clone [URL_DO_SEU_REPOSITORIO]
    ```
    *Ou* descompacte o arquivo.

2.  **Importe para a IDE:**
    *   Abra sua IDE (ex: Eclipse).
    *   Vá em `File` > `Import` > `General` > `Existing Projects into Workspace`.
    *   Selecione a pasta raiz do projeto.

3.  **Execute a Classe Principal:**
    *   Localize a classe `main.Main.java`.
    *   Execute o arquivo como um aplicativo Java.

O jogo será iniciado em uma nova janela.

## 🎮 Mecânica do Jogo

| Tipo de Resíduo | Código no Jogo | Cor da Lixeira (Padrão) |
| :--- | :--- | :--- |
| **Vidro** | V | Verde |
| **Metal** | M | Amarelo |
| **Orgânico** | O | Marrom |
| **Papel** | P | Azul |
| **Plástico** | L | Vermelho |

### Controles

O cesto de coleta pode ser controlado de duas maneiras:

*   **Mouse:** O cesto segue a posição horizontal do cursor.
*   **Teclas de Seta:** Use as setas **Esquerda** e **Direita** para mover o cesto.

### Reiniciar

*   Pressione a tecla **R** a qualquer momento para reiniciar o jogo e a contagem de vidas/pontos.

---

*Desenvolvido para fins acadêmicos.*
