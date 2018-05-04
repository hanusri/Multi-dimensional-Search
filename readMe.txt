Long Project 4
-------
The project is to implement Multi-dimensional search using multiple efficient 
data structures on a store of items.


Contents
--------
* LP4Driver			- Driver program to read input from a file or console and
	call appropriate functions.
* MDS				- Multi-dimensional search class that implements all 
	insertion, deletion, update & search.
* Item				- Details of a single item.
* Count				- Wrapper class to maintain count of items that are being 
	indexed for easy update. In real use cases it would be a collection of 
	references to items being indexed. 
* ItemIndex			- Index of items by id.
* PriceIndex		- Index of item counts by price.
* DescPriceIndex	- Two levels of index for item counts by a single 
	description & price. 
* DescriptionIndex	- Index of item counts by description of an item as a whole.
* DoublyLinkedList	- Traditional Circular Doubly linked list.
* Entry				- A single entry in the DLL.
* TreeList			- Hybrid of DLL & RB Tree where the items are in the DLL and
	the references to the items are index in the RB Tree using Keys. 
* Compare			- Driver program to compare the performance of 
	ConcurrentSkipListMap, TreeMap & TreeList.


Sample Input
------------
The input contains a sequence of lines. Lines starting with "#" are comments. 
Other lines have one operation per line: name of the operation, followed by 
parameters needed for that operation (separated by spaces). Lines with Insert 
operation should have a "0" at the end as marker.
------------
Insert 22 19.97 475 1238 9742 0
# New item with id=22, price="$19.97", description="475 1238 9742"
# Return: 1
#
Insert 12 96.92 44 109 0
# Second item with id=12, price="96.92", description="44 109"
# Return: 1
#
Insert 37 47.44 109 475 694 88 0
# Another item with id=37, price="47.44", description="109 475 694 88"
# Return: 1
#
PriceHike 10 22 10
# 10% price increase for id=12 and id=22
# New price of 12: 106.61, Old price = 96.92.  Net increase = 9.69
# New price of 22: 21.96.  Old price = 19.97.  Net increase = 1.99
# Return: 11.68  (sum of 9.69 and 1.99)
#
FindMaxPrice 475		
# Return: 47.44 (id of items considered: 22, 37)
#
Delete 37
# Return: 1366 (=109+475+694+88)
#
FindMaxPrice 475		
# Return: 21.96 (id of items considered: 22)
#
End
# Lines after "End" are not processed.


Sample Output
-------------
The output is a single number, which is the sum of the return values obtained 
after processing every line.
-------------
1450.08


