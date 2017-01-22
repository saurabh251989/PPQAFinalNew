package com.ppqa.cell;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ExtractedSDDReader {

	private static final String FILENAME = "Input/SDD.txt";

	public static Set<String> getMapReqMother(String args) {
		Set<String> temp = new HashSet<String>();

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
			boolean flag = false;

			while (line != null) {

				if (line.trim().isEmpty()) {

					continue;
				} else {

					if (Character.isDigit(line.charAt(0))) {
						if (line.startsWith(args)) {

							if (line.equals(args)) {

								flag = true;
							} else {

								if (line.startsWith(args + ".")) {

									flag = true;
								}

							}

						} else {
							flag = false;
						}

					} else {
						if (flag) {
							temp.add(line);

						} else {
							;
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
		return temp;
	}

}
