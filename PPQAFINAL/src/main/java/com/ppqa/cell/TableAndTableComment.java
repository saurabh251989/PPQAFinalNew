/**
 * 
 */
package com.ppqa.cell;

import java.util.List;


/**
 * @author Saurabh Kumar
 *
 */
public class TableAndTableComment {

	private String tableName;
	private List<String> comment;
	
	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @return the comment
	 */
	public List<String> getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(List<String> comment) {
		this.comment = comment;
	}

	/**
	 * 
	 */
	public TableAndTableComment() {
		// TODO Auto-generated constructor stub
	}

}
