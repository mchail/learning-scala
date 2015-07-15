# Homework: pick any 2

1. Rosenblatt Perception - Total Gradient
1. Rosenblatt Perception - SGD
1. K means (McQueen) - Total Gradient
1. K means (McQueen) - SGD
1. OLS - Total Gradient
1. OLS - SGD
1. LVQ2 - Total Gradient
1. LVQ2 - SGD

Focusing class today on OLS.

Describing "best fit" of a line through a bunch of points. Straight line - simple problem.

#### Margin-based bidding

MBB = (rev / conv) * (conv / clicks) * something * something

rpc = revenue per conversion  
cr = conversion rate

average over those values to get constants. plug those constants into MBB function. at peak season, rpc and cr go up. but that rise is limited. need appropriate lookback window to ensure that we don't put ourselves in a situation where we start reacting to the curve after it's already started going the other way (seasonal change).

if you understand the cycle, sometimes better to use a function instead. might just match a predictable polynomial. so you model with one of the options from the above list, then hopefully converge on a polynomial solution.

^ `@francois` I don't understand any of what I typed above.

```
y = ax + b

x     y
-------
-2   -4
-1   -1
 0    2
 1    5
 2    8
```

Will solve this using a statistical approach and a machine learning approach.

If it's a straight line, you can pick any two points and solve for a and b. You find the slope (a) then use any point (x, y) to solve for b.

Let's say we don't know that we have a straight line (might have some scatter plot), but we want to fit a straight line through it. Find best fit == minimize the cost function.

**OLS line ALWAYS passes through mean**

So let's find the mean for the x coordinates and the mean for the y coordinates.

```
μx = 0
μy = 2
```

So that gives us one point. OLS always passes through mean of data, so we have the first one. Now we need to find one more. We can do that by finding the slope.

`slope = covariance / variance`

"Intuitively, this makes a lot of sense." <- so says Krishnan. Slope and covariance are the same thing, except that you have a scaling factor, which is the variance of x.

```scala
// covariance fn
// E(xy) = E(x - μx)(y - μy)

x.zip(y).map{ij =>
	val (i, j) = ij
	(i - mux) * (j - muy)
}.sum / x.size
```

Krishnan says we can do a class on why `slope = covariance / variance`, but it devolves into a lot of partial derivatives. The room had no reaction to this.

```
w = (a)
    (b)

^ weight function

trying to solve this using matrices
y = ax + b
y = wt • x
^ dot product

wt = w transposed

cost = (E(y - wt * x)^2) / n
^ we want to minimize the cost function

y is a n by 1 matrix
w = 2 by 1
x = n by 2

x = [-2 1]
    [-1 1]
    [0  1]
    [1  1]
    [2  1]

you want to pick a direction where the slope is the steepest, and walk in that direction. that is called the gradient.

gradient = first partial of the cost function with respect to x and y.
```

Total Gradient = use all points. take average.

```
w1 = math.random = a
w2 = math.random = b

we start with random values, then go where the next point tells you to go. need to choose a step size / training rate (e.g. 0.01). might do 1000 iterations changing the weights.
```

```scala
// the minimize function is what changes the gradients
Iterator.continually{minimize}.take(1000)
```

Linear regression is called one-shot learning. It's a closed-form formula. The ML approach is not one-shot. There's some randomness that you want to converge.

```
y = 0.1x^2 + 2x + 3
want to fit an OLS

find Xs and find Ys
```

In math, the total gradient is typically called the "classical gradient". It's called TG in the paper passed out at the beginning of class.

Stochastic gradients are more susceptible to "bad" points than total gradients. They can be thrown off and will have to then correct. But over a long enough time span / large enough dataset, it will converge. **SGD >> Total Gradient.** SGD is much preferred. More practical to do in reality; you can use a sample of the full dataset and arrive at the same or nearly the same solution. This is explained in the paper.

Banana function (aka Rosenbrock function). Good test set.

We want to find global minimum, not local minimum. SGD will bounce you along local minima until you actually arrive at the global minimum. This is good; that's what we want to find. Looking at banana function will help with understanding this. Matlab will take forever trying to find global minimum of banana fn. It uses classic polynomial techniques, not ML. ML more efficient in cases like that.

SGD useful for online trends. TG useful for offline trends.

Hessian = second derivative determinate.

```
|fxx  fxy|
|fxy  fyy|

= fxx * fyy - (fxy)^2
= highest eigen of hessian = local minimum
```

Math has techniques developed from 1970s forward to solve this stuff. ML has a different set of techniques, and they're converging. Math guy can solve certain situations very quickly. ML has a procedure that applies to any curve.

#### Perception

Simple problem. I've got a bunch of points...I've got a bunch of other points...and they're separated by a line. Perception tries to find one line that nicely divides data.

#### K-means

K-means is, again, very easy (kquote). Clusters of data. K-means clustering turns out to be nothing but an SGD.
