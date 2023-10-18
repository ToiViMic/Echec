package Metier.Piece;

import java.util.ArrayList;

public class Reine extends Piece
{
	private static final char TYPE_REINE = 'Q';

	public Reine(int lig, int col, char couleur)
	{
		super(lig, col, TYPE_REINE, couleur);
	}

	public boolean peutDeplacer(int lig, int col, ArrayList<Piece> ensPiece) 
	{
		return false;
	}

}