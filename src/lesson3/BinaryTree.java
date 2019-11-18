package lesson3;

import kotlin.NotImplementedError;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

// Attention: comparable supported but comparator is not
public class BinaryTree<T extends Comparable<T>> extends AbstractSet<T> implements CheckableSortedSet<T> {

    private static class Node<T> {
        T value;

        Node<T> left = null;

        Node<T> right = null;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root = null;

    private int size = 0;

    @Override
    public boolean add(T t) {
        Node<T> closest = find(t);
        int comparison = closest == null ? -1 : t.compareTo(closest.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<>(t);
        if (closest == null) {
            root = newNode;
        } else if (comparison < 0) {
            assert closest.left == null;
            closest.left = newNode;
        } else {
            assert closest.right == null;
            closest.right = newNode;
        }
        size++;
        return true;
    }

    public boolean checkInvariant() {
        return root == null || checkInvariant(root);
    }

    public int height() {
        return height(root);
    }

    private boolean checkInvariant(Node<T> node) {
        Node<T> left = node.left;
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) return false;
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
    }

    private int height(Node<T> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    /**
     * Удаление элемента в дереве
     * Средняя
     * Ресурсоёмкость   - O(1)
     * Трудоёмкость     - O(n)
     */
    @Override
    public boolean remove(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        if (root == null) return false;
        root = remove(root, t);
        size--;
        return true;
    }

    private Node<T> remove(Node<T> root, T removedElement) {
        if (root == null) return null;

        if (removedElement.compareTo(root.value) < 0)
            root.left = remove(root.left, removedElement);

        else if (removedElement.compareTo(root.value) > 0)
            root.right = remove(root.right, removedElement);

        else if (!(root.left == null || root.right == null)) {
            Node<T> node = new Node<>(min(root.right).value);
            node.right = root.right;
            node.left = root.left;
            root = node;
            root.right = remove(root.right, root.value);
        }

        else root = (root.left != null) ? root.left : root.right;

        return root;
    }

    private Node<T> min(@NotNull Node<T> root) {
        while (true) {
            if (root.left != null) {
                root = root.left;
                continue;
            }
            return root;
        }
    }

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }

    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }

    private Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        }
        else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        }
        else {
            if (start.right == null) return start;
            return find(start.right, value);
        }
    }

    public class BinaryTreeIterator implements Iterator<T> {
        private Node<T> current;
        final Deque<Node<T>> deque = new ArrayDeque<>();

        private BinaryTreeIterator() {
            current = root;
            while (current != null) {
                deque.addFirst(current);
                current = current.left;
            }
        }

        /**
         * Проверка наличия следующего элемента
         * Средняя
         * Ресурсоёмкость   - O(1)
         * Трудоёмкость     - O(1)
         */
        @Override
        public boolean hasNext() {
            return !deque.isEmpty();
        }

        /**
         * Поиск следующего элемента
         * Средняя
         * Ресурсоёмкость   - O(1)
         * Трудоёмкость     - O(n)
         */
        @Override
        public T next() {
            current = deque.removeFirst();
            Node<T> node = current;

            if (current == null) throw new NoSuchElementException();
            if (node.right != null) {
                node = node.right;
                while (node != null){
                    deque.addFirst(node);
                    node = node.left;
                }
            }
            return current.value;
        }

        /**
         * Удаление следующего элемента
         * Сложная
         * Ресурсоемкость   - O(1)
         * Трудоёмкость     - O(n)
         */
        @Override
        public void remove() {
            root = BinaryTree.this.remove(root, current.value);
            size--;
        }
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    @Override
    public int size() {
        return size;
    }


    @Nullable
    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    /**
     * Для этой задачи нет тестов (есть только заготовка subSetTest), но её тоже можно решить и их написать
     * Очень сложная
     */
    @NotNull
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        throw new NotImplementedError();
    }

    /**
     * Найти множество всех элементов меньше заданного
     * Сложная
     * Ресурсоемкость   - O(n)
     * Трудоёмкость     - O(n)
     */
    @NotNull
    @Override
    public SortedSet<T> headSet(T toElement) {
        return generateSet(null, toElement);
    }

    /**
     * Найти множество всех элементов больше или равных заданного
     * Сложная
     * Ресурсоемкость   - O(n)
     * Трудоёмкость     - O(n)
     */
    @NotNull
    @Override
    public SortedSet<T> tailSet(T fromElement) {
        return generateSet(fromElement, null);
    }

    private SortedSet<T> generateSet(T fromElement, T toElement){
        BinaryTreeIterator iter = new BinaryTreeIterator();
        SortedSet<T> result = new TreeSet<>();
        while (iter.hasNext()) {
            T value = iter.next();
            if (toElement == null && fromElement != null && value.compareTo(fromElement) >= 0) result.add(value);
            else if (toElement != null && fromElement == null && value.compareTo(toElement) < 0) result.add(value);
        }
        return result;
    }



    @Override
    public T first() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    @Override
    public T last() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }
}
