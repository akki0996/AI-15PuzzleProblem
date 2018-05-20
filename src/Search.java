import java.util.ArrayList;
import java.util.HashMap;

public class Search {

    public ArrayList<Node> generate_successors(Node parent) {

        ArrayList<Node> possibleMovements = new ArrayList<Node>();
        int curr_x = parent.empty_x;
        int curr_y = parent.empty_y;

        if (curr_y + 1 < 4) {
            Node rightMove = create_node(parent, curr_x, curr_y, curr_x, curr_y + 1);
            possibleMovements.add(rightMove);
        }

        if (curr_x + 1 < 4) {
            Node downMove = create_node(parent, curr_x, curr_y, curr_x + 1, curr_y);
            possibleMovements.add(downMove);
        }

        if (curr_y - 1 > -1) {
            Node leftMove = create_node(parent, curr_x, curr_y, curr_x, curr_y - 1);
            possibleMovements.add(leftMove);
        }

        if (curr_x - 1 > -1) {
            Node upMove = create_node(parent, curr_x, curr_y, curr_x - 1, curr_y);
            possibleMovements.add(upMove);
        }

        return possibleMovements;
    }

    public Node create_node(Node parent, int curr_x, int curr_y, int new_x, int new_y) {
        Node node = new Node(parent, parent.puzzle_board, curr_x, curr_y, new_x, new_y);
        node.cost_source_current_state = parent.cost_source_current_state + 1;
        node.depth = parent.depth + 1;
        return node;
    }

    public void output_summary(Node goal, int num_created, int num_expanded, int max_fringe) {

        Node parent = goal.parent;
        int sol_depth = 0;

        while (parent != null) {
            sol_depth++;
            parent = parent.parent;
        }

        print_summary(sol_depth, num_created, num_expanded, max_fringe);
    }


    public void print_summary(int depth, int num_created, int num_expanded, int max_fringe) {
        System.out.println("Depth:" + depth
                + "\t num_created: " + num_created
                + "\t num_expanded: " + num_expanded
                + "\t max_fringe: " + max_fringe);
    }


    public HashMap<Character, int[]> board_conversion_map(Node goal_node) {

        char[][] goal_state = goal_node.puzzle_board;

        HashMap<Character, int[]> hashMap = new HashMap<>();

        for (int i = 0; i < goal_state.length; i++) {
            for (int j = 0; j < goal_state[i].length; j++) {
                hashMap.put(goal_state[i][j], new int[]{i, j});
            }
        }
        return hashMap;
    }

    /**
     *
     * @param child_node child node of the curr_node
     * @param goal_node goal_node of the current game
     * @param heuristic heuristic to to calculated
     * @param search type of search (AStar vs GBFS)
     * @param tile_value_map contains the index of each value in the goal_state
     */
    public void calc_cost(Node child_node, Node goal_node, String heuristic, String search, HashMap<Character, int[]> tile_value_map) {
        if (heuristic.equals("h1"))
            child_node.total_cost = Heuristic.misplaced_tiles(child_node, goal_node);

        if (heuristic.equals("h2"))
            child_node.total_cost = Heuristic.manhattan_Distance(child_node, goal_node, tile_value_map);

        if (search.equals("AStar"))
            child_node.total_cost += child_node.cost_source_current_state;
    }

    /**
     *
     * @param curr_state curr_state of the game
     * @param goalStateOne first goal_state of the game
     * @param goalStateTwo second goal_state of the game
     * @return if the curr_state has reached the goal_state
     */
    public boolean hasCurrStateReachedGoalState(String curr_state, String goalStateOne, String goalStateTwo) {
        if (curr_state.equals(goalStateOne)) return true;
        if (curr_state.equals(goalStateTwo)) return true;
        return false;
    }
}

