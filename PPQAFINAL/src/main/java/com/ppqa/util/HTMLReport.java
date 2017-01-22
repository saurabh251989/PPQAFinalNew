/**
 * 
 */
package com.ppqa.util;

import java.util.Iterator;
import java.util.List;

import com.ppqa.cell.TableAndTableComment;


/**
 * @author Saurabh Kumar
 *
 */
public class HTMLReport {

	public HTMLReport() {
		
	}

	public void generateHTMLReport(String fileName, List<TableAndTableComment> tableAndTableComment ) {

		System.out.println("<!DOCTYPE html>");
		System.out.println("<html>");
		System.out.println("<head>");
		System.out.println("<style>");
		System.out.println("table, th, td {");
		System.out.println("    border: 1px solid black;");
		System.out.println(" border-collapse: collapse;");
		System.out.println("}");
		System.out.println("th, td {");
		System.out.println("padding: 5px;");
		System.out.println("text-align: left;");
		System.out.println("}");
		System.out.println("</style>");
		System.out.println("</head>");
		System.out.println("<body>");

		System.out.println("<table style=\"width:100%\">");
		System.out.println("<caption>Report</caption>");
		System.out.println("<tr>");
		System.out.println("<th>");
		System.out.println("TableName");
		System.out.println("</th>");
		System.out.println("<th>");
		System.out.println("Comment");
		System.out.println("</th>");

		System.out.println("<tr>");

		for (Iterator<TableAndTableComment> iterator = tableAndTableComment.iterator(); iterator.hasNext();) {
			TableAndTableComment tableAndComment = (TableAndTableComment) iterator.next();

			System.out.println("<tr>");
			System.out.println("<td>");
			System.out.println(tableAndComment.getTableName());
			System.out.println("</td>");
			System.out.println("<td>");
			if (tableAndComment.getComment()!= null) {
				for (int i = 0; i < tableAndComment.getComment().size(); i++) {

					System.out.println(tableAndComment.getComment().get(i));
					System.out.println("<br/>");
				}
			} else {
				System.out.println("OK");
			}
			System.out.println("</td>");
			
			System.out.println("<tr>");

		}

		System.out.println("</table>");

		System.out.println("</body>");
		System.out.println("</html>");

	}

}
