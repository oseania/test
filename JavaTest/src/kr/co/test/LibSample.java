package kr.co.test;

import org.apache.commons.lang.StringUtils;

public class LibSample {

	public static void main(String[] args) {
		String targetStr ="üũ�����";
		
		if(StringUtils.isEmpty(targetStr)) {
			System.out.println("����ֽ��ϴ�.");
		} else {
			System.out.println("��������ʽ��ϴ�.");
		}
	}

}
