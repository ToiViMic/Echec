package Metier.Piece;

import java.util.ArrayList;

public class Roi extends Piece
{
	//vérifier que l'on déplace sur le plateau
	
	
	private static final char TYPE_ROI = 'K';

	public Roi(int lig, int col, char couleur)
	{
		super(lig, col, TYPE_ROI, couleur);
	}

	public boolean peutDeplacer(int ligArr, int colArr, ArrayList<Piece> ensPiece) 
	{
		int diffLig = Math.abs(ligArr - this.getLig());
		int diffCol = Math.abs(colArr - this.getCol());

		// Vérification du déplacement d'une case dans n'importe quelle direction
		if ((diffLig <= 1 && diffCol == 0) || (diffLig == 0 && diffCol <= 1) || (diffLig == 1 && diffCol == 1)) 
		{
			// Vérification que la case de destination ne contient pas une pièce de la même couleur
			for (Piece p : ensPiece) 
			{
				if (p.getLig() == ligArr && p.getCol() == colArr && p.getCouleur() == this.getCouleur()) 
				{
					return false; // Le Roi ne peut pas se déplacer sur une pièce de la même couleur
				}
			}
			return true; // Le déplacement est valide pour le Roi
		}

		return false;
	}


}
