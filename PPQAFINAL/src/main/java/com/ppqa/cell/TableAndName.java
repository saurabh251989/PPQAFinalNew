/**
 * 
 */
package com.ppqa.cell;

import org.jsoup.nodes.Node;

/**
 * @author saurabh.k
 *
 */
public class TableAndName {
	
	
	private String tableName;
	private Node table;
	
	
	
	/**
	 * 
	 */
	public TableAndName() {
	
	}
	
	
	/**
	 * @param tableName
	 * @param table
	 */
	public TableAndName(String tableName, Node table) {
		this.tableName = tableName;
		this.table = table;
	}
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
	 * @return the table
	 */
	public Node getTable() {
		return table;
	}
	/**
	 * @param table the table to set
	 */
	public void setTable(Node table) {
		this.table = table;
	}
	
	
	
	

}
