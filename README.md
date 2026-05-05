# Maze Solver (Java Swing) — AI Search Strategies Visualizer

A Java Swing maze/pathfinding visualizer built for an **Artificial Intelligence course**.  
It demonstrates and compares classic search strategies on a **50×50 grid** with an interactive UI for drawing mazes, placing start/goal cells, and visualizing the explored states and final path.

<img src="https://i.imgur.com/rbgtzgd.gif" width="420" alt="Maze Solver demo">

## Implemented algorithms

- **A\*** (`AStar`): priority by `f = g + h` (tie-break by `h`)
- **Uniform Cost Search** (`Uniform Cost`): priority by `g`  
  (with unit step cost on a grid, it behaves similarly to BFS)
- **Greedy Best-First Search** (`Best Cost`): priority by `h` only

### Heuristic
Manhattan distance to the nearest goal:
- `|goalRow - row| + |goalCol - col|`
- Multiple goals supported (minimum distance is used)

## UI overview

### Cell types
- **Start** (blue) — single start point
- **Goal** (green) — multiple goals supported
- **Block** (black) — obstacles
- **Normal** (white) — empty cell

### Interaction
- Clicking applies the currently selected cell type.
- With **Block** selected, click-and-drag paints blocks.

<img src="https://i.imgur.com/CgLsLjp.gif" width="420" alt="drawing demo">

### Controls
- **search**: runs the selected algorithm and visualizes exploration + final path
- **reset**: clears start/goals and resets cell types
- **clear canvas**: clears visualization/normal cells while keeping blocks/start/goals
- **generate maze**: random blocks (~35%)

### Visualization colors
- **Yellow**: discovered / added to open list
- **Pink**: final traced path (excluding goals)
- **Blue**: start
- **Green**: goals
- **Black**: blocks
- **White**: normal

### Output
- Displays `cost is : X` where `X` is the number of steps along the traced solution path.

## How it works (high level)

- 4-neighborhood grid (left/right/up/down)
- Open list: `PriorityQueue` ordered by the selected strategy comparator
- Closed set to avoid reprocessing
- Unit step cost (`+1` per move)
- Stops when any goal is reached and traces parents back to the start

## Project structure

- `untitled/main/`
  - `Main.java` — JFrame setup / entry point (`main.Main`)
  - `MazePanel.java` — 50×50 grid + random maze generation
  - `CellPanel.java` — per-cell UI + mouse interaction + drag-to-paint blocks
  - `Cell.java`, `CellType.java` — model + heuristic calculation
  - `SettingsManger.java` — shared state (algorithm, selected cell type, start/goals, grid)
  - `Utilities.java` — reset + search + path tracing/coloring
  - UI panels: `AlgorithmSettingsPanel.java`, `AlgorithmTypeChoicesPanel.java`, `TypeOfCellChoicesPanel.java`, `MainButtons.java`
- `untitled/SearchStrategies/`
  - `AbstractSearchStrategy.java` — base class providing the open-list `PriorityQueue`
  - `AStarStrategy.java`, `UniformCostSearchStrategy.java`, `BestFirstSearchStrategy.java` — comparators
  - `AlgorithmType.java` — enum
  - `SearchStrategyFactory.java` — strategy creation

## Running

Main class: `main.Main` (`untitled/main/Main.java`)

### IDE
Run `main.Main`.

### Terminal

macOS / Linux / Git Bash:
```bash
mkdir -p out
find untitled -name "*.java" > sources.txt
javac -d out @sources.txt
java -cp out main.Main
```

Windows (PowerShell):
```powershell
New-Item -ItemType Directory -Force out | Out-Null
$files = Get-ChildItem -Recurse -Filter *.java -Path .\untitled | ForEach-Object { $_.FullName }
javac -d out $files
java -cp out main.Main
```

## Authors
© 2024 Alaa Alawneh, Noor Honjol
