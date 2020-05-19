/**
 * Keeps track of a grid of cells and information about the number of
 * cells per row and column
 *
 */
public class Environment {
  private int rows;
  private int cols;
  private Cell[][] cells;
  
  public Environment(int r, int c) {
    rows = r;
    cols = c;
    cells = new Cell[rows][cols];
    for(int i = 0; i < rows; i++)
    {
      for(int j = 0; j < cols; j++)
      {
        cells[i][j] = new Cell();
      }
    }
  }
  
/**
 * @return Returns the number of columns in the Environment
 */
  public int getNumberOfColumns() {
    return rows;
  }

  /**
   * @return Returns the number of rows in the Environment
   */
  public int getNumberOfRows() {
    return cols;
  }

  /**
   * @return Returns the number of cells in the Environment
   */
  public int getNumberOfCells() {
    return cells.length;
  }
  
  /**
   * @return Returns the LifeForm if present in the cell (r,c)
   */
  public LifeForm getLifeForm(int r, int c) {
    if(r < 0 || c < 0 || r >= rows || c >= cols)
    {
      return null;
    }
    return cells[r][c].getLifeForm();
  }

  /**
   * adds a LifeForm to the specified cell
   * @param l The LifeForm to add
   * @param r The row to add it to
   * @param c The column to add it to
   */
  public void addLifeForm(LifeForm l, int r, int c) {
    if(r < 0 || c < 0 || r >= rows || c >= cols)
    {
      return;
    }
    cells[r][c].addLifeForm(l);   
  }

  /**
   * Removes the LifeForm from the specified cell
   * @param r The row index
   * @param c The column index
   */
  public void removeLifeForm(int r, int c) {
    if(r < 0 || c < 0 || r >= rows || c >= cols)
    {
      return;
    }
    cells[r][c].removeLifeForm();
  }

}
