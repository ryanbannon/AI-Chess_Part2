import java.util.*;

public class AIAgent{
  Random rand;
  //declaring new variables
  ChessProject chessProject;
  boolean possibleAttack = false;

  public AIAgent(){
    rand = new Random();
  }
  /*
    The method randomMove takes as input a stack of potential moves that the AI agent
    can make. The agent uses a rondom number generator to randomly select a move from
    the inputted Stack and returns this to the calling agent.
    */

//Random Move
  public Move randomMove(Stack possibilities){
    int moveID = rand.nextInt(possibilities.size());
    System.out.println("Agent randomly selected move : "+moveID);
    for(int i=1;i < (possibilities.size()-(moveID));i++){
      possibilities.pop();
    }
    Move selectedMove = (Move)possibilities.pop();
    return selectedMove;
  }

//Next best move

    public Move nextBestMove(Stack whitePossibleMoves, Stack blackPossibleMoves) {
        Stack backup = (Stack) whitePossibleMoves.clone();
        Stack black = (Stack) blackPossibleMoves.clone();
        Move whiteMove, standardMove, attackMove;
        Square blackPosition;
        int Point = 0;
        int chosenPiece = 0;
        attackMove = null;

        while (!whitePossibleMoves.empty()) {
            whiteMove = (Move) whitePossibleMoves.pop();
            standardMove = whiteMove;

            //assign 1 point to centre position on board
            if ((standardMove.getStart().getYC() < standardMove.getLanding().getYC())
                    && (standardMove.getLanding().getXC() == 3) && (standardMove.getLanding().getYC() == 3)
                    || (standardMove.getLanding().getXC() == 4) && (standardMove.getLanding().getYC() == 3)
                    || (standardMove.getLanding().getXC() == 3) && (standardMove.getLanding().getYC() == 4)
                    || (standardMove.getLanding().getXC() == 4) && (standardMove.getLanding().getYC() == 4)) {
                Point = 0;
                //assign best move
                if (Point > chosenPiece) {
                    chosenPiece = Point;
                    attackMove = standardMove;
                }
            }

            /*
            return an attacking move if piece has higher point value than centre position
            Note: I have assigned the BlackPawn 2 points which is unconventional so as to have the centre poisition take priority
            Over a random move
            */
            while (!black.isEmpty()) {
                Point = 0;
                blackPosition = (Square) black.pop();
                if ((standardMove.getLanding().getXC() == blackPosition.getXC()) && (standardMove.getLanding().getYC() == blackPosition.getYC())) {
                    //Check for black pawn and assign points
                    if (blackPosition.getName().equals("BlackPawn")) {
                        Point = 2;
                    }
                    //Check for black bishop/knight and assign points
                    else if (blackPosition.getName().equals("BlackBishop") || blackPosition.getName().equals("BlackKnight")) {
                        Point = 3;
                    }
                    //Check for black rook and assign points
                    else if (blackPosition.getName().equals("BlackRook")) {
                        Point = 5;
                    }
                    //Check for black queen and assign points
                    else if (blackPosition.getName().equals("BlackQueen")) {
                        Point = 9;
                    }
                    //Check for black king and assign highest number as king is most valuable and wins the game
                    else if (blackPosition.getName().equals("BlackKing")) {
                        Point = 10;
                    }
                }
                //update bestmove
                if (Point > chosenPiece) {
                    chosenPiece = Point;
                    attackMove = standardMove;
                }
            }
            //reload black squares
            black = (Stack) blackPossibleMoves.clone();
        }
        // use best move if available or just go random
        if (chosenPiece > 0) {
            System.out.println("Best move selected by AI agent:" + chosenPiece);
            return attackMove;
        }

        return randomMove(backup);
    }

// Two Levels Deep

  public Move twoLevelsDeep(Stack whitePossibleMoves, Stack blackPossibleMoves){
		Stack backup = (Stack) whitePossibleMoves.clone();
        Stack black = (Stack) blackPossibleMoves.clone();
        Move whiteMove, standardMove, attackMove;
        Square blackPosition;
        int Point = 0;
        int chosenPiece = 0;
        attackMove = null;

        while (!whitePossibleMoves.empty()) {
            whiteMove = (Move) whitePossibleMoves.pop();
            standardMove = whiteMove;

            //assign 1 point to centre position on board
            if ((standardMove.getStart().getYC() < standardMove.getLanding().getYC())
                    && (standardMove.getLanding().getXC() == 3) && (standardMove.getLanding().getYC() == 3)
                    || (standardMove.getLanding().getXC() == 4) && (standardMove.getLanding().getYC() == 3)
                    || (standardMove.getLanding().getXC() == 3) && (standardMove.getLanding().getYC() == 4)
                    || (standardMove.getLanding().getXC() == 4) && (standardMove.getLanding().getYC() == 4)) {
                Point = 0;
                //assign best move
                if (Point > chosenPiece) {
                    chosenPiece = Point;
                    attackMove = standardMove;
                }
            }
            /*
            return an attacking move if piece has higher point value than centre position
            Note: I have assigned the BlackPawn 2 points which is unconventional so as to have the centre poisition take priority
            Over a random move
            */
            while (!black.isEmpty()) {
                Point = 0;
                blackPosition = (Square) black.pop();
                if ((standardMove.getLanding().getXC() == blackPosition.getXC()) && (standardMove.getLanding().getYC() == blackPosition.getYC())) {
                    //Check for black pawn and assign points
                    if (blackPosition.getName().equals("BlackPawn")) {
                        Point = 2;
                    }
                    //Check for black bishop/knight and assign points
                    else if (blackPosition.getName().equals("BlackBishop") || blackPosition.getName().equals("BlackKnight")) {
                        Point = 3;
                    }
                    //Check for black rook and assign points
                    else if (blackPosition.getName().equals("BlackRook")) {
                        Point = 5;
                    }
                    //Check for black queen and assign points
                    else if (blackPosition.getName().equals("BlackQueen")) {
                        Point = 9;
                    }
                    //Check for black king and assign highest number as king is most valuable and wins the game
                    else if (blackPosition.getName().equals("BlackKing")) {
                        Point = 10;
                    }
                }
                //update bestmove
                if (Point > chosenPiece) {
                    chosenPiece = Point;
                    attackMove = standardMove;
                }
            }
            //reload black squares
            black = (Stack) blackPossibleMoves.clone();
        }
        // use best move if available or just go random
        if (chosenPiece > 0) {
            System.out.println("Two step best move selected by AI agent:" + chosenPiece);
            return attackMove;
        }
        return randomMove(backup);
    }
}
