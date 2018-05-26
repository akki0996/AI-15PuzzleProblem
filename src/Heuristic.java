import java.util.HashMap;

/**
 * This class helps in calculating the h1 and h2 heuristics mentioned in Russell & Norvig
 */
public class Heuristic {

    /**
     * This method calculates the heuristic of misplaced tiles.
     * @param curr_node the current node
     * @param goal_node the goal node
     * @return total number of misplaced tiles.
     */
    public static int misplaced_tiles(Node curr_node, Node goal_node) {
        char[][] curr_state = curr_node.puzzle_board;
        char[][] goal_state = goal_node.puzzle_board;

        int misplaced_tiles = 0;

        for(int i = 0; i < curr_state.length; i++) {
            for(int j = 0; j < curr_state[i].length; j++) {
                if(curr_state[i][j] != ' ') {
                    if (curr_state[i][j] != (goal_state[i][j])) {
                        misplaced_tiles++;
                    }
                } else {
                    continue;
                }
            }
        }
        return misplaced_tiles;
    }

    /**
     *
     * @param curr_node the current node
     * @param goal_node the goal node
     * @param hashMap contains the position of the goal state characters in a hashmap
     * @return the manhattan distance while curr_state to goal_state
     */
    public static int manhattan_Distance(Node curr_node, Node goal_node, HashMap<Character, int[]> hashMap) {

        char[][] curr_state = curr_node.puzzle_board;
        char[][] goal_state = goal_node.puzzle_board;

        int manhattan_dist = 0;

        for(int i = 0; i < curr_state.length; i++) {
            for(int j = 0; j < curr_state[i].length; j++) {
                if(curr_state[i][j] != ' ') {
                    if(curr_state[i][j] != (goal_state[i][j])) {
                        int[] positions = hashMap.get(curr_state[i][j]);
                        manhattan_dist += Math.abs(i - positions[0]) + Math.abs(j - positions[1]);
                    }
                } else {
                    continue;
                }
            }
        }
        return manhattan_dist;
    }
}
