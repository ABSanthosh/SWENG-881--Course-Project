## Test Description

| Item                               | Details                                                                                                                 |
| ---------------------------------- | ------------------------------------------------------------------------------------------------------------------------|
| Test Tour                          | Back Alley Tour Part 2                                                                                                  |
| Description                        | Continue testing minor features, including JavaBeans and handlings white space characters                               |
| Test Duration                      | 90 minutes                                                                                                              |
| Tester                             | Adam Slager                                                                                                             |
| Further Testing <br> Opportunities | N/A                                                          |

## Test Protocol


| NR  | What Done                                                                  | Status        | Comment                                                                                           | Test Artifacts                                      |
| --- | -------------------------------------------------------------------------- | ------------- | ------------------------------------------------------------------------------------------------- | ----------------------------------------------------|
| E-BA2-01   | Test mapping of csv file to Java beans            | No Exceptions | Read in 3 row, 5 columns of data with mixed types and stored as Java Beans | - [Input File](./Tests/inputs/testJavaBeans.csv) <br> - [Test Script](/Exploratory%20Tours/Tests/BackAlleyTour2_JB.java)<br>- [Test Execution](/Image/BackAlleyJB_1.png) |
| E-BA2-02   | Test trimming of standard ASCII space characters preceding and following value in CSV | No Exceptions | File contained no unicode spaces.  FastCSV successfully eliminated spaces  | - [Input File](./Tests/inputs/testTrimSpaces.csv) <br> - [Test Script](/Exploratory%20Tours/Tests/BackAlleyTour2_Modify.java)<br>- [Test Execution](/Image/BackAlleyTrim_1.png)  |
| E-BA2-03   | Tested trimming of unicode space characters preceding and following value in CSV | No Exceptions | Program correctly did not remove unicode space characters |  - [Input File](./Tests/inputs/testStripSpaces.csv) <br> - [Test Script](/Exploratory%20Tours/Tests/BackAlleyTour2_Modify.java)<br>- [Test Execution](/Image/BackAlleyTrim_2.png)  |                          
| E-BA2-04   | Tested stripping of unicode space characters preceding and following value in CSV | No Exceptions | Program correctly removed unicode space characters |  - [Input File](./Tests/inputs/testStripSpaces.csv) <br> - [Test Script](/Exploratory%20Tours/Tests/BackAlleyTour2_Modify.java)<br>- [Test Execution](/Image/BackAlley_Strip1.png)  |  