package kr.co.test;

import java.lang.*;
import java.io.*;
import java.util.*;

public class SyncTest {

	static boolean thr_run_yn = true;
	public static void main(String argv[]) {

		Object lockobj = new Object();
		BufferedReader input = null;

		printMsg("SyncTest start ......", true);

		try {
			input = new BufferedReader(new InputStreamReader(System.in));

			String cmd = null;

			while(true) {

				printMsg("Enter command !! ......", false);

				if((cmd = input.readLine()) == null) break;

				cmd = cmd.toUpperCase();
				/* ===========================================
			     수행 명령 종류
			     LOCK : lockobj에 대한 lock 수행
			     TRUN : Thread 전체 기동
			     QUIT : 종료  
			     =========================================== */
				if(cmd.equals("LOCK")) { //lock 수행

					String lock_data = null;

					printMsg("LOCK command !! ......", false);

					while(true) {
						printMsg("Enter notify <<", false);
						lock_data = input.readLine();
						lock_data = lock_data.toUpperCase();

						if(lock_data.equals("UP")) { //상위로 이동
							break;
						} else if(lock_data.equals("ALL")) { 
							synchronized(lockobj) { //동기화에 따른 처리
								lockobj.notifyAll();
							}
						} else {
							synchronized(lockobj) { //동기화에 따른 처리
								lockobj.notify();
							}
						}
					}

				} else if(cmd.equals("TRUN")) { //Thread 기동

					printMsg("Ready for thread running  !!!", false);
					printMsg("Enter thread count << ", false);

					String trun_data = null;
					int thread_cnt = 0;
					while(true) {
						try {
							trun_data = input.readLine();
							thread_cnt = Integer.parseInt(trun_data);
							break;
						} catch (NumberFormatException trun_nfe) {
							printMsg("Wrong number, try again !!", false);
						} catch (Exception trun_e1) {
							printMsg("Wrong something, try again !!", false);
						}
					}

					for(int i = 1; i <= thread_cnt; i++) { //Thread 생성 및 기동
						SyncThread syncthr = new SyncThread(i,lockobj);
						syncthr.start();
					}

				} else if(cmd.equals("QUIT")) { //종료
					printMsg("Program quit !!!",false);
					printMsg("Thread cleaning !!!",false);
					SyncTest.thr_run_yn = false;

					synchronized(lockobj) { //동기화에 따른 처리
						lockobj.notifyAll();
					}
					break;
				}
			}
		} catch(Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				if(input != null) input.close();
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		}
	}

	public static void printMsg(String msg, boolean header) {
		if(header) {
			System.out.println("===============================================");
		}
		System.out.println(msg);
		if(header) {
			System.out.println("===============================================");
		}
	}
}