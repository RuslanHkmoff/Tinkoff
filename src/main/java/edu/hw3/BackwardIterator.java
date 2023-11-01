package edu.hw3;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BackwardIterator<T> implements Iterator<T> {
    private final List<T> collectionArray;
    private int index;

    public BackwardIterator(Collection<T> collection) {
        index = collection.size() - 1;
        collectionArray = List.copyOf(collection);
    }

    @Override
    public boolean hasNext() {
        return index >= 0;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No such element");
        }
        return collectionArray.get(index--);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported");
    }
}
