import vue.VueJardin;
import modele.Jeu;

public class lancement {

	public static void main(String[] args) {

		Jeu jeu = new Jeu(5, 6);
		jeu.Initialisation();
		VueJardin vue = new VueJardin(jeu);

	}
}
