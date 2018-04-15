import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class AStarSearch extends Search {
    public AStarSearch(Node source, Node goal, String heuristicType) {
        search_AStar(source, goal, heuristicType);
    }

    public void search_AStar(Node source, Node goal, String heuristic_type) {

        HashMap<Character, int[]> goal_hashmap = array_to_hasmap(goal);

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
                        n.total_cost = n.heuristic_one(goal) + n.cost_to_reach_this_node ;
                    if(heuristic_type.equals("h2"))
                        n.total_cost = n.heuristic_two(goal, goal_hashmap) + n.cost_to_reach_this_node;
                    open.add(n);
                }

//                for(Node x: open) {
//                    x.printBoard();
//                    System.out.println(x.heuristic);
//                }
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

    public static HashMap<Character, int[]> array_to_hasmap(Node goal) {
        char[][] puzzle_board = goal.puzzle_board;
        HashMap<Character, int[]> hashMap = new HashMap<>();

        for(int i = 0; i < puzzle_board.length; i++) {
            for(int j = 0; j < puzzle_board[i].length; j++) {
                hashMap.put(puzzle_board[i][j], new int[]{i, j});
            }
        }

        return hashMap;
    }
}
