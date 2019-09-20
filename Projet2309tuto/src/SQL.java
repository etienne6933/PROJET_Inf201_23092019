import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
			//avec le catch on attrape l'exception et l'imprime
		} catch (Exception e) {
			throw e;
		}

	}
	
	
	//fonction sejours qui est appelée dans Main
	public void sejours() throws Exception {
		try {
			Statement st = connexion();
			ResultSet rs = st.executeQuery("select * from tab_patient P");
			while (rs.next()) {
				int ID_PATIENT = rs.getInt(1);
				String SEXE = rs.getString(2);
				System.out.println("ID_P = " + ID_PATIENT + " " + "SEXE_P =" + SEXE);
			}

		} catch (Exception e) {
			throw e;
		}

	}
	
	//fonction nbActes qui est appelée dans Main
	public void nbActes() throws Exception {
		try {
			Statement st = connexion();
			ResultSet rs = st.executeQuery("select count(*) as total from tab_patient");
			while (rs.next()) {
				int total = rs.getInt("total");
				System.out.println("Total = " + total);
			}

		} catch (Exception e) {
			throw e;
		}

	}
}
