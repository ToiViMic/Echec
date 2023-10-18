package Metier.Piece;

import java.util.ArrayList;

public class Roi extends Piece
{
	private static final char TYPE_ROI = 'K';

	public Roi(int lig, int col, char couleur)
	{
		super(lig, col, TYPE_ROI, couleur);
	}

	public boolean peutDeplacer(int lig, int col, ArrayList<Piece> ensPiece) 
	{
		return false;
	}


}