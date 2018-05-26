import java.util.*;

public class BFSearch extends Search {

    public BFSearch(Node src_node, Node goal_node_one, Node goal_node_two) {
        String goal_state_one = Arrays.deepToString(goal_node_one.puzzle_board);
        String goal_state_two = Arrays.deepToString(goal_node_two.puzzle_board);
        search(src_node, goal_state_one, goal_state_two);
    }

    public void search(Node src_node, String goal_state_one, String goal_state_two) {

        Set<String> explored_states = new HashSet<>();

        Queue<Node> queue = new LinkedList<>();
        queue.add(src_node);

        Node dest_node = null;

        int num_created = 1, num_expanded = 0, fringe_size = 0;

        while (!queue.isEmpty()) {

            Node curr_node = queue.poll();
            String curr_state = Arrays.deepToString(curr_node.puzzle_board);

            if (!explored_states.contains(curr_state)) {

                if (hasCurrStateReachedGoalState(curr_state, goal_state_one, goal_state_two)) {
                    dest_node = curr_node;
                    break;
                }

                ArrayList<Node> successors = generate_successors(curr_node);
                queue.addAll(successors);

                num_created += successors.size();
                num_expanded++;
                fringe_size = Math.max(fringe_size, queue.size());

                explored_states.add(curr_state);
            }
        }

        if (dest_node != null) {
            output_summary(dest_node, num_created, num_expanded, fringe_size);
        } else {
            print_summary(-1, -1, -1, -1);
        }
    }

//public String convertArrayToString(char[][] board) {
//        StringBuilder stringBuilder = new StringBuilder();
//        for(int i = 0; i < 4; i++) {
//            for(int j = 0; j < 4; j++) {
//                stringBuilder.append(board[i][j]);
//            }
//        }
//
//        return stringBuilder.toString();
//}
/// " 12356749AB8DEFC" BFS


// "51246A379 C8DEBF" BFS
}