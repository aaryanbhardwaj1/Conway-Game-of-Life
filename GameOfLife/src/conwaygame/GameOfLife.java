package conwaygame;
import java.util.ArrayList;
/**
 * Conway's Game of Life Class holds various methods that will
 * progress the state of the game's board through it's many iterations/generations.
 *
 * Rules 
 * Alive cells with 0-1 neighbors die of loneliness.
 * Alive cells with >=4 neighbors die of overpopulation.
 * Alive cells with 2-3 neighbors survive.
 * Dead cells with exactly 3 neighbors become alive by reproduction.
 */
public class GameOfLife {

    // Instance variables
    private static final boolean ALIVE = true;
    private static final boolean  DEAD = false;

    private boolean[][] grid;    // The board has the current generation of cells
    private int totalAliveCells; // Total number of alive cells in the grid (board)

    /**
    * Default Constructor which creates a small 5x5 grid with five alive cells.
    * This variation does not exceed bounds and dies off after four iterations.
    */
    public GameOfLife() {
        grid = new boolean[5][5];
        totalAliveCells = 5;
        grid[1][1] = ALIVE;
        grid[1][3] = ALIVE;
        grid[2][2] = ALIVE;
        grid[3][2] = ALIVE;
        grid[3][3] = ALIVE;
    }

