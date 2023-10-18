package Metier.Piece;

import java.util.ArrayList;

public class Pion extends Piece
{
	private static final char TYPE_PION = 'P';

	//private boolean premierDepla;
	//private boolean extremEchiq; pour plus tard

	public Pion(int lig, int col, char couleur)
	{
		super(lig, col, TYPE_PION, couleur);
		//this.premierDepla = true;
	}

	public boolean peutDeplacer(int ligArr, int colArr, ArrayList<Piece> ensPiece) 
	{
		int diffLig = ligArr - this.getLig();
		int diffCol = Math.abs(colArr - this.getCol());

		// Vérification du déplacement vers l'avant
		if (this.getCouleur() == 'B') 
		{
			// Pions Blancs
			if (diffLig == -1 && diffCol == 0) 
			{
				return true; // Déplacement d'une case vers l'avant
			} 
			else if (this.getLig() == 6 && diffLig == -2 && diffCol == 0) 
			{
				return true; // Premier déplacement de deux cases vers l'avant
			} 
			else if (diffLig == -1 && diffCol == 1) 
			{
				for (Piece p : ensPiece)
				{
					if (p.getLig() == ligArr && p.getCol() == colArr)
						return true; // Capture diagonale d'une pièce
				}
			}
		} 
		else if (this.getCouleur() == 'N') {
			// Pions Noirs
			if (diffLig == 1 && diffCol == 0) 
			{
				return true; // Déplacement d'une case vers l'avant
			} 
			else if (this.getLig() == 1 && diffLig == 2 && diffCol == 0) 
			{
				return true; // Premier déplacement de deux cases vers l'avant
			} 
			else if (diffLig == 1 && diffCol == 1) 
			{
				for (Piece p : ensPiece)
				{
					if (p.getLig() == ligArr && p.getCol() == colArr)
						return true; // Capture diagonale d'une pièce
				}
			}
		}

		return false; // Le déplacement n'est pas valide pour un pion
	}

	public void deplacer(int ligArr, int colArr) 
	{
		super.deplacer(ligArr, colArr);
		//this.premierDepla = false;
	}
}