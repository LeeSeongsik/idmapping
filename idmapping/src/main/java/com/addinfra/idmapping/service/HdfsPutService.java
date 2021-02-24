package com.addinfra.idmapping.service;

import java.io.IOException;

import org.springframework.stereotype.Component;

@Component
public class HdfsPutService {
	
	public static void executeScript() throws InterruptedException{
	    try{
	        ProcessBuilder pb = new ProcessBuilder("/user/irteam/script/hdfs_put_file.sh");
	        Process p = pb.start();
	        p.waitFor();
	        System.out.println("Script executed successfully");
	    }catch(IOException e){
	        e.printStackTrace();
	        }
	}

	public static void main(String[] args) throws InterruptedException {
	    HdfsPutService hdfsputservice = new HdfsPutService();
	    System.out.println("main");
	    hdfsputservice.executeScript();
	}

	/*	# window 파일 실행
	public static void main(String[] args) {
		Runtime rt = Runtime.getRuntime();
		
		String file = "C:\\Program Files\\PuTTY\\putty.exe";
		Process pro;
		
		try {
			pro = rt.exec(file);
			pro.waitFor();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
*/	

}
