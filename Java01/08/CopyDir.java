/*
文件夹的复制
1.文件复制 copyFile
2.文件创建 mkdirs()
3.递归查找子孙级
*/
import java.io.*;
public class CopyDir {
	public static void main(String[] args) {
		//源目录
		String srcPath = "I:/Java/DataStrcture/sort";
		//目标目录
		String destPath = "I:/Git/";

		FileUtil.copyDir(srcPath,destPath);
	}


	/**
	*拷贝文件夹
	*/
	public static void copyDir(String srcPath,String destPath) {
		File src =  new File(srcPath);
		File dest = new File(destPath);
		copyDir(src,dest);
	}

	/**
	*拷贝文件夹
	*/
	public static void copyDir(File src,File dest) {
		if (src.isDirectory()) {          //是文件夹
			dest = new File(dest,src.getName());
		}
		copyDirDetail(src,dest);
	}
	/**
	*拷贝文件夹细节
	*/
	public static void copyDirDetail(File src,File dest) {
		if (src.isFile()) {
			try {
				FileUtil.copyFile(src,dest);
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}else if (dest.isDirectory()) {
			//确保目标文件夹存在
			dest.mkdirs();
			//获取下一级目录文件
			for (File sub : src.listFiles()) {
				copyDirDetail(sub,new File(dest,sub.getName()));
			}
		}

	} 
}