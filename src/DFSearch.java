import java.util.*;

public class DFSearch extends Search {

    public DFSearch(Node source, Node goal_node_one, Node goal_node_two) {
        String goal_state_one = Arrays.deepToString(goal_node_one.puzzle_board);
        String goal_state_two = Arrays.deepToString(goal_node_two.puzzle_board);
        search(source, goal_state_one, goal_state_two);
    }

    public void search(Node src_node, String goal_state_one, String goal_state_two)  {

        Set<String> explored_states = new HashSet<>();
        Stack<Node> stack = new Stack<>();
        stack.add(src_node);

        Node dest_node = null;

        int num_created = 1, num_expanded = 0, fringe_size = 0;

        while (!stack.isEmpty()) {
            Node curr_node = stack.pop();

            String curr_state = Arrays.deepToString(curr_node.puzzle_board);

            if (!explored_states.contains(curr_state)) {
                if (hasCurrStateReachedGoalState(curr_state, goal_state_one, goal_state_two)) {
                    dest_node = curr_node;
                    break;
                }

                // reverse the generated child as per instructions in the assignment
                ArrayList<Node> successors = generate_successors(curr_node);
                Collections.reverse(successors);
                stack.addAll(successors);

                num_expanded++;
                num_created += successors.size();
                fringe_size = Math.max(fringe_size, stack.size());

                explored_states.add(curr_state);
            }
        }

        if (dest_node != null) {
            output_summary(dest_node, num_created, num_expanded, fringe_size);
        } else {
            print_summary(-1, -1, -1, -1);
        }
    }
}
