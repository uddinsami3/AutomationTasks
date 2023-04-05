@ValidateFileTestCases
Feature: Validate output file data against input files

  Background: create connections to input and output csv files
    Given input files "InstrumentDetails.xlsx" and "PositionDetails.xlsx"
    Given output file "PositionReport.xlsx"

  @TC_013
  Scenario: validate number of records in output file
    Then filter the number of records in input and output files
    And validate the number of records in output file against input file

  @TC_014
  Scenario: check if output file has all the records from input file
    Then filter the records in input file and verify if it is present in output file

  @TC_015
  Scenario: validate total price in output file
    Then filter unit price and quantity from input file and validate against output file
