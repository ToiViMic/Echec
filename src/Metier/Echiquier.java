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

	private final static int LIMITE_PLATEAU = 8;
	private char             couleurTour;
	
	private ArrayList<Piece> ensPiece;
	private ArrayList<Piece> noirElim;
	private ArrayList<Piece> blancElim;

	private char[][]         plateau;


	//innitialisation et construction 
	public Echiquier()
	{
		this.couleurTour = 'B';
		
		this.ensPiece    = new ArrayList<Piece>();
		this.noirElim    = new ArrayList<Piece>();
		this.blancElim   = new ArrayList<Piece>();

		this.plateau     = new char[LIMITE_PLATEAU][LIMITE_PLATEAU];
		this.innitEchiquier();
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


	//methodes pour le jeu

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
		//Si le joueur est en echec
		if ( this.detectEchec(this.couleurTour) )
		{
			if (p.getType() == 'K')
				return this.deplacementEchec(ligArr, colArr, p);
			else
			{
				System.out.println("Vous êtes en echec !");
				return false;
			}
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
		return false;
	}

	/* Ici, le programme va gérer quand la partie est en "Echec", soit quand une pièce peut se déplacer sur le Roi de l'autre couleur.
	 * Il y a une partie détection detectEchec et une partie deplacementEchec qui test si le déplacement du Roi est possible
	 */
	public boolean detectEchec(char coul)
	{
		Piece roi = new Roi(0, 0, coul);
		
		//parcours de l'ensemble des pièces pour trouver le roi d'une couleur
		for (Piece p : ensPiece)
		{
			if (p.getCouleur() == coul && p.getType() == 'K')
			{
				roi.setLig(p.getLig());
				roi.setCol(p.getCol());
			}
		}

		//parcours l'ensemble de pièces pour savoir si une d'entre elle peut se déplacer sur le roi (échec)
		for (Piece p : ensPiece)
		{
			if ( p.getCouleur() != roi.getCouleur() && p.peutDeplacer(roi.getLig(), roi.getCol(), this.ensPiece) )
				return true;
		}

		return false;
	}

	//méthode qui permet de placer les pieces mortes dans la bonne liste
	public void eliminer (Piece p)
	{
		//verifie si il n'est pas déjà élminier
		if (this.ensPiece.get(ensPiece.indexOf(p))!=null)
			return;
		
		if (p.getCouleur() == 'B')
		{
			this.blancElim.add(p);
		}
		else
		{
			this.noirElim.add(p);
		}
		this.ensPiece.remove(p);
	}

	public boolean deplacementEchec(int ligArr, int colArr, Piece roi)
	{
		char couleurJoueur = roi.getCouleur();
		int ligDep = roi.getLig();
		int colDep = roi.getCol();

		// Vérification si le déplacement du Roi est valide
		if (roi.peutDeplacer(ligArr, colArr, this.ensPiece)) 
		{
			// Simuler le déplacement du Roi
			char temp = plateau[ligArr][colArr];
			plateau[ligArr][colArr] = plateau[ligDep][colDep];
			plateau[ligDep][colDep] = ' ';

			// Vérifier si le Roi est en échec après le déplacement
			if (!detectEchec(couleurJoueur)) 
			{
				// Le Roi peut se déplacer en toute sécurité
				roi.deplacer(ligArr, colArr);
				changerTour();
				return true;
			} 
			else 
			{
				// Annuler le déplacement si le Roi est en échec
				System.out.println("Le Roi ne peut pas se déplacer ici");
				plateau[ligDep][colDep] = plateau[ligArr][colArr];
				plateau[ligArr][colArr] = temp;
			}
		}

		return false; // Le déplacement du Roi n'est pas autorisé
	}

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

	//getters
	public char getCouleurTour()       { return this.couleurTour; }
	public Piece getPiece(int lig, int col)
	{
		for (Piece p : this.ensPiece)
			if (p.getLig() == lig && p.getCol() == col)
				return p;
		return null;
	}
	
	//setters
	public void setCouleurTour(char c) { this.couleurTour = c; }

}