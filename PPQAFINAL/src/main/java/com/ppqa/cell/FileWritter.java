/**
 * 
 */
package com.ppqa.cell;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Saurabh Kumar
 *
 */
public class FileWritter {

	/**
	 * 
	 */
	public FileWritter(String tempNode) {
		try{
			
			//tempNode.replaceAll("<br>","\n");
		    PrintWriter writer = new PrintWriter("temp.txt", "UTF-8");
		    String[] split = tempNode.split("> </a>");
		   String temp= split[1].replaceAll("<br>","\n");
				   temp=temp.replaceAll("</td>","");
				   temp=temp.replaceAll("<b>","");
				   temp=temp.replaceAll("</b>","");
				   temp=temp.replaceAll("&lt;","<");
				   temp=temp.replaceAll("&gt;",">");

		    writer.println(temp);
		    writer.close();
		} catch (IOException e) {
		}
	
	}

}
