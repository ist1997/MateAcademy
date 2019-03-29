package HomeWork.hw4;

public class MyLinkedList<T> implements MyList<T> {
    private Node<T> firstNode;
    private Node<T> lastNode;
    private int size;

    public MyLinkedList() {
        size = 0;
    }

    @Override
    public void add(T value) {
        Node<T> addNode;
        if (size == 0) {
            addNode = new Node<>(null, null, value);
            firstNode = addNode;
            lastNode = addNode;
            size++;
        } else {
            addNode = new Node<>(null, lastNode, value);
            lastNode.next = addNode;
            lastNode = addNode;
            size++;
        }
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            int currentIndex = 0;
            Node<T> addNode;
            Node<T> currentNode = firstNode;
            while (currentIndex < index) {
                currentNode = currentNode.next;
                currentIndex++;
            }
            if (index == 0) {
                addNode = new Node<>(firstNode, null, value);
                firstNode.prev = addNode;
                firstNode = addNode;
            } else if (index == size) {
                addNode = new Node<>(null, lastNode, value);
                lastNode.next = addNode;
                lastNode = addNode;
            } else {
                addNode = new Node<>(currentNode, currentNode.prev, value);
                currentNode.prev.next = addNode;
                currentNode.prev = addNode;
            }
            size++;
        }
    }

    @Override
    public void addAll(MyList<T> list) {
        for (int i = 0; i < list.size(); i++){
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        Node<T> getNode = getNode(index);
        return getNode.value;
    }

    private Node<T> getNode(int index) {
        int currentIndex = 0;
        Node<T> getNode = firstNode;
        while (currentIndex < index) {
            getNode = getNode.next;
            currentIndex++;
        }
        return getNode;
    }

    @Override
    public void set(T value, int index) {
        getNode(index).value = value;
    }

    @Override
    public T remove(int index) {
        if (size == 0) {
            throw new NegativeArraySizeException();
        }
        Node<T> removeNode = getNode(index);
        if (index == 0) {
            firstNode = removeNode.next;
            firstNode.prev = null;
        } else if (index == size - 1) {
            lastNode = removeNode.prev;
            lastNode.next = null;
        } else {
            removeNode.next.prev = removeNode.prev;
            removeNode.prev.next = removeNode.next;
        }
        return null;
    }

    @Override
    public T remove(T t) {
        int currentIndex = 0;
        Node<T> removeNode = firstNode;
        while (removeNode.value != t && currentIndex < size) {
            removeNode = removeNode.next;
            currentIndex++;
        }
        remove(currentIndex);
        return removeNode.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        Node<T> addNode = firstNode;
        StringBuilder result = new StringBuilder();
        result.append("[").append(addNode.value.toString());
        while (addNode.next != null) {
            addNode = addNode.next;
            result.append(", ").append(addNode.value.toString());
        }
        result.append("]");
        return result.toString();
    }

    class Node<T> {
        private Node<T> next;
        private Node<T> prev;
        private T value;

        public Node(Node<T> next, Node<T> prev, T value) {
            this.next = next;
            this.prev = prev;
            this.value = value;
        }

    }

}
