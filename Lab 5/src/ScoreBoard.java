//Marcus Smith
//48373771

import java.util.ArrayList;

public class ScoreBoard {
    private Launcher I;
    private float scoreBoardX;
    private float scoreBoardY;
    private float PLAYER_NAME_WIDTH = 300;
    private float PLAYER_SCORE_WIDTH = 100;
    private float SCORE_BOARD_SINGLE_HEIGHT = 75;
    private ArrayList<Player> players;

    ScoreBoard(Launcher I, ArrayList<Player> players, float scoreBoardY, float scoreBoardX) {
        this.I = I;
        this.scoreBoardX = scoreBoardX;
        this.scoreBoardY = scoreBoardY;
        this.players = players;


    }

    public void drawScoreBoard() {
        float tempY = scoreBoardY;
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);

            I.textSize(40);
            I.fill(player.getPlayerColor()[0], player.getPlayerColor()[1], player.getPlayerColor()[2]);
            I.rect(scoreBoardX, tempY, PLAYER_NAME_WIDTH, SCORE_BOARD_SINGLE_HEIGHT);
            I.rect(scoreBoardX + PLAYER_NAME_WIDTH, tempY, PLAYER_SCORE_WIDTH, SCORE_BOARD_SINGLE_HEIGHT);
            I.fill(0);
            I.text(player.getPlayerName(), scoreBoardX, (tempY + 45));
            I.text(player.getPlayerScore(), (scoreBoardX + PLAYER_NAME_WIDTH + 20), (tempY + 45));
            tempY = tempY + SCORE_BOARD_SINGLE_HEIGHT;

        }
    }

    public void addPlayerScore(int playerIndex, int score) {
       Player player = players.get(playerIndex);
       player.getPlayerScore();
       player.setPlayerScore(score + player.getPlayerScore());


        }
public float[] getPlayerColor(int playerIndex){
    Player player = players.get(playerIndex);
    return player.getPlayerColor();
}
public int getPlayerCount(){
        return players.size();
}
public Player getWinner(){
        int index = 0;
        int highestScore = 0;

        for(int i = 0; i < players.size(); i++){
            if(players.get(i).getPlayerScore() == highestScore){
                return new Player(I, "draw");
            }
            if(players.get(i).getPlayerScore() > highestScore){
                index = i;
                highestScore = players.get(i).getPlayerScore();
            }
        }
        return players.get(index);
}
    }


