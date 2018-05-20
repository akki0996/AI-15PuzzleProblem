import java.util.*;

public class AStarSearch extends Search {


    public AStarSearch(Node src_node, Node goal_node, String heuristic) {
        search_AStar(src_node, goal_node, heuristic);
    }

    public void search_AStar(Node src_node, Node goal_node, String heuristic) {

        String goal_state = Arrays.deepToString(goal_node.puzzle_board);

        Set<String> explored_states = new HashSet<>();

        PriorityQueue<Node> unexplored = new PriorityQueue<>(10, new PComparator());
        unexplored.add(src_node);

        Node dest_node = null;
        int num_created = 1, num_expanded = 0, fringe_size = 0;

        HashMap<Character, int[]> tile_value_map = board_conversion_map(goal_node);

        while (!unexplored.isEmpty()) {

            Node curr_node = unexplored.poll();
            String curr_state = Arrays.deepToString(curr_node.puzzle_board);

            if (!explored_states.contains(curr_state)) {
                if (curr_state.equals(goal_state)) {
                    dest_node = curr_node;
                    break;
                }

                ArrayList<Node> successors = generate_successors(curr_node);
                for (Node child : successors) {
                    calc_cost(child, goal_node, heuristic, "AStar", tile_value_map);
                    unexplored.add(child);
                }

                num_created += successors.size();
                num_expanded++;
                fringe_size = Math.max(fringe_size, unexplored.size());

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
