import java.util.Comparator;

public class PComparator implements Comparator<Node> {

    @Override
    public int compare(Node n1, Node n2) {
        if (n1.heuristic < n2.heuristic) {
            return -1;
        }

        if (n1.heuristic > n2.heuristic) {
            return 1;
        }

        return 0;
    }
}
