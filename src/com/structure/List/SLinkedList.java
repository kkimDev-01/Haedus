package com.structure.List;

import java.util.*;

public class SLinkedList<E> implements List<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public SLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    private Node<E> search(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> x = head;

        for (int i = 0; i < index; i++) {
            x = x.next;
        }

        return x;

    }

    public void addFirst(E value) {
        Node<E> newNode = new Node<E>(value);
        newNode.next = head;
        head = newNode;
        size++;
    }

    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    public void addLast(E value) {
        Node<E> newNode = new Node<E>(value);

        if (size == 0) {
            addFirst(value);
            return;
        }

        tail.next = newNode;
        tail = newNode;
        size++;
    }

    @Override
    public void add(int index, E element) {

        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            addFirst(element);
            return;
        }

        if (index == size) {
            addLast(element);
            return;
        }

        Node<E> prev_Node = search(index -1);

        Node<E> next_Node = prev_Node.next;

        Node<E> newNode = new Node<E>(element);

        prev_Node.next = null;
        prev_Node.next = newNode;
        newNode.next = next_Node;
        size++;

    }

    public E remove() {
        Node<E> headNode = head;
        if (headNode == null) {
            throw new NoSuchElementException();
        }

        E element = headNode.data;

        Node<E> nextNode = head.next;

        head.data = null;
        head.next = null;

        head = nextNode;
        size++;

        if (size == 0) {
            tail = null;
        }

        return element;
    }

    @Override
    public E remove(int index) {

        if (index == 0) {
            return remove();
        }

        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> prevNode = search(index - 1);

        Node<E> removedNode = prevNode.next;

        Node<E> nextNode = removedNode.next;

        E element = removedNode.data;

        prevNode.next = nextNode;

        if (prevNode.next == null) {
            tail = prevNode;
        }

        removedNode.next = null;
        removedNode.data = null;

        size--;

        return element;
    }

    @Override
    public boolean remove(Object o) {

        Node<E> prevNode = head;
        boolean hasValue = false;
        Node<E> x = head;

        for (; x != null; x = x.next) {
            if (o.equals(x.data)) {
                hasValue = true;
                break;
            }
            prevNode = x;
        }

        if (x==null) {
            return false;
        }

        if (x.equals(head)) {
            remove();
            return true;
        }
        else {
            prevNode.next = x.next;

            if (prevNode.next == null) {
                tail = prevNode;
            }

            x.data = null;
            x.next = null;
            size--;
            return true;
        }

    }

    @Override
    public E get(int index) {
        return search(index).data;
    }

    @Override
    public E set(int index, E element) {

        Node<E> replaceNode = search(index);
        replaceNode.data = null;
        replaceNode.data = element;

        return element;

    }

    @Override
    public int indexOf(Object o) {
        int index = 0;

        for (Node<E> x = head; x != null; x = x.next) {
            if (o.equals(x.data)) {
                return index;
            }
            index++;

        }
        return -1;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
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
    public void clear() {

        for (Node<E> x = head; x != null;) {
            Node<E> nextNode = x.next;
            x.data = null;
            x.next = null;
            x = nextNode;
        }
        head = tail = null;

        size = 0;

    }

    @Override
    public Object clone() throws CloneNotSupportedException {

        @SuppressWarnings("unchecked")
        SLinkedList<? super E> clone = (SLinkedList<? super E>) super.clone();

        clone.head = null;
        clone.tail = null;
        clone.size = 0;

        for (Node<E> x = head; x != null; x = x.next) {
            clone.addLast(x.data);
        }

        return clone;

    }

    @Override
    public Object[] toArray() {

        Object[] array = new Object[size];
        int idx = 0;
        for (Node<E> x = head; x != null; x = x.next) {
            array[idx++] = (E) x.data;
        }

        return array;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }
}//class
