package org.ldv.sio.getap.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ServiceJDBC")
public class JDBC {
	private DataSource ds;

	public DataSource getDs() {
		return ds;
	}

	@Autowired
	public void setDs(DataSource ds) {
		this.ds = ds;
	}

	public void feedBDD(String file) {
		String QUOTE = "\'";

		try {
			Connection con = ds.getConnection();

			// Insertion dans la base de donnees d'une liste
			String lien = file;
			if (lien.contains('\\' + "")) {
				file = file.replace('\\', '/');
			}
			System.out.println(file);
			LectureFichier lf = new LectureFichier(file);
			ArrayList<Utilisateur> listUser = lf
					.createListUser(lf.getFileCsv());

			// parcour toute la liste d'utilisateur, les ins�re en base de
			// donn�es avec leur donees respectives
			for (int i = 1; i < listUser.size(); i++) {
				Statement select = con.createStatement();
				ResultSet rs = select
						.executeQuery("SELECT libelle FROM Classe WHERE libelle = "
								+ QUOTE + listUser.get(i).classe + QUOTE);
				rs.last();
				int count = rs.getRow();
				rs.beforeFirst();

				// Si la requete ne retourne aucun r�sultat, on insert la
				// nouvelle classe dans la base
				if (count == 0) {
					Statement st;
					String requete = "";
					st = con.createStatement();
					String classe = QUOTE + listUser.get(i).classe + QUOTE;
					requete = "INSERT INTO Classe(libelle) VALUES(" + classe
							+ ");";
					st.executeUpdate(requete);
				}

				// Requete d'insertion de l'utilisateur dans la base
				PreparedStatement param = con
						.prepareStatement("INSERT INTO getap.user(nom, prenom, ine, login, mdp, role, idClasse ) VALUES(?, ?, ?, ?, ?, ?, ?);");

				param.setNString(1, listUser.get(i).nom);
				param.setNString(2, listUser.get(i).prenom);
				param.setNString(3, listUser.get(i).ine);

				// d�finition de la base d'un login : 1ere lettre prenom + nom
				String baseLogin = listUser.get(i).prenom.charAt(0)
						+ listUser.get(i).nom; // correspond au login de
												// l'utilisateur

				// Requete permettant de retourner tous les login qui commence
				// par la 1ere lettre de son prenom + son nom
				Statement login = con.createStatement();
				ResultSet set = login
						.executeQuery("SELECT login FROM user WHERE login LIKE "
								+ QUOTE
								+ baseLogin
								+ "%"
								+ QUOTE
								+ "AND nom = "
								+ QUOTE
								+ listUser.get(i).nom
								+ QUOTE
								+ " AND prenom = "
								+ QUOTE
								+ listUser.get(i).prenom + QUOTE);
				set.last();
				count = set.getRow();
				set.beforeFirst();

				// Si aucun r�sultat n'est retourn�, le login n'existe pas , on
				// ins�re donc le login cr�� au moment de la lecture du fichier
				// .csv
				if (count == 0) // GOOD
					param.setNString(4, listUser.get(i).login);

				// Si un r�sultat est retourn�, le login existe d�j�, on cr�� un
				// login avec un chiffre en plus avec une incr�mentation
				else {
					int max = 1;
					while (set.next()) {
						String sLogin = set.getNString("login");
						int nbCarac = baseLogin.length();
						if (nbCarac != sLogin.length()) {
							String sNumber = sLogin.substring(nbCarac);
							System.out.println(sLogin + " => " + sNumber
									+ " => " + baseLogin);
							int number = Integer.parseInt(sNumber);
							if (max < number)
								max = number;
						}
					}
					max++;
					String sMax = String.valueOf(max);
					String log = baseLogin + sMax;
					param.setNString(4, log);
				}

				param.setNString(5, listUser.get(i).pass);

				// System.out.println("Fichier : " + fichier);
				param.setNString(6, "eleve");

				// Requete retournant l'id de la classe de l'utilisateur
				Statement classe = con.createStatement();
				String sql = "SELECT id FROM Classe WHERE libelle = " + QUOTE
						+ listUser.get(i).classe + QUOTE;
				ResultSet result = classe.executeQuery(sql);
				if (result.next()) {
					int clas = result.getInt("id");
					param.setInt(7, clas);
				}

				// Execution de la requete !
				param.executeUpdate();

			}

			// fermeture
			con.close();
		} catch (SQLException ex) {
			System.err.println("==> SQLException: ");
			while (ex != null) {
				System.out.println("Message: " + ex.getMessage());
				System.out.println("SQLState: " + ex.getSQLState());
				System.out.println("ErrorCode: " + ex.getErrorCode());
				ex = ex.getNextException();
				System.out.println("");
			}
		}

	}
}
