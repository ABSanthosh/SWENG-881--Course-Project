## Test Description

| Item                               | Details                                                                                                                 |
| ---------------------------------- | ------------------------------------------------------------------------------------------------------------------------|
| Test Tour                          | Back Alley Tour                                                                                                         |
| Description                        | To test several of more minor features of the program, specifically those involving compressed files and indexed reading|
| Test Duration                      | 90 minutes                                                                                                              |
| Tester                             | Adam Slager                                                                                                             |
| Further Testing <br> Opportunities | Potential to continue testing additional minor features                                                                 |

## Test Protocol


| NR  | What Done                                                                  | Status        | Comment                                                                                           | Test Artifacts                                      |
| --- | -------------------------------------------------------------------------- | ------------- | ------------------------------------------------------------------------------------------------- | ----------------------------------------------------|
| E-BA1-01   | Wrote test case to read small compressed file and test the output               | No Exceptions | Read in 4 rows and three columns without exception                                                | - [Input File](./Tests/inputs/reader-file-a01.csv.gz) <br> - [Test Script](/Exploratory%20Tours/Tests/BackAlleyTour.java)<br>- [Test Execution](/Image/BackAlley_1.png) |
| E-BA1-02   | Wrote test case to read very large compressed file and test the output            | No Exceptions | File contains 10M entries                        | - Input File Excluded Due to Size <br> - [Test Script](/Exploratory%20Tours/Tests/BackAlleyTour.java)<br>- [Test Execution](/Image/BackAlley_1.png) |
| E-BA1-03   | Wrote test case to write compressed file and subsequently read the same compressed file | No Exceptions | Wrote and read in 4 rows and 3 columns without exception   |  - [Test Script](/Exploratory%20Tours/Tests/BackAlleyTour.java)<br>- [Test Execution](/Image/BackAlley_1.png) |                          |
| E-BA1-04   | Tested indexed reading of very large (100M line) file | No Exceptions | - Initial file read under 10000ms<br>- Subequent indexed page reads took 12ms or less | - [Test Script](/Exploratory%20Tours/Tests/BackAlleyTourIndex.java)<br>- [Test Execution](/Image/BackAlley_2.png) |
