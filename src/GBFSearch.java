import java.util.*;

public class GBFSearch extends Search {

    public GBFSearch(Node source, Node goal, String heuristicType) {
        search(source, goal, heuristicType);
    }

    public void search(Node source, Node goal, String heuristic) {

        Node destination = null;
        int num_created = 1, num_expanded = 0, fringe_size = 0;

        HashMap<Character, int[]> tile_value_map = board_conversion_map(goal);
        ArrayList<Node> closed = new ArrayList<>();

        PriorityQueue<Node> open = new PriorityQueue<>(10, new PComparator());
        open.add(source);

        while (!open.isEmpty()) {

            Node node_low_cost = open.poll();

            if (check_closed(node_low_cost, closed) == false) {
                if (node_low_cost.equals(goal)) {
                    destination = node_low_cost;
                    output_summary(destination, num_created, num_expanded, fringe_size);
                    return;
                }

                ArrayList<Node> successors = generate_successors(node_low_cost);

                for(Node child: successors) {
                    calc_cost(child, goal, heuristic, "GBFS", tile_value_map);
                    open.add(child);
                }

                num_created += successors.size();
                num_expanded++;
                fringe_size = Math.max(fringe_size, open.size());
            }

            closed.add(node_low_cost);
        }

        if (destination == null) print_summary(-1, -1, -1, -1);

    }

    public boolean check_closed(Node cur_node, ArrayList<Node> closed) {
        for(Node n: closed) {
            if(Arrays.deepEquals(n.puzzle_board, cur_node.puzzle_board)) {
                return true;
            }
        }
        return false;
    }
}
