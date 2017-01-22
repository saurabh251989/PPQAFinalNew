/**
 * 
 */
package com.ppqa.cell;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Saurabh Kumar
 *
 */
public class Compare {

	/**
	 * 
	 */
	public Compare() {
		// TODO Auto-generated constructor stub
	}

	static void getCompare(Set<String> inputForCompare, Set<String> CompareWith) {

		PPQA.sb.append("\n\n**************REQ MOTHER***************\n");

		Collection<String> listOne = inputForCompare;

		Collection<String> listTwo = CompareWith;
		Collection<String> similar = new HashSet<String>(listOne);
		Collection<String> different = new HashSet<String>();
		different.addAll(listOne);
		different.addAll(listTwo);

		similar.retainAll(listTwo);
		different.removeAll(similar);

		if (different.size() > 0) {

			PPQA.sb.append("\n");

			PPQA.sb.append("\n");
			PPQA.sb.append("Mapped :" + listOne);
			PPQA.sb.append("\n");
			PPQA.sb.append("\nSDD :" + listTwo);
			PPQA.sb.append("\n");
			PPQA.sb.append("\nSimilar :" + similar);
			PPQA.sb.append("\n");
			PPQA.sb.append("\nDifferent :" + different);
			PPQA.sb.append("\n");

		} else {
			PPQA.sb.append("\t******PASS****");
			PPQA.sb.append("\n");

		}

	}

	static void getCompare(List<String> inputForCompare, List<String> CompareWith) {


		Collection<String> listOne = inputForCompare;

		Collection<String> listTwo = CompareWith;
		Collection<String> similar = new HashSet<String>(listOne);
		Collection<String> different = new HashSet<String>();
		different.addAll(listOne);
		different.addAll(listTwo);

		similar.retainAll(listTwo);
		different.removeAll(similar);

		if (different.size() > 0) {

			PPQA.sb.append("\n");

			PPQA.sb.append("\n");
			PPQA.sb.append("Req Mother :" + listOne);
			PPQA.sb.append("\n");
			PPQA.sb.append("\nMapped ReqMother :" + listTwo);
			PPQA.sb.append("\n");
			PPQA.sb.append("\nSimilar :" + similar);
			PPQA.sb.append("\n");
			PPQA.sb.append("\nDifferent :" + different);
			PPQA.sb.append("\n");

		} else {
			PPQA.sb.append("\t******PASS****");
			PPQA.sb.append("\n");

		}

	}
}
