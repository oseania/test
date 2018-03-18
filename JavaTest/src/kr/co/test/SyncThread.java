package kr.co.test;

import java.lang.*;
import java.io.*;
import java.util.*;

public class SyncThread extends Thread {

	private int turn = 0;
	private Object lockobj = null;
	private String titleStr = null;

	public SyncThread(int turn, Object lockobj) { //Thread 생성자
		this.turn = turn;
		this.lockobj = lockobj;
		this.titleStr = "[" + turn + "'s Thr] ";
	}

	public void run() {  //Thread 처리 구현

		SyncTest.printMsg(titleStr + "starting ...",false);

		try {

			while(true) {
				if(!SyncTest.thr_run_yn) {
					SyncTest.printMsg(titleStr + "Thread stop ....",false);
					break;
				}
				SyncTest.printMsg(titleStr + "Try LOCK acquisition!",false);
				synchronized(lockobj) {
					SyncTest.printMsg(titleStr + "LOCK acquisition success!",false);
					SyncTest.printMsg(titleStr + "3초 sleep start",false);
					Thread.sleep(3000); 
					SyncTest.printMsg(titleStr + "3초 sleep end",false);
					SyncTest.printMsg(titleStr + "LOCK Wait start",false);
					lockobj.wait();
					SyncTest.printMsg(titleStr + "LOCK Wait end",false);
				}
			}
		} catch (Exception e) {
			SyncTest.printMsg(titleStr + "오류 발생 !\n" + e.toString(), false);
		}
	} 
}
