import java.util.Comparator;

/**
 * This class helps priority queue in deciding the priority for states
 */
public class PComparator implements Comparator<Node> {

    @Override
    public int compare(Node n1, Node n2) {
        if (n1.total_cost < n2.total_cost) {
            return -1;
        }

        if (n1.total_cost > n2.total_cost) {
            return 1;
        }

        return 0;
    }
}
