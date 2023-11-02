import Metier.*;
import Metier.Piece.*;

public class Controleur 
{
	public Controleur()
	{
		Echiquier e    = new Echiquier ();

		e.jouer();
	
		//System.out.println(e.getPiece(6, 1).getCouleur());
		System.out.println(e.deplacement(6, 1, 4, 1));
		System.out.println(e.deplacement(1, 0, 3, 0));
		System.out.println(e.deplacement(7, 1, 5, 2));
		System.out.println(e.deplacement(5, 2, 3, 3));
		System.out.println(e.toString()); 
		System.out.println(e.deplacement(3, 0, 4, 1));
		System.out.println(e.toString()); 
	}
	public static void main (String[]a)
	{
		new Controleur();
	} 
}