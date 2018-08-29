package com.bonaparte.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.enmus.ExcelType;
import org.jeecgframework.poi.excel.entity.params.ExcelExportEntity;
import org.jeecgframework.poi.excel.export.styler.ExcelExportStylerBorderImpl;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Created by yangmingquan on 2018/8/29.
 */
public class ExcelUtils {
    public static final Log logger = LogFactory.getLog(ExcelUtils.class);
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName, boolean isCreateHeader, HttpServletResponse response){
        ExportParams exportParams = new ExportParams(title, sheetName);
        exportParams.setCreateHeadRows(isCreateHeader);
        defaultExport(list, pojoClass, fileName, response, exportParams);

    }

    /**
     * 导出Excel
     * @param list 数据集合
     * @param title 标题
     * @param sheetName sheet名
     * @param pojoClass 映射对象
     * @param fileName 导出文件名
     * @param response 请求响应
     */
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass,String fileName, HttpServletResponse response){
        ExportParams exportParams = new ExportParams(title, sheetName);
        exportParams.setType(ExcelType.XSSF);
        exportParams.setStyle(ExcelExportStylerBorderImpl.class); //设置自定义样式
        defaultExport(list, pojoClass, fileName, response, exportParams);
    }
    public static void exportExcel(List<Map<String, Object>> list, String title, String sheetName, List<ExcelExportEntity> entityList, String fileName, HttpServletResponse response){
        ExportParams exportParams = new ExportParams(title, sheetName);
        exportParams.setType(ExcelType.XSSF);
        exportParams.setStyle(ExcelExportStylerBorderImpl.class); //设置自定义样式
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams,entityList,list);
        if (workbook != null)
            downLoadExcel(fileName, response, workbook);
    }

    public static void exportExcel(List<Map<String, Object>> list, String fileName, HttpServletResponse response){
        defaultExport(list, fileName, response);
    }

    private static void defaultExport(List<?> list, Class<?> pojoClass, String fileName, HttpServletResponse response, ExportParams exportParams) {
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams,pojoClass,list);
        if (workbook != null)
            downLoadExcel(fileName, response, workbook);
    }

    private static void defaultExport(List<Map<String, Object>> list, String fileName, HttpServletResponse response) {
        Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.XSSF);
        if (workbook != null)
            downLoadExcel(fileName, response, workbook);
    }

    private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); //导出xlsx
            response.setHeader("content-Type", "application/octet-stream");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException("下载Excel数据失败！");
        }
    }

    /**
     * 导入Excel文件
     * @param filePath 文件路径
     * @param titleRows 标题行数
     * @param headerRows 标题头行数
     * @param pojoClass 实体类
     * @param <T> 泛型
     * @return
     */
    public static <T> List<T> importExcel(String filePath,Integer titleRows,Integer headerRows, Class<T> pojoClass){
        if (StringUtils.isBlank(filePath)){
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
        }catch (NoSuchElementException e){
            logger.error("模板不能为空");
            throw new RuntimeException("模板不能为空");
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error("导入Excel模板数据失败！" + e.getMessage());
            throw new RuntimeException("导入Excel模板数据失败！");
        }
        return list;
    }

    /**
     * 导入Excel文件
     * @param file 文件对象
     * @param titleRows 标题行数
     * @param headerRows 标题头行数
     * @param pojoClass 实体类
     * @param <T> 泛型
     * @return
     */
    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass){
        if (file == null){
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
        }catch (NoSuchElementException e){
            logger.error("excel文件不能为空");
            throw new RuntimeException("excel文件不能为空");
        } catch (Exception e) {
            logger.error("导入excel模板数据失败！" + e.getMessage());
            throw new RuntimeException("导入excel模板数据失败！");
        }
        return list;
    }
}
