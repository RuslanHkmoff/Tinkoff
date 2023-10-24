package edu.hw3;

import java.util.Collection;
import java.util.Iterator;

public class BackwardIterator<T> implements Iterator<T> {
    private final Object[] collectionArray;

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    private int index;

    public BackwardIterator(Collection<T> collection) {
        index = collection.size() - 1;
        collectionArray = collection.toArray();
    }

    @Override
    public boolean hasNext() {
        return index >= 0;
    }

    @Override
    public T next() {
        return (T) collectionArray[index--];
    }
}
