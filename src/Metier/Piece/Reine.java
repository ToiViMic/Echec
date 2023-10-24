package Metier.Piece;

import java.util.ArrayList;

import Metier.Piece.Fou;
import Metier.Piece.Tour;

public class Reine extends Piece
{
	private static final char TYPE_REINE = 'Q';

	public Reine(int lig, int col, char couleur)
	{
		super(lig, col, TYPE_REINE, couleur);
	}

	public boolean peutDeplacer(int ligArr, int colArr, ArrayList<Piece> ensPiece) 
	{
		int ligDep  = super.getLig();
		int colDep  = super.getCol();

		int diffLig = Math.abs(ligArr - super.getLig());
    	int diffCol = Math.abs(colArr - super.getCol());

		//verifie que l'on deplace en ligne OU en colonne
		if ((diffCol > 0 && diffLig == 0) || (diffCol == 0 && diffLig > 0) || (diffLig == diffCol))
		{

			if ((diffLig == diffCol))
			{
				// Vérification du chemin libre entre ligDep, colDep et ligArr, colArr
				int incLig = (ligArr > ligDep) ? 1 : -1;
				int incCol = (colArr > colDep) ? 1 : -1;
				int tempLig = ligDep + incLig;
				int tempCol = colDep + incCol;

				while (tempLig != ligArr && tempCol != colArr) 
				{
					for ( Piece p : ensPiece )
					{
						if (p.getLig() == tempLig && p.getCol() == tempCol)
							return false; // Il y a une pièce sur le chemin
					}
					tempLig += incLig;
					tempCol += incCol;
				}
			}
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
		}

		return true;
	}

}