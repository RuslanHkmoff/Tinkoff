package edu.hw6.task3;

import java.nio.file.DirectoryStream;
import java.nio.file.Path;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {
    // TODO
    default AbstractFilter and(AbstractFilter filter) {
        return (predicate) -> this.accept(predicate) && filter.accept(predicate);
    }
}
