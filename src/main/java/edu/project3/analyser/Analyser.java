package edu.project3.analyser;

import edu.project3.Statistic;
import java.util.List;

public interface Analyser<T> {
    List<Statistic> getStatistic(List<T> data, String from, String to);
}
