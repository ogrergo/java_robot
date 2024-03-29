package simulation;

import ihm.GraphicalElement;
import ihm.IGSimulateur;
import ihm.MapIndexOutOfBoundsException;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;

import donnees.Case;
import donnees.Incendie;
import donnees.InvalidCaseException;
import donnees.NatureTerrain;
import donnees.Robot;
import donnees.RobotType;
import donnees.WorldElement;
import evenement.Manager;
/**
 * <b><code>SimulationWindow</code> g�re l'affichage des donn�es sur l'ihm </b>
 * <p>
 * 
 * @author Lucas Bchini, Robin Jean, Louis van Beurden
 */
public class SimulationWindow {
	/**
	 * Les donn�es.
	 */
	private SimulationModel data;
	/**
	 * L'IHM.
	 */
	private IGSimulateur ihm;
	/**
	 * Le controleur.
	 */
	private SimulationController controller;

	/**
	 * Le tableau de tiles. une tiles represente une case.
	 */
	private Tile[][] tiles;
	/**
	 * Un tabeau associatif pour retrouv� une tile � partir d'une case.
	 */
	private HashMap<WorldElement,Tile> hash_elem;

	/**
	 * Creer une SimulationWindow � partir d'un model et d'une classe de manager.
	 * @param model les donn�es.
	 * @param m la classe du manager.
	 */
	public SimulationWindow(SimulationModel model, Class<? extends Manager> m) {
		data = model;
		initDisplay(m);
	}

	/**
	 * Initialise l'affichage.
	 * @param m le manager.
	 */
	private void initDisplay(Class<? extends Manager> m) {
		initTiles();
		controller = new SimulationController(data, this, m);
		ihm = new IGSimulateur(data.getData().getCarte().getNbLignes(), data.getData().getCarte().getNbColonnes(), controller);
		controller.restart();
	}

	/**
	 * Creer les Tiles et les initialise.
	 */
	private void initTiles() {
		hash_elem = new HashMap<WorldElement, Tile>();
		tiles = new Tile[data.getData().getCarte().getNbLignes()][data.getData().getCarte().getNbColonnes()];
		for(int i = 0; i < data.getData().getCarte().getNbLignes(); i++)
			for(int j = 0; j < data.getData().getCarte().getNbColonnes() ; j++)
				try {
					tiles[i][j] = new Tile(i, j, data.getData().getCarte().getCase(i, j).getNature());
				} catch (InvalidCaseException e) {
					e.printStackTrace();
				}
	}
	
