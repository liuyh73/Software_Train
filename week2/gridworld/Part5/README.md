[TOC]

### Folder1

Use the linked list to implement the SparseBoundedGrid. Here, I choose the SparseGridNode to complete it.



### Folder2

Use the HashMap or TreeMap to implement the SparseBoundedGrid. Here, I choose the HashMap to complete it.



### Folder3

I use the Two-dimensional array to implement the UnBoundedGrid and use some functions of other demos.



In the following table, r=grid's row and c=grid's col and n=grid's actors.

| 方法                         | SparseGridNode | LinkedList<OccupantInCol> | HashMap | TreeMap |
| :--------------------------- | :------------: | :-----------------------: | :-----: | :-----: |
| getNeighbors                 |      O(c)      |           O(c)            |  O(1)   | O(logn) |
| getEmptyAdjacentLocations    |      O(c)      |           O(c)            |  O(1)   | O(logn) |
| getOccupiedAdjacentLocations |      O(c)      |           O(c)            |  O(1)   | O(logn) |
| getOccupiedLocations         |     O(c+n)     |          O(r+n)           |  O(n)   |  O(n)   |
| get                          |      O(c)      |           O(c)            |  O(1)   | O(logn) |
| put                          |      O(c)      |           O(c)            |  O(1)   | O(logn) |
| remove                       |      O(c)      |           O(c)            |  O(1)   | O(logn) |



