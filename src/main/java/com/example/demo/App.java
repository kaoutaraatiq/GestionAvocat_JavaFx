package com.example.demo;

import com.example.demo.service.AvocatService;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws IOException {
        //Afficher la liste des avocats
        AvocatService avocatService = new AvocatService();
        //for( Avocat avocat :avocatService.findAll())
          //  System.out.println(avocat);
        //Supprimer un avocat
        //AvocatDaoImp avocatDaoImp=new AvocatDaoImp();
        //avocatDaoImp.deleteById(2);
        //Chercher un avocat
        //Avocat avocat1=avocatDaoImp.findById(2);
        ////System.out.println("Voila l'avocat recherché  "+avocat1);
        avocatService.importerDepuisText("src/main/resources/inputDataText.txt ");
        //avocatService.exporterVersText();
        //avocatService.importerDepuisExcel("src/main/resources/excelData.xlsx");
        //avocatService.exporterVersExcel("src/main/resources/excelData1.xlsx");


    }
}
