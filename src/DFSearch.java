import java.util.ArrayList;
import java.util.Stack;

public class DFSearch extends Search {

    public DFSearch(Node source, Node goal) {
        search(source, goal);
    }

    public void search(Node source, Node goal) {

        Stack<Node> stack = new Stack<>();
        stack.add(source);

        Node destination = null;

        int num_created = 1, num_expanded = 0, fringe_size = 0;

        while (!stack.isEmpty()) {
            Node node = stack.pop();

            if (check_repeats(node) == false) {
                if (node.equals(goal)) {
                    destination = node;
                    output_summary(destination, num_created, num_expanded, fringe_size);
                    return;
                }


                ArrayList<Node> successors = generate_successors(node);
                stack.addAll(successors);

                num_expanded++;
                num_created += successors.size();
                fringe_size = Math.max(fringe_size, stack.size());
            }
        }

        if (destination == null) print_summary(-1, -1, -1, -1);
    }
}
