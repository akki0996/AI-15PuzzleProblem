import java.util.HashMap;

public class Heuristic {

    public static int misplaced_tiles(Node curr_state, Node goal_state) {
        char[][] board1 = curr_state.puzzle_board;
        char[][] board2 = goal_state.puzzle_board;

        int misplaced_tiles = 0;

        for(int i = 0; i < board1.length; i++) {
            for(int j = 0; j < board1[i].length; j++) {
                if(board1[i][j] != ' ') {
                    if (board1[i][j] != (board2[i][j])) {
                        misplaced_tiles++;
                    }
                } else {
                    System.out.println("Hey what's up hello");
                }
            }
        }

        return misplaced_tiles;
    }


    public static int manhattan_Distance(Node curr_state, Node goal_state, HashMap<Character, int[]> hashMap) {

        char[][] board1 = curr_state.puzzle_board;
        char[][] board2 = goal_state.puzzle_board;

        int manhattan_dist = 0;

        for(int i = 0; i < board1.length; i++) {
            for(int j = 0; j < board1[i].length; j++) {
                if(board1[i][j] != ' ') {
                    if(board1[i][j] != (board2[i][j])) {
                        int[] positions = hashMap.get(board1[i][j]);
                        manhattan_dist += Math.abs(i - positions[0]) + Math.abs(j - positions[1]);
                    }
                } else {
                    System.out.println("Hey what's up hello");
                }

            }
        }
        return manhattan_dist;
    }
}
