One common way to mess with collections is `take` and `drop`.

- take
- drop
- takeWhile
- dropWhile

```scala
val k = (1 to 100).toList
	.take(5)

val k = (1 to 100).toList
	.takeWhile(x => x < 10)

	.drop(5) // (6 to 100)
	.dropWhile{k => k < 6} // (6 to 100)
```

`take` and `drop` are ways to get from a list to a smaller list.

Let's say I want to perform a task n times. In most languages, you use a for loop. In scala, you use an `Iterator`.

```scala
Interator.continually{task}.take(n)

def task = {println("hello")}
Iterator.continually{task}.take(5).toList

// take returns another iterator
```

```scala
Iterator.continually{task} // "task" is a side-effect task

Iterator.iterate(10){k => k + 2}.takeWhile{k => k < 20}

// fibonacccccci
Iterator.iterate((0, 1)){t => (t._1 + t._2, t._1)}.take(10).toList.map{_._1}
List.iterate((0, 1), 10){f => (f._1 + f._2, f._1)}.map(_._1)
Seq.iterate
```

Shell scripting
```scala
import sys.process._
"ls".!
```

for-comprehensions (generators)
```scala
for {
	x <- (1 to 10)
	y <- (2 to 5)
	if (x * y < 25)
} yield (x / y)
```
You can think of these as "constraint-solvers".

You can have any number of constraints. Any number of variables. Rewrite the above as a flatmap.

```scala
(1 to 10).toList
	.flatMap{x =>
		val y = (2 to 5).toList
		if (x * y < 25)
			Some(x / y)
		else
			None
	}
	.zip()
```

find all pythagoreans
```scala
val ans = for {
	x <- (1 to 19)
	y <- (1 to 19)
	val z = math.sqrt(x * x + y * y).toInt
	if (x * x + y * y == z * z)
} yield ((x, y, z))
```

# Homework

1. Using for-comps (and any other scala), come up with a list of Efron dice.


```
            js        java        data/sql
Tom          1           4             7
Dick         3           3             6
Harry        2           5             5
```

Tom vs Dick
```
1-3     Tom Lost, Dick Wins
1-3     L
1-6     L
4-3     W
4-3     W
4-6     L
7-3     W
7-3     W
7-6     W
```

Tom beats Dick 5/9  
Dick beats Harry 5/9  
Harry beats Tom 5/9

This is one example of efron dice. The winners are looped... And all with the same probability.

```
Find all such
(a b c)
(d e f)
(g h i)
where the above properties are true
scale for all numbers is 1 to 10
```

Last scala topic: try-catch

```scala
import scala.util.{Try, Success, Failure}

val x = Try{func}
val x = Try{"cd foo"!}

x match {
	case Success(v) => v.whatever
	case Failure(v) => v.whatever
}
```

Useful scala libraries to know
1. Spark (data science)
2. scalajs (frontend)
3. scalatra (small web framework), play (larger web framework) (backend)
4. colt, apache math (commons-math) // math stuff
5. deeplearning4j (large-scale neural nets)

Write more memory-efficient scala
0. `-Xmx2g -Xms2g`
1. look into Stream (lazy list)
2. use spark

Check out the Stream implementation of fibonacci sequence. http://stackoverflow.com/a/9864521/1529781

Big data data structure: RDD. Can contain any of the scala collections. An abstraction over those DSs? There are some differences. I.e. you might not want to do a `size`. But there's a method called `count`.

Resilient Distributed Dataset = only important big data DS.

Distributed = no ordering.

1. what are the most frequent operations in RDD
2. how to go from bigdata <=> smalldata (and when it's possible/reasonable)

```scala
sc.parallelize((1 to 1000).toList) // sc = spark context. needs to be run in the spark shell.
                                   // the parallelize operation goes from small to big data
```

Read file: `sc.textfile("foo.txt")`

Basics:
1. `sc` is the spark context
2. `rdd` is very much like a list
3. nothing is ordered

# Homework: read a dmp access log in spark

-------------------

Optimizations
- hardcode integer comparisons.
- check for win between two players as early as possible - before declaring additional generators.
- in minimum solution, sum of scores for player is 12. no set of scores with sum below 12 will lead to solution.
- compare each subsequent rating individually (vs two sets of three numbers all at once).
	- need at least two wins by the second number chosen.
- using Tuples for player data structure seem to be much faster than using Lists.
- can't have a player with two 1s or two 10s. (not implemented)
