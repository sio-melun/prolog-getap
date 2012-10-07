package org.ldv.sio.getap.app;

import java.awt.Font;
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
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
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
			List<DemandeValidationConsoTempsAccPers> dctap) {

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

				rs.last();
				String eleve = "Nom : " + rs.getString("nom");
				eleve += "\nPrénom : " + rs.getString("prenom");
				int idClasse = rs.getInt("idClasse");
				rs.beforeFirst();
				ResultSet rs2 = select
						.executeQuery("SELECT classe.* from classe where id = "
								+ idClasse);
				rs2.last();
				eleve += "\nClasse : " + rs2.getString("libelle") + "\n\n\n";
				rs2.beforeFirst();

				Paragraph paragraph = new Paragraph(eleve);
				paragraph.setAlignment(Element.ALIGN_LEFT);
				document.add(paragraph);

				com.itextpdf.text.Font fontbold = FontFactory.getFont(
						"Times-Roman", 18, Font.BOLD);
				paragraph = new Paragraph("Les demandes de validations\n\n",
						fontbold);
				paragraph.setAlignment(Element.ALIGN_CENTER);
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

				PdfPTable table = new PdfPTable(4);

				PdfPCell c1 = new PdfPCell(new Phrase(
						"Temps total effectué (72h)", FontFactory.getFont(
								FontFactory.TIMES_ROMAN, 15,
								com.itextpdf.text.Font.BOLD)));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setPaddingBottom(7);
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase("Temps total validé",
						FontFactory.getFont(FontFactory.TIMES_ROMAN, 15,
								com.itextpdf.text.Font.BOLD)));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setPaddingBottom(7);
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase("Temps total en attente",
						FontFactory.getFont(FontFactory.TIMES_ROMAN, 15,
								com.itextpdf.text.Font.BOLD)));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setPaddingBottom(7);
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase("Temps total refusé",
						FontFactory.getFont(FontFactory.TIMES_ROMAN, 15,
								com.itextpdf.text.Font.BOLD)));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setPaddingBottom(7);
				table.addCell(c1);

				table.setHeaderRows(1);

				c1 = new PdfPCell(new Phrase((timeTT / 60 - (timeTT % 60 / 60))
						+ "h " + (timeTT % 60) + "min"));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setPaddingBottom(4);
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase(
						(timeVal / 60 - (timeVal % 60 / 60)) + "h "
								+ (timeVal % 60) + "min"));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setPaddingBottom(4);
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase(
						(timeAtt / 60 - (timeAtt % 60 / 60)) + "h "
								+ (timeAtt % 60) + "min"));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setPaddingBottom(4);
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase(
						(timeRef / 60 - (timeRef % 60 / 60)) + "h "
								+ (timeRef % 60) + "min"));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setPaddingBottom(4);
				table.addCell(c1);

				c1 = new PdfPCell(new Phrase(timeTTpercent + "%"));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setPaddingBottom(4);
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase(timeValPercent + "%"));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setPaddingBottom(4);
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase(timeAttPercent + "%"));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setPaddingBottom(4);
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase(timeRefPercent + "%"));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setPaddingBottom(4);
				table.addCell(c1);

				document.add(table);

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
