package boardgame;

// instancia o tabuleiro
public class Board {
	
	private Integer rows;
	private Integer columns;
	// tabuleiro � feito de linhas e colunas
	
	
	private Piece[][] pieces;
	//intancia a matriz de pe�as
	
	public Board(Integer rows, Integer columns) { // recebe da partida de xadrez o n�mero de linhas e coluans
		if (rows <1 || columns <1) {
			throw new BoardException("O tabuleiro de de xadrez deve ser uma matriz 8x8");
		}
		this.rows = rows; 
		this.columns = columns;
		pieces = new Piece[rows][columns]; // ao ser instanciado o tabuleio intancia tamb�m a matriz de pe�as de mesmo tamanho
	}
	public Integer getRows() {
		return rows;
	}

	public Integer getColumns() {
		return columns;
	}
	public Piece piece (int row, int column) { // diz a posi��o da pe�a
		if (!positionExists(row,column)) {
			throw new BoardException("posi��o inexistente!");
		}
		return pieces [row][column];
	}
	public Piece piece (Position position) {// busca a posi��o da pe�a
		if (!positionExists(position)) {
			throw new BoardException("posi��o inexistente!");
		}
		return pieces [position.getRow()][position.getColumn()];
	}
	
	public void placePiece (Piece piece, Position position) {
		if (thereIsAPiece(position)) {
			throw new BoardException("ja h� uma pe�a na posi��o: "+position);
		}
		pieces [position.getRow()][position.getColumn()] = piece; // atribui a pe�a "piece" a matriz de pe�as "pieces"
		piece.position = position; // atribui a posi��o "position" a atribui��o de posi��o da pe�a
	}
	public Piece removePiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("posi��o inexistente!");
		}
		if (piece(position)== null) {
			return null;
		}
		Piece aux = piece(position);
		aux.position = null;
		pieces[position.getRow()][position.getColumn()] = null;
		return aux;
		
	}
	public boolean positionExists (int row, int column) {
		return row >= 0 && row<rows && column >=0 && column < columns;
	}
	public boolean positionExists (Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}
	public boolean thereIsAPiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("posi��o inexistente!");
		}
		return piece(position) !=null;
	}
}
