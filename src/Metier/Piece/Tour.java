package Metier.Piece;

import java.util.ArrayList;

public class Tour extends Piece
{
	private static final char TYPE_TOUR = 'T';

	public Tour(int lig, int col, char couleur)
	{
		super(lig, col, TYPE_TOUR, couleur);
	}

	public boolean peutDeplacer(int lig, int col, ArrayList<Piece> ensPiece) 
	{
		return false;
	}
}