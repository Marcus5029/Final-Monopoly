//Marcus Smith
//48373771
 public class Card {
    private int cardWidth;
    private int cardHeight;
    private int value;
    private int xCord = 0;
    private int yCord = 0;
    private float[] cardColor = {0, 255, 0};
    private boolean show = false;
    private Launcher I;


    public Card(Launcher I, int value, int cardWidth, int cardHeight ) {
        this.I = I;
        this.cardWidth = cardWidth;
        this.cardHeight = cardHeight;
        this.value = value;
    }


    public void drawCard() {
        System.out.println(cardWidth);

        if (show == false) {
            I.fill((int)cardColor[0], (int)cardColor[1], (int)cardColor[2]);
            I.rect(xCord, yCord, cardWidth, cardHeight);
        } else {
            I.fill((int)cardColor[0], (int)cardColor[1], (int)cardColor[2]);
            I.rect(xCord, yCord, cardWidth, cardHeight);
            I.fill(0);
            I.textSize(35);
            I.text(value, xCord + cardWidth / 4, yCord + cardHeight / 2);
        }
    }


    void setCardColor(float[] cardColor) {
        this.cardColor = cardColor;
    }
    float[] getCardColor() {
        return cardColor;
    }
    int getValue() {
        return value;
    }
    void setValue(int value) {
        this.value = value;
    }
    int getXCord() {
        return xCord;
    }
    void setXCord(int xCord) {
        this.xCord = xCord;
    }
    int getYCord() {
        return yCord;
    }
    void setYCord(int yCord) {
        this.yCord = yCord;
    }
    boolean getShow() {
        return show;
    }
    void setShow(boolean show) {
        this.show = show;
    }
    int getCardWidth() {
        return cardWidth;
    }
    void setCardWidth( int cardWidth) {
        this.cardWidth = cardWidth;
    }
    int getCardHeight() {
        return cardHeight;
    }
    void setCardHeight( int cardHeight) {
        this.cardHeight = cardHeight;
    }
    public String toString() {
        return "Card value of: " + this.value + "with xCord" + this.xCord + "with yCord" + this.yCord;
    }
}

