
import java.util.*;

public class Driver {
    public static void main(String[] args) {
        // Goal state initialization
        String goal_state = "123456789ABCDEF  H";
        Node goal = create_source_node(goal_state.split(" "));
        goal.printSummary();

        Scanner scan = new Scanner(System.in);
        System.out.print("Input the args: ");

        String input_str = scan.nextLine();
        input_str = input_str.replaceAll("[\"]", "");

        String[] arguments = input_str.split(" ");
        Node source = create_source_node(arguments);

        if(arguments[2].equals("BFS")) {
            BFSearch bfs =  new BFSearch(source, goal);
        }

        if(arguments[2].equals("DFS")) {
            DFSearch dfs =  new DFSearch(source, goal);
        }

        if(arguments[2].equals("GBFS")) {
            GBFSearch gbfs =  new GBFSearch(source, goal, arguments[3]);
        }

        if(arguments[2].equals("AStar")) {
            AStarSearch astar =  new AStarSearch(source, goal, arguments[3]);
        }

        if(arguments[2].equals("DLS")) {
            DLSearch dls =  new DLSearch(source, goal, Integer.parseInt(arguments[3]));
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


