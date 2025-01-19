import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Gui {
    private JFrame frame;
    private JPanel gamePanel;
    private JPanel infoPanel;
    private GameButton[] buttonArr = new GameButton[9];
    private JButton resetButton = new JButton("Reset");
    private final Font GAME_FONT = new Font("Arial", Font.BOLD, 100);
    private ActionListener buttonListener;
    private JTextArea gameInfo = new JTextArea();

    ActionListener resetListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("hi");
            frame.setVisible(false);
            frame.dispose();
            Board newB = new Board();
        }
    };



   Gui(ActionListener buttonListener){
       this.buttonListener = buttonListener;

       init();
       frame.setVisible(true);
   }

   private void init(){



       frame = new JFrame();
       frame.setSize(new Dimension(501,551)); //divisble by 3
       frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       frame.setLayout(new BorderLayout());
       frame.setResizable(false);


       gamePanel = new JPanel(new GridLayout(3,3,0,0));
       gamePanel.setPreferredSize(new Dimension(501,501));
       addButtons(gamePanel);

       gameInfo.setEditable(false);
       gameInfo.setFont(new Font("Arial", Font.BOLD, 40));
       gameInfo.setBorder(new BasicBorders.MarginBorder());
       gameInfo.setText("Your Turn");
       resetButton.setFont(new Font("Arial", Font.BOLD, 20));

       infoPanel = new JPanel(new BorderLayout());
       infoPanel.setPreferredSize(new Dimension(501,50));
       infoPanel.add(resetButton, BorderLayout.EAST);
       infoPanel.add(gameInfo, BorderLayout.CENTER);

       frame.add(gamePanel, BorderLayout.CENTER);
       frame.add(infoPanel, BorderLayout.SOUTH);

       resetButton.addActionListener(resetListener);




   }

   public void addButtons(JPanel panel){
       for(GameButton gameButton : buttonArr){
           gameButton = new GameButton();
           gameButton.addActionListener(buttonListener);
           gameButton.setFont(GAME_FONT);
           gameButton.setFocusable(false);
           panel.add(gameButton);
       }
   }

   public void won(String letter){
       gameInfo.setText(letter +"Won!");
   }

    public void updateTurn(String letter){

        gameInfo.setText( letter  + " 's turn");
    }


}







