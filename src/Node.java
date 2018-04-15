import java.util.Arrays;

public class Node {

    public Node parent;

    public char[][] puzzleBoard;

    public int emptyX;
    public int emptyY;


    public Node(Node parent, char[][] puzzleBoard, int emptyX, int emptyY, int swapX, int swapY) {
        this.parent = parent;
        this.emptyX = swapX;
        this.emptyY = swapY;

        this.puzzleBoard = deepCopy(puzzleBoard);

        char element = puzzleBoard[swapX][swapY];
        this.puzzleBoard[swapX][swapY] = this.puzzleBoard[emptyX][emptyY];
        this.puzzleBoard[emptyX][emptyY] = element;
    }

    public char[][] deepCopy(char[][] original) {
        char[][] result = new char[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return result;
    }

    public void printBoard() {
        for(int i = 0; i < this.puzzleBoard.length; i++) {
            for(int j = 0; j < this.puzzleBoard[i].length; j++) {
                System.out.print(this.puzzleBoard[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean equals(Node node) {
        return Arrays.deepEquals(this.puzzleBoard, node.puzzleBoard);
    }
}
