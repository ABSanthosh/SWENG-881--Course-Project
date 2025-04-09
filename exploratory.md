
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
|2 | Tested reading of ~900 million line file using CSV Reader | No Exceptions | - Created ~900 million line test file using below website and manually copying and pasting data with columns for 'first', 'last', and 'phone'  https://www.convertcsv.com/generate-test-data.htm<br>- Wrote and executed test script to read in file and count number of lines read.<br> - Noted that run time for processing file was 85121ms|
|3| Tested reading and writing of 100 million line file using CSVReader and CSVWriter | No Exceptions | - Wrote Java program to read in lines from 100 million line file created above using CSVReader and immediately write those lines to a new csv file using CSVWriter<br> - Noted that run time for processing was 20486ms<br>- Used Windows fc to compare files and noted that they were exact duplicates of each other
|4| Tested reading and writing of ~900 million line file using CSVReader and CSVWriter | No Exceptions | - Wrote Java program to read in lines from ~900 million line file created above using CSVReader and immediately write those lines to a new csv file using CSVWriter<br> - Noted that run time for processing was 176041ms<br>- Attemped to use Windows FC to compare files, but file size was too large.  This is a limitation of Windows FC and not the program being tested.<br>-Visually compared size of files with Windows Explorer and noted that files appear identifical|

## Test Artificacts
