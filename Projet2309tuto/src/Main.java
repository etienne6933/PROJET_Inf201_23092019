//on importe Scanner
import java.util.Scanner;

//La classe Main
public class Main {
	//La fonction main
	public static void main(String[] args) throws Exception {
		//cr�er un nouvel objet qui appelle la classe SQL on la nommera base
		SQL base = new SQL();
		//ajouter l'entr�e clavier pour dire qu'on va lire l'entr�e clavier
		Scanner sc = new Scanner(System.in);
		System.out.println("Choisir une option: \n a. Pour afficher les diff�rents s�jours d�un patient : taper 1 ");
		System.out.println(" b. Pour afficher le Nombre d�actes par patient sur une p�riode donn�e ('2004-01-01' au '2004-12-31') : taper 2 ");
		System.out.println(" c. Pour afficher les diagnostics les plus fr�quents chez les femmes : taper 3 ");
		System.out.println(" d. Pour afficher les patients ayant eu le plus d�hospitalisations  : taper 4 ");
		System.out.println(" e. Pour afficher les patients �g�s de plus de 80 ans  : taper 5 ");
		System.out.println(" f. Pour mettre � jour le pr�nom d'un patient  : taper 6 ");
		System.out.println(" f. Pour sortir du programme  : taper 7 ");
		
		//On cr�e une variable entier
		int choix = sc.nextInt();
		//On demande d'afficher le choix concaten� au caract�re tap� r�cup�r� dans Scanner	
		System.out.println("Choix: " + choix);
		//ajout while pour boucler sur les requ�tes
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
			//le default est �quivalent au else ici si aucun choix connu tap� par l'utilisateur
			default: System.out.println("Erreur de saisie, choisissez un des choix disponibles!");
			}
			System.out.println();
			System.out.println("Saisissez un nouveau choix disponible");
			choix = sc.nextInt(); 
		} while(choix != 7);
		
	}

}
