package Metier.Piece;

import java.util.ArrayList;

//classe mere de pion, cavalier, etc.
public abstract class Piece 
{
	private int  lig;
	private int  col;
	private char type;
	private char couleur;
	
	public Piece(int lig, int col, char type, char couleur)
	{
		this.lig     = lig;
		this.col     = col;
		this.type    = type;
		this.couleur = couleur;
	}

	public abstract boolean peutDeplacer(int ligArr, int colArr, ArrayList<Piece> ensPiece);
	public void    deplacer    (int ligArr, int colArr)
	{
		lig = ligArr;
		col = colArr;
	}

	public int  getLig()     { return lig;     }
	public int  getCol()     { return col;     }
	public char getType()    { return type;    }
	public char getCouleur() { return couleur; }

	public void setLig(int lig)          { this.lig     = lig;     }
	public void setCol(int col)          { this.col     = col;     }
	public void setType(char type)       { this.type    = type;    }
	public void setCouleur(char couleur) { this.couleur = couleur; }

}
