package com.victor.findpara;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * 1.实现功能
 * 2.测试执行时间
 * 3.优化测试时间
 * 4.实现多线程，测试时间
 */
public class FindParaInPath {

	public static void main(String[] args) {
		String searchPara = "java";
		File filePath = new File("E:\\javademo\\c5");
		String fileNameFilter = ".*\\.(txt)|(java)";
		Pattern pattern = Pattern.compile(fileNameFilter);
		Collection<File> fileList = new LinkedList<File>();
		getAllTheFile(pattern, filePath, fileList);
		if(fileList.isEmpty()){
			System.err.println("空目录，没有文件!");
			return;
		}
		for(File file : fileList){
			matchStr(searchPara, file);
		}
	}
	
	//获得指定路径下的所有文件
	private static void getAllTheFile(Pattern pattern, File filePath, Collection<File> fileList){
		if(filePath.isFile()){
			Matcher matcher = pattern.matcher(filePath.getName());
			if(matcher.find()){
				fileList.add(filePath);
			}
		}else{
			for(File file : filePath.listFiles()){
				getAllTheFile(pattern, file, fileList);
			}
		}
	}

	private static void matchStr(String regex, File fileName) {
		Pattern pattern = Pattern.compile(regex);
		try {
			FileInputStream fileInputStream = new FileInputStream(fileName);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					fileInputStream));
			String parseLine = null;
			while ((parseLine = br.readLine()) != null) {
				Matcher matcher = pattern.matcher(parseLine);
				if(matcher.find()){
					System.err.println("在这个文件找到" + regex + ". File Path: " + fileName.getAbsolutePath());
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
