package impresionticket;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

/**
 *
 * @author jorge
 */
public class TextoPrintable implements Printable {
    
    private String texto;

    public TextoPrintable(String texto) {
        this.texto = texto;
    }
    
    
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex > 0 || texto == null) {
            return Printable.NO_SUCH_PAGE;
        }
		
		Paper paper = pageFormat.getPaper();
		int origenX = (int) paper.getImageableX();
		int origenY = (int) paper.getImageableY();
		
		double width = pageFormat.getImageableWidth();
		double height = pageFormat.getImageableHeight();
        
        graphics.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        FontMetrics metrics = graphics.getFontMetrics(graphics.getFont());
        int dy = metrics.getHeight();               
        
        String[] lineas = texto.split("\n");
        int x = origenX, y = origenY + dy;
        for (String linea : lineas) {
        
            graphics.drawString(linea, x, y);
            y += dy;
        }        
        return Printable.PAGE_EXISTS;
    }

}
