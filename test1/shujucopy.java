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
	 * sumNum �ļ�����<BR/>
	 * <P/>
	 * notpicNum ͼƬ����<BR/>
	 * <P/>
	 * startTime ��ʼʱ��<BR/>
	 * <P/>
	 * endTime ����ʱ��<BR/>
	 * <P/>
	 */
	static Long fileNum = 0L, folderNum = 0L, sumNum = 0L, notpicNum = 0L,
			i = 0L, zhi = 0L;
	static long startTime = System.currentTimeMillis();
	static Date date=new Date();
	static DateFormat format=new SimpleDateFormat("yyyyMMdd"+"001");
    static String wenjianjia=format.format(date);
	public static void main(String[] args) {
		// ��ѯ�����ݿ��1000������
		//List<String> listpath = new ArrayList<String>();
		// �жϵ�ǰĿ¼���ļ�����
		File file2 = new File("����һ���ļ���");
		if (file2 == null || !file2.isDirectory()) {// �ж��ļ����Ƿ����
			file2.mkdir();// ����
			//log.debug("�����ļ��гɹ� ����Ϊ��" + wenjianjia);
		}
		// �ŵ�listѭ���õ�path
		for (String path : listpath) {

			File file = new File(path);
			DataInputStream dis = null;
			DataOutputStream dos = null;

			try {
				// �ҵ�ֵ��Ӧ��λ��
				dis = new DataInputStream(new FileInputStream(
						file.getAbsoluteFile()));// ������
				// ȡ��ͼƬ�ŵ�һ��Ŀ¼��
				dos = new DataOutputStream(new FileOutputStream("�����·��"
						+ file.getName().replace(".", "_" + (i++) + ".")));// ������
				int temp;
				// ��д����
				while ((temp = dis.read()) != -1) {// ������
					dos.write(temp);// �Ѷ���������д�뵽Temp�ļ���
				}
			} catch (IOException e) {
				// д��־ͼƬ�����ж�
				System.out.println("//д��־ͼƬ�����ж� �쳣�ļ���ַ��"
						+ file.getAbsolutePath());
			} catch (Exception e) {
				// д��־�߳��ж�
				System.out.println("//д��־�߳��ж�");
			} finally {
				sumNum++;
				// �ر���
				try {
					if (dis != null) {
						dis.close();
					}
					if (dos != null) {
						dos.close();
					}
				} catch (Exception e) {
					System.out.println("���ر�ʧ��");
				}
			}

			notpicNum++;

		}

		// ��¼����
		long endTime = System.currentTimeMillis();
		System.out.println("�ļ�����:" + notpicNum + ",��ȡ��ͼƬ�У�" + sumNum + ",��ʱԼ��"
				+ (endTime - startTime) + "ms");
	}
}
