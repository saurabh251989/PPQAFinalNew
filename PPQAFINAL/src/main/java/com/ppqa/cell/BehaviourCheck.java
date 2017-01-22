package com.ppqa.cell;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Saurabh Kumar
 *
 */
public class BehaviourCheck {

	public static List<String> getDiffBehaviourCheck(Map<String, Set<String>> maappedBehaviour,
			Map<String, Set<String>> sddBehaviour) {

		
		PPQA.sb.append("\n\n*********Behaviour Mapping***********\n");
		
		for (HashMap.Entry<String, Set<String>> entry : maappedBehaviour.entrySet()) {

			Set<String> temp = entry.getValue();

			Set<String> temp1 = sddBehaviour.get(entry.getKey());

			if (temp1 != null) {

				Collection<String> listOne = temp;

				Collection<String> listTwo = temp1;
				Collection<String> similar = new HashSet<String>(listOne);
				Collection<String> different = new HashSet<String>();
				different.addAll(listOne);
				different.addAll(listTwo);
				similar.retainAll(listTwo);
				different.removeAll(similar);

				if (different.size() > 0) {
					PPQA.sb.append(entry.getKey());
					PPQA.sb.append("\n");
					PPQA.sb.append("Mapped :"+listOne);
					PPQA.sb.append("\n");
					PPQA.sb.append("\nSDD :"+listTwo);
					PPQA.sb.append("\n");
					PPQA.sb.append("\nSimilar :"+similar);
					PPQA.sb.append("\n");
					PPQA.sb.append("\nDifferent :"+different);
					PPQA.sb.append("\n");
				}
				else
				{
					PPQA.sb.append(entry.getKey());
					PPQA.sb.append("\n");
					PPQA.sb.append("\t****PASS****");
					PPQA.sb.append("\n");
				}
			} else {
				PPQA.sb.append(entry.getKey() + " is not found in Extarcted SDD");
				PPQA.sb.append("\n");
			}
		}
		return null;
	}
}
