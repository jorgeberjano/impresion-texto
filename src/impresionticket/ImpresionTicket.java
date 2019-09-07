package impresionticket;

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
        //PageFormat format = printerjob.pageDialog(new PageFormat());
        printerjob.setJobName("ticket");
        printerjob.setPrintable(new TextoPrintable(texto));

        boolean ok = printerjob.printDialog();
        if (!ok) {
            return;
        }

        try {
            printerjob.print();

            //       printerjob.
//        printerjob.setCopies(1);
//        
//        for (PrintService printService : PrinterJob.lookupPrintServices()) {
//            System.out.println("check printer name of service " + printService);
//            if (printerName.equals(printService.getName())) {
//            System.out.println("correct printer service do print...");
//            printerjob.setPrintService(printService);
//            printerjob.print();
//            break;
//        }
//    
        } catch (PrinterException ex) {
            ex.printStackTrace();
        }

    }

}
