package Metier.Piece;

import java.util.ArrayList;

public class Pion extends Piece
{
	private static final char TYPE_PION = 'P';

	private boolean premierDepla;
	//private boolean extremEchiq; pour plus tard

	public Pion(int lig, int col, char couleur)
	{
		super(lig, col, TYPE_PION, couleur);
		this.premierDepla = true;
	}

	public boolean peutDeplacer(int ligArr, int colArr, ArrayList<Piece> ensPiece) 
	{
		//verifie si on deplace
		if (ligArr == super.getLig() ) { return false; }

		//verifie que l'on deplace vers l'avant le pion en fonction de la couleur
		if (premierDepla)
		{
			if (!( super.getCol() == colArr &&
				((super.getCouleur() == 'N' && super.getLig() <= ligArr - 2) ||
		         (super.getCouleur() == 'B' && super.getLig() <= ligArr + 2) )))
				return false;
		}
		else
		{
			if (!( super.getCol() == colArr && 
				((super.getCouleur() == 'N' && super.getLig() == ligArr - 1) ||
		    	 (super.getCouleur() == 'B' && super.getLig() == ligArr + 1 ) )))
				return false;
		}


		//verifie qu'il y ai un ennemi sur les diagonales
		if ( ( (colArr == (super.getCol()+1) && ligArr == super.getLig()+1 || colArr == (super.getCol()-1) && ligArr == super.getLig()+1) && super.getCouleur() == 'N' ) ||
		     ( (colArr == (super.getCol()+1) && ligArr == super.getLig()-1 || colArr == (super.getCol()-1) && ligArr == super.getLig()-1) && super.getCouleur() == 'B' )  )
		{
			for ( Piece p : ensPiece )
			{
				if (( p.getLig() == ligArr && p.getCol() == colArr ) && super.getCouleur() != p.getCouleur()) { return true;  }
				if (( p.getLig() == ligArr && p.getCol() == colArr ) && super.getCouleur() == p.getCouleur()) { return false; }
			}
		}

		//verifie qu'il n'y ai personne sur le chemin en ligne droite
		for ( Piece p : ensPiece )
		{
			if (( super.getCol() == colArr && p.getLig() == ligArr && p.getCol() == colArr )) { return false;}
		}
		
		return true;
	}

	public void echangBoutPlateau (Piece piece)
	{
		piece.setLig(this.getLig());
		piece.setCol(this.getCol());
	}

	public void deplacer(int lig, int col) 
	{
		super.deplacer(lig, col);
		this.premierDepla = false;
	}
}