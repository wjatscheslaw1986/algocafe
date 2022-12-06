package algos.maze;

public class PathNode<T> implements Comparable<PathNode<T>> {
    private final T state;
    private PathNode<T> parent;
    private double cost, heuristic;

    public T getState() {
        return state;
    }

    public PathNode<T> getParent() {
        return parent;
    }

    public double getCost() {
        return cost;
    }

    public double getHeuristic() {
        return heuristic;
    }

    public PathNode(T state, PathNode<T> parent) {
        this(state, parent, .0d, .0d);
    }

    public PathNode(T state, PathNode<T> parent, double cost, double heuristic) {
        this.state = state;
        this.parent = parent;
        this.cost = cost;
        this.heuristic = heuristic;
    }

    @Override
    public int compareTo(PathNode<T> o) {
        Double mine = this.cost + this.heuristic;
        Double their = o.cost + o.heuristic;
        return mine.compareTo(their);
    }
}