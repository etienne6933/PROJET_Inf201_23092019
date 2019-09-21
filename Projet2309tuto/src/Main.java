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
		System.out.println(" b. Pour afficher le Nombre d’actes subis par patient sur une période : taper 2 ");
		System.out.println(" c. Pour afficher les diagnostics les plus fréquents chez les femmes : taper 3 ");
		System.out.println(" d. Pour afficher les patients ayant eu le plus d’hospitalisations  : taper 4 ");
		System.out.println(" e. Pour afficher les patients âgés de plus de 80 ans  : taper 5 ");
		System.out.println(" f. Pour mettre à jour le sexe d'un patient  : taper 6 ");
		
		//On crée une variable entier
		int choix = sc.nextInt();
		//On demande d'afficher le choix concatené au caractère tapé récupéré dans Scanner	
		System.out.println("Choix: " + choix);
		switch(choix){
		//en rouge les fonctions systeme en vert les fonctions que je viens de créer
		//les fonctions de base comme case sont tout de suite reconnues
		//les fonctions que je crée comme sejours ou nbActes ne sont pas tout se suite reconnues
		//tant qu'elles n'existent pas dans la classe SQL
		//on va utiliser case pour tester x cas avec une sortie break à chaque fois 
		//pour ne pas saisir le code SQL dans main on appelle depuis main uniquement la fonction sejour par exemple de la classe SQL qui contient le code
						
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
		case 6: base.majsexe();
		break;
		
		//le default est équivalent au else ici si aucun choix connu tapé par l'utilisateur
		default: System.out.println("Erreur de saisie, choisissez un des choix disponibles!");
		}
	}

}
