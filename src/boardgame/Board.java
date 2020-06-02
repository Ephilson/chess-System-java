package boardgame;

// instancia o tabuleiro
public class Board {
	
	private Integer rows;
	private Integer columns;
	// tabuleiro é feito de linhas e colunas
	
	
	private Piece[][] pieces;
	//intancia a matriz de peças
	
	public Board(Integer rows, Integer columns) { // recebe da partida de xadrez o número de linhas e coluans
		if (rows <1 || columns <1) {
			throw new BoardException("O tabuleiro de de xadrez deve ser uma matriz 8x8");
		}
		this.rows = rows; 
		this.columns = columns;
		pieces = new Piece[rows][columns]; // ao ser instanciado o tabuleio intancia também a matriz de peças de mesmo tamanho
	}
	public Integer getRows() {
		return rows;
	}

	public Integer getColumns() {
		return columns;
	}
	public Piece piece (int row, int column) { // diz a posição da peça
		if (!positionExists(row,column)) {
			throw new BoardException("posição inexistente!");
		}
		return pieces [row][column];
	}
	public Piece piece (Position position) {// busca a posição da peça
		if (!positionExists(position)) {
			throw new BoardException("posição inexistente!");
		}
		return pieces [position.getRow()][position.getColumn()];
	}
	
	public void placePiece (Piece piece, Position position) {
		if (thereIsAPiece(position)) {
			throw new BoardException("ja há uma peça na posição: "+position);
		}
		pieces [position.getRow()][position.getColumn()] = piece; // atribui a peça "piece" a matriz de peças "pieces"
		piece.position = position; // atribui a posição "position" a atribuição de posição da peça
	}
	public Piece removePiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("posição inexistente!");
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
			throw new BoardException("posição inexistente!");
		}
		return piece(position) !=null;
	}
}
