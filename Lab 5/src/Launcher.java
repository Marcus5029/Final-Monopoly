//Marcus Smith
//48373771
import processing.core.*;

public class Launcher extends PApplet {
    Game game = new Game(this, 2, 40, 20, 5, 4);
//Board board = new Board(this, 40, 20, 4, 5);
    public void settings(){size(600,800);
    }

       public void draw(){
//           board.drawBoard();
           game.drawGame();

        }
        public void mousePressed(){
           game.playerTurn(mouseX, mouseY);

        }
    public static void main(String[]  args) {

        PApplet.main("Launcher", args);

    }

    }