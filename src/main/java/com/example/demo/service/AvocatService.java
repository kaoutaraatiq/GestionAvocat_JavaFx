package com.example.demo.service;

import  com.example.demo.dao.AvocatDao;
import  com.example.demo.dao.impl.AvocatDaoImp;
import com.example.demo.entities.Avocat;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.List;

public class AvocatService {
    private AvocatDao avocatDao = new AvocatDaoImp();

    public List<Avocat> findAll() {
        return avocatDao.findAll();
    }

    public void save(Avocat avocat) {

        avocatDao.insert(avocat);

    }
    public void update(Avocat avocat) {

        avocatDao.update(avocat);

    }
    public Avocat find(int id){
        return avocatDao.findById(id);
    }
    public void remove(Avocat avocat) {
        avocatDao.deleteById(avocat.getId());
    }

    public void exporterVersText(){
        List<Avocat> list=findAll();
        try( FileOutputStream fout = new FileOutputStream("src/main/resources/outputDateText1.txt"))
        {

            for(Avocat avo : list){
                fout.write(avo.toString().getBytes());
                fout.write('\n');



                System.out.println(avo);
            }
        }
        catch (IOException e) {
            System.out.println(e.getStackTrace());
        }


    }
    public void importerDepuisText(String file) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        //ArrayList<Avocat> list = new ArrayList<>();
        Avocat a;
        String readLine = br.readLine();

        while(readLine != null){

            String [] avocat  = readLine.split("\\|");


            a = new Avocat();
            a.setId(Integer.valueOf(avocat[0].trim()));
            a.setNom(avocat[1].trim());
            a.setPrenom(avocat[2].trim());
            a.setAdress(avocat[3].trim());
            a.setDescription(avocat[4].trim());
            a.setRendez_vous(avocat[5].trim());
            a.setSpecialite(avocat[6].trim());
            a.setTelephone(avocat[7].trim());
            //list.add(a);
            if (this.find(a.getId())==null)
                this.save(a);
            else
                this.update(a);

            readLine = br.readLine();
        }

    }





    public void exporterVersExcel(String file){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("Sheet");

        XSSFRow header = spreadsheet.createRow(0);
        header.createCell(0).setCellValue("Id");
        header.createCell(1).setCellValue("Nom");
        header.createCell(2).setCellValue("Prenom");
        header.createCell(2).setCellValue("adress");
        header.createCell(2).setCellValue("description");
        header.createCell(2).setCellValue("rendez_vous");
        header.createCell(2).setCellValue("specialite");
        header.createCell(2).setCellValue("telephone");


        int rowNumber=1;
        for (Avocat avocat : this.findAll()){
            XSSFRow row = spreadsheet.createRow(rowNumber++);
            row.createCell(0).setCellValue(avocat.getId());
            row.createCell(1).setCellValue(avocat.getNom());
            row.createCell(2).setCellValue(avocat.getPrenom());
            row.createCell(2).setCellValue(avocat.getAdress());
            row.createCell(2).setCellValue(avocat.getDescription());
            row.createCell(2).setCellValue(avocat.getRendez_vous());
            row.createCell(2).setCellValue(avocat.getSpecialite());
            row.createCell(2).setCellValue(avocat.getTelephone());

        }

        try (FileOutputStream out = new FileOutputStream(new File(file))) {
            workbook.write(out);
            System.out.println("Avocats Data Exported Successfully To "+ file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void importerDepuisExcel(String file){

        try {
            FileInputStream inputStream = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int i=0;
            for (Row row : sheet) {
                if (i != 0)
                {
                    Avocat avocat = new Avocat();
                    avocat.setId((int) row.getCell(0).getNumericCellValue());
                    avocat.setNom(String.valueOf(row.getCell(1).getStringCellValue()));
                    avocat.setPrenom(String.valueOf(row.getCell(2).getStringCellValue()));
                    avocat.setAdress(String.valueOf(row.getCell(3).getStringCellValue()));
                    avocat.setDescription(String.valueOf(row.getCell(4).getStringCellValue()));
                    avocat.setRendez_vous(String.valueOf(row.getCell(5).getStringCellValue()));
                    avocat.setSpecialite(String.valueOf(row.getCell(6).getStringCellValue()));
                    avocat.setTelephone(String.valueOf(row.getCell(7).getNumericCellValue()));


                    if (this.find(avocat.getId()) instanceof Avocat)
                        this.update(avocat);
                    else
                        this.save(avocat);
                }
                i=1;
            }
            workbook.close();
            inputStream.close();
            System.out.println("Avocats Data Imported Successfully From "+ file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
