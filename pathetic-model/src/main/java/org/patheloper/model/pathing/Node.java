package org.patheloper.model.pathing;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.patheloper.api.wrapper.PathPosition;
import org.patheloper.api.wrapper.PathVector;
import org.patheloper.util.ComputingCache;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Node implements Comparable<Node> {

    private final Integer depth;
    private final ComputingCache<Double> heuristic = new ComputingCache<>(this::heuristic);

    @EqualsAndHashCode.Include
    private final PathPosition position;
    private final PathPosition target;
    private final PathPosition start;
    
    private Node parent;

    public Node(PathPosition position, PathPosition start, PathPosition target, Integer depth) {
        this.position = position;
        this.target = target;
        this.start = start;
        this.depth = depth;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public boolean isAtTarget() {
        return this.position.getBlockX() == target.getBlockX()
                && this.position.getBlockY() == target.getBlockY()
                && this.position.getBlockZ() == target.getBlockZ();
    }

    private double heuristic() {
        double v = calculatePerpendicularDistance();
        return this.position.octileDistance(target)
                * (v*0.00002)
                + 0.01*(this.target.distance(this.position)
                + this.start.distance(this.position));
    }

    private double calculatePerpendicularDistance() {
        PathVector a = this.position.toVector();
        PathVector b = this.start.toVector();
        PathVector c = this.target.toVector();
        return a.subtract(b).getCrossProduct(c.subtract(b)).length() / c.subtract(b).length();
    }
    
    @Override
    public int compareTo(Node o) {
        // This is used in the priority queue to sort the nodes
        return (int) Math.signum(this.heuristic.get() - o.heuristic.get());
    }
}
