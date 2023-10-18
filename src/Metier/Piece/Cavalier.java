package Metier.Piece;

import java.util.ArrayList;

public class Cavalier extends Piece
{
	private static final char TYPE_CAV = 'C';

	public Cavalier(int lig, int col, char couleur)
	{
		super(lig, col, TYPE_CAV, couleur);
	}

	public boolean peutDeplacer(int ligArr, int colArr, ArrayList<Piece> ensPiece) 
	{	
		int diffLig = Math.abs(ligArr - super.getLig());
    	int diffCol = Math.abs(colArr - super.getCol());

		// Vérification de la condition en "L"
		if ((diffLig == 2 && diffCol == 1) || (diffLig == 1 && diffCol == 2)) 
		{
			// Vérification des limites de l'échiquier
			if (ligArr >= 0 && ligArr < 8 && colArr >= 0 && colArr < 8) 
				return true; // Le déplacement est valide pour un Cavalier
		}

		return false;
	}

}