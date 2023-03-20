import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Labirinto extends JPanel implements KeyListener {
    private static final long serialVersionUID = 1L;
    private int x = 20, y = 20;
    private int starX = 50, starY = 50;
    private int endX = 300, endY = 300;
    private boolean finished = false;

    private boolean[][] walls = new boolean[30][30]; // matriz booleana para representar paredes

    public Labirinto() {
        addKeyListener(this);
        setFocusable(true);
        setBackground(new Color(220, 255, 220)); // define a cor de fundo como verde claro

        // adiciona paredes aleatórias
        for (int i = 0; i < walls.length; i++) {
            for (int j = 0; j < walls[0].length; j++) {
                if (Math.random() < 0.3) { // 30% de chance de ter uma parede em cada posição
                    walls[i][j] = true;
                }
            }
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        if (!finished) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.fillOval(x, y, 20, 20);
            // desenha a estrela vermelha
//            g.setColor(Color.RED);
//            g.fillPolygon(new int[]{starX, starX + 20, starX + 30, starX + 40, starX + 60, starX + 50, starX + 60, starX + 40, starX + 30, starX + 20},
//                    new int[]{starY, starY + 20, starY + 20, starY + 40, starY + 40, starY + 60, starY + 80, starY + 60, starY + 60, starY + 40}, 10);
            // desenha o ponto final do jogo
            g.setColor(Color.BLUE);
            g.fillOval(endX, endY, 20, 20);
            // desenha as paredes
            g.setColor(Color.BLACK);
            for (int i = 0; i < walls.length; i++) {
                for (int j = 0; j < walls[0].length; j++) {
                    if (walls[i][j]) {
                        g.fillRect(i * 20, j * 20, 20, 20);
                    }
                }
            }
        } else {
            // desenha a mensagem de "Você ganhou!"
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Você ganhou!", 150, 200);
        }
    }

    public void keyPressed(KeyEvent e) {
        if (!finished) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    if (!walls[x / 20][y / 20 - 1]) y -= 5; // verifica se há parede acima
                    break;
                case KeyEvent.VK_DOWN:
                    if (!walls[x / 20][y / 20 + 1]) y += 5; // verifica se há parede abaixo


                    break;
                case KeyEvent.VK_LEFT:
                    if (!walls[x / 20 - 1][y / 20]) x -= 5; // verifica se há parede à esquerda
                    break;
                case KeyEvent.VK_RIGHT:
                    if (!walls[x / 20 + 1][y / 20]) x += 5; // verifica se há parede à direita
                    break;
            }
            if (x == endX && y == endY) { // verifica se o jogador chegou ao fim
                finished = true;
            }
            repaint();
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Labirinto");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Labirinto lab = new Labirinto();
        frame.add(lab);
        frame.setSize(620, 640);
        frame.setVisible(true);
        frame.setTitle("O Labirinto");
        frame.setLocationRelativeTo(null);
    }
}