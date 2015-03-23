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
 * 1.ʵ�ֹ���
 * 2.����ִ��ʱ��
 * 3.�Ż�����ʱ��
 * 4.ʵ�ֶ��̣߳�����ʱ��
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
			System.err.println("��Ŀ¼��û���ļ�!");
			return;
		}
		for(File file : fileList){
			matchStr(searchPara, file);
		}
	}
	
	//���ָ��·���µ������ļ�
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
					System.err.println("������ļ��ҵ�" + regex + ". File Path: " + fileName.getAbsolutePath());
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
