import java.util.ArrayList;
import java.util.Stack;

public class DFSearch extends Search {

    public DFSearch(Node source, Node goal) {
        search(source, goal);
    }

    public void search(Node source, Node goal) {

        Stack<Node> stack = new Stack<>();
        stack.add(source);

        Node source_goal = null;

        int num_created = 1, num_expanded = 0, fringe_size = 0;

        while (!stack.isEmpty()) {
            Node node = stack.pop();

            if (check_repeats(node) == false) {
                if (node.equals(goal)) {
                    source_goal = node;
                    output_summary(source_goal, num_created, num_expanded, fringe_size);
                    return;
                }

                num_expanded++;
                ArrayList<Node> possibleMoves = possible_moves(node);
                stack.addAll(possibleMoves);
                num_created += possibleMoves.size();
                fringe_size = Math.max(fringe_size, stack.size());
            }
        }

        if (source_goal == null) print_summary(-1, -1, -1, -1);
    }
}
