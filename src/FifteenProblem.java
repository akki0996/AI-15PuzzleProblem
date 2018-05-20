
import java.util.*;
import java.util.stream.Collectors;

public class FifteenProblem {
    public static void main(String[] args) {

        Node goal_node_one = create_node("123456789ABCDEF ");
        Node goal_node_two = create_node("123456789ABCDFE ");

        Node src_node = create_node(args[0]);

        if(args[1].equals("BFS")) {
            BFSearch bfs =  new BFSearch(src_node, goal_node_one, goal_node_two);
        }

        if(args[1].equals("DFS")) {
            DFSearch dfs =  new DFSearch(src_node, goal_node_one, goal_node_two);
        }

        if(args[1].equals("GBFS")) {
            GBFSearch gbfs =  new GBFSearch(src_node, goal_node_one, args[2]);
        }

        if(args[1].equals("AStar")) {
            AStarSearch astar =  new AStarSearch(src_node, goal_node_one, args[2]);
        }

        if(args[1].equals("DLS")) {
            DLSearch dls =  new DLSearch(src_node, goal_node_one, goal_node_two, Integer.parseInt(args[1]));
        }
    }

    public static Node create_node(String state) {
        int empty_x = 0;
        int empty_y = 0;

        int count = 0;

        char[][] board = new char[4][4];

        for(int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                char c = state.charAt(count);

                if (c == ' ') {
                    empty_x = i;
                    empty_y = j;
                }

                board[i][j] = c;
                count++;
            }
        }
        return new Node(null, board, empty_x, empty_y, empty_x, empty_y);
    }
}


