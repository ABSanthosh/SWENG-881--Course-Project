## **FedEx Tour Report**

### Test Description

| Item                               | Details                                                                             |
| ---------------------------------- | ----------------------------------------------------------------------------------- |
| Test Tour                          | FedEx Tour                                                                          |
| Description                        | Track the “amount” field from input through processing, verifying data is preserved |
| Test Duration                      | 45 minutes                                                                          |
| Tester                             | Santhosh                                                                            |
| Further Testing <br> Opportunities | Track multiple columns simultaneously; use with filtered/aggregated datasets        |

### Test Protocol

| NR                 | What Done                                                      | Status                                                           | Comment                                                                                           | Test Artifacts                                                                       |
| ------------------ | -------------------------------------------------------------- | ---------------------------------------------------------------- | ------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------ |
| 1                  | Read `fedex-input.csv` and extracted columns `id` and `amount` | No Exceptions                                                    | Correct values were read from input, verified manually                                            | [Test Script](./Tests/FedExTour.java)<br>[Input CSV](./Tests/inputs/fedex-input.csv) |
| 2                  | Wrote selected columns to `fedex-output.csv` using `CsvWriter` | No Exceptions                                                    | File format and values were correct in output. Fields aligned and properly quoted where necessary | [Output](./Tests/inputs/fedex-output.csv)                                            |
| FastCSV/app/inputs | 4                                                              | Opened output in spreadsheet viewer and verified layout visually | No Exceptions                                                                                     | Output is viewable and properly delimited by common spreadsheet tools                |
