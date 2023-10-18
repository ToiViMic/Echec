package Metier.Piece;

import java.util.ArrayList;

public class Fou extends Piece
{
	private static final char TYPE_FOU = 'F';

	public Fou(int lig, int col, char couleur)
	{
		super(lig, col, TYPE_FOU, couleur);
	}

	public boolean peutDeplacer(int ligArr, int colArr, ArrayList<Piece> ensPiece) 
	{	
		int ligDep  = super.getLig();
		int colDep  = super.getCol();
		int diffLig = Math.abs(ligArr - ligDep);
		int diffCol = Math.abs(colArr - colDep);

		// Vérification du déplacement en diagonale
		if (diffLig == diffCol) 
		{
			// Vérification des limites de l'échiquier
			if (ligArr >= 0 && ligArr < 8 && colArr >= 0 && colArr < 8) 
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

				return true; // Le déplacement est valide pour un Fou
			}
		}

		return false; // Le déplacement n'est pas valide pour un Fou
	}
}