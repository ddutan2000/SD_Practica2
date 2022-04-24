/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ec.edu.ups.imprimir;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.StringTokenizer;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.JOptionPane;

/**
 *
 * @author dduta
 */
public class Imprimir {

    private static int x1;
    private static int y2;
    private static Font fuente;
    private static String cadenaDeTexto;

    public void imprimirTexto(String texto) {

        cadenaDeTexto = texto;
        x1 = (20 * 595) / 210;
        y2 = (20 * 840) / 297;
        fuente = new Font("Times New Roman", Font.PLAIN, 12);

        FormatoPagina page = new FormatoPagina();

        try {
            page.imprimirT();
        } catch (PrinterException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static class FormatoPagina implements Printable {

        String[] lineas;
        int inicio = 0;
        int[] paginas;

        @Override
        public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {

            FontMetrics metrics = g.getFontMetrics(fuente);

            int altodelinea = metrics.getHeight();
            int lineasTotales = 0;

            if (paginas == null) {
                int numeroPaginas = 1;
                paginas = new int[0];
            }
            if (pageIndex > paginas.length) {
                return NO_SUCH_PAGE;
            }

            Graphics2D g2d = (Graphics2D) g;

            g2d.setFont(fuente);
            int start = (pageIndex == 0) ? 0 : paginas[pageIndex - 1];
            int end = (pageIndex == paginas.length) ? (lineasTotales) : paginas[pageIndex];
            FontMetrics fm = g.getFontMetrics();

            g2d.setFont(fuente);
            StringTokenizer lineasVh = new StringTokenizer(cadenaDeTexto, "\n");

            while (lineasVh.hasMoreTokens()) {
                g.drawString(lineasVh.nextToken(), x1, y2);
                y2 = y2 + altodelinea;
            }

            return PAGE_EXISTS;
        }

        @SuppressWarnings("empty-statement")
        public void imprimirT() throws PrinterException {

            PrinterJob job = PrinterJob.getPrinterJob();
            try {
                job.setPrintService(findPrintService("Microsoft Print to PDF"));
                PageFormat pf = new PageFormat();

                pf.setOrientation(1);

                Paper paper = new Paper();
                paper.setSize(597.6D, 842.4D);
                paper.setImageableArea(0.0D, 0.0D, 597.6D, 842.4D);
                pf.setPaper(paper);
                job.setPrintable(this, pf);

                job.print();
            } catch (PrinterException ex) {
                JOptionPane.showMessageDialog(null, "REVISE LA CONFIGURACION DE LA IMPRESORA", "ERROR", 0);
            }
        }

    }

    private static PrintService findPrintService(String printerName) {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService printService : printServices) {
            if (printService.getName().trim().equals(printerName)) {
                return printService;
            }
        }
        return null;
    }
}
