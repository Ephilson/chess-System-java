package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

// � uma subclasse de pe�as que ir� conter as pe�as funcionais do jogo

public abstract class ChessPiece extends Piece {
	
	
	private Color color;
	

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}
	
	protected boolean isThereOpponentPiece(Position position) {
		
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p != null && p.getColor() != color;
		
	}
	


}
