public class GridInsertException extends RuntimeException {
	private int badColumn;
	
	public GridInsertException(int badColumn) {
		super("The column : "+badColumn+" is already full.");
		this.badColumn = badColumn;
	}
}
