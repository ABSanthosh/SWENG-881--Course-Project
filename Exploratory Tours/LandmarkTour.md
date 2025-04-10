## **Landmark Tour Report**

### Test Description

| Item                               | Details                                                                                               |
| ---------------------------------- | ----------------------------------------------------------------------------------------------------- |
| Test Tour                          | Landmark Tour                                                                                         |
| Description                        | Verify correct handling of sequences by reordering data and checking structural and content integrity |
| Test Duration                      | 60 minutes                                                                                            |
| Tester                             | Santhosh                                                                                              |
| Further Testing <br> Opportunities | Reorder within subsets (e.g., per product category); reorder multiple files together                  |

### Test Protocol

| NR  | What Done                                                   | Status        | Comment                                                           | Test Artifacts                                     |
| --- | ----------------------------------------------------------- | ------------- | ----------------------------------------------------------------- | -------------------------------------------------- |
| 1   | Loaded original data from `landmark-input.csv`              | No Exceptions | Verified 4 rows and 3 columns exist                               | [Input CSV](./Tests/inputs/landmark-input.csv)     |
| 2   | Shuffled rows randomly and wrote to `landmark-temp.csv`     | No Exceptions | Manual comparison confirms sequence change, but data preserved    | [Temp Output](./Tests/inputs/landmark-temp.csv)    |
| 3   | Read shuffled file and wrote to final `landmark-output.csv` | No Exceptions | Verified that structure remained unchanged after second I/O pass  | [Final Output](./Tests/inputs/landmark-output.csv) |
| 4   | Compared first and second output using diff tool            | No Exceptions | Differences observed only in row order; values remained identical |                                                    |
