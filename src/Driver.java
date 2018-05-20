
import java.util.*;
import java.util.stream.Collectors;

public class Driver {
    public static void main(String[] args) {
        // Goal state initialization
        String goal_state_one = "123456789ABCDEF  H";
        String goal_state_two = "123456789ABCDFE  H";

        Node goal_node_one = create_source_node(goal_state_one.split(" "));
        Node goal_node_two = create_source_node(goal_state_two.split(" "));



        Scanner scan = new Scanner(System.in);
        System.out.print("Input the args: ");

        String input_str = scan.nextLine();
        input_str = input_str.replaceAll("[\"]", "");

        String[] arguments = input_str.split(" ");
        Node src_node = create_source_node(arguments);


        if(arguments[2].equals("BFS")) {
            BFSearch bfs =  new BFSearch(src_node, goal_node_one, goal_node_two);
        }

        if(arguments[2].equals("DFS")) {
            DFSearch dfs =  new DFSearch(src_node, goal_node_one, goal_node_two);
        }

        if(arguments[2].equals("GBFS")) {
            GBFSearch gbfs =  new GBFSearch(src_node, goal_node_one, arguments[3]);
        }

        if(arguments[2].equals("AStar")) {
            AStarSearch astar =  new AStarSearch(src_node, goal_node_one, arguments[3]);
        }

        if(arguments[2].equals("DLS")) {
            DLSearch dls =  new DLSearch(src_node, goal_node_one, goal_node_two, Integer.parseInt(arguments[3]));
        }
    }


    public static Node create_source_node(String[] arguments) {
        int emptyX, emptyY;
        int X = 0, Y = 0;

        char[][] board = new char[4][4];

        for(int j = 0; j < arguments[0].length(); j++) {
            board[X][Y++ % 4] = arguments[0].charAt(j);
            if(Y % 4 == 0) X++;
        }

        emptyX = X;
        emptyY = Y % 4;

        Y += 1;
        if(Y % 4 == 0) X++;

        for(int j = 0; j < arguments[1].length(); j++) {
            board[X][Y++ % 4] = arguments[1].charAt(j);
            if(Y % 4 == 0) X++;
        }

        return new Node(null, board, emptyX, emptyY, emptyX, emptyY);
    }
}


