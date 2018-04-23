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

        Node destination = null;

        int num_created = 1, num_expanded = 0, fringe_size = 0;

        while (!queue.isEmpty()) {

            Node node = queue.poll();
            //node.printSummary();

//            if (check_repeats(node) == false) {
                if (node.equals(goal)) {
                    destination = node;
                    output_summary(destination, num_created, num_expanded, fringe_size);
                    return;
                }

                ArrayList<Node> successors = generate_successors(node);
                queue.addAll(successors);

                num_created += successors.size();
                num_expanded++;
                fringe_size = Math.max(fringe_size, queue.size());
            }
        }

//        if (destination == null) print_summary(-1, -1, -1, -1);
//

    }
//}
//


/// " 12356749AB8DEFC" BFS


// "51246A379 C8DEBF" BFS