package org.example;

public class Node implements Comparable<Node> {
    private String label;
    private String path = "";
    private long frequency;
    private Node rightChild;
    private Node leftChild;
    private Node parent;
    private ChildDirection direction;

    public Node(String label, long frequency) { // for leafs creation at the initialization; only leaves have labels
        this.label = label;
        this.frequency = frequency;
    }

    public Node(Node leftChild, Node rightChild) { // for non-leaf node creation

        if(leftChild == null || rightChild == null) {
            throw new RuntimeException("Argument cannot be null");
        }

        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.frequency = leftChild.getFrequency() + rightChild.getFrequency();
    }

    @Override
    public int compareTo(Node that) {
        return Long.compare(this.frequency, that.frequency);
    }

    public long getFrequency() {
        return frequency;
    }

    public String getLabel() {
        return label;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public ChildDirection getDirection() {
        return direction;
    }

    public void setDirection(ChildDirection direction) {
        this.direction = direction;
    }
}
