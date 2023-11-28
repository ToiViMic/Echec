package Metier;

import Metier.Piece.*;
import java.util.ArrayList;
import java.util.Scanner;

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

	private String[][]         plateau;


	//innitialisation et construction 
	public Echiquier()
	{
		this.couleurTour = 'B';
		
		this.ensPiece    = new ArrayList<Piece>();
		this.noirElim    = new ArrayList<Piece>();
		this.blancElim   = new ArrayList<Piece>();

		this.plateau     = new String[LIMITE_PLATEAU][LIMITE_PLATEAU];
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
				this.plateau[lig][col] = echInnit[lig][col];
				
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
		if (this.plateau[ligArr][colArr] != " ")
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
			if (this.plateau[ligArr][colArr] != " ")
			{
				Piece pDest = getPiece(ligArr, colArr);
				this.ensPiece.remove(pDest);
			}
			p.deplacer(ligArr, colArr);
			this.plateau[ligDep][colDep] = " ";
			this.plateau[ligArr][colArr] = ""+p.getType()+p.getCouleur();
			//this.changerTour();
			System.out.println("yaa probleme chef");
			return true;
		}
		System.out.println("Fin méthode deplacement -> return false");
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


	//methode qui lance le jeu, une sorte de main mais qui n'intervient que lorsqu'on le veut, on peut intervenir avant, pour faire bouger des pièces par exemple
	public void jouer ()
	{
		//demander la piece que le joueur choisi
		Scanner sc = new Scanner(System.in);
		String str = "";
		int lig = 0;
		int col = 0;
		int ligA = 0;
		int colA = 0;

		System.out.println(toString());

		System.out.println("C'est au tour du joueur " + getCouleurTour());

		do
		{
			System.out.println("Quelle est l'emplacement de la piece que vous voulez jouer ? (ligne,colonne)");
			str = sc.nextLine();
			if (str.length() > 2)
			{
				lig = Integer.parseInt(""+str.charAt(0));
				col = Integer.parseInt(""+str.charAt(2));
			}
		}while (getPiece(lig, col) == null && getPiece(lig, col).getCouleur() != getCouleurTour());
		System.out.println(getPiece(lig, col));
		System.out.println(lig + ""  + col);
		do
		{
			System.out.println("Où voulez vous deplacer cette pièce ? (ligne,colonne)");
			str = sc.nextLine();
			if (str.length() > 2)
			{
				ligA = Integer.parseInt(""+str.charAt(0));
				colA = Integer.parseInt(""+str.charAt(2));
			}
			System.out.println(deplacement(lig, col, ligA, colA));
			System.out.println(""+ligA+""+ colA);
		}while (!deplacement(lig, col, ligA, colA));

		changerTour();
	}
}