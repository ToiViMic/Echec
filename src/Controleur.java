import Metier.*;
import Metier.Piece.*;

public class Controleur 
{
	public Controleur()
	{
		Echiquier e = new Echiquier ();
	
		//System.out.println(e.getPiece(6, 1).getCouleur());
		e.deplacement(6, 4, 5, 4);
		e.deplacement(1, 3, 2, 3);
		e.deplacement(7, 5, 3, 1);
		e.deplacement(0, 4, 1, 3);
		System.out.println(e.toString()); 
	}
	public static void main (String[]a)
	{
		new Controleur();
	}
}