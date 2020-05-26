package chess;

import boardgame.Board;
import boardgame.Piece;

// é uma subclasse de peças que irá conter as peças funcionais do jogo

public abstract class ChessPiece extends Piece {
	
	private Color color;

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	


}
