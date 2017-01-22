
package com.ppqa.cell;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;

import com.ppqa.instance.Instance;
import com.ppqa.util.AttributePosition;
import com.ppqa.util.NodeOperation;

/**
 * 
 * @author Saurabh Kumar
 * @version 1.0
 *
 */

public class PPQA {

	static final Logger logger = Logger.getLogger(PPQA.class);
	static StringBuilder sb = null;

	public static void main(String[] args) throws IOException {

		Files.walk(Paths.get("Input/")).forEach(filePath -> {
			Set<String> reqMotherMappedMts = null;

			if (Files.isRegularFile(filePath) && (filePath.getFileName().toString().endsWith(".HTM"))
					|| (filePath.getFileName().toString().endsWith(".HTML"))
					|| (filePath.getFileName().toString().endsWith(".htm"))
					|| (filePath.getFileName().toString().endsWith(".html"))) {
				sb = new StringBuilder();
				String fileName = filePath.getFileName().toString().substring(0,
						filePath.getFileName().toString().lastIndexOf("."));

				Document doc = null;
				try {

					reqMotherMappedMts = new HashSet<>();

					doc = Jsoup.parse(filePath.toFile(), "UTF-8");
				} catch (IOException e) {

				}

				List<Node> childList = doc.body().childNodes();

				List<Node> childListNodde3 = childList.get(3).childNodes();

				for (Iterator<Node> iterator = childListNodde3.iterator(); iterator.hasNext();) {
					Node node = iterator.next();

					NodeOperation nodeOperation = new NodeOperation();

					List<Node> nodeList = node.childNodes();

					List<Node> nodeListMTS = nodeOperation.removeTextNode(nodeList);

					Instance.attributePosition = new AttributePosition(nodeListMTS.get(0));

					HashMap<String, Integer> att = Instance.attributePosition.getAttributeAndPosition();

					Set<String> lAttributeList = new HashSet<String>();

					lAttributeList.add("PUID");
					lAttributeList.add("Req_Mother");
					lAttributeList.add(
							"Module Test Specification of Field Loadable SW - Level A Software (from S3.2.1 onwards)");
					lAttributeList.add("Val_Remark");
					lAttributeList.add("Req_Comment");
					lAttributeList.add("VV_Verification_Justification");
					lAttributeList.add("VV_Verification_Procedure_Name");
					lAttributeList.add("VV_Verification_Procedure_Version");
					lAttributeList.add("Req_PR_Change_History");
					lAttributeList.add("Tested_Code_Label");
					lAttributeList.add("VV_Verification_Result");
					lAttributeList.add("Val_Means");
					lAttributeList.add("Val_Reference");
					lAttributeList.add("Tested_Baseline");
					lAttributeList.add("Req_Status");
					lAttributeList.add("VV_Verification_Procedure_Dev_Status");

					Set<String> lAttribute = att.keySet();
					List<String> comment = new ArrayList<String>();

					for (Iterator<String> it = lAttributeList.iterator(); it.hasNext();) {
						String attribute = it.next();
						if (lAttribute.contains(attribute)) {

						} else {

							comment.add("Attribute " + attribute + " is not present in HTML ");
						}

					}

					boolean flag = false;

					if (!(lAttribute.contains("PUID") && lAttribute.contains("VV_Verification_Procedure_Name")
							&& lAttribute.contains("VV_Verification_Procedure_Version"))) {
						flag = true;

					} else {
						flag = false;
					}

					if (flag == false) {

						boolean flagFirst = false;

						for (Iterator<Node> iterator2 = nodeListMTS.iterator(); iterator2.hasNext();) {
							if (flagFirst == false) {
								flagFirst = true;
								iterator2.next();
								continue;
							}
							Node node2 = (Node) iterator2.next();

							List<Node> temp = nodeOperation.removeTextNode(node2.childNodes());

							Document doc1 = Jsoup.parse(temp.get(att.get("PUID")).toString());
							String PUID = doc1.text();

							if (!PUID.isEmpty()) {
								Document Req_Mother = Jsoup.parse(temp.get(att.get("Req_Mother")).toString());
								String[] split = Req_Mother.text().split(";");

								List<String> asList = new ArrayList<>();

								for (int i = 0; i < split.length; i++) {
									reqMotherMappedMts.add(split[i]);
									asList.add(split[i].substring((split[i].lastIndexOf("-") + 1), split[i].length()));

								}

								sb.append(PUID);
								sb.append("\n");

								new FileWritter(temp
										.get(att.get(
												"Module Test Specification of Field Loadable SW - Level A Software (from S3.2.1 onwards)"))
										.toString());

								ExtractedMappedBehaviourReader.getMapReqMother(null);

								List<String> mapReqMother = SDDMapReader.getMapReqMother(null);
								Compare.getCompare(asList, mapReqMother);

							}

						}

					}

					else {

					}
				}
				try {
					String moduleNo = ReadExcel.getModuleNumber(fileName);
					Compare.getCompare(reqMotherMappedMts, ExtractedSDDReader.getMapReqMother(moduleNo));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				Map<String, Set<String>> SDDMappedBehaviour = ExtractedBehaviourReader.getMapBehaviourFromSDD(null);
				Map<String, Set<String>> MTSMappedBehaviour = ExtractedMappedBehaviourReader.getBehaviour();
				BehaviourCheck.getDiffBehaviourCheck(MTSMappedBehaviour, SDDMappedBehaviour);
				try {
					outputFile(fileName, sb);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});

	}

	protected static void outputFile(String name, StringBuilder temp) throws FileNotFoundException {

		PrintWriter writer = null;
		try {
			writer = new PrintWriter("Output/" + name + "_output.txt", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		writer.println(temp);
		writer.close();

	}

}
