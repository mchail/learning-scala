Topics
- GD review

GD
- univariate
- regression
- k means

HW: Read

Leon Battius

# Homework

Find a paper called "SGD Tricks". NYU paper (CS PhD ML track)

http://research.microsoft.com/pubs/192769/tricks-2012.pdf

"parallelized SGD" being released by Dr. Jordan on Spark in one or two months. First time we have a parallelized SGD. Beating all previous benchmarks. 1960s math, but it's catching fire right now. Speeding up GD is a huge win.

Let's look at simple univariate function. Using this context to talk about "gradient ascent" (not **de**scent).

```
y = x^1/x

log y = (1/x)(log x)
(1/y)(dy/dx) = (1/x)(1/x) + (log x)(-1/x^2)
dy/dx = x^1/x((1 - log x) / x^2) = 0
1 - log x = 0
x = e
```

Ok, that was the math way. Now let's look at how to do it with gradient ascent.

Start with a guess. Find the slope at that guess. Notice as we climb up the curve, gradient becomes closer to 0. Keep doing this until we stabilize.

```
xnew = xguess + gradguess * trainingRate

^ notice this uses + instead of - since we're doing ascent now
```

Natural Gradient Descent (NGD) adds another multiplier to the training rate. Take the inverse hessian and find the max eigenvalue (wtf). Called the Second Order Gradient Descent.

```scala
def func(x: Double) = {
	math.pow(x, 1.0 / x)
}

def deriv(x: Double, f: Double => Double) = {
	val deltax = math.abs(x / 1000.0) // for production, might need if condition for x = 0 to make sure you still have a delta
	val x1 = x
	val x2 = x + deltax
}
```

```scala
(1 to 1000).foldLeft(xguess) {(acc, b) =>
	val xguess = acc
	xguess + deriv(sguess) * 0.01
}

// if you don't know how many steps it might take...
Iterator.continually
```

```
y = ax + b

a = covariance / variance
b = ymean - a * xmean
```

# Homework

`y = 2x^2 + 3x + 4`

```scala
x = (-10 to 10 by 0.5).toList
y = x.map{
	ax^2 + bx + c
}
```

Need to solve for three variables. (fa, fb, fc)

Play around with choosing different starting values. Play around with the x values you start with.

Gradient descent does well at linear lines, well at parabolas, not very well at cubic functions.

Pick any set of xs and any set of ys.

Use spark. Use mllib. There is a `GradientDescent` library that implements minibatchSGD. Should be a simple one-line function call. If you give it lots of xs and ys, it does a good job. Fewer points, worse job.

https://spark.apache.org/docs/latest/mllib-clustering.html#k-means
