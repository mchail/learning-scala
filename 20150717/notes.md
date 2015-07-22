1. ML background
2. ML via apache math
3. ML via mllib (spark-based)
4. HW help

### What is machine learning at Marin?

- optimization problems
- recommendation problems
- computer vision problems

Optimization
- margin-based bidding
- goal-based bidding
- position-based bidding

Constraint optimization. Convex curves and non-convex options.

Comparing one user vs another. Distance comparison, cosine comparison.

For a given set of users, find the set of other users that have similar URL history.

Computer vision
- second channel
- detect what ad is showing up on tv
- also show same ad on twitter/facebook/whatever

Preparing for ML
- Study calc2/calc3. Recommended Gilbert/Strang. Tells you what to do with concepts, what the applications are, rather than history/theory.
  - Gradients
  - directional derivatives (slope, integrals, derivatives, taylor series, approximations)
- Linear algebra
  - Strang book again is the best one
- Probability theory
  - Ross1, Ross2 (a {first,second} course in probability)
- Goal-based bidding algo: "Optimization by Vector Space Methods"
- Need some ML book as well. "Introduction to Machine Learning" by Alpaydin

Use mllib if using spark. If writing standard scala, use apache-math.

`scala -cp commonsmath.jar`

By end of lesson, know how to use gradient descent.

Gradient = partials in x, y, z space.
         = directional derivative

convex curve = bowl-shaped. easy to find minimum value of a convex curve.

want to find global minimum

1. gradient descence (total gradient descent = TGD)
1. SGD = stochastic gradient descent

Gradient descent applies to any n-dimensional curve.

GD steps

1. I have a point. x = 1
1. find the slope at x = 1
1. walk in the opposite direction of the slope
1. how far to walk? walk a little bit. now repeat.

Gradient = derivative for more than 1 dimension

```scala
def y(x: Double) = {x * x + 3 * x + 4}

def gradient(x: Double) = {
	val daltax = math.abs(x / 1000)
	(y(x) - y(x + deltax)) / deltax
}

def task(x: Double, trainingRate: Double) = {
	grad
}
```

f(x) = 1 + x + x^2/2 + x^3/6 ...

f(1) = 2.71828 = e

math.exp(1)

# HOMEWORK code up the series above. Need a factorial function. Think about how to write the factorial function to avoid integer overflow.

Come within 7 decimal points of `math.exp(1)`

```
xt = xt-1 - grad(xt-1) * tr
```

Best AI division right now is Facebook Paris. Followed by FB NY. Then Google. Then Uber.

y = -x^1/x

find minimum

```scala
// not quite right yet...
object Gradient extends App	{

	def y(x: Double) = {-math.pow(x, 1.0 / x)}

	def gradient(x: Double) = {
		val deltax = math.abs(x / 1000)
		(y(x + deltax) - y(x)) / deltax
	}

	def task(x: Double, trainingRate: Double) = {
		val slope = gradient(x)
		val newX = x - slope * trainingRate
		println(newX)
		newX
	}

	val sol = (1 to 100000).foldLeft(2.0) {
		(a, b) => task(a, 0.01)
	}

	println((sol, y(sol), gradient(sol)))

}
```

### OLS

- via total
- via SGD

For an OLS line, sloper is covariance / variance(x)

Line passes through mean of all the points. To find intercept, can use slope and the point (mux, muy).

```
y = ax + b
muy = a * muy + b
```

```
x   |   y
---------
x1  |  y1
x2  |  y2
x3  |  y3
x4  |  y4
... | ...
x10 | y10

want
y = ax + b

Cheating (statistical approach)

a = covar/varx
b = muy - a*mux
```

One of measure of how good a fit a line is: take the difference on the y-axis of each point vs the line

```
sqrt(d1^2 + d2^2 + d3^2 ...) / n

want to minimize this

Relating this to gradient...

LossFunction = J(a, b) = sqrt(a*x1 + b - y1)^2 + ...)/n

we know all variables but a and b. all the xs and ys are points of our dataset.

y = f(x)
xt = xt-1 - gradient * trainingRate
```

So now we're doing this same thing but over two variables.

```
at = at-1 - gradient * trainingRate

need first partial derivative of J for a and b

(fa) = (dJ/da)
(fb)   (dJ/db)
```

```
dJ/da = fa =

def gradJ(a: Double, b: Double) = {
	da = math.abs(a / 100.0)
	grada = (J(a, b) - J(a + da, b)) / da
	db = math.abs(b / 100.0)
	gradb = (J(a, b) - J(a, b + db)) / db
	(grada, gradb)
}
```

### SGD

Each time through the loop, randomly pick one point. Loop more times than the dataset. The expression to use is in the paper.

To pick a random point
```scala
util.shuffle(x.toList).head
// then get the corresponding y

// next iteration, shuffle again and pick a new (x, y)

// or
val idx = (math.random * x.size).toInt
(x(idx), y(idx))
```

### k-means clustering

Used all over the place. Computer vision, market segmentation, etc.

Easy for humans to do. More interesting still when you get to two-dimensions (or more).

Goal is to find number of clusters and the k-mean (centroid) of each cluster. Latter goal is more important.

1. pick an arbitrary number of clusters
1. say k = 3
1. first n points are the centroids
1. assign each point to the nearest cluster centroid
1. compute the centroids of each list as the new c-values
1. assign all values again
1. repeat until the centroids don't change

1. decide how many clusters you want = k
1. assign k points to k clusters
1. assign remaining to the closest of above
1. compute centroids
1. GOTO step 2

some empirical evidence that beyond k=7, results aren't useful to humans

k-means++ was discovered in 2007. available in commons-math.

convergence means the centroids don't change or change very little.

for multiple dimensions, the distance changes and the centroid calculation changes. distance in n-dimensions is easy. compute the centroid by averaging all the values in each dimension separately.

machine learning + computer vision = cognitive vision

k-means clustering to figure out object boundaries, then something else to figure out what the object is.
