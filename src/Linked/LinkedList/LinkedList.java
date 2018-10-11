package Linked.LinkedList;

public class LinkedList extends List {
    protected Node head;
    protected int numNodes = 1;
    protected Node tail;

    public class Node {
        protected Node next = null;
        protected Object data;

        protected Node(Object data) {
            this.data = data;
        }

        public Object getData() {
            return this.data;
        }

        public Node getNext() {
            return next;
        }
    }

    public LinkedList(Object data) {
        head = new Node(data);
        tail = head;
    }

    //ADD
    @Override
    public void add(int index, Object data) {
        Node temp = moveToIndex(index - 1);
        if (temp == null) {
            temp = moveToTail();
        }
        Node current = temp.next;
        temp.next = new Node(data);
        temp.next.next = current;
        numNodes++;
    }

    @Override
    public void addFirst(Object data) {
        Node temp = head;
        head = new Node(data);
        head.next = temp;
        numNodes++;
    }

    @Override
    public void addLast(Object data) {
        Node temp = moveToTail();
        temp.next = new Node(data);
        tail = temp.next;
        numNodes++;
    }

    //REMOVE
    @Override
    public boolean remove(int index) {
        if (moveToIndex(index) == null) return false;
        if (index == 0) {
            removeFirst();
        } else if (index == numNodes - 1) {
            removeLast();
        } else {
            Node temp = moveToIndex(index - 1);
            Node current = temp.next;
            temp.next = current.next;
            current.next = null;
            numNodes--;
        }
        return true;
    }


    @Override
    public boolean remove(Object data) {
        Node temp = head;
        for (int i = 0; i < numNodes; i++) {
            if (temp.getData().equals(data)) {
                if (remove(i)) {
                    return true;
                }
            }
            temp = temp.next;
        }
        return false;
    }

    @Override
    public void removeFirst() {
        Node temp = head.next;
        head.next = null;
        head = temp;
        numNodes--;
    }

    @Override
    public void removeLast() {
        Node temp = moveToIndex(numNodes - 2);
        temp.next = null;
        tail = temp;
        numNodes--;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
    }

    //GET
    @Override
    public Node getNode(Object index) {
        return moveToIndex((Integer) index);
    }

    @Override
    public int size() {
        return numNodes;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    //CHECK
    @Override
    public boolean isContain(Object data) {
        Node temp = head;
        while (!(temp == null)) {
            if (temp.getData().equals(data)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    @Override
    public int indexOf(Object data) {
        Node temp = head;
        for (int i = 0; i < numNodes; i++) {
            if (temp.getData().equals(data)) {
                return i;
            }
            temp = temp.next;

        }
        return -1;
    }

    @Override
    public boolean isRound() {
        if (!(getNode(numNodes - 1).next == null)) {
            if (getNode(numNodes - 1).next.equals(getHead())) {
                return true;
            }
        }
        return false;
    }

    //SUPPORT
    @Override
    public LinkedList clone() {
        Node cloneCursor = head;
        LinkedList clone = new LinkedList(cloneCursor.getData());
        for (int i = 1; i < numNodes; i++) {
            cloneCursor = cloneCursor.next;
            clone.addLast(cloneCursor.getData());
        }
        return clone;
    }

    @Override
    public void printList() {
        System.out.println("list (" + numNodes + ")");
        Node temp = head;
        for (int i = 0; i < numNodes; i++) {
            System.out.println("element " + i + ": " + temp.data);
            temp = temp.next;
        }
        System.out.println("\n");
    }


    protected Node moveToIndex(int index) {
        Node cursor = head;
        if (index < numNodes && index >= 0) {
            for (int i = 0; i < index; i++) {
                cursor = cursor.next;
            }
            return cursor;
        }
        return null;
    }


    protected Node moveToTail() {
        Node cursor = head;
        while (!(cursor == tail)) {
            cursor = cursor.next;
        }
        return cursor;
    }
}
