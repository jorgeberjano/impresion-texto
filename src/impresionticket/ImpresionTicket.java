package impresionticket;

import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 *
 * @author jorge
 */
public class ImpresionTicket {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String texto;
        if (args.length > 0) {
            texto = leerTextoDeFichero(args[0]);
        } else {
            texto = "ESTO ES UNA PRUEBA DE IMPRESIÓN\n0123456789012345678901234567890123456789";
        }
        ImpresionTicket impresion = new ImpresionTicket();
        impresion.imprimir(texto);
    }

    private static String leerTextoDeFichero(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }

    public void imprimir(String texto) {
        PrinterJob printerjob = PrinterJob.getPrinterJob();        
        printerjob.setJobName("ticket");
				
        boolean ok = printerjob.printDialog();
        if (!ok) {
            return;
        }
		
		PageFormat pageFormat = printerjob.defaultPage(); //pageDialog(new PageFormat());
		Paper paper = pageFormat.getPaper();
		paper.setSize(612, 9269);
		paper.setImageableArea(0, 0, 612, 9269);
		
		pageFormat.setPaper(paper);
		pageFormat.setOrientation(PageFormat.PORTRAIT);
		printerjob.setPrintable(new TextoPrintable(texto), pageFormat);

        try {
            printerjob.print();

        } catch (PrinterException ex) {
            ex.printStackTrace();
        }
    }
}

