package com.printer;

import java.awt.print.*;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.Font;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.util.Vector;
import java.text.MessageFormat;

public class Printer {

    public static String str = "";
    public static Graphics2D g2d = null;

    // Constractor 1 Print String
    public Printer(String s) {
        str = s;
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(new MyPrintable());
        if (pj.printDialog()) { //  Ckicl print in windows window print setings
            try {
                pj.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }   
        }
    }

    // Constractor 2 Print JTable
    public Printer(JTable jt) {
        int last_height = jt.getRowHeight();
        //Font last_font = jt.getFont();
        //jt.setFont(new Font("Arial", Font.PLAIN, 33));
        jt.setRowHeight(50);
        try {
            jt.print(JTable.PrintMode.FIT_WIDTH);
        } catch (PrinterException ex) {
            ex.printStackTrace();
        }
        //jt.setFont(last_font);
        jt.setRowHeight(last_height);
    }

    // Take a string and make it lines list
    protected static void set(Vector<String> list) {
        String[] lines = str.split("\n");
        for (String line : lines) {
            list.add(line);
        }            
    }

    // Print String iner Class
    public static class MyPrintable implements Printable {
        @Override  // Print my String
        public int print(Graphics graphics, PageFormat pageFormat, int page) throws PrinterException { 
            if (page > 0) {
                return NO_SUCH_PAGE;
            }
            Vector<String> lines = new Vector<String>();
            set(lines);
            int lint_x = 50;
            int line_y = 40;
            for (String line : lines) {
                graphics.drawString(line, lint_x, line_y);
                line_y += 27;
            }
            return PAGE_EXISTS;    
        }
    }
/*
    // Print JTable inner Class
    public static class MyPrintableTable implements Printable {
        @Override  // Print my String
        public int print(Graphics graphics, PageFormat pageFormat, int page) throws PrinterException {
            if (page > 0) {
                return NO_SUCH_PAGE;
            }

            int x = 50;
            int y = 40;

            graphics.drawTable(jt, x, y);

            return PAGE_EXISTS;    
        }
    }
*/
}