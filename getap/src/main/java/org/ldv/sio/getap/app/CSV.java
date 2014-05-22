package org.ldv.sio.getap.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ServiceCSV")
public class CSV {

	private DataSource ds;
	private ResultSet rs;

	public DataSource getDs() {
		return ds;
	}

	@Autowired
	public void setDs(DataSource ds) {
		this.ds = ds;
	}

	public void export(HttpServletResponse response, String role) {

		try {
			Connection con = ds.getConnection();
			Statement select = con.createStatement();
			if (role == "prof-principal") {
				rs = select
						.executeQuery("SELECT id, nom, prenom, login, mdp, idEtab, idClasse FROM user where role='"
								+ role + "' order by nom");
			} else {
				rs = select
						.executeQuery("SELECT nom, prenom, libelle, login, mdp, idEtab FROM user, classe where role='"
								+ role
								+ "' and user.idClasse = classe.id order by classe.libelle, user.nom, user.prenom");
			}
			try {
				PrintWriter writer = response.getWriter();
				// FileWriter writer = new FileWriter("F:/test2.csv");
				if (role == "prof-principal") {
					writer.println("nom;prenom;idEtab;login;mdp");
				} else {
					writer.println("nom;prenom;idEtab;login;mdp;Classe");
				}
				while (rs.next()) {
					writer.append(rs.getString("nom"));
					writer.append(";");
					writer.append(rs.getString("prenom"));
					writer.append(";");
					writer.append(rs.getString("idEtab"));
					writer.append(";");
					writer.append(rs.getString("login"));
					writer.append(";");
					writer.append(rs.getString("mdp"));
					if (role != "prof-principal") {
						writer.append(";");
						writer.append(rs.getString("libelle"));
					}
					writer.println();
				}
				writer.flush();
				writer.close();

			} catch (IOException ioe) {
				ioe.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
