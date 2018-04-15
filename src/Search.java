import java.util.ArrayList;

public class Search {

    public static ArrayList<Node> possible_moves(Node parent) {
        ArrayList<Node> possibleMovements = new ArrayList<Node>();
        int curr_x = parent.emptyX;
        int curr_y = parent.emptyY;

        if (curr_y + 1 < 4) {
            Node rightMove = new Node(parent, parent.puzzle_board, curr_x, curr_y, curr_x,  curr_y + 1);
            rightMove.cost_to_reach_this_node = parent.cost_to_reach_this_node + 1;
            possibleMovements.add(rightMove);
        }

        if (curr_x - 1 > -1 ) {
            Node downMove = new Node(parent, parent.puzzle_board, curr_x, curr_y, curr_x - 1, curr_y);
            downMove.cost_to_reach_this_node = parent.cost_to_reach_this_node + 1;
            possibleMovements.add(downMove);
        }

        if (curr_y - 1 > -1) {
            Node leftMove = new Node(parent, parent.puzzle_board, curr_x, curr_y, curr_x, curr_y - 1);
            leftMove.cost_to_reach_this_node = parent.cost_to_reach_this_node + 1;
            possibleMovements.add(leftMove);
        }

        if (curr_x + 1 < 4) {
            Node upMove = new Node(parent, parent.puzzle_board, curr_x, curr_y,curr_x + 1, curr_y);
            upMove.cost_to_reach_this_node = parent.cost_to_reach_this_node + 1;
            possibleMovements.add(upMove);
        }

        return possibleMovements;
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
}
