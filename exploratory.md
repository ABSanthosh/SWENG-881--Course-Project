
## Test Description
|Item                              | Details                   |
|----------------------------------|----------------------------|
|Test Tour                         | Intellectual Tour         |
|Description                       | The goal is to see how the software will handle under stress by testing the reading and writing of large files|
|Test Duration                     | 90 minutes |
|Tester                            | Adam Slager |
|Further Testing <br> Opportunities| TBD|

## Test Protocol
|NR | What Done                   | Status        |    Comment     |
|---|-----------------------------|---------------|----------------|
|1  | Tested reading of 100 million line file using CSVReader |  No Exceptions | - Created 100 million line test file using below website with columns for 'first', 'last', and 'phone'  https://www.convertcsv.com/generate-test-data.htm<br>- Wrote and executed test script to read in file and count number of lines read.<br> - Noted that run time for processing file was 9636ms|
|2 | Tested reading of ~900 million line file using CSV Reader | No Exceptions | - Created 100 million line test file using below website with columns for 'first', 'last', and 'phone'  https://www.convertcsv.com/generate-test-data.htm<br>- Wrote and executed test script to read in file and count number of lines read.<br> - Noted that run time for processing file was 9636ms|
|3| Tested reading and writing of 100 million line file using CSVReader and CSVWriter | No Exceptions | Comment Here |
|4| Tested reading and writing of ~900 million line file using CSVReader and CSVWriter | No Exceptions | Comment Here |

## Test Artificacts
