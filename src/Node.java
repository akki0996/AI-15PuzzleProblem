import java.util.Arrays;

/**
 * This class stores a store and properties associated with a state
 */
public class Node {

    public Node parent;

    public char[][] puzzle_board;

    public int empty_x;
    public int empty_y;

    public int total_cost;

    public int cost_source_current_state = 0;

    public int depth = 0;

    /**
     * @param parent  parent of the current node
     * @param board   state associated with the current node
     * @param empty_x location x of the blank
     * @param empty_y location y of the blank
     * @param swapX   location x of new blank
     * @param swapY   location y of new blank
     */
    public Node(Node parent, char[][] board, int empty_x, int empty_y, int swapX, int swapY) {
        this.parent = parent;
        this.empty_x = swapX;
        this.empty_y = swapY;

        this.puzzle_board = deepCopy(board);

        char element = puzzle_board[swapX][swapY];
        this.puzzle_board[swapX][swapY] = this.puzzle_board[empty_x][empty_y];
        this.puzzle_board[empty_x][empty_y] = element;
    }


    /**
     * This method creates a deep copy of the passed 2d array
     *
     * @param original current state of the node
     * @return a deep copy of 2d char array
     */
    public char[][] deepCopy(char[][] original) {
        char[][] result = new char[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return result;
    }

    /**
     * This method prints the summary the node, for example, location information.
     */
    public void printSummary() {
        for (int i = 0; i < this.puzzle_board.length; i++) {
            for (int j = 0; j < this.puzzle_board[i].length; j++) {
                System.out.print(this.puzzle_board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("x:" + empty_x + " " + "y:" + empty_y);
        System.out.println("Cost: source_state -> curr_state: " + cost_source_current_state);
        System.out.println("Depth:" + depth);
        System.out.println("Total cost: " + total_cost + "\n");
    }

    /**
     * This method compare two states and return a boolean value
     *
     * @param node current node
     * @return true or false depending on the condition check
     */
    public boolean equals(Node node) {
        return Arrays.deepEquals(this.puzzle_board, node.puzzle_board);
    }
}
