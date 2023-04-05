package com.test.steps;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import cucumber.api.java.en.Then;
public class ValidateOutputFileSteps {
    public static Fillo instrumentFillo;
    public static Connection instrumentConn;
    public static Recordset instrumentRS;
    public static Fillo positionFillo;
    public static Connection positionConn;
    public static Recordset positionRS;
    public static Fillo outputFillo;
    public static Connection outputConn;
    public static Recordset outputRS;
    public static int inputRecordSize=0;
    public static int outputRecordSize=0;

    @Given("^input files \"([^\"]*)\" and \"([^\"]*)\"$")
    public void input_files_and(String arg1, String arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        instrumentFillo = new Fillo();
        positionFillo = new Fillo();
        instrumentConn = instrumentFillo.getConnection(System.getProperty("user.dir") + "\\resources\\drivers\\input\\" + arg1);
        positionConn = positionFillo.getConnection(System.getProperty("user.dir") + "\\resources\\drivers\\input\\" + arg2);
    }

    @Given("^output file \"([^\"]*)\"$")
    public void output_file(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        outputFillo = new Fillo();
        outputConn = outputFillo.getConnection(System.getProperty("user.dir") + "\\resources\\drivers\\output\\" + arg1);
    }

    @Then("^filter the number of records in input and output files$")
    public void filter_the_number_of_records_in_input_and_output_files() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        String outFileQuery = "SELECT DISTINCT PositionID FROM PositionReport";
        String inputFileQuery = "SELECT ID FROM PositionDetails";

        positionRS = positionConn.executeQuery(inputFileQuery);
        inputRecordSize = positionRS.getCount();
        outputRS = outputConn.executeQuery(outFileQuery);
        outputRecordSize = outputRS.getCount();

    }

    @Then("^validate the number of records in output file against input file$")
    public void validate_the_number_of_records_in_output_file_against_input_file() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("input records are: " + inputRecordSize);
        System.out.println("output records are: " + outputRecordSize);
        System.out.println(inputRecordSize == outputRecordSize);

    }

    @Then("^filter the records in input file and verify if it is present in output file$")
    public void filter_the_records_in_input_file_and_verify_if_it_is_present_in_output_file() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        String instrumentQuery = "SELECT ID FROM InstrumentDetails";
        String positionQuery = "";
        int invalidRecords = 0;
        String outputQuery ="";
        instrumentRS = instrumentConn.executeQuery(instrumentQuery);
        while(instrumentRS.next()){
            System.out.println(instrumentRS.getField("ID"));
            positionQuery = "SELECT * FROM PositionDetails WHERE InstrumentID='" + instrumentRS.getField("ID") + "'";
            positionRS = positionConn.executeQuery(positionQuery);
            if(!(positionRS.getCount() > 0)){
                System.out.println("record not present: " + instrumentRS.getField("ID"));
                invalidRecords += 1;
            }
        }
        if(!(invalidRecords > 0)){
            System.out.println("records are matching for instrument and position inputs");
            invalidRecords = 0;
        }

        //verify position records with output files
        positionQuery = "SELECT ID FROM PositionDetails";
        positionRS = positionConn.executeQuery(positionQuery);
        while(positionRS.next()){
            System.out.println(positionRS.getField("ID"));
           outputQuery = "SELECT * FROM PositionReport WHERE PositionID='" + positionRS.getField("ID") + "'";
            outputRS = outputConn.executeQuery(outputQuery);
            if(!(outputRS.getCount() > 0)){
                System.out.println("record not present: " + positionRS.getField("ID"));
                invalidRecords += 1;
            }
        }
        if(!(invalidRecords > 0)){
            System.out.println("records are matching for input and output");
        }

    }

    @Then("^filter unit price and quantity from input file and validate against output file$")
    public void filter_unit_price_and_quantity_from_input_file_and_validate_against_output_file() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        String instrumentQuery = "SELECT * FROM InstrumentDetails";
        String positionQuery = "SELECT * FROM PositionDetails";
        String outputQuery = "SELECT * FROM PositionReport";

        instrumentRS = instrumentConn.executeQuery(instrumentQuery);
        while(instrumentRS.next()){
            positionQuery = "SELECT * FROM PositionDetails WHERE InstrumentID='" + instrumentRS.getField("ID") + "'";
            positionRS = positionConn.executeQuery(positionQuery);

            while(positionRS.next()){
                //System.out.println(positionRS.getField("ID"));
                int totalPrice = Integer.parseInt(instrumentRS.getField("Unit_price")) * Integer.parseInt(positionRS.getField("Quantity"));
                outputQuery = "SELECT * FROM PositionReport WHERE PositionID='" + positionRS.getField("ID") + "' AND Total_price=" + totalPrice;
                outputRS = outputConn.executeQuery(outputQuery);
                if(outputRS.getCount() > 0){
                    System.out.println("Total price matches with inputs " + positionRS.getField("ID"));

                }
            }
        }
        //positionRS = positionConn.executeQuery(positionQuery);
    }


}
