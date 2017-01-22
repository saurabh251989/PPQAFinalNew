package com.ppqa.cell;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ExtractedMappedBehaviourReader {

	private static final String FILENAME = "temp.txt";
	private static Map<String, Set<String>> behaviour = new HashMap<>();

	/**
	 * @return the behaviour
	 */
	public static Map<String, Set<String>> getBehaviour() {
		return behaviour;
	}

	public static Map<String, Set<String>> getMapReqMother(String[] args) {

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

					break;
				} else {
					line=line.replace(" ", "");

					String[] split = line.split(":");
					if (split.length > 1) {
						if (behaviour.containsKey("SDD_OPSW-CI-"+split[0])) {

							Set<String> temp1 = behaviour.get("SDD_OPSW-CI-"+split[0]);
							String[] split2 = split[1].replace(";", "").split(",");

							temp1.addAll(Arrays.asList(split2));
							behaviour.put("SDD_OPSW-CI-"+split[0].replace(";", ""), temp1);

						} else {
							Set<String> temp1 = new HashSet<String>();
							String[] split2 = split[1].replace(";", "").split(",");

							temp1.addAll(Arrays.asList(split2));
							behaviour.put("SDD_OPSW-CI-"+split[0].replace(";", ""), temp1);

						}
					} else {
						if (behaviour.containsKey("SDD_OPSW-CI-"+split[0])) {

							Set<String> temp1 = behaviour.get("SDD_OPSW-CI-"+split[0]);
							temp1.add("");
							behaviour.put("SDD_OPSW-CI-"+split[0].replace(";", ""), temp1);

						} else {
							Set<String> temp1 = new HashSet<String>();
							temp1.add("");
							behaviour.put("SDD_OPSW-CI-"+split[0].replace(";", ""), temp1);

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
