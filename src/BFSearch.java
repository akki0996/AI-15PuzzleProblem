import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFSearch extends Search {

    public BFSearch(Node source, Node goal) {
        search(source, goal);
    }

    public void search(Node source, Node goal) {

        Queue<Node> queue = new LinkedList<Node>();
        queue.add(source);

        Node source_goal = null;

        int num_created = 1, num_expanded = 0, fringe_size = 0;

        while (!queue.isEmpty()) {

            Node node = queue.poll();

            if (check_repeats(node) == false) {
                if (node.equals(goal)) {
                    source_goal = node;
                    output_summary(source_goal, num_created, num_expanded, fringe_size);
                    return;
                }

                num_expanded++;

                ArrayList<Node> possibleMoves = possible_moves(node);

                queue.addAll(possibleMoves);

                num_created += possibleMoves.size();

                fringe_size = Math.max(fringe_size, queue.size());
            }
        }

        if (source_goal == null) print_summary(-1, -1, -1, -1);


    }
}
