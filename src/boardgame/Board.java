package boardgame;


// instancia o tabuleiro
public class Board {
	
	private Integer rows;
	private Integer columns;
	// tabuleiro � feito de linhas e colunas
	
	private Piece[][] pieces;
	//intancia a matriz de pe�as
	
	public Board(Integer rows, Integer columns) { // recebe da partida de xadrez o n�mero de linhas e coluans
		super();
		this.rows = rows; 
		this.columns = columns;
		pieces = new Piece[rows][columns]; // ao ser instanciado o tabuleio intancia tamb�m a matriz de pe�as de mesmo tamanho
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getColumns() {
		return columns;
	}
	public void setColumns(Integer columns) {
		this.columns = columns;
	}
	public Piece piece (int row, int column) { // diz a posi��o da pe�a
		return pieces [row][column];
	}
	public Piece piece (Position position) {// busca a posi��o da pe�a
		return pieces [position.getRow()][position.getColumn()];
	}
	
}
