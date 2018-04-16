import java.util.ArrayList;
import java.util.Stack;

public class DLSearch extends Search {

    public DLSearch(Node source, Node goal, int depth) {
        search(source, goal, depth);
    }

    public void search(Node source, Node goal, int depth) {

        Stack<Node> stack = new Stack<>();
        stack.add(source);

        Node source_goal = null;

        int num_created = 1, num_expanded = 0, fringe_size = 0;

        while (!stack.isEmpty()) {
            Node node = stack.pop();

            if(node.depth < depth) {
                if (check_repeats(node) == false) {
                    if (node.equals(goal)) {
                        source_goal = node;
                        output_summary(source_goal, num_created, num_expanded, fringe_size);
                        return;
                    }

                    num_expanded++;
                    ArrayList<Node> successors = generate_successors(node);
                    stack.addAll(successors);
                    num_created += successors.size();
                    fringe_size = Math.max(fringe_size, stack.size());
                }
            }
        }

        if (source_goal == null) print_summary(-1, -1, -1, -1);
    }
}
