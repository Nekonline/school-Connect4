
public class Token {
	
	char c;
	
	protected Token(char c) {
		this.c = c;
	}
	
	protected char GetToken(){
		return this.c;
	}
	
	protected void SetToken(char c){
		this.c = c;
	}
	
}
