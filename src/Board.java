import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Board {

    Player x = new Player("x");
    Player o  = new Player("o");
    Player playing = x;
    Gui board;
    boolean won = false;
    ActionListener buttonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            GameButton g = (GameButton) e.getSource();
            handleButton(g);
        }
    };

    Board(){
         board = new Gui(buttonListener);


    }

    public void handleButton(GameButton button){
        button.setText(playing.letter);
        button.removeActionListener(buttonListener);
        playing.moves.add(button.index);
        gameLogic();
        if(playing == x){
            playing = o;
        }else{
            playing = x;
        }
        if(!won){
            board.updateTurn(playing.letter);
        }

    }

    private final int[][] winningMoves = {
            {0, 1, 2},  // Top row
            {3, 4, 5},  // Middle row
            {6, 7, 8},  // Bottom row
            {0, 3, 6},  // Left column
            {1, 4, 7},  // Middle column
            {2, 5, 8},  // Right column
            {0, 4, 8},  // Diagonal from top-left to bottom-right
            {2, 4, 6}   // Diagonal from top-right to bottom-left
    };

    private void gameLogic(){
        int count = 0;

            for(int[] j : winningMoves){
                for(int k : j){
                    for(int l : playing.moves){
                        if(l == k){
                            count ++;
                        }
                    }



                }
                if(count == 3){
                    won = true;
                    won();
                }

                count = 0;
            }


    }

    private void won(){
        board.won(playing.letter);
    }




}
