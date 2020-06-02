package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private int turn;
	private int aux;
	private Color currentPlayer;
	private Board board;
	private boolean check;
	
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();
	
	public ChessMatch() {
		board = new Board(8,8); // ao iniciar uma partida a partida incia um tabuleiro 8x8
		turn = 1;
		aux = 1;
		currentPlayer = Color.WHITE;
		initialSetup(); // incia a posição das peças
	}
	
	public boolean getCheck() {
		return check;
	}

	public int getTurn() {
		return turn;
	}
	public Color getCurrentPlayer() {
		return currentPlayer;
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
	
	public boolean[][] possibleMoves(ChessPosition sourcePosition){
		Position position = sourcePosition.toPosition();
		validadeSourcePosition(position);
		return board.piece(position).possibleMoves();
	}
	
	public ChessPiece perfomChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validadeSourcePosition(source);
		validadeTargetPosition(source,target);
		Piece capturedPiece = makeMove(source,target);
		
		if (testCheck(currentPlayer)) {
			undoMove(source,target,capturedPiece);
			throw new ChessException("tou can't put yourself int check");
		}
		
		check = (testCheck(opponent(currentPlayer)))? true:false;
		
		nextTurn();
		return (ChessPiece)capturedPiece;
	}
	
	private Piece makeMove (Position source, Position target) {
		Piece p = board.removePiece(source);
		Piece capturePiece = board.removePiece(target);
		board.placePiece(p, target);
		if (capturePiece!=null) {
			piecesOnTheBoard.remove(capturePiece);
			capturedPieces.add(capturePiece);
		}

		return capturePiece;
	}
	
	private void undoMove(Position source, Position target,Piece capturedPiece) {
		Piece p = board.removePiece(target);
		board.placePiece(p, source);
		
		if (capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}
	}
	
	private void validadeSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("Position not on the board ");
		}
		if (currentPlayer != ((ChessPiece)board.piece(position)).getColor()) {
			throw new ChessException("The chosen piece is not yours");
		}
		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible move for the chosen piece");
		}
	}
	private void validadeTargetPosition (Position source, Position target) {
		if (!board.piece(source).possibleMove(target)) {
			throw new ChessException("The chosen piece can't move to target position");
		}
	}
	
	private void nextTurn() {
		aux ++;
		if (aux %2 ==1) {
			turn++;
		}
		currentPlayer = (currentPlayer == Color.WHITE)? Color.BLACK : Color.WHITE;
	}
	
	protected Color opponent(Color color) {
		return (color == Color.WHITE)? Color.BLACK : Color.WHITE;
	}
	
	private ChessPiece king(Color color) {
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor()==color).collect(Collectors.toList());
		for (Piece p : list) {
			if(p instanceof King) {
				return (ChessPiece) p;
			}
		}
		throw new IllegalStateException("there is no"+color+"king on the board");
		
	}
	
	private boolean testCheck(Color color) {
		Position kingPosition = king(color).getChessPosition().toPosition();
		List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor()==opponent(color)).collect(Collectors.toList());
		for (Piece piece : opponentPieces) {
			boolean[][] mat = piece.possibleMoves();
			if(mat[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;
			}
			
		}
		return false;
	}
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece);
	}
	
	private void initialSetup() {
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
	}
	
}
