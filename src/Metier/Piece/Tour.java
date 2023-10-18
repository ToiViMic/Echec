package Metier.Piece;

import java.util.ArrayList;

public class Tour extends Piece
{
	private static final char TYPE_TOUR = 'T';

	public Tour(int lig, int col, char couleur)
	{
		super(lig, col, TYPE_TOUR, couleur);
	}

	public boolean peutDeplacer(int ligArr, int colArr, ArrayList<Piece> ensPiece) 
	{
		int diffLig = Math.abs(ligArr - super.getLig());
    	int diffCol = Math.abs(colArr - super.getCol());

		//verifie que l'on deplace en ligne OU en colonne
		if (!((diffCol > 0 && diffLig == 0) || (diffCol == 0 && diffLig > 0)))
		{
			return false;
		}

		//verifie qu'il n'y a personne entre le point de depart et celui d'arrive

		return true;
	}

	
}