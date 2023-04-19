//Marcus Smith
//48373771
import java.util.ArrayList;

public class Game {
    private int playerTurn = 0;
    private Board board;
    private boolean gameRunning;
    private ScoreBoard scoreBoard;
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
            returnCard.setCardColor(scoreBoard.getPlayerColor(playerTurn));
            scoreBoard.addPlayerScore(playerTurn, returnCard.getValue());
            playerTurn = (playerTurn+1)% scoreBoard.getPlayerCount();
            returnCard.setShow(true);

            updateFinishedGameState();
        }
    }


    public void updateFinishedGameState(){
        gameRunning = board.isBoardFinished();
    }
}