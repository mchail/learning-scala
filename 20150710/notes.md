Format: csv file, 4 columns

userid, url, rectype, count

3 types of record type
- 1 seen an add on this url
- 2 clicked an add on this url
- 3 converted on this url

about 9000 files per day

all.txt = DMP data for 2015-06-01

smaller.txt = subset of only those users who engaged with >1 url and only those urls having at least 100 users

matrix of urls and users

about 12.5M users and 80K urls

about 1T cells in the matrix

# HOMEWORK: build the urls by users matrix for smaller.txt

want to know how many urls there are and how many users there are in smaller.txt. dimensions of matrix.

```scala
val m = Map(1 -> "abc", 2 -> "cde")

m.mapValues // operate only on the values

m.map{case(k, v) => (k, v + 3)}
```

```scala
scala> Map(1 -> 3, 2 -> 4).toList
res7: List[(Int, Int)] = List((1,3), (2,4))
```

```scala
scala> List.tabulate(100){x => (x+1, 100-x)}.toMap
res11: scala.collection.immutable.Map[Int,Int] = Map(69 -> 32, 88 -> 13, 5 -> 96, 10 -> 91, 56 -> 45, 42 -> 59, 24 -> 77, 37 -> 64, 25 -> 76, 52 -> 49, 14 -> 87, 20 -> 81, 46 -> 55, 93 -> 8, 57 -> 44, 78 -> 23, 29 -> 72, 84 -> 17, 61 -> 40, 89 -> 12, 1 -> 100, 74 -> 27, 6 -> 95, 60 -> 41, 85 -> 16, 28 -> 73, 38 -> 63, 70 -> 31, 21 -> 80, 33 -> 68, 92 -> 9, 65 -> 36, 97 -> 4, 9 -> 92, 53 -> 48, 77 -> 24, 96 -> 5, 13 -> 88, 41 -> 60, 73 -> 28, 2 -> 99, 32 -> 69, 34 -> 67, 45 -> 56, 64 -> 37, 17 -> 84, 22 -> 79, 44 -> 57, 59 -> 42, 27 -> 74, 71 -> 30, 12 -> 89, 54 -> 47, 49 -> 52, 86 -> 15, 81 -> 20, 76 -> 25, 7 -> 94, 39 -> 62, 98 -> 3, 91 -> 10, 66 -> 35, 3 -> 98, 80 -> 21, 35 -> 66, 48 -> 53, 63 -> 38, 18 -> 83, 95 -> 6, 50 -> 51, 67 -> 34, 16 -> 85, 31 -> 70, 11 -> 90, 72 -> 29, 43 -> ...
```

```scala
// sort by values
scala> val map = List.tabulate(100){x => (x+1, 100-x)}.toMap.toList.sortBy(_._2)
map: List[(Int, Int)] = List((100,1), (99,2), (98,3), (97,4), (96,5), (95,6), (94,7), (93,8), (92,9), (91,10), (90,11), (89,12), (88,13), (87,14), (86,15), (85,16), (84,17), (83,18), (82,19), (81,20), (80,21), (79,22), (78,23), (77,24), (76,25), (75,26), (74,27), (73,28), (72,29), (71,30), (70,31), (69,32), (68,33), (67,34), (66,35), (65,36), (64,37), (63,38), (62,39), (61,40), (60,41), (59,42), (58,43), (57,44), (56,45), (55,46), (54,47), (53,48), (52,49), (51,50), (50,51), (49,52), (48,53), (47,54), (46,55), (45,56), (44,57), (43,58), (42,59), (41,60), (40,61), (39,62), (38,63), (37,64), (36,65), (35,66), (34,67), (33,68), (32,69), (31,70), (30,71), (29,72), (28,73), (27,74), (26,75), (25,76), (24,77), (23,78), (22,79), (21,80), (20,81), (19,82), (18,83), (17,84), (16,85), (15,86), (1...
// sort by keys
scala> val map = List.tabulate(100){x => (x+1, 100-x)}.toMap.toList.sorted
map: List[(Int, Int)] = List((1,100), (2,99), (3,98), (4,97), (5,96), (6,95), (7,94), (8,93), (9,92), (10,91), (11,90), (12,89), (13,88), (14,87), (15,86), (16,85), (17,84), (18,83), (19,82), (20,81), (21,80), (22,79), (23,78), (24,77), (25,76), (26,75), (27,74), (28,73), (29,72), (30,71), (31,70), (32,69), (33,68), (34,67), (35,66), (36,65), (37,64), (38,63), (39,62), (40,61), (41,60), (42,59), (43,58), (44,57), (45,56), (46,55), (47,54), (48,53), (49,52), (50,51), (51,50), (52,49), (53,48), (54,47), (55,46), (56,45), (57,44), (58,43), (59,42), (60,41), (61,40), (62,39), (63,38), (64,37), (65,36), (66,35), (67,34), (68,33), (69,32), (70,31), (71,30), (72,29), (73,28), (74,27), (75,26), (76,25), (77,24), (78,23), (79,22), (80,21), (81,20), (82,19), (83,18), (84,17), (85,16), (86,15), (8...
```

