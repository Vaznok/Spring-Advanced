package beans.view;

import beans.models.Ticket;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Component
public class PDFView extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter pdfWriter,
                                    HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        @SuppressWarnings("unchecked")
        List<Ticket> tickets = (List<Ticket>) model.get("tickets");

        PdfPTable table = new PdfPTable(4);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

        table.addCell("Event");
        table.addCell("Seats");
        table.addCell("Customer");
        table.addCell("Date");

        for (Ticket ticket : tickets) {
            table.addCell(ticket.getEvent().getName());
            table.addCell(ticket.getSeats());
            table.addCell(ticket.getUser().getName());
            table.addCell(ticket.getDateTime().toString());
        }
        document.add(table);
    }
}
