//Marcus Smith
//48373771
import static java.lang.Math.random;

public class Player {
    private String playerName;
    private int playerScore;
    private float[] playerColor = new float[] {(float) (random() * 255), (float) (random() * 255), (float) (random() * 255)};
    private Launcher I;

    Player(Launcher I, String playerName) {

        this.playerName = playerName;
        playerScore = 0;
    }

    public void addScore(int score) {
        playerScore = playerScore + score;
    }
    public float[] getPlayerColor() {
        return playerColor;
    }
    void setPlayerColor(float[] playerColor) {
        this.playerColor = playerColor;
    }
    public String getPlayerName() {
        return playerName;
    }
    void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public int getPlayerScore() {
        return playerScore;
    }
    void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }
    public String toString() {
        String returnObject = this.playerName+ ":" + this.playerScore;

        return returnObject;
    }
}