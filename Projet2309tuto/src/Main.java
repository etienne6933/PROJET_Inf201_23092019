//on importe Scanner
import java.util.Scanner;

//La classe Main
public class Main {
	//La fonction main
	public static void main(String[] args) throws Exception {
		//créer un nouvel objet qui appelle la classe SQL on la nommera base
		SQL base = new SQL();
		//ajouter l'entrée clavier pour dire qu'on va lire l'entrée clavier
		Scanner sc = new Scanner(System.in);
		System.out.println("Choisir une option: \n a. Pour afficher les différents séjours d’un patient : taper 1 ");
		System.out.println(" b. Pour afficher le Nombre d’actes par patient sur une période donnée ('2004-01-01' au '2004-12-31') : taper 2 ");
		System.out.println(" c. Pour afficher les diagnostics les plus fréquents chez les femmes : taper 3 ");
		System.out.println(" d. Pour afficher les patients ayant eu le plus d’hospitalisations  : taper 4 ");
		System.out.println(" e. Pour afficher les patients âgés de plus de 80 ans  : taper 5 ");
		System.out.println(" f. Pour mettre à jour le prénom d'un patient  : taper 6 ");
		System.out.println(" f. Pour sortir du programme  : taper 7 ");
		
		//On crée une variable entier
		int choix = sc.nextInt();
		//On demande d'afficher le choix concatené au caractère tapé récupéré dans Scanner	
		System.out.println("Choix: " + choix);
		//ajout while pour boucler sur les requêtes
		do {
			switch(choix){
		
			case 1: base.sejours();
			break;
			case 2: base.nbActes();
			break;
			case 3: base.freqdiag();
			break;
			case 4: base.hospitmaxi();
			break;
			case 5: base.pat80etplus();
			break;
			case 6: base.majprenom();
			break;
			//le default est équivalent au else ici si aucun choix connu tapé par l'utilisateur
			default: System.out.println("Erreur de saisie, choisissez un des choix disponibles!");
			}
			System.out.println();
			System.out.println("Saisissez un nouveau choix disponible");
			choix = sc.nextInt(); 
		} while(choix != 7);
		
	}

}
