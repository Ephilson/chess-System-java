package boardgame;

// cria a classe de peças a qual o tabuleiro tem acesso

public class Piece {
	
	protected Position position;
	private Board board;
	
	public Piece(Board board) {
		this.board = board;
	}

	protected Board getBoard() {
		return board;
	}
	

	
	
}
