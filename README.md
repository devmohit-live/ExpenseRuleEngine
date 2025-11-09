We have a list of expense and rules. Aim is to validate rules for each expense.

Each expense is similar to

```json
{
expenseid: "1",
itemId: "Item1",
expensetype: "Food",
amountInUsd : "250",
sellerType : "restaurant",
SellerName "ABC restaurant"
}
```


List of rules similar to

- Total expense should not be > 175
- Seller type restaurant should not have expense more that 45
- Entertainment expense type should not be charged


Run the rules on expense and flag the rule which do not satisfy. Implement following:

`evaluateRule(List<rule> , List<expense>)`


Debug/Run Snapshots:

![debug state 1.png](src/main/resources/images/debug%20state%201.png)
![debug state 2.png](src/main/resources/images/debug%20state%202.png)
![debug state 3.png](src/main/resources/images/debug%20state%203.png)
![happy path.png](src/main/resources/images/happy%20path.png)
![validatio check.png](src/main/resources/images/validatio%20check.png)