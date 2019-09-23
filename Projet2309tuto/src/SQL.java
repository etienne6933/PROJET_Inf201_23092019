import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

public class SQL {
	//on créé une fonction type statement nommée connexion
	public Statement connexion() throws Exception {
		//le try pour essayer ce code
		try {
			// chargement du pilote MySQL, récupération d’un objet de type Class
			Class.forName("com.mysql.cj.jdbc.Driver");
			// ouverture de la connexion proprement dite 
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet2309","root","");
			//Pour pouvoir passer une requête à la base, il faut un objet de type Statement.
			//Cet objet s’obtient en faisant appel à la méthode createStatement() de notre objet de type Connection
			Statement st= con.createStatement();
			//le return est quasi booleen pour dire si la connexion est Ok ou pas
			return st;
			//avec le catch on attrape l'exception et on l'affiche
		} catch (Exception e) {
			throw e;
		}

	}

	//fonction sejours qui est appelée dans Main
	public void sejours() throws Exception {
		try {
			Statement st = connexion();
			ResultSet rs = st.executeQuery("select T_P.NOM, T_P.PRENOM, T_H.ID_HOSPITALISATION, T_H.DATE_ENTREE, T_H.DATE_SORTIE FROM tab_hospitalisation T_H INNER JOIN tab_patient T_P ON T_H.ID_PATIENT=T_P.ID_PATIENT ORDER BY `T_P`.`NOM` ASC");
			while (rs.next()) {
				String NOM = rs.getString(1);
				String PRENOM = rs.getString(2);
				int ID_HOSPITALISATION = rs.getInt(3);
				//attention date de sortie peut être null créer une exception
				Date DATE_ENTREE = rs.getDate(4);
				Date DATE_SORTIE = rs.getDate(5);
				System.out.println("Nom = " + NOM + "||" + "Prenom = " + PRENOM + "||"+"ID_HOSP = " + ID_HOSPITALISATION + "||" + "DATE_ENTREE = " + DATE_ENTREE+ "||" + "DATE_SORTIE = " + DATE_SORTIE);
			}

		} catch (Exception e) {
			// e.printStackTrace();Capture de l'erreur quand une date est nulle
			System.out.println("\n"+"CAUSE de l'erreur : -----------"+e.getCause().getMessage()+"--------------------"+"\n");
			throw e;
		}

	}

