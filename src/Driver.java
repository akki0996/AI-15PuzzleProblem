
import java.util.*;

public class Driver {
    public static void main(String[] args) {
        // Goal state initialization
        String goal_state = "123456789ABCDEF  H";
        Node goal = create_source_node(goal_state.split(" "));
        goal.printBoard();

//        Scanner scan = new Scanner(System.in);
//        System.out.print("Input the args: ");
//
//        String input_str = scan.nextLine();
//        input_str = input_str.replaceAll("[\"]", "");
//
//
//        String[] arguments = input_str.split(" ");
//        Node source = create_source_node(arguments);

        HashMap<Character, int[]> hashMap = convert(goal);

        String one = "123456789 ABCDEF";
        Node one_goal = create_source_node(one.split(" "));
        one_goal.printBoard();
        one_goal.heuristic = one_goal.heuristic_two(goal, hashMap);

        System.out.println(one_goal.heuristic);
//
//        String two = "123456789 ABCDEF";
//        Node two_goal = create_source_node(two.split(" "));
//        two_goal.printBoard();
//        two_goal.heuristic = two_goal.cal_heuristic(goal);
//
//        String three = "123456789ABCD EF";
//        Node three_goal = create_source_node(three.split(" "));
//        three_goal.printBoard();
//        three_goal.heuristic = three_goal.cal_heuristic(goal);
//
//
//        System.out.println("one_goal: " + one_goal.heuristic);
//        System.out.println("two_goal: " + two_goal.heuristic);
//        System.out.println("three_goal: " + three_goal.heuristic);
//
//
//        PriorityQueue<Node> open = new PriorityQueue<>(10, new PComparator());
//
//        open.add(one_goal);
//        open.add(three_goal);
//        open.add(two_goal);
//
//
//        open.poll().printBoard();
//        open.poll().printBoard();
//        open.poll().printBoard();



//        if(arguments[2].equals("BFS")) {
//            BFSearch bfs =  new BFSearch(source, goal);
//        }
//
//        if(arguments[2].equals("DFS")) {
//            DFSearch dfs =  new DFSearch(source, goal);
//        }
//
//        if(arguments[2].equals("GBFS")) {
//            GBFSearch gbfs =  new GBFSearch(source, goal, arguments[3]);
//        }


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

        //System.out.println(Y);
        //System.out.println(emptyX + " " +  emptyY);

        Y += 1;
        if(Y % 4 == 0) X++;

        for(int j = 0; j < arguments[1].length(); j++) {
            board[X][Y++ % 4] = arguments[1].charAt(j);
            if(Y % 4 == 0) X++;
        }

        return new Node(null, board, emptyX, emptyY, emptyX, emptyY);
    }


    public static HashMap<Character, int[]> convert(Node goal) {
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


