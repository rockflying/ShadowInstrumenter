package soot.jimple.instrumenter.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import soot.Unit;
import soot.util.Chain;

public class FlowPathParser {
	private Chain<Unit> units;

	public FlowPathParser(String filePath) {
		try {
			String encoding = "GBK";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					System.out.println(lineTxt);
				}
				read.close();
			} else {
				System.out.println("Cannot find the file");
			}
		} catch (Exception e) {
			System.out.println("Failed to read from file");
			e.printStackTrace();
		}
	}

	public FlowPathParser() {

	}

	public void parse() {

	}
}
