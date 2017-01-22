package com.ppqa.cell;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SDDMapReader {

	private static final String FILENAME = "temp.txt";

	public static List<String> getMapReqMother(String[] args) {
		List<String> temp = new ArrayList<>();

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
					String[] split = line.split(":");
					temp.add(split[0].replace(";", ""));
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
