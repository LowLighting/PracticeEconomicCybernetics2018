import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;


public class Client extends JFrame {

    private final int FIELD_SIZE = 10;
    private final int RIVAL_PANEL_SIZE = 400;
    private final int PLAYER_PANEL_SIZE = RIVAL_PANEL_SIZE / 2;
    private final int RIVAL_CELL_CIZE = RIVAL_PANEL_SIZE / FIELD_SIZE;
    private final int PLAYER_CELL_CIZE = PLAYER_PANEL_SIZE / FIELD_SIZE;

    private static int port = 5003;
    private static String adress = "10.160.50.92";
    private boolean gameOver = false;

    private ObjectOutputStream out;
    private ObjectInputStream input;

    private Canvas rivalPanel;
    private Canvas playerPanel;

    private int[][] playerField;
    private int[][] enemyField;

    public static void main(String[] args) {
        new Client();
    }

    private Client() {
        try {
            InetAddress ipAdress = InetAddress.getByName(adress);
            Socket socket = new Socket(ipAdress, port);

            InputStream inputS = socket.getInputStream();
            OutputStream outS = socket.getOutputStream();

            input = new ObjectInputStream(inputS);
            out = new ObjectOutputStream(outS);

            playerField = (int[][]) input.readObject();

            enemyField = (int[][]) input.readObject();

            setTitle("SeaBattle");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setResizable(false);

            rivalPanel = new Canvas();
            rivalPanel.setPreferredSize(new Dimension(RIVAL_PANEL_SIZE, RIVAL_PANEL_SIZE));
            rivalPanel.setBackground(Color.blue);
            rivalPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {

                    try {
                        if (!gameOver) {
                            if (input.readBoolean()) {  //turn

                                super.mouseReleased(e);
                                int x = e.getY() / RIVAL_CELL_CIZE; // coordinates transformation
                                out.writeInt(x);
                                out.flush();
                                int y = e.getX() / RIVAL_CELL_CIZE;
                                out.writeInt(y);
                                out.flush();
                                int event = e.getButton();
                                out.writeInt(event);
                                out.flush();
                                playerPanel.repaint();
                                rivalPanel.repaint();

                            }
                            gameOver = input.readBoolean();
                            playerField = (int[][]) input.readObject();
                            enemyField = (int[][]) input.readObject();
                            playerPanel.repaint();
                            rivalPanel.repaint();
                        }
                    } catch (Exception ex) {
                        System.out.println("Mouse exception");
                    }

                }
            });

            playerPanel = new Canvas();
            playerPanel.setPreferredSize(new Dimension(PLAYER_PANEL_SIZE, PLAYER_PANEL_SIZE));
            playerPanel.setBackground(Color.blue);

            JButton init = new JButton("New game");

            JButton exit = new JButton("Exit");

            JTextArea board = new JTextArea();
            board.setEditable(false);
            JScrollPane scroll = new JScrollPane(board);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout());
            buttonPanel.add(init);
            buttonPanel.add(exit);

            JPanel container = new JPanel();
            container.setLayout(new BorderLayout());

            container.add(playerPanel, BorderLayout.NORTH);
            container.add(scroll, BorderLayout.CENTER);
            container.add(buttonPanel, BorderLayout.SOUTH);

            setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
            add(rivalPanel);
            add(playerPanel);

            pack();
            setLocationRelativeTo(null);
            setVisible(true);

            playerPanel.repaint();
            rivalPanel.repaint();

        } catch (Exception ex) {
            System.out.println("Connection error");
        }

    }

    private void paintShipsAndShots(Graphics g, int cellSize, int[][] field) {
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {

                System.out.print(playerField[i][j] + " ");

                if (field[i][j] == 3) {
                    g.setColor(Color.red);
                    System.out.println("x ="+(i * cellSize + 1));
                    System.out.println("y ="+(j * cellSize + 1));
                    g.fill3DRect(j * cellSize + 1, i * cellSize + 1, cellSize - 2, cellSize - 2, true);
                }
                if (field[i][j] == 2) {
                    g.setColor(Color.green);
                    g.fill3DRect(j* cellSize + 1, i * cellSize + 1, cellSize - 2, cellSize - 2, true);
                }
                if (field[i][j] == 1) {
                    g.setColor(Color.white);
                    g.fillRect(j * cellSize + cellSize / 2 - 3, i * cellSize + cellSize / 2 - 3, 8, 8);
                }
            }
            System.out.println();
        }
    }

    class Canvas extends JPanel { // for painting

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            int cellSize = (int) getSize().getWidth() / FIELD_SIZE;
            g.setColor(Color.white);
            for (int i = 1; i < FIELD_SIZE; i++) {
                g.drawLine(0, i * cellSize, FIELD_SIZE * cellSize, i * cellSize);
                g.drawLine(i * cellSize, 0, i * cellSize, FIELD_SIZE * cellSize);
            }
            if (cellSize == PLAYER_CELL_CIZE) {
                paintShipsAndShots(g, cellSize, playerField);
                System.out.println("opponent's move");
            } else {
                paintShipsAndShots(g, cellSize, enemyField);

            }

        }

    }

}
