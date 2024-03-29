import java.util.*;

public class GBFSearch extends Search {

    public GBFSearch(Node src_node, Node goal_node, String heuristic) {
        search(src_node, goal_node, heuristic);
    }

    public void search(Node src_node, Node goal_node, String heuristic) {

        HashMap<Character, int[]> tile_value_map = board_conversion_map(goal_node);
        Set<String> explored = new HashSet<>();

        String goal_state = Arrays.deepToString(goal_node.puzzle_board);

        PriorityQueue<Node> unexplored = new PriorityQueue<>(10, new PComparator());
        unexplored.add(src_node);

        Node dest_node = null;
        int num_created = 1, num_expanded = 0, fringe_size = 0;

        while (!unexplored.isEmpty()) {

            // node with lowest cost
            Node curr_node = unexplored.poll();
            String curr_state = Arrays.deepToString(curr_node.puzzle_board);

            if (!explored.contains(curr_state)) {
                if (curr_state.equals(goal_state)) {
                    dest_node = curr_node;
                    break;
                }

                ArrayList<Node> successors = generate_successors(curr_node);

                for (Node child : successors) {
                    calc_cost(child, goal_node, heuristic, "GBFS", tile_value_map);
                    unexplored.add(child);
                }

                num_created += successors.size();
                num_expanded++;
                fringe_size = Math.max(fringe_size, unexplored.size());

                explored.add(curr_state);
            }
        }

        if (dest_node != null) {
            output_summary(dest_node, num_created, num_expanded, fringe_size);
        } else {
            print_summary(-1, -1, -1, -1);
        }
    }
}
