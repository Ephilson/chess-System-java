package chess;

import boardgame.Board;

public class ChessMatch {

	private Board board;

	public ChessMatch() {
		board = new Board(8,8); // ao iniciar uma partida a partida incia um tabuleiro 8x8
	}
	
	public ChessPiece[][] getPieces(){ // a partida era buscar a posição das peças
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];// cria uma matriz de tamanho igual ao tabuleiro
		for(int i=0;i<board.getRows();i++) {
			for(int j=0;j<board.getColumns();j++) {
				mat[i][j] = (ChessPiece) board.piece(i,j); // retorna posição da peça tipo "peça" e indicamos que essa deve ser do tipo "peça de xadrez"
			}
		}
		return mat; // retorna a matriz de peças de xadrez
	}
	
	
	
}
