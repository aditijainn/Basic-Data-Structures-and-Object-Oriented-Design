/**
 * Name: Aditi Jain
 * ID: A17098965
 * Email: adj003@ucsd.edu
 * Sources used: None
 * 
 * Implements the RPS game using methods created in RPSAbstract.java.
 */

import java.util.Scanner;

/**
 * Contains all the methods that play the game using user input
 */
public class RPS extends RPSAbstract {
    
    /**
     * Assigns values to instance variables and creates arrays
     * @param moves - allowed moves inputted by the player 
     */
    public RPS(String[] moves) {
        this.possibleMoves = moves;
        this.playerMoves = new String[MAX_GAMES];
        this.cpuMoves = new String[MAX_GAMES];
    }

    /**
     * Takes the user move, the CPU move, and determines who wins.
     * @param playerMove - move of the player
     * @param cpuMove - move of the CPU
     * @return -1 for invalid move, 0 for tie, 1 for player win, 2 for cpu win
     */
    public int determineWinner(String playerMove, String cpuMove) {
        // TODO
        int output = INVALID_INPUT_OUTCOME;
        if(isValidMove(playerMove) && isValidMove(cpuMove)) {
            for(int i = 0; i<possibleMoves.length; i++) {
                int index = i+1;
                if(i<possibleMoves.length-1) {
                    if(possibleMoves[i].equals(playerMove)) {
                        if(possibleMoves[index].equals(cpuMove)) {
                            output = PLAYER_WIN_OUTCOME;
                        }
                    }
                    else if(possibleMoves[i].equals(cpuMove)) {
                        if(possibleMoves[index].equals(playerMove)) {
                            output = CPU_WIN_OUTCOME;
                        }
                    }
                }
                else if(possibleMoves[possibleMoves.length-1].equals(playerMove)) {
                    if(possibleMoves[possibleMoves.length-2].equals(cpuMove)) {
                        output = CPU_WIN_OUTCOME;
                    }
                    else if(possibleMoves[0].equals(cpuMove)) {
                        output = PLAYER_WIN_OUTCOME;
                    }
                }
                else if(possibleMoves[possibleMoves.length-1].equals(cpuMove)) {
                    if(possibleMoves[possibleMoves.length-2].equals(playerMove)) {
                        output = PLAYER_WIN_OUTCOME;
                    }
                    else if(possibleMoves[0].equals(playerMove)) {
                        output = CPU_WIN_OUTCOME;
                    }
                }
            }
            if(output == INVALID_INPUT_OUTCOME) {
                output = TIE_OUTCOME;
            }
            return output;
        }
        else {
            return output;
        }
    }

    /**
     * Main method that reads user input, generates cpu move, and plays game
     * 
     * @param args - arguments passed in from command line in String form
     */
    public static void main(String[] args) {
        // If command line args are provided use those as the possible moves
        String[] moves = new String[args.length];
        if (args.length >= MIN_POSSIBLE_MOVES) {
            for (int i = 0; i < args.length; i++) {
                moves[i] = args[i];
            }
        } else {
            moves = RPS.DEFAULT_MOVES;
        }
        // Create new game and scanner
        RPS game = new RPS(moves);
        Scanner in = new Scanner(System.in);

        // While user does not input "q", play game
        System.out.println(PROMPT_MOVE);
        String input = in.nextLine();
        while(!input.equals(QUIT)) {
            if(game.isValidMove(input)) {
                game.play(input, game.genCPUMove());
                System.out.println(PROMPT_MOVE);
                input = in.nextLine();
            }
            else {
                System.out.println(INVALID_INPUT);
                System.out.println(PROMPT_MOVE);
                input = in.nextLine();
            }
        }
        if(input.equals("q")) {
            game.end();
        }
        in.close();
    }
}
