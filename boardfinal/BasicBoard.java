package boardfinal;

public class BasicBoard implements Board, Redraw extends SetupWindow {

      public BasicBoard() {

          board = new Spot[8][8];

          public void buildBoard() {

              for(int i = 0; i < 8; i++) {
                for(int j = 0; j < 8; j++) {
                    board[i][j].add(new Spot(i, j));
                }
              }
          }
      }
}
