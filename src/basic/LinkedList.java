package basic;

import java.util.stream.IntStream;

public class LinkedList {
    public static void main(String[] args) {
        Node root = new Node(1);

        Node node2 = new Node(20);
        Node node3 = new Node(13);
        Node node4 = new Node(12);
        Node node5 = new Node(5);

        root.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

//        printLinkedList(root);
        bubbleSort(root);
        printLinkedList(root);
    }

    private static void bubbleSort(Node root) {
        int linkedListSize = 0;
        Node backupRoot = root;

        while (root != null) {
            root = root.next;
            linkedListSize++;
        }

        for (int i = 0; i < linkedListSize; i++) {
            Node cNode = backupRoot;

            for (int j = 0; j < linkedListSize - i - 1; j++) {
                if (cNode.value > cNode.next.value) {
                    int tmp = cNode.value;
                    cNode.value = cNode.next.value;
                    cNode.next.value = tmp;
                }

                cNode = cNode.next;
            }
        }
    }

    private static void printLinkedList(Node root) {
        if (root == null) {
            return;
        }

        System.out.print(root.value + " ");

        printLinkedList(root.next);
    }
}

class Node {
    int value;
    Node next;

    public Node(int value) {
        this.value = value;
    }
}