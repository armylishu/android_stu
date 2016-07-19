package com.ls.multithread;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
/*
 * 多线程下载类
	多线程下载原理，获取要下载的文件的长度，根据设置的线程数计算每个线程下载的起始点和终止点，
	文件长度：length
	线程数：threadCount
	线程下载长度：size = length/threadCount
	线程ID：threadID （0开始）
	起始点：startIndex = threadID * size
	终止点：endIndex = (threadID + 1) * size - 1
*/
public class MutilThread {
	static int length;
	static int threadCount = 3;
	//下载文件路径
		static 	String path = "http://192.168.199.166:8080/TGPSetup.exe";
	/*//获取下载文件的长度
*/
	public static void main(String[] args){
		int size,startIndex,endIndex;
		
		
		try {
			URL url = new URL(path);
			HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();
			httpsURLConnection.setRequestMethod("GET");
			//设置超时
			httpsURLConnection.setConnectTimeout(5000);
			httpsURLConnection.setReadTimeout(5000);
			httpsURLConnection.connect();
			if(httpsURLConnection.getResponseCode() == 200){
				length = httpsURLConnection.getContentLength();
			}else {
				return;
			}
			//在本地生成一个与下载文件同大小的临时文件
			/*
			 * 随机存储文件
			 * file 如果不写绝对路径，则将存储在项目根目录下
			 * mode 有,r,rw,rws,rwd等模式，一般用rwd，因为在读写的同时会将文件同步写在硬盘上，不经过缓存
			 * */
			RandomAccessFile randomAccessFile = new RandomAccessFile("TGPSetup.exe", "rwd");
			//设置与下载文件同大小的临时文件，抢占空间
			randomAccessFile.setLength(length);
			//不要一直拿着RandomAccessFile的引用
			randomAccessFile.close();
			size = length / threadCount;
			for (int i = 0; i < threadCount; i++) {
//				设置每个线程下载的开始位置与结束位置
				startIndex = i * size;
				endIndex = (i + 1) * size -1;
				if(i == threadCount -1){
					endIndex = length - 1;
				}
				//开始多线程
				System.out.println(i+"----"+startIndex+"---"+endIndex);
				new DownloadThread(startIndex, endIndex, i).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
class DownloadThread extends Thread{
	int startIndex;
	int endIndex;
	int threadID;
	
	public DownloadThread(int startIndex, int endIndex, int threadID) {
		super();
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.threadID = threadID;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			URL url = new URL(MutilThread.path);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestMethod("GET");
			httpURLConnection.setConnectTimeout(5000);
			httpURLConnection.setReadTimeout(5000);
			httpURLConnection.setRequestProperty("Range", "bytes=" + startIndex + 
					"-" + endIndex);
			RandomAccessFile randomAccessFile = new RandomAccessFile("TGPSetup.exe", "rwd");
			if (httpURLConnection.getResponseCode() == 206) {
				InputStream  inputStream = httpURLConnection.getInputStream();
				byte[] bytes = new byte[1024];
				int len = 0;
				while ((len = inputStream.read(bytes)) != -1) {
					randomAccessFile.write(bytes, 0, len);
				}
				randomAccessFile.close();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
