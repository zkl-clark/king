package kingUtil;

import com.google.common.io.Resources;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * hdfs工具类接口 Created by zhangxin on 2016/11/10.
 */
public class HDFSUtil {

	public static void main(String[] args) {
        try {
            createFolder("/kingcall");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	public static Configuration configuration = new Configuration();

	public static FileSystem getFileSystem() {
		configuration.addResource(Resources.getResource("core-site.xml"));
		FileSystem hdfs = null;
		try {
			hdfs = FileSystem.get(configuration);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return hdfs;
	}

	/**
	 * 查看一个目录或文件是否存在
	 * 
	 * @param hdfsPath
	 *            目录或文件完整路径
	 * @return 存在返回true，否则返回false
	 */

	public static boolean existsFiles(String hdfsPath) throws IOException {
		FileSystem hdfs = getFileSystem();
		Path path = new Path(hdfsPath);
		return hdfs.exists(path);
	}

	/**
	 * 创建文件夹 可以递归创建 如果文件夹已存在，则直接返回true
	 * 
	 * @param hdfsPath
	 *            HDFS文件夹路径
	 * @return 创建成功，返回true，否则返回false
	 */
	public static boolean createFolder(String hdfsPath) throws IOException {
		FileSystem hdfs = getFileSystem();
		Path path = new Path(hdfsPath);
		if (hdfs.exists(path)) {
			return true;
		} else {
			boolean obj = hdfs.mkdirs(path);
			if (obj) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * 创建文件 可以递归创建 如果已存在，则覆盖，不管是否有内容
	 * 
	 * @param hdfsPath
	 *            HDFS文件完整路径
	 * @return 创建成功，返回true，否则返回false
	 */
	public static boolean createFile(String hdfsPath) throws IOException {
		FileSystem hdfs = getFileSystem();
		Path path = new Path(hdfsPath);
		if (hdfs.exists(path)) {
			return true;
		} else {
			Object obj = hdfs.create(path);// 默认存在则覆盖,hdfs.createNewFile(f),存在则创建失败
			if (obj == null) {
				return false;
			} else {
				return true;
			}
		}
	}

	/**
	 * 创建文件 可以递归创建 如果已存在，则覆盖，不管是否有内容
	 * 
	 * @param hdfsPath
	 *            HDFS文件完整路径
	 * @param message
	 *            要写入的内容
	 * @return 创建成功，返回true，否则返回false
	 */
	public static boolean createFile(String hdfsPath, String message) throws IOException {
		FileSystem hdfs = getFileSystem();
		Path path = new Path(hdfsPath);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(hdfs.create(path)));
		bw.write(message);
		bw.flush();
		bw.close();
		return true;
	}

	/**
	 * 读文件 如果文件不存在？
	 * 
	 * @param hdfsPath
	 *            文件完整路径
	 * @return 读取的内容，文件不存在或读取失败，返回false
	 */
	public static List<String> readFile(String hdfsPath) throws IOException {
		List<String> returnValue = new ArrayList<String>();
		FileSystem hdfs = getFileSystem();
		Path path = new Path(hdfsPath);
		FSDataInputStream inputStream = hdfs.open(path);
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		LineNumberReader lineNumberReader = new LineNumberReader(inputStreamReader);
		String str = null;
		while ((str = lineNumberReader.readLine()) != null)
			returnValue.add(str);
		lineNumberReader.close();
		inputStreamReader.close();
		inputStream.close();
		hdfs.close();
		return returnValue;
	}

	/**
	 * 上传本地文件到HDFS 如果已存在，则删除
	 *
	 * @param localPath
	 *            本地文件完整路径
	 * @param hdfsFolder
	 *            HDFS文件夹路径
	 * @return 上传成功，返回true，否则返回false
	 */
	public static boolean localUploadHDFS(String localPath, String hdfsFolder) throws IOException {
		return true;
	}

	/**
	 * 上传文件夹到hdfs
	 * 
	 * @param localPath
	 *            本地文件夹完整路径
	 * @param hdfsFolder
	 *            HDFS文件夹路径
	 * @return 上传成功，返回true，否则返回false
	 * @throws IOException
	 */
	public static boolean UploadTOHDFS(String localPath, String hdfsFolder) throws IOException {
		System.out.println("文件上传开始");
		FileSystem hdfs = getFileSystem();
		File file = new File(localPath);
		if (!file.exists()) {
			System.err.println("要上传的文件不存在");
			return false;
		}else {
			hdfs.copyFromLocalFile(new Path(localPath), new Path(hdfsFolder));
			System.out.println("文件上传结束");
			return true;
		}
	}

	/**
	 * 下载HDFS文件到本地 如果已存在，则删除
	 * 
	 * @param localPath
	 *            本地文件夹完整路径
	 * @param hdfsFile
	 *            HDFS文件路径
	 * @return 下载成功，返回true，否则返回false
	 */
	public static boolean DownTOLocal( String hdfsFile,String localPath) throws IOException {
		Path path=new Path(hdfsFile);
		FileSystem hdfs = getFileSystem();
		File file=new File(localPath);
		if (file.exists()) {
			System.out.println("输出文件夹已经存在");
			return false;
		}else {
			if(!hdfs.exists(path)){
				System.out.println("HDFS文件夹不存在");
			}else {
				hdfs.copyToLocalFile(path, new Path(localPath));
				return true;
			}
		}
		return true;
	}

	/**
	 * 删除文件或目录 文件和目录均可删除 如果目录不为空呢，则递归删除
	 * 
	 * @param hdfsPath
	 *            文件完整路径
	 * @return 删除成功，返回true，不存在或失败返回false
	 */
	public static boolean deletePath(String hdfsPath) throws IOException {
		FileSystem hdfs = getFileSystem();
		Path path = new Path(hdfsPath);
		System.out.println("删除结束");
		return hdfs.delete(path, true);
	}
}
