package ru.express.bank;

import java.util.*;
import java.util.function.UnaryOperator;

public class DIYArrayList<E> implements List<E> {

  private int size = 0;
  private Object[] elements;
  private static final int DEFAULT_SIZE = 10;

  public DIYArrayList() {
    elements = new Object[DEFAULT_SIZE];
  }

  public DIYArrayList(int s) {
    elements = new Object[s];
  }

  @Override
  public void replaceAll(UnaryOperator<E> operator) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void sort(Comparator<? super E> c) {
    Arrays.sort((E[]) elements, 0, size, c);
  }

  @Override
  public Spliterator<E> spliterator() {
    throw new UnsupportedOperationException();
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean contains(Object o) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Iterator<E> iterator() {
    return new DIYIterator();
  }

  @Override
  public Object[] toArray() {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> T[] toArray(T[] a) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean add(E e) {
    Integer newSize = size + 1;
    elements = Arrays.copyOf(elements, newSize);
    elements[size] = e;
    size++;
    return true;
  }

  @Override
  public boolean remove(Object o) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean addAll(int index, Collection<? extends E> c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void clear() {
    throw new UnsupportedOperationException();
  }

  @Override
  public E get(int index) {
    return (E)elements[index];
  }

  @Override
  public E set(int index, E element) {
    Objects.checkIndex(index, size);
    E oldElement = (E)elements[index];
    elements[index] = element;
    return oldElement;
  }

  @Override
  public void add(int index, E element) {
    throw new UnsupportedOperationException();
  }

  @Override
  public E remove(int index) {
    throw new UnsupportedOperationException();
  }

  @Override
  public int indexOf(Object o) {
    throw new UnsupportedOperationException();
  }

  @Override
  public int lastIndexOf(Object o) {
    throw new UnsupportedOperationException();
  }

  @Override
  public ListIterator<E> listIterator() {
    return new DIYIteratorList();
  }

  @Override
  public ListIterator<E> listIterator(int index) {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<E> subList(int fromIndex, int toIndex) {
    throw new UnsupportedOperationException();
  }

  private class DIYIterator implements Iterator<E> {
    int cursor;

    @Override
    public boolean hasNext() {
      return cursor != size;
    }

    @Override
    public E next() {
      if (cursor > size) {
        throw new NoSuchElementException();
      }

      E element = (E) elements[cursor];
      cursor++;
      return element;
    }
  }

  private class DIYIteratorList implements ListIterator<E> {
    int cursor;
    int currentIndex = -1;

    @Override
    public boolean hasNext() {
      return cursor != size;
    }

    @Override
    public E next() {
      if (cursor > size) {
        throw new NoSuchElementException();
      }

      E element = (E) elements[cursor];
      currentIndex = cursor;
      cursor++;
      return element;
    }

    @Override
    public boolean hasPrevious() {
      return cursor != 0;
    }

    @Override
    public E previous() {
      if (cursor < 0) {
        throw new NoSuchElementException();
      }

      E element = (E) elements[cursor];
      currentIndex = cursor;
      cursor--;
      return element;
    }

    @Override
    public int nextIndex() {
      return cursor;
    }

    @Override
    public int previousIndex() {
      return currentIndex - 1;
    }

    @Override
    public void remove() {
      if (currentIndex < 0) {
        throw new IllegalStateException();
      }
      DIYArrayList.this.remove(currentIndex);
      cursor = currentIndex;
    }

    @Override
    public void set(E e) {
      if (currentIndex < 0) {
        throw new IllegalStateException();
      }
      DIYArrayList.this.set(currentIndex, e);
    }

    @Override
    public void add(E e) {
      throw new UnsupportedOperationException();
    }
  }
}