    /**
    * Constructor used that will take in values to create a grid with a given number
    * of alive cells
    * @param file is the input file with the initial game pattern formatted as follows:
    * An integer representing the number of grid rows, say r
    * An integer representing the number of grid columns, say c
    * Number of r lines, each containing c true or false values (true denotes an ALIVE cell)
    */
    public GameOfLife (String file) {
        
        StdIn.setFile(file);
            
        int r = StdIn.readInt();
        int c = StdIn.readInt();
            
        grid = new boolean[r][c];

        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                boolean stateOfCell = StdIn.readBoolean();
                grid[i][j] = stateOfCell;
                totalAliveCells++;
            }
        }
    }

    /**
     * Returns grid
     * @return boolean[][] for current grid
     */
    public boolean[][] getGrid () {
        return grid;
    }
    
    /**
     * Returns totalAliveCells
     * @return int for total number of alive cells in grid
     */
    public int getTotalAliveCells () {
        return totalAliveCells;
    }

    /**
     * Returns the status of the cell at (row,col): ALIVE or DEAD
     * @param row row position of the cell
     * @param col column position of the cell
     * @return true or false value "ALIVE" or "DEAD" (state of the cell)
     */
    public boolean getCellState (int row, int col) {

        if(grid[row][col] == true){
            return true;
        }

        return false; 
    }

    /**
     * Returns true if there are any alive cells in the grid
     * @return true if there is at least one cell alive, otherwise returns false
     */
    public boolean isAlive () {

        boolean alv = false;

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                if(grid[i][j] == true){
                    alv = true;
                }
            }
        }

        return alv;
    }

    /**
     * Determines the number of alive cells around a given cell.
     * Each cell has 8 neighbor cells which are the cells that are 
     * horizontally, vertically, or diagonally adjacent.
     * 
     * @param col column position of the cell
     * @param row row position of the cell
     * @return neighboringCells, the number of alive cells (at most 8).
     */
    public int numOfAliveNeighbors (int row, int col) {

        int aliveneighbors = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        if(((row > 0) && (row < (rows-1)) && ((col > 0) && (col < cols-1)))){ //Basic conditions (without edges or sides)
            if(grid[row+1][col] == true){
                aliveneighbors++;
            }if(grid[row][col+1] == true){
                aliveneighbors++;
            }if(grid[row-1][col] == true){
                aliveneighbors++;
            }if(grid[row][col-1] == true){
                aliveneighbors++;
            }if(grid[row-1][col-1] == true){
                aliveneighbors++;
            }if(grid[row-1][col+1] == true){
                aliveneighbors++;
            }if(grid[row+1][col-1] == true){
                aliveneighbors++;
            }if(grid[row+1][col+1] == true){
                aliveneighbors++;
            }
        }

        if((row == 0 && col == 0)){ //Top-left corner
            if(grid[row][col+1]){
                aliveneighbors++;
            }
            if(grid[row+1][col]){
                aliveneighbors++;
            }
            if(grid[row+1][col+1]){
                aliveneighbors++;
            }
            if(grid[row][cols-1]){
                aliveneighbors++;
            }
            if(grid[row+1][cols-1]){
                aliveneighbors++;
            }
            if(grid[rows-1][col]){
                aliveneighbors++;
            }
            if(grid[rows-1][col+1]){
                aliveneighbors++;
            }
            if(grid[rows-1][cols-1]){
                aliveneighbors++;
            }
        }

        if((row == 0 && col == cols-1)){ //Top-right corner
            if(grid[0][0]){
                aliveneighbors++;
            }
            if(grid[1][0]){
                aliveneighbors++;
            }
            if(grid[rows-1][0]){
                aliveneighbors++;
            }
            if(grid[row][col-1]){
                aliveneighbors++;
            }
            if(grid[row+1][col]){
                aliveneighbors++;
            }
            if(grid[row+1][col-1]){
                aliveneighbors++;
            }
            if(grid[rows-1][col]){
                aliveneighbors++;
            }
            if(grid[rows-1][col-1]){
                aliveneighbors++;
            }
        }

        if((row == rows-1 && col == 0)){ //Bottom-left corner
            if(grid[0][0]){
                aliveneighbors++;
            }
            if(grid[0][col+1]){
                aliveneighbors++;
            }
            if(grid[row-1][col]){
                aliveneighbors++;
            }
            if(grid[row-1][col+1]){
                aliveneighbors++;
            }
            if(grid[row][col+1]){
                aliveneighbors++;
            }
            if(grid[0][cols-1]){
                aliveneighbors++;
            }
            if(grid[row][cols-1]){
                aliveneighbors++;
            }
            if(grid[row-1][cols-1]){
                aliveneighbors++;
            }
        }
        
        if((row == rows-1 && col == cols-1)){ //Bottom-right corner
            if(grid[row][col-1]){
                aliveneighbors++;
            }
            if(grid[row-1][col]){
                aliveneighbors++;
            }
            if(grid[row-1][col-1]){
                aliveneighbors++;
            }
            if(grid[row][0]){
                aliveneighbors++;
            }
            if(grid[row-1][0]){
                aliveneighbors++;
            }
            if(grid[0][0]){
                aliveneighbors++;
            }
            if(grid[0][col]){
                aliveneighbors++;
            }
            if(grid[0][col-1]){
                aliveneighbors++;
            }
        }

        if((row == 0) && !((col == 0) || (col == cols-1))){ //Top-edge
            if(grid[row][col-1]){
                aliveneighbors++;
            }
            if(grid[row][col+1]){
                aliveneighbors++;
            }
            if(grid[row+1][col]){
                aliveneighbors++;
            }
            if(grid[row+1][col-1]){
                aliveneighbors++;
            }
            if(grid[row+1][col+1]){
                aliveneighbors++;
            }
            if(grid[rows-1][col]){
                aliveneighbors++;
            }
            if(grid[rows-1][col-1]){
                aliveneighbors++;
            }
            if(grid[rows-1][col+1]){
                aliveneighbors++;
            }
        }

        if((row == rows-1) && !((col == 0) || (col == cols-1))) { //Bottom-edge
            if(grid[row][col-1]){
                aliveneighbors++;
            }
            if(grid[row][col+1]){
                aliveneighbors++;
            }
            if(grid[row-1][col]){
                aliveneighbors++;
            }
            if(grid[row-1][col-1]){
                aliveneighbors++;
            }
            if(grid[row-1][col+1]){
                aliveneighbors++;
            }
            if(grid[0][col]){
                aliveneighbors++;
            }
            if(grid[0][col+1]){
                aliveneighbors++;
            }
            if(grid[0][col-1]){
                aliveneighbors++;
            }
        }
        
        if((col == 0) && !((row == 0) || (row == rows-1))){ //Left-edge
            if(grid[row+1][col]){
                aliveneighbors++;
            }
            if(grid[row-1][col]){
                aliveneighbors++;
            }
            if(grid[row][col+1]){
                aliveneighbors++;
            }
            if(grid[row-1][col+1]){
                aliveneighbors++;
            }
            if(grid[row+1][col+1]){
                aliveneighbors++;
            }
            if(grid[row][cols-1]){
                aliveneighbors++;
            }
            if(grid[row-1][cols-1]){
                aliveneighbors++;
            }
            if(grid[row+1][cols-1]){
                aliveneighbors++;
            }

        }

        if((col == cols-1) && !((row == 0) || (row == rows-1))){ //Right-edge
            if(grid[row+1][col]){
                aliveneighbors++;
            }
            if(grid[row-1][col]){
                aliveneighbors++;
            }
            if(grid[row][col-1]){
                aliveneighbors++;
            }
            if(grid[row-1][col-1]){
                aliveneighbors++;
            }
            if(grid[row+1][col-1]){
                aliveneighbors++;
            }
            if(grid[row][0]){
                aliveneighbors++;
            }
            if(grid[row-1][0]){
                aliveneighbors++;
            }
            if(grid[row+1][0]){
                aliveneighbors++;
            }
        }
        
        return aliveneighbors;
    }

    /**
     * Creates a new grid with the next generation of the current grid using 
     * the rules for Conway's Game of Life.
     * 
     * @return boolean[][] of new grid (this is a new 2D array)
     */
    public boolean[][] computeNewGrid () {

        int rn = grid.length;
        int cn = grid[0].length;

        boolean[][] theGrid = new boolean[rn][cn];

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if((grid[i][j] == true) && (numOfAliveNeighbors(i, j) < 2)){ //Cell dies of loneliness
                    theGrid[i][j] = false;
                }
                else if((grid[i][j] == true) && (numOfAliveNeighbors(i, j) > 3)){ //Cell dies to overpopulation
                    theGrid[i][j] = false;
                }
                else if((grid[i][j] == false) && (numOfAliveNeighbors(i, j) == 3)){ //Cell becomes alive by reproduction
                    theGrid[i][j] = true;
                }
                else{
                    theGrid[i][j] = grid[i][j];
                }
            }
        }

        return theGrid;
    }

    /**
     * Updates the current grid (the grid instance variable) with the grid denoting
     * the next generation of cells computed by computeNewGrid().
     * 
     * Updates totalAliveCells instance variable
     */
    public void nextGeneration () {

        grid = computeNewGrid();
    }

    /**
     * Updates the current grid with the grid computed after multiple (n) generations. 
     * @param n number of iterations that the grid will go through to compute a new grid
     */
    public void nextGeneration (int n) {

        for(int i = 0; i < n; i++){
            grid = computeNewGrid();
        }
    }

    /**
     * Determines the number of separate cell communities in the grid
     * @return the number of communities in the grid, communities can be formed from edges
     */
    
    public int numOfCommunities() {
        
        int numOfComms = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        WeightedQuickUnionUF wquf = new WeightedQuickUnionUF(rows, cols);
        for(int row = 0; row < grid.length; row++){
            for(int col = 0; col < grid[0].length; col++){
                if(grid[row][col]){
                    if(((row > 0) && (row < (rows-1)) && ((col > 0) && (col < cols-1)))){ //Basic conditions (without edges or sides)
                        if(grid[row+1][col] == true){
                            wquf.union(row, col, row+1, col);
                        }if(grid[row][col+1] == true){
                            wquf.union(row, col, row, col+1);
                        }if(grid[row-1][col] == true){
                            wquf.union(row, col, row-1, col);
                        }if(grid[row][col-1] == true){
                            wquf.union(row, col, row, col-1);
                        }if(grid[row-1][col-1] == true){
                            wquf.union(row, col, row-1, col-1);
                        }if(grid[row-1][col+1] == true){
                            wquf.union(row, col, row-1, col+1);
                        }if(grid[row+1][col-1] == true){
                            wquf.union(row, col, row+1, col-1);
                        }if(grid[row+1][col+1] == true){
                            wquf.union(row, col, row+1, col+1);
                        }
                    }
            
                    if((row == 0 && col == 0)){ //Top-left corner
                        if(grid[row][col+1]){
                            wquf.union(row, col, row, col+1);
                        }
                        if(grid[row+1][col]){
                            wquf.union(row, col, row+1, col);
                        }
                        if(grid[row+1][col+1]){
                            wquf.union(row, col, row+1, col+1);
                        }
                        if(grid[row][cols-1]){
                            wquf.union(row, col, row, cols-1);
                        }
                        if(grid[row+1][cols-1]){
                            wquf.union(row, col, row+1, cols-1);
                        }
                        if(grid[rows-1][col]){
                            wquf.union(row, col, rows-1, col);
                        }
                        if(grid[rows-1][col+1]){
                            wquf.union(row, col, rows-1, col+1);
                        }
                        if(grid[rows-1][cols-1]){
                            wquf.union(row, col, rows-1, cols-1);
                        }
                    }
            
                    if((row == 0 && col == cols-1)){ //Top-right corner
                        if(grid[0][0]){
                            wquf.union(row, col, 0, 0);
                        }
                        if(grid[1][0]){
                            wquf.union(row, col, 1, 0);
                        }
                        if(grid[rows-1][0]){
                            wquf.union(row, col, rows-1, 0);
                        }
                        if(grid[row][col-1]){
                            wquf.union(row, col, row, col-1);
                        }
                        if(grid[row+1][col]){
                            wquf.union(row, col, row+1, col);
                        }
                        if(grid[row+1][col-1]){
                            wquf.union(row, col, row+1, col-1);
                        }
                        if(grid[rows-1][col]){
                            wquf.union(row, col, rows-1, col);
                        }
                        if(grid[rows-1][col-1]){
                            wquf.union(row, col, rows-1, col-1);
                        }
                    }
            
                    if((row == rows-1 && col == 0)){ //Bottom-left corner
                        if(grid[0][0]){
                            wquf.union(row, col, 0, 0);
                        }
                        if(grid[0][col+1]){
                            wquf.union(row, col, 0, col+1);
                        }
                        if(grid[row-1][col]){
                            wquf.union(row, col, row-1, col);
                        }
                        if(grid[row-1][col+1]){
                            wquf.union(row, col, row-1, col+1);
                        }
                        if(grid[row][col+1]){
                            wquf.union(row, col, row, col+1);
                        }
                        if(grid[0][cols-1]){
                            wquf.union(row, col, 0, cols-1);
                        }
                        if(grid[row][cols-1]){
                            wquf.union(row, col, row, cols-1);
                        }
                        if(grid[row-1][cols-1]){
                            wquf.union(row, col, row-1, cols-1);
                        }
                    }
                    
                    if((row == rows-1 && col == cols-1)){ //Bottom-right corner
                        if(grid[row][col-1]){
                            wquf.union(row, col, row, col-1);
                        }
                        if(grid[row-1][col]){
                            wquf.union(row, col, row-1, col);
                        }
                        if(grid[row-1][col-1]){
                            wquf.union(row, col, row-1, col-1);
                        }
                        if(grid[row][0]){
                            wquf.union(row, col, row, 0);
                        }
                        if(grid[row-1][0]){
                            wquf.union(row, col, row-1, 0);
                        }
                        if(grid[0][0]){
                            wquf.union(row, col, 0, 0);
                        }
                        if(grid[0][col]){
                            wquf.union(row, col, 0, col);
                        }
                        if(grid[0][col-1]){
                            wquf.union(row, col, 0, col-1);

                        }
                    }
            
                    if((row == 0) && !((col == 0) || (col == cols-1))){ //Top-edge
                        if(grid[row][col-1]){
                            wquf.union(row, col, row, col-1);
                        }
                        if(grid[row][col+1]){
                            wquf.union(row, col, row, col+1);
                        }
                        if(grid[row+1][col]){
                            wquf.union(row, col, row+1, col);
                        }
                        if(grid[row+1][col-1]){
                            wquf.union(row, col, row+1, col-1);
                        }
                        if(grid[row+1][col+1]){
                            wquf.union(row, col, row+1, col+1);
                        }
                        if(grid[rows-1][col]){
                            wquf.union(row, col, rows-1, col);
                        }
                        if(grid[rows-1][col-1]){
                            wquf.union(row, col, rows-1, col-1);
                        }
                        if(grid[rows-1][col+1]){
                            wquf.union(row, col, rows-1, col+1);
                        }
                    }
            
                    if((row == rows-1) && !((col == 0) || (col == cols-1))) { //Bottom-edge
                        if(grid[row][col-1]){
                            wquf.union(row, col, row, col-1);
                        }
                        if(grid[row][col+1]){
                            wquf.union(row, col, row, col+1);
                        }
                        if(grid[row-1][col]){
                            wquf.union(row, col, row-1, col);
                        }
                        if(grid[row-1][col-1]){
                            wquf.union(row, col, row-1, col-1);
                        }
                        if(grid[row-1][col+1]){
                            wquf.union(row, col, row-1, col+1);
                        }
                        if(grid[0][col]){
                            wquf.union(row, col, 0, col);
                        }
                        if(grid[0][col+1]){
                            wquf.union(row, col, 0, col+1);
                        }
                        if(grid[0][col-1]){
                            wquf.union(row, col, 0, col-1);
                        }
                    }
                    
                    if((col == 0) && !((row == 0) || (row == rows-1))){ //Left-edge
                        if(grid[row+1][col]){
                            wquf.union(row, col, row+1, col);
                        }
                        if(grid[row-1][col]){
                            wquf.union(row, col, row-1, col);
                        }
                        if(grid[row][col+1]){
                            wquf.union(row, col, row, col+1);
                        }
                        if(grid[row-1][col+1]){
                            wquf.union(row, col, row-1, col+1);
                        }
                        if(grid[row+1][col+1]){
                            wquf.union(row, col, row+1, col+1);
                        }
                        if(grid[row][cols-1]){
                            wquf.union(row, col, row, cols-1);
                        }
                        if(grid[row-1][cols-1]){
                            wquf.union(row, col, row-1, cols-1);
                        }
                        if(grid[row+1][cols-1]){
                            wquf.union(row, col, row+1, cols-1);
                        }
            
                    }
            
                    if((col == cols-1) && !((row == 0) || (row == rows-1))){ //Right-edge
                        if(grid[row+1][col]){
                            wquf.union(row, col, row+1, col);
                        }
                        if(grid[row-1][col]){
                            wquf.union(row, col, row-1, col);
                        }
                        if(grid[row][col-1]){
                            wquf.union(row, col, row, col-1);
                        }
                        if(grid[row-1][col-1]){
                            wquf.union(row, col, row-1, col-1);
                        }
                        if(grid[row+1][col-1]){
                            wquf.union(row, col, row+1, col-1);
                        }
                        if(grid[row][0]){
                            wquf.union(row, col, row, 0);
                        }
                        if(grid[row-1][0]){
                            wquf.union(row, col, row-1, 0);
                        }
                        if(grid[row+1][0]){
                            wquf.union(row, col, row+1, 0);
                        }
                    }
                }
            }
        }

        ArrayList<Integer> comms = new ArrayList<Integer>();

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] && !(comms.contains(wquf.find(i, j)))){
                    comms.add(wquf.find(i, j));
                }
            }
        }

        return comms.size();
    }
}
