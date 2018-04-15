import java.util.Arrays;
import java.util.HashMap;

public class Node {

    public Node parent;

    public char[][] puzzle_board;

    public int emptyX;
    public int emptyY;

    public int heuristic;


    public Node(Node parent, char[][] puzzle_board, int emptyX, int emptyY, int swapX, int swapY) {
        this.parent = parent;
        this.emptyX = swapX;
        this.emptyY = swapY;

        this.puzzle_board = deepCopy(puzzle_board);

        char element = puzzle_board[swapX][swapY];
        this.puzzle_board[swapX][swapY] = this.puzzle_board[emptyX][emptyY];
        this.puzzle_board[emptyX][emptyY] = element;
    }

    public char[][] deepCopy(char[][] original) {
        char[][] result = new char[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return result;
    }

    public void printBoard() {
        for(int i = 0; i < this.puzzle_board.length; i++) {
            for(int j = 0; j < this.puzzle_board[i].length; j++) {
                System.out.print(this.puzzle_board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean equals(Node node) {
        return Arrays.deepEquals(this.puzzle_board, node.puzzle_board);
    }

    public int heuristic_one(Node goal) {
        char[][] firstOne = this.puzzle_board;
        char[][] secondOne = goal.puzzle_board;

        int difference = 0;

        for(int i = 0; i < firstOne.length; i++) {
            for(int j = 0; j < firstOne[i].length; j++) {
                if(firstOne[i][j] != (goal.puzzle_board[i][j])) {
                    difference++;
                }
            }
        }

        return difference;
    }

    /** Manhattan Distance */
    public int heuristic_two(Node goal, HashMap<Character, int[]> hashMap) {

        char[][] firstOne = this.puzzle_board;
        char[][] secondOne = goal.puzzle_board;

        int manhattan_dist = 0;

        for(int i = 0; i < firstOne.length; i++) {
            for(int j = 0; j < firstOne[i].length; j++) {
                if(firstOne[i][j] != (goal.puzzle_board[i][j])) {
                    int[] positions = hashMap.get(firstOne[i][j]);
                    manhattan_dist += Math.abs(i - positions[0]) + Math.abs(j - positions[1]);
                }
            }
        }
        return manhattan_dist;
    }
}
