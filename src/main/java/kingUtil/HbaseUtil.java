package kingUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by kingcall 2017年-10月-25日,16时-47分
 * Descibe
 *
 * @author kingcall
 */
public class HbaseUtil {
    private static Connection con = null;
    private static Configuration conf = null;
    public static Logger log = LogManager.getLogger();
    public static List<Cell> getDataForGet(String tablename, String row, String family, String column) {
        if (isNotExistsTable(tablename)) {
            System.out.println(tablename + "..........is not exists");
            return null;
        }
        try {
            Table table = getConnection().getTable(TableName.valueOf(tablename));
            Get get = new Get(Bytes.toBytes(row));
            get.addColumn(Bytes.toBytes(family), Bytes.toBytes(column));
            Result result = table.get(get);
            List<Cell> lists = result.listCells();
            return lists;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<List<Cell>> getDataForGet(String tableName, List<String> rows) {
        Table table = null;
        if (isNotExistsTable(tableName)) {
            System.out.println(tableName + "..........is not exists");
            return null;
        } else {
            try {
                table = getConnection().getTable(TableName.valueOf(tableName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (rows == null || rows.size() == 0) {
            System.out.println("rows can't be null or it's size is not be zero");
            return null;
        }
        List<Get> gets = new ArrayList<>();
        for (String row : rows) {
            if (row == null || row.equals("")) {
                continue;
            } else {
                Get get = new Get(Bytes.toBytes(row));
                gets.add(get);
            }
        }
        List<List<Cell>> returnlist = new ArrayList<>();
        try {
            Result[] results = table.get(gets);
            System.out.println("------->" + results.length);
            if (results != null && results.length >= 1) {
                for (Result result : results) {
                    returnlist.add(result.listCells());
                }
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return returnlist;
    }

    public static void delRows(String tableName, List<String> rows) {
        if (rows == null) {
            System.out.println("删除行不能为空");
            return;
        }
        if (isNotExistsTable(tableName)) {
            System.out.println(tableName + "..........is not exists");
            return;
        }
        try {
            Table table = getConnection().getTable(TableName.valueOf(tableName));
            for (String row : rows) {
                Delete delete = new Delete(Bytes.toBytes(row));
                table.delete(delete);
            }
            System.out.println("删除成功");
        } catch (IOException e) {
            System.err.println("删除失败————" + e);
        }
    }

    public static void delRow(String tablename, String rowKey) {
        if (rowKey.equals("") || rowKey == null) {
            System.out.println("删除行不能为空");
            return;
        }
        if (isNotExistsTable(tablename)) {
            System.out.println(tablename + "..........is not exists");
            return;
        }
        try {
            Table table = getConnection().getTable(TableName.valueOf(tablename));
            Delete delete = new Delete(Bytes.toBytes(rowKey));
            table.delete(delete);
            System.out.println("删除成功");
        } catch (IOException e) {
            System.err.println("删除失败————" + e);
        }
    }

    public static void insertRow(String tableName, String rowKey, String familyName, String columnName, String value) throws IOException {
        Put put = new Put(Bytes.toBytes(rowKey));
        Table table = getConnection().getTable(TableName.valueOf(tableName));
        put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(columnName), Bytes.toBytes(value));
        table.put(put);
        System.out.println("添加单条数据完毕");
        table.close();
    }

    public static void insertRows_Familys_columns_one(String tableName, List<String> rows, List<String> columnFamilys, List<String> columns, List<String> values) throws IOException {
        if (values == null || rows == null || values.size() == 0 || rows.size() == 0 || isNotExistsTable(tableName) || columnFamilys == null || columns == null) {
            System.out.println("数据不合法");
            return;
        }
        if (columns.size() != columnFamilys.size()) {
            System.out.println("列簇和列不匹配");
            return;
        }
        if (rows.size() * columns.size() != values.size()) {
            System.out.println("数据数量大小不匹配");
            System.out.println(rows.size() + "          " + columns.size() + "           " + values.size());
            return;
        }
        if (isNotExistsTable(tableName)) {
            System.out.println(tableName + "表不存在");
            return;
        }
        List<Put> puts = new ArrayList<>();
        int k = 0;
        for (int i = 0; i < rows.size(); i++) {
            for (int j = 0; j < columnFamilys.size(); j++) {
                Put put = new Put(Bytes.toBytes(rows.get(i)));
                put.addColumn(Bytes.toBytes(columnFamilys.get(j)), Bytes.toBytes(columns.get(j)), Bytes.toBytes(values.get(k)));
                k++;
                puts.add(put);
            }
        }
        Table table = getConnection().getTable(TableName.valueOf(tableName));
        table.put(puts);
        System.out.println("数据插入完成");

    }

    public static void insertRows_oneFamily_onecolumn(String tableName, List<String> rows, String columnFamily, String column, List<String> values) {
        if (values == null || rows == null || values.size() == 0 || rows.size() == 0 || isNotExistsTable(tableName) || columnFamily == null) {
            System.out.println("数据不合法");
            return;
        }
        String flag = "";
        if (rows.size() != values.size()) {
            System.err.println("输入数据大小不一致,请问是否继续操作： 是 yes,否 no");
            Scanner sc = new Scanner(System.in);
            flag = sc.next();
            sc.close();
        }
        if (flag.equals("") || flag.equals("yes")) {
            int m = rows.size() >= values.size() ? values.size() : rows.size();
            List<Put> puts = new ArrayList<>(m);
            for (int i = 0; i < m; i++) {
                Put put = new Put(Bytes.toBytes(rows.get(i)));
                put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column), Bytes.toBytes(values.get(i)));
                puts.add(put);
            }
            try {
                Table table = getConnection().getTable(TableName.valueOf(tableName));
                table.put(puts);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("数据插入完成");
        } else {
            System.out.println("您已经取消了插入");
        }
    }

    public static Result getResult(String tableName, String rowKey) {
        Get get = new Get(Bytes.toBytes(rowKey));
        HTable htable = null;
        try {
            htable = new HTable(getConfiguration(), tableName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Result result = null;
        try {
            htable = new HTable(conf, Bytes.toBytes(tableName));
            result = htable.get(get);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (KeyValue k : result.list()) {
            System.out.println(Bytes.toString(k.getFamily()));
            System.out.println(Bytes.toString(k.getQualifier()));
            System.out.println(Bytes.toString(k.getValue()));
            System.out.println(k.getTimestamp());
        }
        return result;
    }

    public static void getDescribeTable(String tablename) {
        if (isNotExistsTable(tablename)) {
            System.out.println(tablename + "........is not exists");
        } else {
            try {
                HTable table = new HTable(getConfiguration(), tablename);
                HTableDescriptor desc = table.getTableDescriptor();
                HColumnDescriptor[] columnFamilies = desc.getColumnFamilies();

                for (HColumnDescriptor t : columnFamilies) {
                    System.out.println("列簇......................" + Bytes.toString(t.getName()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean createTableWithRegion(String tablename, String[] regions, String... familyNames) throws IOException {
        if (isNotExistsTable(tablename)) {
            Admin admin = getAdmain();
            HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf(tablename));
            for (String family : familyNames) {
                HColumnDescriptor tempfamily = new HColumnDescriptor(family);
                tempfamily.setMaxVersions(1);//只保留一个版本，也就是最新的
                tableDescriptor.addFamily(tempfamily);
            }
            if (regions != null) {
                byte[][] region = new byte[regions.length][];
                for (int i = 0; i < regions.length; i++) {
                    region[i] = regions[i].getBytes();
                }
                admin.createTable(tableDescriptor,region);
            } else {
                admin.createTable(tableDescriptor);
            }
            admin.close();
            System.out.println(tablename + ".........created ");
            return true;
        } else {
            System.out.println(tablename + ".........has benn existed");
            return false;
        }
    }

    public static boolean delTable(String tablename) {
        Admin admin = getAdmain();
        TableName tableName = TableName.valueOf(tablename);
        if (!isNotExistsTable(tablename)) {
            try {
                if (admin.isTableEnabled(tableName)) {
                    admin.disableTable(tableName);
                    admin.deleteTable(tableName);
                    System.out.println(tablename + ".................deleted  sucessfully");
                    return true;
                } else {
                    admin.deleteTable(tableName);
                    System.out.println(tablename + ".................deleted  sucessfully");
                    return true;
                }
            } catch (Exception e) {
                System.err.println(tablename + " deleted  falied,reason is not clear");
                e.printStackTrace();
                return false;
            }
        } else {
            System.err.println(tablename + " deleted  sucessfully,because the table is never exists");
            return true;
        }
    }

    public static Connection getConnection() {
        if (con == null) {
            Configuration configuration = getConfiguration();
            try {
                con = ConnectionFactory.createConnection(configuration);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else {
            if (con.isClosed()||con.isAborted()){
                Configuration configuration = getConfiguration();
                try {
                    con = ConnectionFactory.createConnection(configuration);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return con;
    }

    public static boolean isNotExistsTable(String tablename) {
        TableName tname = null;
        try {
            Admin admin = getAdmain();
            tname = TableName.valueOf(tablename);
            if (admin.tableExists(tname)) {
                {
                    return false;
                }
            } else {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Admin getAdmain() {
        Connection con = getConnection();
        Admin admin = null;
        try {
            admin = con.getAdmin();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (admin == null) {
            System.err.println("admin is null,you should be careful");
            log.error("admin is null,you should be careful");
            return admin;
        } else {
            return admin;
        }
    }

    public static Configuration getConfiguration() {
        if (conf == null) {
            conf = HBaseConfiguration.create();
            conf.addResource("hbase.xml");
        }
        return conf;
    }


    public static void getAllData(String tableName) throws IOException {
        Table table =getConnection().getTable(TableName.valueOf(tableName));
        Scan scan = new Scan();
        ResultScanner rs = table.getScanner(scan);
        StringBuffer sb=new StringBuffer();
        File file=new File("D:\\Documents\\kingcall\\1.txt");
        BufferedWriter bw=new BufferedWriter(new FileWriter(file));
        for (Result r : rs) {
            List<Cell> cells = r.listCells();
            for (Cell cell : cells) {
                Set<String> set= JSON.parseObject(Bytes.toString(CellUtil.cloneValue(cell)),new TypeReference<Set<String>>(){});
                String rowkey=new String(CellUtil.cloneRow(cell)).trim();
               for (String s:set){
                   bw.write(rowkey+"");
                   bw.write(s+"\r\n");
               }
            }
        }

        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        getAllData("url");
    }
}
