
public class Grid {
	
	int width;
	int height;
	char grid[][];

	
	protected Grid(int width, int height) {
		this.height = height;
		this.width = width;
		this.grid = new char[height][width];
	}
	
	protected int getHeight() {
		return this.height;		
	}
	protected int getWidth() {
		return this.width;		
	}
	
	protected void initGrid(){
		int i,j;
		for (i=0; i<width; i++){
			for (j=0; j<height ; j++){
				grid[j][i]='.';
			}		
		}	
	}
	
	protected void displayGrid(){
		int i,j,k;
		
		for (k=1; k<width+1; k++){
			System.out.print(k +"  ");
		}
		System.out.print("\n");
		
		for (j=0; j<height; j++){
			for(i=0; i<width; i++){
				System.out.print(grid[j][i]+"  ");
			}
			System.out.print("\n");
		}	
	}
	
	protected int insertToken(int column, Token token) throws GridInsertException{
		int i;
		char c = token.GetChar();
		for(i=height-1; i>=0; i--){
			if(grid[i][column] == '.'){
				grid[i][column] = c;
				return i;
			}
		}
		throw new GridInsertException(column);
	}
	
	protected int checkWin(int line, int column, Token token) {
		char c = token.GetChar();
		int i,j;
		int counter = 1;
		
		//LINE
		for (i=column-1; i>=0; i--) {
			if (grid[line][i] == c)
				counter ++;
			else
				break;
		}
		for (i=column+1; i<width; i++) {
			if (grid[line][i] == c)
				counter ++;
			else
				break;
		}
		// Check line
		if (counter >= 4)
			return 1;
		
		
		//COLUMN
		counter = 1;
		for (i=line-1; i>=0; i--) {
			if (grid[i][column] == c)
				counter ++;
			else 
				break;
		}
		for (i=line+1; i<height; i++) {
			if (grid[i][column] == c)
				counter ++;
			else
				break;
		}
		// Check column
		if (counter >= 4)
			return 1;
		
		
		// TODO UNE DIAG EST MERDIQUE
		// Diag 1
		counter=1;
		
		for (i=column-1, j=1; i>=0 && line-j>=0; i--, j--) {
			if (grid[line-j][i] == c)
				counter ++;
			else
				break;
		}
		for (i=column+1, j=1; i<width && line+j<height; i++, j++) {
			if (grid[line+j][i] == c)
				counter ++;
			else
				break;
		}
		// Check diag 1
		if (counter >= 4)
			return 1;
		
		// Diag 2
		counter=1;
		
		for (i=column-1, j=1; i>=0 && line+j<height ; i--, j++) {
			if (grid[line+j][i] == c)
				counter ++;
			else
				break;
		}
		for (i=column+1, j=1; i<width && line-j>=0; i++, j--) {
			if (grid[line-j][i] == c)
				counter ++;
			else
				break;
		}
		// Check diag 2
		if (counter >= 4)
			return 1;

		return 0;		
		
	}
	
	
}
