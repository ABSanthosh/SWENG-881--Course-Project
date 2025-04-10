## **Collector's Tour Report**

### Test Description

| Item                               | Details                                                                              |
| ---------------------------------- | ------------------------------------------------------------------------------------ |
| Test Tour                          | Collector’s Tour                                                                     |
| Description                        | Collect and count all unique values of the `amount` column to test value enumeration |
| Test Duration                      | 40 minutes                                                                           |
| Tester                             | Santhosh                                                                             |
| Further Testing <br> Opportunities | Perform uniqueness test on transformed or normalized fields                          |

### Test Protocol

| NR  | What Done                                                                  | Status        | Comment                                                                                           | Test Artifacts                                   |
| --- | -------------------------------------------------------------------------- | ------------- | ------------------------------------------------------------------------------------------------- | ------------------------------------------------ |
| 1   | Read `collector-input.csv`                                                 | No Exceptions | 4 rows, 3 columns; file loaded without error                                                      | [Input File](./Tests/inputs/collector-input.csv) |
| 2   | Extracted and stored all unique values of `amount` in a set                | No Exceptions | Set contained 3 entries: `500`, `600`, `700`; duplicates correctly removed                        | [Test Script](./Tests/CollectorsTour.java)       |
| 3   | Printed count of unique values to console                                  | No Exceptions | Console log showed expected value count = 3                                                       | TODO(add screenshot)                             |
| 4   | Re-ran test after modifying data to include lowercase and formatted values | No Exceptions | Collector distinguished values with formatting; recommend normalization pass in future iterations | TODO(add screenshot)                             |
