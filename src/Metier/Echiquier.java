package Metier;

import Metier.Piece.*;
import java.util.ArrayList;

public class Echiquier
{
	public final String[][] DEBUT_DE_PARTIE = {
	{"TN","CN","FN","QN","KN","FN","CN","TN"},
	{"PN","PN","PN","PN","PN","PN","PN","PN"},
	{"  ","  ","  ","  ","  ","  ","  ","  "},
	{"  ","  ","  ","  ","  ","  ","  ","  "},
	{"  ","  ","  ","  ","  ","  ","  ","  "},
	{"  ","  ","  ","  ","  ","  ","  ","  "},
	{"PB","PB","PB","PB","PB","PB","PB","PB"},
	{"TB","CB","FB","KB","QB","FB","CB","TB"}};

	private static int       LIMITE_PLATEAU = 8;
	private char             couleurTour;
	
	private ArrayList<Piece> ensPiece;
	private char[][]         plateau;

	public Echiquier()
	{
		this.couleurTour = 'B';
		this.ensPiece    = new ArrayList<Piece>();
		this.plateau     = new char[LIMITE_PLATEAU][LIMITE_PLATEAU];
		this.innitEchiquier();
	}

	public Piece getPiece(int lig, int col)
	{
		for (Piece p : this.ensPiece)
			if (p.getLig() == lig && p.getCol() == col)
				return p;
		return null;
	}

	public void innitEchiquier()
	{
		this.innitEchiquier(DEBUT_DE_PARTIE);
	}
	

	public void innitEchiquier(String[][] echInnit)
	{
		for(int lig = 0; lig < Echiquier.LIMITE_PLATEAU; lig++)
		{
			for(int col = 0; col < Echiquier.LIMITE_PLATEAU; col++)
			{
				char couleur = echInnit[lig][col].charAt(1);
				char choix = echInnit[lig][col].charAt(0);
				this.plateau[lig][col] = choix;
				
				switch (choix)
				{
					case 'T' :
					{
						this.ensPiece.add(new Tour(lig, col, couleur));
						break;
					}
					case 'C' :
					{
						this.ensPiece.add(new Cavalier(lig, col, couleur));
						break;
					}
					case 'F' :
					{
						this.ensPiece.add(new Fou(lig, col, couleur));
						break;
					}
					case 'Q' :
					{
						this.ensPiece.add(new Reine(lig, col, couleur));
						break;
					}
					case 'K' :
					{
						this.ensPiece.add(new Roi(lig, col, couleur));
						break;
					}
					case 'P' :
					{
						this.ensPiece.add(new Pion(lig, col, couleur));
						break;
					}
				}
			}
		}
	}

	//Mise à jour du plateau pour affichage
	public void MajPlateau ()
	{
		
	}

	public boolean deplacement(int ligDep, int colDep, int ligArr, int colArr)
	{
		// CONTROLER SI LE ROI EST EN ECHEC

		//pour ne pas deplacer en dehors du plateau
		if (ligArr < 0 || ligArr > LIMITE_PLATEAU - 1 || colArr < 0 || colArr > LIMITE_PLATEAU - 1)
			return false;
		
		Piece p = getPiece(ligDep, colDep);
		if (p == null)
		{
			System.out.println("Erreur dans la sélection d'une pièce"); 
			return false; 
		}

		char couleurJoueur = p.getCouleur(); 

		//vérifie si y'a une pièce et vérifie si la pièce à la destination est de la même couleur que la pièce de départ
		if (this.plateau[ligArr][colArr] != ' ')
		{
			Piece pDest = getPiece(ligArr, colArr);
			if (pDest != null)
			{
				if (pDest.getCouleur() == couleurJoueur)
				{
					System.out.println("Erreur dans la destination choisie");
					return false;
				}
			}
		}

		//test si la couleur du tour est la couleur de la pièce de départ
		if (this.couleurTour != couleurJoueur) { return false; }

		if ( p.peutDeplacer(ligArr, colArr, this.ensPiece) )
		{
			if (this.plateau[ligArr][colArr] != ' ')
			{
				Piece pDest = getPiece(ligArr, colArr);
				this.ensPiece.remove(pDest);
			}
			p.deplacer(ligArr, colArr);
			this.plateau[ligDep][colDep] = ' ';
			this.plateau[ligArr][colArr] = p.getType();
			this.changerTour();
			return true;
		}
		System.out.println("Fin méthode deplacement -> return false");
		//TEMPORAIRE ! changerTour pour tests
		this.changerTour();
		return false;
	}

	public char getCouleurTour()       { return this.couleurTour; }
	public void setCouleurTour(char c) { this.couleurTour = c;}
e
	public void changerTour()
	{
		System.out.println("Entrée dans changerTour() : " + this.getCouleurTour());
		if ( this.getCouleurTour() == 'B')
			this.setCouleurTour('N');
		else
			this.setCouleurTour('B');
	}

	public String toString ()
	{
		String sRet = "\n+---+---+---+---+---+---+---+---+\n| ";
		for(int lig = 0; lig < Echiquier.LIMITE_PLATEAU; lig++)
		{
			for(int col = 0; col < Echiquier.LIMITE_PLATEAU; col++)
			{
				sRet += "" + this.plateau[lig][col] + " | ";
			}
			sRet += "\n+---+---+---+---+---+---+---+---+";
			if (lig < Echiquier.LIMITE_PLATEAU-1)
				sRet+="\n| ";
		}
		return sRet;
	}
}