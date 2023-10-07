package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;


/**
 * There are 2 types of nodes: leaves and non-leaves.
 * Only the leaves are labelled
 * In the process, the leaves are pushed down the tree.
 *
 * mapOfLeaves is used for quick access to a given leaf based on the label.
 *
 * It's not clear if among 2 smallest elements, if the smaller comes after the larger, I should preserve the order
 * or should I swap them. I assume I should swap (sort)
 */
public class RoboticCodeRepresentationGenerator {
    private TreeMap<String, Node> mapOfLeaves = new TreeMap<>();

    public RoboticCodeRepresentationGenerator(List<String> issuedCommands) {
        List<Node> nodes = issuedCommands.stream()
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .entrySet()
                .stream().map(e -> new Node(e.getKey(), e.getValue()))
                .collect(Collectors.toCollection(ArrayList::new));
        nodes.forEach(n -> mapOfLeaves.put(n.getLabel(), n));  // held just for quick access
        while(nodes.size() > 1) {
//            nodes.sort(Node::compareTo);
//            Node left = nodes.remove(0);
//            Node right = nodes.remove(0);
            Node left = removeAndReturnSmallestNode(nodes);
            Node right = removeAndReturnSmallestNode(nodes);
            Node combined = new Node(left, right);
            left.setParent(combined);
            left.setDirection(ChildDirection.LEFT_CHILD);
            right.setParent(combined);
            right.setDirection(ChildDirection.RIGHT_CHILD);
            nodes.add(combined);
        }
    }

    private Node removeAndReturnSmallestNode(List<Node> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        Node minNode = list.stream().min(Node::compareTo).orElse(null);
        list.remove(minNode);
        return minNode;
    }

    public String getRcr(String label) {
        Node node = mapOfLeaves.get(label);
        if(node == null) {
            throw new RuntimeException("Wrong label");
        }
        StringBuilder pathBuilder = new StringBuilder();
        while(node.getParent() != null) {
            if(node.getDirection().equals(ChildDirection.LEFT_CHILD)) {
                pathBuilder.append("0");
            } else if(node.getDirection().equals(ChildDirection.RIGHT_CHILD)) {
                pathBuilder.append("1");
            }
            node = node.getParent();
        }
        return pathBuilder.reverse().toString();
    }
}