	/**
	 * Redessine la Tile � la coordonn�e (i,j)
	 * @param i la ligne.
	 * @param j la colonne.
	 */
	private void draw(int i, int j) {
		try {
			ihm.paintGraphicalElement(j, i, tiles[i][j]);
		} catch (MapIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Redessine la Tile.
	 * @param t la tile.
	 */
	private void draw(Tile t) {
		draw(t.getLigne(), t.getColonne());
	}
	
	/**
	 * Redessine toute les tiles.
	 */
	private void draw() {
		for(int i = 0; i < data.getData().getCarte().getNbLignes(); i++)
			for(int j = 0; j < data.getData().getCarte().getNbColonnes() ; j++) {
				draw(i,j);
			}
	}
	
	/**
	 * Retourne la tile associ� � la case.
	 * @param c la case
	 * @return la tile.
	 */
	private Tile getTile(Case c) {
		return tiles[c.getLigne()][c.getColonne()];
	}
	
	/**
	 * Update le wordelement dans la bonne Tile.
	 * @param e la bonne tile.
	 */
	public void update(WorldElement e) {
		Tile t = hash_elem.put(e, getTile(e.getCase()));
		if(t != null) t.removeElem(e);
		getTile(e.getCase()).addElem(e);
		draw(getTile(e.getCase()));
	}
	
	/**
	 * Update toute les Tile.
	 */
	public void updateAll() {
		hash_elem.clear();
		
		initTiles();
		
		for(Incendie inc : data.getData().getIncendies()) {
			hash_elem.put(inc, getTile(inc.getCase()));
			getTile(inc.getCase()).addElem(inc);
		}
		
		for(Robot rob : data.getData().getRobots()) {
			hash_elem.put(rob, getTile(rob.getCase()));
			getTile(rob.getCase()).addElem(rob);
		}
		
		draw();
	}
	
	/**
	 * <b><code>Tile</code> g�re l'affichage d'une case sur l'ihm </b>
	 * <p>
	 * 
	 * @author Lucas Bchini, Robin Jean, Louis van Beurden
	 */
	private static class Tile implements GraphicalElement {

		private static Image[] background = new Image[5];
		private static Image incendie_img;
		private static Image[] robots_img = new Image[4];
		static {
			try {
				background[0] = ImageIO.read(new File("data/mer.png"));
				background[1] = ImageIO.read(new File("data/tree.png"));
				background[2] = ImageIO.read(new File("data/pierre.png"));
				background[3] = ImageIO.read(new File("data/vide.png"));
				background[4] = ImageIO.read(new File("data/house.png"));
				
				incendie_img = ImageIO.read(new File("data/fire1.png"));
				//incendie_img[1] = ImageIO.read(new File("data/fire1.png"));

				robots_img[0] = ImageIO.read(new File("data/Lugia-drone.png"));
				robots_img[1] = ImageIO.read(new File("data/wall_e-chenille.png"));//"data/chenille.png"));
				robots_img[2] = ImageIO.read(new File("data/AT-AT_pattes.png"));	//"data/patte.png"));
				robots_img[3] = ImageIO.read(new File("data/Batmobile-roues.png"));//"data/roue.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		int ligne, colonne;
		
		int index_background;
		/**
		 * Ensemble des WordElement present sur la tile.
		 */
		Set<WorldElement> elem = new HashSet<WorldElement>();
		Set<WorldElement> toremove =  new HashSet<WorldElement>();
		
		public Tile(int i, int j,NatureTerrain terrainLibre) {
			setBackground(terrainLibre);
			ligne = i;
			colonne = j;
		}

		public int getLigne() {
			return ligne;
		}
		

		public int getColonne() {
			return colonne;
		}
		
		
		public void removeElem(WorldElement e) {
			elem.remove(e);
		}

		public void addElem(WorldElement e) {
			elem.add(e);
		}

		private void setBackground(NatureTerrain terrainLibre) {
			switch(terrainLibre) {
			case EAU:index_background = 0;break;
			case FORET:index_background = 1;break;
			case ROCHE:index_background = 2;break;
			case TERRAIN_LIBRE:index_background = 3;break;
			case HABITAT:index_background = 4;break;
			}
		}

		@Override
		public void paint(int arg0, int arg1, int arg2, int arg3,
				Graphics2D arg4) {

			Image back = background[index_background];
			for(int i = 0; i < arg2; i += back.getHeight(null))
				for(int j = 0; j < arg3; j+=back.getWidth(null))
					arg4.drawImage(back, arg0 + i, arg1 + j, null);
			

			for(WorldElement e : elem) {
				if(!e.isAlive()) {
					toremove.add(e);
					continue;
				}
				
				if(e instanceof Robot) {
					
					Image img = getRobotImage((Robot) e);
					arg4.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
					arg4.drawImage(img,arg0,arg1,  arg2 - arg0, arg3 - arg1, null);
				} else {
					for(int i = arg0; i < arg2; i += back.getHeight(null))
						for(int j = arg1; j < arg3; j+=back.getWidth(null))
							arg4.drawImage(incendie_img, i, j, null);
				}
			}
			for(WorldElement e : toremove)
				removeElem(e);
			
			toremove.clear();
		}

		private static Image getRobotImage(Robot r) {
			if(r.getType() == RobotType.DRONE)
				return robots_img[0];
			
			if(r.getType() == RobotType.CHENILLES)
				return robots_img[1];
			
			if(r.getType() == RobotType.PATTES)
				return robots_img[2];
			
			if(r.getType() == RobotType.ROUES)
				return robots_img[3];
			
			return null;
		}

	}

}
