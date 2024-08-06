package SearchStrategies;

import java.util.List;

public interface ISearchStrategy {
    //this will be changed with implementation of algorithm
    void compare(List<Object> openList, List<Object>closeList);
}