	//fonction nbActes qui est appelée dans Main limité à 10 lignes
	public void nbActes() throws Exception {
		try {
			Statement st = connexion();
			ResultSet rs = st.executeQuery("select count(DISTINCT T_A.ID_AKT) AS NB_ACTES, T_H.ID_HOSPITALISATION, T_H.ID_PATIENT, T_P.NOM,T_P.PRENOM \r\n" + 
					"from tab_acte T_A \r\n" + 
					"INNER JOIN tab_hospitalisation T_H ON T_A.ID_HOSPITALISATION=T_H.ID_HOSPITALISATION \r\n" + 
					"INNER JOIN tab_patient T_P ON T_H.ID_PATIENT=T_P.ID_PATIENT  \r\n" + 
					"WHERE T_H.DATE_ENTREE BETWEEN '2004-01-01' AND '2004-12-31'\r\n" + 
					"GROUP BY ID_PATIENT  \r\n" + 
					"ORDER BY `T_H`.`ID_PATIENT` ASC\r\n" + 
					"LIMIT 10");

			while (rs.next()) {
				int NB_ACTES = rs.getInt(1);
				int ID_HOSPITALISATION = rs.getInt(2);
				int ID_PATIENT = rs.getInt(3);
				String NOM = rs.getString(4);
				String PRENOM = rs.getString(5);


				System.out.println("Total des actes = " + NB_ACTES + "||"+ "ID_HOSP = " + ID_HOSPITALISATION + "||"+ "NOM_Patient = " + NOM + "||"+ "PRENOM_Patient = " + PRENOM);
			}

		} catch (Exception e) {
			throw e;
		}

	}
	//fonction freqdiag qui est appelée dans Main
	public void freqdiag() throws Exception {
		try {
			Statement st = connexion();
			ResultSet rs = st.executeQuery("SELECT   COUNT(CODE_CIM10) AS NOMBRE_DE_CAS, CODE_CIM10, LIBELLE_CIM110, SEXE AS SEXE_PATIENT\r\n" + 
					"FROM     tab_diagnostic\r\n" + 
					"INNER JOIN tab_cim10 ON tab_diagnostic.CODE_CIM10 = tab_cim10.ID_CIM10\r\n" + 
					"INNER JOIN tab_hospitalisation ON tab_diagnostic.ID_HOSPITALISATION = tab_hospitalisation.ID_HOSPITALISATION\r\n" + 
					"INNER JOIN tab_patient ON tab_hospitalisation.ID_PATIENT = tab_patient.ID_PATIENT\r\n" + 
					"WHERE tab_patient.SEXE = '2'\r\n" + 
					"GROUP BY CODE_CIM10\r\n" + 
					"HAVING   COUNT(CODE_CIM10) > 1  \r\n" + 
					"ORDER BY `NOMBRE_DE_CAS`  DESC\r\n" + 
					"");
			while (rs.next()) {
				int NOMBRE_DE_CAS = rs.getInt(1);
				String CODE_CIM10 = rs.getString(2);
				String LIBELLE_CIM110 = rs.getString(3);
				int SEXE_PATIENT = rs.getInt(4);

				System.out.println("NOMBRE_DE_CAS = " + NOMBRE_DE_CAS + "||"+ "CODE_CIM10 = " + CODE_CIM10 + "||"+ "LIBELLE_CIM10 = " + LIBELLE_CIM110+ "||"+ "SEXE_PATIENT = " + SEXE_PATIENT);
			}

		} catch (Exception e) {
			throw e;
		}

	}
	//fonction hospitmaxi qui est appelée dans Main
	public void hospitmaxi() throws Exception {
		try {
			Statement st = connexion();
			ResultSet rs = st.executeQuery("SELECT count(distinct ID_HOSPITALISATION) AS NOMBRE_HOSPIT,tab_hospitalisation.ID_PATIENT,NOM,PRENOM,DATE_NAISSANCE  from tab_hospitalisation\r\n" + 
					"INNER JOIN tab_patient ON tab_hospitalisation.ID_PATIENT = tab_patient.ID_PATIENT\r\n" + 
					"GROUP BY ID_PATIENT\r\n" + 
					"ORDER BY `NOMBRE_HOSPIT`  DESC\r\n" + 
					"");
			while (rs.next()) {
				int NOMBRE_HOSPIT = rs.getInt(1);
				int ID_PATIENT = rs.getInt(2);
				String NOM = rs.getString(3);
				String PRENOM = rs.getString(4);
				Date DATE_NAISSANCE = rs.getDate(5);

				System.out.println("NOMBRE_HOSPIT = " + NOMBRE_HOSPIT + "||"+ "ID_PATIENT = " + ID_PATIENT + "||"+ "NOM = " + NOM + "||"+ "PRENOM = " + PRENOM + "||"+ "DATE_NAISS = " + DATE_NAISSANCE);
			}

		} catch (Exception e) {
			throw e;
		}

	}
	//fonction pat80etplus qui est appelée dans Main
	public void pat80etplus() throws Exception {
		try {
			Statement st = connexion();
			ResultSet rs = st.executeQuery("select ID_PATIENT, NOM, PRENOM, DATE_NAISSANCE, SEXE from tab_patient where DATE_NAISSANCE < (SELECT CURDATE()- INTERVAL 29200 DAY)");
			while (rs.next()) {
				int ID_PATIENT = rs.getInt(1);
				String NOM = rs.getString(2);
				String PRENOM = rs.getString(3);
				Date DATE_NAISSANCE = rs.getDate(4);
				int SEXE = rs.getInt(5);

				System.out.println("ID_PATIENT = " + ID_PATIENT + "||"+ "NOM = " + NOM + "||"+ "PRENOM = " + PRENOM + "||"+ "DATE_NAISS = " + DATE_NAISSANCE + "||"+ "SEXE = " + SEXE );
			}

		} catch (Exception e) {
			throw e;
		}

	}
	//fonction majsexe qui est appelée dans Main
	public void majprenom() throws Exception {
		Scanner scanneridp = new Scanner(System.in);
		System.out.println("Saisissez un ID_PATIENT svp :");
		int idp = scanneridp.nextInt();
		Scanner scannerprenom = new Scanner(System.in);
		System.out.println("Saisissez un PRENOM svp :");
		String prenom = scannerprenom.next();
		
		try {Statement st = connexion();
		st.executeUpdate("update tab_patient set prenom = '"+prenom+"' where id_patient = '"+idp+"'");
		
			System.out.println("Patient modifié!" );
		

			

		} catch (Exception e) {
			throw e;
		}
	}
}

