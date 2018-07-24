package com.bonaparte.util;

import com.bonaparte.bean.DynaBean;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by yangmingquan on 2018/7/24.
 */
public class ReadExcelUtil {
    // 工作表
    private Sheet sheet;

    // 工作表索引
    private int sheetIndex;

    // Excel 列 与 Bean 属性的顺序映射集
    private Map<Integer, String> mapper;

    // 动态 Bean
    private DynaBean dynaBean;

    // 最大解析的单元格索引
    private int maxColumnIndex;

    /**
     * 构造可读的 Excel 实例
     *
     * @param file
     *            Excel 文件
     */
    public ReadExcelUtil(File file) {
        try {
            init(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 构造可读的 Excel 实例
     *
     * @param in
     *            Excel 文件输入流
     */
    public ReadExcelUtil(InputStream in) {
        init(in);
    }

    /**
     * 解析 Excel 文档到一个 List 列表, 约定第一行为标题行, 标题行不被解析
     *
     * @param beanClass
     *            简单的 JavaBean 类型, Excel 文档的每一行解析成该类的一个实例
     * @param mapper
     *            Excel 文档的列映射到 JavaBean 对象的属性列表<br>
     *            格式串 A：["prop_name1", "prop_name2", ...]<br>
     *            格式串 B：["1:prop_name1", "2:prop_name2", ...]
     * @return 返回解析 Excel 文档的 List 结果列表
     */
    public <T> List<T> asList(Class<T> beanClass, String... mapper) {
        // 解析映射对照表
        this.mapper = parseMapper(mapper);
        // 实例化一个动态 Bean 对象
        this.dynaBean = new DynaBean(beanClass);
        List<T> beanList = new ArrayList<T>();
        // 工作表的行集
        Iterator<Row> rows = sheet.iterator();
        // 约定第一行为标题行, 不解析
        Row rowOfTitle = rows.next();
        // 实际的总的列数
        this.maxColumnIndex = rowOfTitle.getPhysicalNumberOfCells();
        // 迭代工作表的行集
        while (rows.hasNext()) {
            @SuppressWarnings("unchecked")
            T bean = (T) parseRow(rows.next());
            beanList.add(bean);
        }
        return beanList;
    }

    /**
     * 设置读取的工作表的索引
     *
     * @param sheetIndex
     *            工作表的索引
     */
    public void setSheetIndex(int sheetIndex) {
        this.sheetIndex = sheetIndex;
    }

    /**
     * 初始化工作表
     *
     * @param in
     *            Excel 输入流
     */
    private void init(InputStream in) {
        try {
            // 工作薄
            Workbook workbook = WorkbookFactory.create(in);
            // 获取索引指定的工作表
            sheet = workbook.getSheetAt(sheetIndex);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解析 Excel 文档的一行到一个 Bean 的对象里
     *
     * @param row
     *            Excel 文档中的行对象
     * @return 返回解析行的 Bean 对象
     */
    private Object parseRow(Row row) {
        // 创建一个 Bean 的实例
        dynaBean.newBeanInstance();
        // 行的单元格列表
        Iterator<Cell> cells = row.cellIterator();
        // 迭代单元格列表
        while (cells.hasNext()) {
            // 单元格
            Cell cell = cells.next();
            // 当前的单元格的索引
            int columnIndex = cell.getColumnIndex();
            // 索引超出最大解析的索引值结束迭代
            if (columnIndex >= maxColumnIndex) {
                break;
            }
            // 单元格映射到 Bean 的属性名称
            String name = mapper.get(columnIndex);
            // 如果不在对照表内, 跳过该列
            if (name == null) {
                continue ;
            }
            // 单元格映射到 Bean 的属性类型
            Class<?> type = dynaBean.getFieldType(name);
            // 单元格映射到 Bean 的属性的值
            Object value;

            try {
                if (type == Date.class) {
                    value = cell.getDateCellValue();
                } else {
                    // 设置单元格的类型为字符串类型
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    // 获取单元格字符串内容的值
                    String stringCellValue = cell.getStringCellValue();
                    // 进行数据类型转换
                    if(stringCellValue.equals("NULL")){
                        stringCellValue="";
                    }
                    value = commonTypeConvert(stringCellValue, type);
                }
            }catch (Exception e){
                int errorRow=row.getRowNum()+1;
                int errorColumn=cell.getColumnIndex()+1;
                throw new RuntimeException("读取Excel的"+errorRow+"行"+errorColumn+"列出错了."+e);
            }
            // 设置到 Bean 对象
            dynaBean.setFieldValue(name, value);
        }
        // 返回 Bean 的实例
        return dynaBean.getBean();
    }

    /**
     * 解析映射的字符串
     *
     * @param mapper
     *            格式串 A：["prop_name1", "prop_name2", ...]
     *            格式串 B：["1:prop_name1", "2:prop_name2", ...]
     * @return 返回解析后的对照表
     */
    private Map<Integer, String> parseMapper(String... mapper) {
        Map<Integer, String> map = new HashMap<Integer, String>();
        String item;
        int length = mapper.length;
        for (int i = 0; i < length; i++) {
            item = mapper[i];
            if (item.contains(":")) {
                String[] items = item.split(":");
                map.put(Integer.valueOf(items[0]), items[1]);
            } else {
                map.put(i, item);
            }
        }
        return map;
    }

    /**
     * 常用的数据类型转换
     *
     * @param value
     *            被转换的对象
     * @param type
     *            期望得到的类型
     * @return 返回转换后的数据类型对象
     */
    private Object commonTypeConvert(String value, Class<?> type) {
        if (type == String.class) {
            return value;
        }
        if (type == Character.TYPE || type == Character.class) {
            return value.charAt(0);
        }
        if (type == Boolean.TYPE || type == Boolean.class) {
            // 真值
            if (value == "1" || value.equals("是")
                    || value.equalsIgnoreCase("Y")
                    || value.equalsIgnoreCase("YES")
                    || value.equalsIgnoreCase("T")
                    || value.equalsIgnoreCase("TRUE")) {
                return true;
            }
            // 假值
            if (value == "0" || value.equals("否")
                    || value.equalsIgnoreCase("N")
                    || value.equalsIgnoreCase("NO")
                    || value.equalsIgnoreCase("F")
                    || value.equalsIgnoreCase("FALSE")) {
                return false;
            }
        }
        // 解析成双精度类型
        Double objVal = Double.valueOf(value);
        // 以下均基于双精度数据上作的类型转换
        if (type == Byte.TYPE || type == Byte.class) {
            return objVal.byteValue();
        }
        if (type == Short.TYPE || type == Short.class) {
            return objVal.shortValue();
        }
        if (type == Integer.TYPE || type == Integer.class) {
            return objVal.intValue();
        }
        if (type == Long.TYPE || type == Long.class) {
            return objVal.longValue();
        }
        if (type == Float.TYPE || type == Float.class) {
            return objVal.floatValue();
        }
        if (type == Double.TYPE || type == Double.class) {
            return objVal;
        }
        throw new ClassCastException("Cannot cast java.lang.String to " + type.getName());
    }
}
