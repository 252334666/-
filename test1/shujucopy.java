package test1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class shujucopy {
	/**
	 * sumNum 文件总数<BR/>
	 * <P/>
	 * notpicNum 图片数量<BR/>
	 * <P/>
	 * startTime 开始时间<BR/>
	 * <P/>
	 * endTime 结束时间<BR/>
	 * <P/>
	 */
	static Long fileNum = 0L, folderNum = 0L, sumNum = 0L, notpicNum = 0L,
			i = 0L, zhi = 0L;
	static long startTime = System.currentTimeMillis();
	static Date date=new Date();
	static DateFormat format=new SimpleDateFormat("yyyyMMdd"+"001");
    static String wenjianjia=format.format(date);
	public static void main(String[] args) {
		// 查询出数据库的1000条数据
		//List<String> listpath = new ArrayList<String>();
		// 判断当前目录的文件个数
		File file2 = new File("创建一个文件夹");
		if (file2 == null || !file2.isDirectory()) {// 判断文件夹是否存在
			file2.mkdir();// 创建
			//log.debug("创建文件夹成功 名字为：" + wenjianjia);
		}
		// 放到list循环拿到path
		for (String path : listpath) {

			File file = new File(path);
			DataInputStream dis = null;
			DataOutputStream dos = null;

			try {
				// 找到值对应的位置
				dis = new DataInputStream(new FileInputStream(
						file.getAbsoluteFile()));// 流读入
				// 取出图片放到一个目录下
				dos = new DataOutputStream(new FileOutputStream("放入的路径"
						+ file.getName().replace(".", "_" + (i++) + ".")));// 流读出
				int temp;
				// 读写数据
				while ((temp = dis.read()) != -1) {// 读数据
					dos.write(temp);// 把读到的数据写入到Temp文件中
				}
			} catch (IOException e) {
				// 写日志图片传输中断
				System.out.println("//写日志图片传输中断 异常文件地址："
						+ file.getAbsolutePath());
			} catch (Exception e) {
				// 写日志线程中断
				System.out.println("//写日志线程中断");
			} finally {
				sumNum++;
				// 关闭流
				try {
					if (dis != null) {
						dis.close();
					}
					if (dos != null) {
						dos.close();
					}
				} catch (Exception e) {
					System.out.println("流关闭失败");
				}
			}

			notpicNum++;

		}

		// 记录数量
		long endTime = System.currentTimeMillis();
		System.out.println("文件共有:" + notpicNum + ",提取的图片有：" + sumNum + ",耗时约："
				+ (endTime - startTime) + "ms");
	}
}
