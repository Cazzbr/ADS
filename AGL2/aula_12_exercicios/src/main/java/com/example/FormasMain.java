package com.example;

import java.io.FileOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.ss.usermodel.CellType;



public class FormasMain {
    public static void main(String[] args) throws Exception {
        
        ArrayList<Forma> formas = new ArrayList<>();
        
        formas.add(new Circulo(10, "Circulo"));
        formas.add(new Quadrado(10, "Quadrado"));
        formas.add(new Triangulo(10, "Triangulo", 10,10));
        formas.add(new Esfera(10, "Esfera"));
        formas.add(new Cubo(10, "Cubo"));
        formas.add(new Tetraedro(10, "Tetraedro"));

        //for (Forma f : formas) {
        //    System.out.println(f.getArea());
        //    if (f instanceof FormaTridimensional){
        //        System.out.println(((FormaTridimensional) f).getVolume());
        //    }
        //}

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sh = wb.createSheet("AreaAndVolume");

        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put("1", new Object[]{"Forma", "Area", "Volume"});
        for (int i = 0 ; i < formas.size(); i++){
            Forma f = formas.get(i);
            String vol;
            if (f instanceof FormaTridimensional){
                vol = ((FormaTridimensional) f).getVolume() + "";
            }else{vol = "-";}
            data.put((i + 2) + "", new Object[]{f.getNome(), f.getArea(), vol});
            
        }
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key: keyset){
            XSSFRow row = sh.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj: objArr){
                XSSFCell cell = row.createCell(cellnum++);
                cell.setCellType(CellType.STRING);
                cell.setCellValue(obj + "");
            }
        }
        try (FileOutputStream out = new FileOutputStream( new File("DB.xlsx"))){
            wb.write(out);
        }catch (Exception e){e.printStackTrace();}
        wb.close();
    }
}
