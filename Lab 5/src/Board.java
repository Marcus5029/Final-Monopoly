//Marcus Smith
//48373771
import java.util.ArrayList;

import static java.lang.Math.random;

public class Board {
    private int CARD_WIDTH = 80;
    private int CARD_HEIGHT = 80;
    private int cardSpacing;
    private int rows;
    private int columns;
    private int numberOfCards = 0;
    private Launcher I;
    private ArrayList<Card> cards;


    Board(Launcher I, int cardSpacing, int numberOfCards, int rows, int columns) {
        this.I = I;
        this.cardSpacing = cardSpacing;
        this.numberOfCards = numberOfCards;
        this.rows = rows;
        this.columns = columns;
        cards = new ArrayList<Card>();
        createCards();
        shuffleCards();
        setupCardLocation();
    }

    private void createCards() {
        int cardValue = 1;

        for (int i=0; i < numberOfCards; i++)
        {
            cards.add(new Card( I, cardValue, CARD_WIDTH, CARD_HEIGHT));

            if ( i%2 == 0)
            {
                cardValue++;
            }
        }

    }

    private void shuffleCards() {

        for (int i = 0; i < numberOfCards; i++) {
            double r1 =  random() * cards.size();
            double r2 =  random() * cards.size();
            Card value1 = cards.get((int) r1);
            Card value2 = cards.get((int) r2);
            cards.set((int) r1, value2);
            cards.set((int) r2, value1);
        }
    }


    private void setupCardLocation() {
        int xCord = 10;
        int yCord = 10;
        int index = 0;
        for (int i = 0; i < rows; i++) {

            xCord = 10 + 120 * i;
            for (int t = 0; t < columns; t++) {
                yCord= 10 + 120 * t;
                Card tempCard = cards.get(index);
                tempCard.setXCord(xCord);
                tempCard.setYCord(yCord);
                index++;
            }
        }
    }


    public void drawBoard() {

        for (int i = 0; i < (numberOfCards ); i++) {

            Card tempCard = cards.get(i);
            tempCard.drawCard();
        }
    }


    public Card checkForCollision(int xCord, int yCord) {
        for (int i = 0; i < numberOfCards; i++) {
            Card tempCard = cards.get(i);
            int cardX = tempCard.getXCord();
            int cardY = tempCard.getYCord();
            if ((cardX < xCord) && (cardX + CARD_WIDTH > xCord) && (cardY< yCord) && (cardY + CARD_HEIGHT > yCord)) {
                return cards.get(i);
            }
        }
        return null;
    }
    public boolean isBoardFinished(){
        boolean finished = false;

        for(int i = 0; i < cards.size(); i++){
            Card card = cards.get(i);
            if(card.getShow()){
                finished = true;
            }
        }
        return finished;
    }
    public void drawWinnerPanel(Player winner){
        float[] playerColor = new float[3];
        for (int i = 0; i < playerColor.length; i++) {
            playerColor[i] = winner.getPlayerColor()[i];
        }
            I.background(playerColor[0], playerColor[1], playerColor[2]);
        I.fill(255);
                I.text(winner.getPlayerName(), I.width/2, I.height/2);

        }

    }

