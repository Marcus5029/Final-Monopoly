package gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

//Zelda Shirk & Marcus Smith
//CRCP3305 Final Project

public class MonopolyMain extends JFrame{

    private JPanel contentIncluder;
    static JTextArea infoConsole;
    JPanel playerAssetsPanel;
    CardLayout c1 = new CardLayout();
    ArrayList<Player> players = new ArrayList<Player>();
    static int turnCounter = 0;
    JButton btnNextTurn;
    JButton btnRollDice;
    JButton btnPayRent;
    JButton btnBuy;
    JTextArea panelPlayer1TextArea;
    JTextArea panelPlayer2TextArea;
    Board gameBoard;
    Player player1;
    Player player2;
    Boolean doubleDiceForPlayer1 = false;
    Boolean doubleDiceForPlayer2 = false;
    static int nowPlaying = 0;
    int myInt = 10;
    Integer myInteger = myInt;



    public MonopolyMain() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setSize(1080,720);
        contentIncluder = new JPanel();
        contentIncluder.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentIncluder);
        contentIncluder.setLayout(null);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(225, 6, 632, 630);
        contentIncluder.add(layeredPane);

        gameBoard = new Board(6,6,612,612);
        gameBoard.setBackground(new Color(0, 0, 255));
        layeredPane.add(gameBoard, Integer.valueOf(0));

        player1 = new Player(1, Color.GREEN);
        players.add(player1);
        layeredPane.add(player1, Integer.valueOf(1));

        player2 = new Player(2, Color.PINK);
        players.add(player2);
        layeredPane.add(player2, Integer.valueOf(1));

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.BLUE);

        rightPanel.setBounds(0, 6, 1100, 700);
        contentIncluder.add(rightPanel);
        rightPanel.setLayout(null);

        btnBuy = new JButton("Buy");
        btnBuy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Player currentPlayer = players.get(nowPlaying);
                infoConsole.setText("You bought "+gameBoard.getAllSquares().get(currentPlayer.getCurrentSquareNumber()).getName());
                currentPlayer.buyTitleDeed(currentPlayer.getCurrentSquareNumber());
                int withdrawAmount = gameBoard.getAllSquares().get(currentPlayer.getCurrentSquareNumber()).getPrice();
                currentPlayer.withdrawFromWallet(withdrawAmount);
                btnBuy.setEnabled(false);
                updatePanelPlayer1TextArea();
                updatePanelPlayer2TextArea();
            }
        });
        btnBuy.setBounds(250, 635, 117, 29);
        rightPanel.add(btnBuy);
        btnBuy.setEnabled(false);

        btnPayRent = new JButton("Pay Rent");
        btnPayRent.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Player currentPlayer = players.get(nowPlaying);
                Player ownerOfTheSquare = players.get((Player.ledger.get(currentPlayer.getCurrentSquareNumber()))==1?0:1);
                infoConsole.setText("You paid to the player "+ownerOfTheSquare.getPlayerNumber());

                int withdrawAmount = gameBoard.getAllSquares().get(currentPlayer.getCurrentSquareNumber()).getRentPrice();
                System.out.println(withdrawAmount);
                currentPlayer.withdrawFromWallet(withdrawAmount);
                ownerOfTheSquare.depositToWallet(withdrawAmount);

                btnNextTurn.setEnabled(true);
                btnPayRent.setEnabled(false);

                updatePanelPlayer1TextArea();
                updatePanelPlayer2TextArea();
            }

        });
        btnPayRent.setBounds(700, 635, 117, 29);
        rightPanel.add(btnPayRent);
        btnPayRent.setEnabled(false);

        Dice dice1 = new Dice(200, 206, 40, 40);
        layeredPane.add(dice1, Integer.valueOf(1));

        Dice dice2 = new Dice(375, 406, 40, 40);
        layeredPane.add(dice2, Integer.valueOf(1));

        btnRollDice = new JButton("Roll Dice");
        btnRollDice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if(nowPlaying == 0) {

                    int dice1OldValue = dice1.getFaceValue();
                    int dice2OldValue = dice2.getFaceValue();
                    dice1.rollDice();
                    dice2.rollDice();
                    int dicesTotal = dice1.getFaceValue() + dice2.getFaceValue();
                    if(dice1.getFaceValue() == dice2.getFaceValue()) {
                        doubleDiceForPlayer1 = true;
                    } else {
                        doubleDiceForPlayer1 = false;
                    }
                    player1.move(dicesTotal);
                    if(Player.ledger.containsKey(player1.getCurrentSquareNumber())
                            && Player.ledger.get(player1.getCurrentSquareNumber()) != player1.getPlayerNumber()
                    ) {
                        btnBuy.setEnabled(false);
                        btnRollDice.setEnabled(false);
                        btnNextTurn.setEnabled(false);
                        btnPayRent.setEnabled(true);
                    }
                    if (Player.ledger.containsKey(player1.getCurrentSquareNumber())
                            && Player.ledger.get(player1.getCurrentSquareNumber()) == player1.getPlayerNumber()) {
                        btnBuy.setEnabled(false);
                        btnPayRent.setEnabled(false);
                        btnNextTurn.setEnabled(true);
                    }
                    if(gameBoard.getUnbuyableSquares().contains(gameBoard.getAllSquares().get(player1.getCurrentSquareNumber()))) {
                        btnBuy.setEnabled(false);
                        btnNextTurn.setEnabled(true);
                    } else if (!Player.ledger.containsKey(player1.getCurrentSquareNumber())) {
                        btnBuy.setEnabled(true);
                        btnNextTurn.setEnabled(true);
                        btnPayRent.setEnabled(false);
                    }


                } else {
                    // player2's turn
                    int dice1OldValue = dice1.getFaceValue();
                    int dice2OldValue = dice2.getFaceValue();
                    dice1.rollDice();
                    dice2.rollDice();
                    int dicesTotal = dice1.getFaceValue() + dice2.getFaceValue();
                    if(dice1.getFaceValue() == dice2.getFaceValue()) {
                        doubleDiceForPlayer2 = true;
                    } else {
                        doubleDiceForPlayer2 = false;
                    }
                    player2.move(dicesTotal);
                    if(Player.ledger.containsKey(player2.getCurrentSquareNumber())
                            && Player.ledger.get(player2.getCurrentSquareNumber()) != player2.getPlayerNumber()
                    ) {
                        btnBuy.setEnabled(false);
                        btnRollDice.setEnabled(false);
                        btnNextTurn.setEnabled(false);
                        btnPayRent.setEnabled(true);
                    }
                    if(Player.ledger.containsKey(player2.getCurrentSquareNumber())
                            && Player.ledger.get(player2.getCurrentSquareNumber()) == player2.getPlayerNumber()) {
                        btnBuy.setEnabled(false);
                        btnPayRent.setEnabled(false);

                    }
                    if(gameBoard.getUnbuyableSquares().contains(gameBoard.getAllSquares().get(player2.getCurrentSquareNumber()))) {
                        btnBuy.setEnabled(false);
                        btnNextTurn.setEnabled(true);
                    } else if (!Player.ledger.containsKey(player2.getCurrentSquareNumber())) {
                        btnBuy.setEnabled(true);
                        btnNextTurn.setEnabled(true);
                        btnPayRent.setEnabled(false);
                    }

                }

                btnRollDice.setEnabled(false);
                if(doubleDiceForPlayer1 || doubleDiceForPlayer2) {
                    infoConsole.setText("Click Next Turn!");
                } else {
                    infoConsole.setText("Click Next Turn!");
                }


                layeredPane.remove(gameBoard);
                layeredPane.add(gameBoard, Integer.valueOf(0));

                updatePanelPlayer1TextArea();
                updatePanelPlayer2TextArea();

            }
        });
        btnRollDice.setBounds(840, 7, 246, 700);
        rightPanel.add(btnRollDice);

        btnNextTurn = new JButton("Next Turn");
        btnNextTurn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnRollDice.setEnabled(true);
                btnBuy.setEnabled(false);
                btnPayRent.setEnabled(false);
                btnNextTurn.setEnabled(false);

                if(nowPlaying == 0 && doubleDiceForPlayer1) {
                    nowPlaying = 0;
                    doubleDiceForPlayer1 = false;
                } else if(nowPlaying == 1 && doubleDiceForPlayer2) {
                    nowPlaying = 1;
                    doubleDiceForPlayer2 = false;
                } else if(!doubleDiceForPlayer1 && !doubleDiceForPlayer2) {
                    nowPlaying = (nowPlaying + 1) % 2;
                }


                c1.show(playerAssetsPanel, ""+(nowPlaying==0 ? 1 : 2));
                updatePanelPlayer1TextArea();
                updatePanelPlayer2TextArea();
                infoConsole.setText("Player "+(nowPlaying==0 ? 1 : 2)+"'s turn!");
            }



        });

        btnNextTurn.setBounds(425, 620, 235, 53);
        rightPanel.add(btnNextTurn);
        btnNextTurn.setEnabled(false);

        JPanel test = new JPanel();
        test.setBounds(840, 10, 246, 750);
        rightPanel.add(test);
        test.setLayout(null);

        playerAssetsPanel = new JPanel();
        playerAssetsPanel.setBounds(1, 8, 230, 750);
        rightPanel.add(playerAssetsPanel);
        playerAssetsPanel.setLayout(c1);

        JPanel panelPlayer1 = new JPanel();
        panelPlayer1.setBackground(Color.RED);
        playerAssetsPanel.add(panelPlayer1, "1");
        panelPlayer1.setLayout(null);

        JLabel panelPlayer1Title = new JLabel("Player 1's Assets");
        panelPlayer1Title.setForeground(Color.WHITE);
        panelPlayer1Title.setHorizontalAlignment(SwingConstants.CENTER);
        panelPlayer1Title.setBounds(0, 6, 240, 16);
        panelPlayer1.add(panelPlayer1Title);

        panelPlayer1TextArea = new JTextArea();
        panelPlayer1TextArea.setBounds(10, 34, 230, 149);
        panelPlayer1.add(panelPlayer1TextArea);

        JPanel panelPlayer2 = new JPanel();
        panelPlayer2.setBackground(Color.BLUE);
        playerAssetsPanel.add(panelPlayer2, "2");
        panelPlayer2.setLayout(null);
        c1.show(playerAssetsPanel, ""+nowPlaying);

        JLabel panelPlayer2Title = new JLabel("Player 2's Assets");
        panelPlayer2Title.setForeground(Color.WHITE);
        panelPlayer2Title.setHorizontalAlignment(SwingConstants.CENTER);
        panelPlayer2Title.setBounds(0, 6, 240, 16);
        panelPlayer2.add(panelPlayer2Title);

        panelPlayer2TextArea = new JTextArea();
        panelPlayer2TextArea.setBounds(10, 34, 230, 149);
        panelPlayer2.add(panelPlayer2TextArea);

        updatePanelPlayer1TextArea();
        updatePanelPlayer2TextArea();


        infoConsole = new JTextArea();
        infoConsole.setColumns(20);
        infoConsole.setRows(5);
        infoConsole.setBounds(6, 6, 234, 56);
        test.add(infoConsole);
        infoConsole.setLineWrap(true);
        infoConsole.setText("Player 1: start the game by clicking Roll Dice!");

    }


    public void updatePanelPlayer2TextArea() {
        String result = "";
        result += "Current Balance: "+player2.getWallet()+"\n";

        result += "Title Deeds: \n";
        for(int i = 0; i < player2.getTitleDeeds().size(); i++) {
            result += " - "+gameBoard.getAllSquares().get(player2.getTitleDeeds().get(i)).getName()+"\n";
        }

        panelPlayer2TextArea.setText(result);
    }

    public void updatePanelPlayer1TextArea() {
        String result = "";
        result += "Current Balance: "+player1.getWallet()+"\n";

        result += "Title Deeds: \n";
        for(int i = 0; i < player1.getTitleDeeds().size(); i++) {
            result += " - "+gameBoard.getAllSquares().get(player1.getTitleDeeds().get(i)).getName()+"\n";
        }


        panelPlayer1TextArea.setText(result);
    }

    public static void errorBox(String infoMessage, String titleBar) {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.ERROR_MESSAGE);
    }


    public static void main(String[] args) {

        MonopolyMain frame = new MonopolyMain();
        frame.setVisible(true);

    }

}