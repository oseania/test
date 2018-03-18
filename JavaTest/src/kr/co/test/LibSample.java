package kr.co.test;

import org.apache.commons.lang.StringUtils;

public class LibSample {

	public static void main(String[] args) {
		String targetStr ="체크대상문자";
		
		if(StringUtils.isEmpty(targetStr)) {
			System.out.println("비어있습니다.");
		} else {
			System.out.println("비어있지않습니다.");
		}
	}

}
