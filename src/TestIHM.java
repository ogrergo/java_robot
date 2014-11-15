import ihm.*;

import java.awt.Color;

public class TestIHM {
	public static void main(String[] args) {		
		Invaders invaders = new Invaders();
	}
}

class Invaders implements Simulable {
	private int nbLignes = 20;
	private int nbColonnes = 20;
    private IGSimulateur ihm;  // l'IHM associee a ce simulateur
    private long date = 0;
    
	public Invaders() {
		// cree l'IHM et l'associe a ce simulateur (this), qui en tant que
		// Simulable recevra les evenements suite aux actions de l'utilisateur
		ihm = new IGSimulateur(nbColonnes, nbLignes, this);
		dessine();    // mettre a jour l'affichage
	}
	
	@Override
	public void next() {
		date++;
		System.out.println("TODO: avancer la simulation \"d'un pas de temps\": " + date);
		System.out.println("  => faire descendre les invaders!");
		dessine();    // mettre a jour l'affichage
	}

	@Override
	public void restart() {
		System.out.println("TODO: remettre le simulateur dans son état initial");
		date = 0;
		dessine();    // mettre a jour l'affichage
	}

	private void dessine() {
        // Afficher les donnees 		
		try {
			ihm.paintBox(9, 4, Color.BLACK);
			ihm.paintBox(10, 4, Color.BLACK);
			ihm.paintBox(11, 4, Color.BLACK);

			ihm.paintBox(8, 5, Color.BLACK);
			ihm.paintBox(9, 5, Color.BLACK);
			ihm.paintBox(10, 5, Color.BLACK);
			ihm.paintBox(11, 5, Color.BLACK);
			ihm.paintBox(12, 5, Color.BLACK);

			ihm.paintBox(7, 6, Color.BLACK);
			ihm.paintBox(8, 6, Color.BLACK);
			ihm.paintBox(9, 6, Color.BLACK);
			ihm.paintBox(10, 6, Color.BLACK);
			ihm.paintBox(11, 6, Color.BLACK);
			ihm.paintBox(12, 6, Color.BLACK);
			ihm.paintBox(13, 6, Color.BLACK);

			ihm.paintBox(6, 7, Color.BLACK);
			ihm.paintBox(7, 7, Color.BLACK);
			ihm.paintBox(10, 7, Color.BLACK);
			ihm.paintBox(13, 7, Color.BLACK);
			ihm.paintBox(14, 7, Color.BLACK);

			ihm.paintBox(6, 8, Color.BLACK);
			ihm.paintBox(7, 8, Color.BLACK);
			ihm.paintBox(10, 8, Color.BLACK);
			ihm.paintBox(13, 8, Color.BLACK);
			ihm.paintBox(14, 8, Color.BLACK);

			ihm.paintBox(6, 9, Color.BLACK);
			ihm.paintBox(7, 9, Color.BLACK);
			ihm.paintBox(8, 9, Color.BLACK);
			ihm.paintBox(9, 9, Color.BLACK);
			ihm.paintBox(10, 9, Color.BLACK);
			ihm.paintBox(11, 9, Color.BLACK);
			ihm.paintBox(12, 9, Color.BLACK);
			ihm.paintBox(13, 9, Color.BLACK);
			ihm.paintBox(14, 9, Color.BLACK);

			ihm.paintBox(8, 10, Color.BLACK);
			ihm.paintBox(9, 10, Color.BLACK);
			ihm.paintBox(10, 10, Color.BLACK);
			ihm.paintBox(11, 10, Color.BLACK);
			ihm.paintBox(12, 10, Color.BLACK);

			ihm.paintBox(7, 11, Color.BLACK);
			ihm.paintBox(8, 11, Color.BLACK);
			ihm.paintBox(9, 11, Color.BLACK);
			ihm.paintBox(10, 11, Color.BLACK);
			ihm.paintBox(11, 11, Color.BLACK);
			ihm.paintBox(12, 11, Color.BLACK);
			ihm.paintBox(13, 11, Color.BLACK);

			ihm.paintBox(7, 12, Color.BLACK);
			ihm.paintBox(8, 12, Color.BLACK);
			ihm.paintBox(9, 12, Color.BLACK);
			ihm.paintBox(10, 12, Color.BLACK);
			ihm.paintBox(11, 12, Color.BLACK);
			ihm.paintBox(12, 12, Color.BLACK);
			ihm.paintBox(13, 12, Color.BLACK);

			ihm.paintBox(6, 13, Color.BLACK);
			ihm.paintBox(8, 13, Color.BLACK);
			ihm.paintBox(12, 13, Color.BLACK);
			ihm.paintBox(14, 13, Color.BLACK);

			for (int i = 6; i < 15; i++) {
				ihm.paintBox(i, 15, Color.BLUE);
			}
			ihm.paintImage(4, 15, "images/feu.png", 0.8, 0.8);
			ihm.paintString(7, 15, Color.YELLOW, "I");
			ihm.paintString(8, 15, Color.YELLOW, "N");
			ihm.paintString(9, 15, Color.YELLOW, "V");
			ihm.paintString(10, 15, Color.YELLOW, "A");
			ihm.paintString(11, 15, Color.YELLOW, "D");
			ihm.paintString(12, 15, Color.YELLOW, "E");
			ihm.paintString(13, 15, Color.YELLOW, "R");
			ihm.paintImage(16, 15, "images/feu.png", 0.8, 0.8);
			ihm.paintBox(14, 13, Color.BLACK);

		} catch (MapIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}	
}

	
