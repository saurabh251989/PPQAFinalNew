package com.ppqa.cell;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ExtractedBehaviourReader {

	private static final String FILENAME = "Input/behaviour.txt";

	public static Map<String, Set<String>> getMapBehaviourFromSDD(String args) {
		
		Map<String, Set<String>> behaviour = new HashMap<>();

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(FILENAME));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			String line = null;

			try {
				line = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			while (line != null) {

				if (line.trim().isEmpty()) {

					continue;
				} else {

					if (line.startsWith("SDD_")) {
						String[] split = line.split(":");
						if (split.length > 1) {
							if (behaviour.containsKey(split[0])) {

								Set<String> temp1 = behaviour.get(split[0]);
								temp1.add(split[1]);
								behaviour.put(split[0], temp1);

							} else {
								Set<String> temp1 = new HashSet<String>();
								temp1.add(split[1]);
								behaviour.put(split[0], temp1);

							}
						} else {

							if (behaviour.containsKey(split[0])) {

								Set<String> temp1 = behaviour.get(split[0]);
								temp1.add("");
								behaviour.put(split[0], temp1);

							} else {
								Set<String> temp1 = new HashSet<String>();
								temp1.add("");
								behaviour.put(split[0], temp1);

							}

						}

					}

				}

				try {
					line = br.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return behaviour;
	}

}
