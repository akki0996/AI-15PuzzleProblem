import java.util.ArrayList;
import java.util.HashMap;

public class Search {

    public ArrayList<Node> generate_successors(Node parent) {

        ArrayList<Node> possibleMovements = new ArrayList<Node>();
        int curr_x = parent.empty_x;
        int curr_y = parent.empty_y;

        if (curr_y + 1 < 4) {
            Node rightMove = create_node(parent, curr_x, curr_y, curr_x,  curr_y + 1);
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
            Node upMove = create_node(parent, curr_x, curr_y,curr_x - 1, curr_y);
            possibleMovements.add(upMove);
        }

        return possibleMovements;
    }

    public Node create_node(Node parent, int curr_x, int curr_y, int new_x, int new_y) {
        Node node = new Node(parent, parent.puzzle_board, curr_x, curr_y,new_x, new_y);
        node.cost_source_current_state = parent.cost_source_current_state + 1;
        node.depth = parent.depth + 1;
        return node;
    }

    public boolean check_repeats(Node root) {
        Node parent = root.parent;

        if(root != null) {
            while (parent != null) {
                if (root.equals(parent))
                    return true;
                parent = parent.parent;
            }
        }

        return false;
    }

    public void output_summary(Node goal, int num_created, int num_expanded, int max_fringe) {

        Node parent = goal.parent;
        int sol_depth = 0;

        while(parent != null) {
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


    public HashMap<Character, int[]> board_conversion_map(Node goal_state) {
        char[][] board = goal_state.puzzle_board;
        HashMap<Character, int[]> hashMap = new HashMap<>();

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                hashMap.put(board[i][j], new int[]{i, j});
            }
        }
        return hashMap;
    }

    public void calc_cost(Node child, Node goal, String heuristic, String search, HashMap<Character, int[]> tile_value_map) {
        if(heuristic.equals("h1"))
            child.total_cost = Heuristic.misplaced_tiles(child, goal);

        if(heuristic.equals("h2"))
            child.total_cost = Heuristic.manhattan_Distance(child, goal, tile_value_map);

        if(search.equals("AStar"))
            child.total_cost += child.cost_source_current_state;
    }

}
