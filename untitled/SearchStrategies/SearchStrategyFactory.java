package SearchStrategies;

public class SearchStrategyFactory {
    public static AbstractSearchStrategy createSearchStrategy(AlgorithmType algorithmType) {
        switch (algorithmType) {
            case AStar -> {
                return new AStarStrategy();
            }
            case BestFirstSearch ->{
                return new BestFirstSearchStrategy();
            }
            case UniformCostSearch -> {
                return new UniformCostSearchStrategy();
            }

        }
        return null;
    }
}
