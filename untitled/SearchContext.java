import SearchStrategies.ISearchStrategy;

import java.util.ArrayList;
import java.util.List;

public class SearchContext {

    protected ISearchStrategy searchStrategy;

    //the type will be changed
    protected List<Object> openList;
    protected List<Object> closeList;

    public SearchContext(ISearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
        openList = new ArrayList<Object>();
        closeList = new ArrayList<>();
    }

    //the type will be changed
    public void search(Object graph){

    }

    

}