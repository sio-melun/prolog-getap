package org.ldv.sio.getap.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ServiceDemandesCSV")
public class DemandesCSV {

	private DataSource ds;

	public DataSource getDs() {
		return ds;
	}

	@Autowired
	public void setDs(DataSource ds) {
		this.ds = ds;
	}

	public void export(HttpServletResponse response, Long id,
			List<DemandeConsoTempsAccPers> dctap) {

		try {
			Connection con = ds.getConnection();
			Statement select = con.createStatement();
			ResultSet rs = select
					.executeQuery("SELECT user.* FROM user where id = " + id);

			try {
				PrintWriter writer = response.getWriter();

				rs.last();
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				int idClasse = rs.getInt("idClasse");
				rs.beforeFirst();
				ResultSet rs2 = select
						.executeQuery("SELECT classe.* from classe where id = "
								+ idClasse);
				rs2.last();
				String classe = rs2.getString("libelle");
				rs2.beforeFirst();
				writer.println(nom + ";" + prenom + ";" + classe + "\n");
				writer.println("Professeur;Type d'accompagnement;Temps;Date\n");
				writer.println("Demandes validées");
				for (int i = 0; i < dctap.size(); i++) {
					if (dctap.get(i).getEtat() == 1
							|| dctap.get(i).getEtat() == 32) {
						writer.append(dctap.get(i).getProf().getNom() + " "
								+ dctap.get(i).getProf().getPrenom());
						writer.append(";");
						writer.append(dctap.get(i).getAccPers().getNom());
						writer.append(";");
						writer.append(dctap.get(i).getMinutes() / 60
								- (dctap.get(i).getMinutes() % 60 / 60) + "h "
								+ dctap.get(i).getMinutes() % 60 + "min");
						writer.append(";");
						writer.append(dctap.get(i).getDateAction() + "");
						writer.println("");
					}
				}
				writer.println("\nDemandes refusées");
				for (int i = 0; i < dctap.size(); i++) {
					if (dctap.get(i).getEtat() == 2
							|| dctap.get(i).getEtat() == 8
							|| dctap.get(i).getEtat() == 64) {
						writer.append(dctap.get(i).getProf().getNom() + " "
								+ dctap.get(i).getProf().getPrenom());
						writer.append(";");
						writer.append(dctap.get(i).getAccPers().getNom());
						writer.append(";");
						writer.append(dctap.get(i).getMinutes() / 60
								- (dctap.get(i).getMinutes() % 60 / 60) + "h "
								+ dctap.get(i).getMinutes() % 60 + "min");
						writer.append(";");
						writer.append(dctap.get(i).getDateAction() + "");
						writer.println("");
					}
				}
				writer.println("\nDemandes en cours");
				for (int i = 0; i < dctap.size(); i++) {
					if (dctap.get(i).getEtat() == 0
							|| dctap.get(i).getEtat() == 4
							|| dctap.get(i).getEtat() > 1023) {
						writer.append(dctap.get(i).getProf().getNom() + " "
								+ dctap.get(i).getProf().getPrenom());
						writer.append(";");
						writer.append(dctap.get(i).getAccPers().getNom());
						writer.append(";");
						writer.append(dctap.get(i).getMinutes() / 60
								- (dctap.get(i).getMinutes() % 60 / 60) + "h "
								+ dctap.get(i).getMinutes() % 60 + "min");
						writer.append(";");
						writer.append(dctap.get(i).getDateAction() + "");
						writer.println("");
					}
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
