//Marcus Smith
//48373771
import java.util.ArrayList;

public class Game {
    private int playerTurn = 0;
    private Board board;
    private boolean gameRunning;
    private ScoreBoard scoreBoard;
    private ArrayList<Player> players = new ArrayList<Player>();
    private Launcher I;

    Game(Launcher I, int numberOfPlayers, int cardSpacing, int cardCounts, int rows, int columns) {
        this.I = I;
        board = new Board( I, cardSpacing, cardCounts, rows, columns);
        ArrayList<Player> players = new ArrayList<Player>();
        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(new Player(I, "player" + i));
        }
        playerTurn = 0;
        scoreBoard = new ScoreBoard(I, players,600, 50);
        gameRunning = true;
    }


    public void drawGame() {
        if (gameRunning) {
            I.background(255, 255, 255);
            board.drawBoard();
            scoreBoard.drawScoreBoard();
        }
        else{
            board.drawWinnerPanel(scoreBoard.getWinner());
        }
    }



    public void playerTurn(int xCord, int yCord) {
        Card returnCard = board.checkForCollision(xCord, yCord);
        if (returnCard != null) {
            returnCard.setCardColor(players.get(playerTurn).getPlayerColor());
            this.addScore( players.get(playerTurn), returnCard.getValue());
            if (playerTurn == players.size()-1) {
                playerTurn = 0;
            } else {
                playerTurn += 1;
            }
//            this.printScore();
            returnCard.setShow(true);

            updateFinishedGameState();
        }
    }
    private void addScore(Player player, int playerScore) {
        player.setPlayerScore(playerScore);
    }
    private void printScore() {
        for (int i = 0; i < players.size(); i++) {
            System.out.println(players.get(i).toString());
        }
    }
    public void updateFinishedGameState(){
        gameRunning = board.isBoardFinished();
    }
}