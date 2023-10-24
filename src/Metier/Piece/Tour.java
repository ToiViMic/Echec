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

		//si on se deplace en colonne
		if (diffCol > 0 && diffLig == 0)
		{
			if (ligArr < super.getLig())
				for (int cpt = super.getCol(); cpt < colArr; cpt++)
				{
					for (Piece p : ensPiece)
					{
						if (p.getCol()==cpt && p.getLig()==ligArr)
							return false;
					}
				}
			else
				for (int cpt = super.getCol(); cpt <= colArr; cpt--)
				{
					for (Piece p : ensPiece)
					{
						if (p.getCol()==cpt && p.getLig()==ligArr)
							return false;
					}
				}
		}
		//si on deplace en ligne
		if (diffCol == 0 && diffLig > 0)
		{
			if (ligArr < super.getLig())
				for (int cpt = super.getLig(); cpt < ligArr; cpt++)
				{
					for (Piece p : ensPiece)
					{
						if (p.getCol()==colArr && p.getLig()==cpt)
							return false;
					}
				}
			else
				for (int cpt = super.getLig(); cpt <= ligArr; cpt--)
				{
					for (Piece p : ensPiece)
					{
						if (p.getCol()==colArr && p.getLig()==cpt)
							return false;
					}
				}
		}

		return true;
	}

	
}