Things you should know in the List api
- map
- filter
- foldLeft
- softBy
- ::
- size
- head
- tail
- reverse
- ++
- zip
- zipWithIndex
- intersect
- union
- slice
- indexOf
- indexWhere
- find
- contains
- maxBy
- minBy
- groupBy
- flatMap
- collect
- distinct


```scala
scala> val a = List(1, 2, 3, 4)
a: List[Int] = List(1, 2, 3, 4)

scala> val b = List("a", "b", "c", "d")
b: List[String] = List(a, b, c, d)

scala> a.zip(b)
res2: List[(Int, String)] = List((1,a), (2,b), (3,c), (4,d))

scala> a.zip(b).toMap
res3: scala.collection.immutable.Map[Int,String] = Map(1 -> a, 2 -> b, 3 -> c, 4 -> d)
```

```scala
// inefficient way to do something similar
(1 to 100).foldLeft(Map[Int, String]()){(a, b) => a ++ Map(b -> {"emp" + b})}

// long-winded zip with index
scala> val l = List(7, 5, 9, 2, 3)
l: List[Int] = List(7, 5, 9, 2, 3)

scala> l.zip((0 to l.size)).toList
res13: List[(Int, Int)] = List((7,0), (5,1), (9,2), (2,3), (3,4))

// slicing is exclusive of rhs

scala> val l = List(5, 4, 2, 1, 3)
l: List[Int] = List(5, 4, 2, 1, 3)

scala> l.slice(1, 3)
res15: List[Int] = List(4, 2)
```

Strings are just lists of characters.

```scala
scala> "fred".zipWithIndex.toMap
res20: scala.collection.immutable.Map[Char,Int] = Map(f -> 0, r -> 1, e -> 2, d -> 3)
```

Lookups on Maps
```scala
val m = Map(2 -> "foo", 3 -> "bar")
m(2) // => "foo"
m(4) // => exception
// so we don't do that. we use options instead.

m.get(2) // => Some("foo")
m.get(4) // => None
m.get(2).get // => "foo"

val opt = m.get(2)
if (opt.isDefined)
	opt.get
else
	println("no value")

// but there's a more succinct way

m.getOrElse(2, 0)
m.getOrElse(7, "defaultValue")
```

Lookups on Lists
```scala
val l = List(5, 4, 2, 1, 17)
l.contains(100) // expensive, returns true or false
l.indexOf
l.indexWhere
l.indexOf(100) // -1
l.indexWhere{x => x < 2}
l.find // returns option
```

```scala
case class Employee(age: Int, name: String, salary: Double)
val e = (1 to 1000).map{i => Employee(math.random * 10 + 30, "emp" + i, math.random * 40000 + 10000)}

// find the employee with the highest salary
e.sortBy{x:Employee => x.salary}.reverse.head
// simpler
e.maxBy{x => x.salary}

// count number of 35 year olds. filter and count. or use groupBy
e.groupBy(_.age).map{kv => (kv._1 -> kv._2.size)}
```

HINT: run a groupBy on the dataset to understand it

`reduce` is like `foldLeft` but doesn't start with an initial value. should write reduces that are associative - independent of order that elements are processed (prepare for distributed systems).

`foldLeft` lets you start with an element of an arbitrary type. `reduce` will always return the type of the data in the list, since that's all you operate on.

```scala
List(1, 2, 3).map{i => (1 to i)}.flatten
List(1, 2, 3).foldLeft(List[Int]()){(a, b) => a++(1 to b)}
List(1, 2, 3).flatMap{i => (1 to i)}
```

```scala
List(1, 2, 3, 4, 5).flatMap{i =>
	if (i % 2 == 1)
		Some(1 to i)
	else
		None
}
```

Collect
```scala
// allows you to pass only some members of a list through a partial function


def func(x: Int) = x + 2

List(1, 2, 3, 4, 5).collect {
	case x if (x % 2 == 0) =>
		x + 2
}

// might want to look up the PartialFunction class
```

Reading from a file
```scala
scala.io.Source
	.fromFile("smaller.txt")
	.getLines
	.toList
	.map{x: String => mkschema(x)}

// first thing you generally do is connote the strings into objects. whatever applies to the domain.

case dmpschema(user: String, url: String, rectype: Int, count: Int)

// function to turn strings into dmpschema objects

def mkschema(f: String) : dmpschema = {
	val arr = f.split(",")
	dmpschema(arr(0), arr(1), arr(2).toInt, arr(3).toInt)
}

val data = List[dmpschema]

// do a distinct on the user/url pairs
// group by user
// group by url
```

First thing we should all do is build the small matrix. Distinct urls by distinct users.

Take the list of `dmpschema` objects, map to url, call distinct. Done.

Optional tasks
1. Add score for each user/url
2. Histogram
	1. number of users who only clicked on n URLs
	2. count of users who clicked on each URL, sorted. top 100 or 1000.

```scala
// conversions 10x value over clicks
// clicks 10x over imps
if (obj.recType == 3) {
	obj.count * 100
else if...
else...
}
```

You'll end up with lists of elements and scores. Zip them together.
