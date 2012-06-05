package org.ldv.sio.getap.app;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Service("ServiceStatsPDF")
public class StatsPDF {

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
			Document document = new Document(PageSize.A4);

			try {
				OutputStream out = response.getOutputStream();
				PdfWriter writer = PdfWriter.getInstance(document, out);
				writer.setViewerPreferences(PdfWriter.PageLayoutSinglePage
						| PdfWriter.PageModeUseThumbs);

				document.open();

				String string = new String();
				rs.last();
				String eleve = "Nom : " + rs.getString("nom");
				eleve += "\nPrénom : " + rs.getString("prenom");
				int idClasse = rs.getInt("idClasse");
				rs.beforeFirst();
				ResultSet rs2 = select
						.executeQuery("SELECT classe.* from classe where id = "
								+ idClasse);
				rs2.last();
				eleve += "\nClasse : " + rs2.getString("libelle");
				rs2.beforeFirst();

				Paragraph paragraph = new Paragraph(eleve);
				paragraph.setAlignment(Element.ALIGN_RIGHT);
				document.add(paragraph);

				int timeTT = 0, timeVal = 0, timeAtt = 0, timeRef = 0;
				for (int i = 0; i < dctap.size(); i++) {
					timeTT += dctap.get(i).getMinutes();
					if (dctap.get(i).getEtat() == 1
							|| dctap.get(i).getEtat() == 32) {
						timeVal += dctap.get(i).getMinutes();
					} else if (dctap.get(i).getEtat() == 2
							|| dctap.get(i).getEtat() == 8
							|| dctap.get(i).getEtat() == 64) {
						timeRef += dctap.get(i).getMinutes();
					} else if (dctap.get(i).getEtat() == 0
							|| dctap.get(i).getEtat() == 4
							|| dctap.get(i).getEtat() > 1023) {
						timeAtt += dctap.get(i).getMinutes();
					}

				}
				double timeTTpercent = timeVal, timeValPercent = timeVal, timeAttPercent = timeAtt, timeRefPercent = timeRef;
				timeTTpercent = Math.round((timeTTpercent / (72 * 60) * 100)
						* Math.pow(10.0, 2))
						/ Math.pow(10.0, 2);
				timeValPercent = Math.round(((timeValPercent / timeTT) * 100)
						* Math.pow(10.0, 2))
						/ Math.pow(10.0, 2);
				timeAttPercent = Math.round(((timeAttPercent / timeTT) * 100)
						* Math.pow(10.0, 2))
						/ Math.pow(10.0, 2);
				timeRefPercent = Math.round(((timeRefPercent / timeTT) * 100)
						* Math.pow(10.0, 2))
						/ Math.pow(10.0, 2);
				string += "\n\n\n\n\nTemps total effectué sur 72h : "
						+ (timeTT / 60 - (timeTT % 60 / 60)) + "h "
						+ (timeTT % 60) + "min soit " + timeTTpercent + "%";
				string += "\n\nTemps total validé : "
						+ (timeVal / 60 - (timeVal % 60 / 60)) + "h "
						+ (timeVal % 60) + "min soit " + timeValPercent + "%";
				string += "\n\nTemps total en attente : "
						+ (timeAtt / 60 - (timeAtt % 60 / 60)) + "h "
						+ (timeAtt % 60) + "min soit " + timeAttPercent + "%";
				string += "\n\nTemps total Refusé : "
						+ (timeRef / 60 - (timeRef % 60 / 60)) + "h "
						+ (timeRef % 60) + "min soit " + timeRefPercent + "%";

				paragraph = new Paragraph(string);
				paragraph.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraph);

			} catch (DocumentException de) {
				de.printStackTrace();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			document.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
