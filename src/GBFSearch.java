import java.util.*;

public class GBFSearch extends Search {

    public GBFSearch(Node source, Node goal, String heuristicType) {
        search_GBFS(source, goal, heuristicType);
    }

    public void search_GBFS(Node source, Node goal, String heuristic_type) {

        Node destination = null;

        ArrayList<Node> closed = new ArrayList<>();

        PriorityQueue<Node> open = new PriorityQueue<>(10, new PComparator());
        open.add(source);


        while (!open.isEmpty()) {
            Node low_heu_node = open.poll();

            if (check_closed(low_heu_node, closed) == false) {
                if (low_heu_node.equals(goal)) {
                    destination = low_heu_node;
                    destination.printBoard();
                    System.out.println("FOUND SOLUTION");
                    return;
                }

                ArrayList<Node> possibleMoves = possible_moves(low_heu_node);

                for(Node n: possibleMoves) {
                    if(heuristic_type.equals("h1"))
                        n.heuristic = n.heuristic_one(goal);
                    open.add(n);
                }
            }

            closed.add(low_heu_node);
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
