package varship;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;

public class Server implements Serializable {

    private boolean firstPlayerAction;

    public static void main(String[] args) {
        new Server();
    }

    private Server() {
        try {
            BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("lol.txt")) );
            bf.write(InetAddress.getLocalHost().toString());
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Socket clientSocket = null;
        ServerSocket serverSocket = null;
        Player playerAction;
        Player enemy;
        boolean isMissed = false;
        int x, y;
        String message;
        int event;

        try {
            Random random = new Random();

            int port = 5003;
            serverSocket = new ServerSocket(port);
            System.out.println("Start Server!");

            ArrayList<Player> players = new ArrayList<Player>();
            while (players.size() < 2) {
                clientSocket = serverSocket.accept();
                Player player = new Player(clientSocket);
                players.add(player);
                System.out.println("Client connect");
            }

            System.out.println("----------Start game---------");

            Player player1 = players.get(0);
            Player player2 = players.get(1);

            player1.out.writeObject(player1.getField());
            player1.out.writeObject(player2.encryptField());

            player2.out.writeObject(player2.getField());
            player2.out.writeObject(player1.encryptField());

            boolean gameOver = false;
            while (!gameOver) {
                playerAction = (Player) (firstPlayerAction ? players.get(0) : players.get(1));
                enemy = (Player) (firstPlayerAction ? players.get(1) : players.get(0));
                firstPlayerAction = !firstPlayerAction;

                isMissed = false;

                while (!isMissed) {

                    playerAction.out.writeBoolean(true);//Your turn 1-2
                    playerAction.out.flush();
                    enemy.out.writeBoolean(false);//enemy turn; //2-1
                    enemy.out.flush();

                    x = playerAction.input.readInt(); //1-3
                    y = playerAction.input.readInt(); //1-4
                    event = playerAction.input.readInt(); //1-5
                    System.out.println(x + " ; " + y + " | " + event);

                    if (event == 1) // если нажата левая кнопка мыши и игра не окончена
                    {
                        if (!enemy.hitSamePlace(x, y)) {   //если человек еще не поражал эту точку

                            if (enemy.checkHit(x, y)) { //вы поразили цель
                                enemy.shotInCell(x, y);
                                message = "You hit the target";
                                System.out.println(message);
                                if (enemy.checkDefeat()) {    //нет выживших кораблей противника
                                    message = "YOU WON!";
                                    gameOver = true;
                                    System.out.println(message);

                                }

                            } else if (enemy.CheckForEmptinessCell(x, y)) {
                                enemy.shotInCell(x, y);
                                isMissed = true;
                                message = "You missed. Enemy turn";
                                System.out.println(message);

                            }
                        }
                    }
                    playerAction.out.writeBoolean(gameOver);
                    enemy.out.writeBoolean(gameOver);

                    playerAction.out.writeObject(playerAction.getField());
                    playerAction.out.writeObject(enemy.encryptField());

                    enemy.out.writeObject(enemy.getField());
                    enemy.out.writeObject(playerAction.encryptField());
                    System.out.println(isMissed);
                }

            }
                System.out.println("----------Game over---------");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
