# Potter kata

[More info](http://codingdojo.org/kata/Potter/)

## Strategies

Representation:

| number of books of one title | number of books of one title | number of books of one title | number of books of one title | number of books of one title |
|---|---|---|---|---|

Ordered by quantity in descending order, regardless of the title.

### Minimum number of groups

Initially:

| 3 | 3 | 1 | 1 | 1 |
|---|---|---|---|---|

Subtracting 1:

| 2 | 2 | 0 | 0 | 0 |
|---|---|---|---|---|

Result: group of 5 books

Subtracting 1:

| 1 | 1 | 0 | 0 | 0 |
|---|---|---|---|---|

Result: group of 2 books

| 1 | 1 | 0 | 0 | 0 |
|---|---|---|---|---|

Result: not computing because there are only 1's; group of 2 books

### Not minimum number of groups

Initially:

| 3 | 3 | 1 | 1 | 1 |
|---|---|---|---|---|

Subtracting 1, **stopping when getting the first zero**:

| 2 | 2 | 0 | 1 | 1 |
|---|---|---|---|---|

Result: group of 3 books

Subtracting 1, **stopping when getting the first zero**:

| 1 | 1 | 0 | 0 | 1 |
|---|---|---|---|---|

Result: group of 3 books

| 1 | 1 | 0 | 0 | 1 |
|---|---|---|---|---|

Result: not computing because there are only 1's; group of 3 books 
