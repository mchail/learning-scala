object EfronDice extends App {

	val gridSize = 3
	val maxRating = 10
	val winningScore = 5
	val minSum = 12

	// returns count of comparisons that p1 "wins" over p2
	def compare(a: Int, b: Int, c: Int, d: Int) = {
		List(d > a, d > b, d > c).count{x => x}
	}

	val solutions = for {
		a <- (1 to maxRating)
		b <- (1 to maxRating)
		c <- (math.max(minSum - (a + b), 1) to maxRating)

		d <- (1 to maxRating)
		val p2p1s1 = compare(a, b, c, d)
		e <- (1 to maxRating)
		val p2p1s2 = compare(a, b, c, e)
		if (p2p1s1 + p2p1s2 >= 2)
		f <- (math.max(minSum - (d + e), 1) to maxRating)
		val p2p1s3 = compare(a, b, c, f)

		val p2p1 = p2p1s1 + p2p1s2 + p2p1s3
		if (p2p1 >= winningScore)

		g <- (1 to maxRating)
		val p3p2s1 = compare(d, e, f, g)
		h <- (1 to maxRating)
		val p3p2s2 = compare(d, e, f, h)
		if (p3p2s1 + p3p2s2 >= 2)
		i <- (math.max(minSum - (g + h), 1) to maxRating)
		val p3p2s3 = compare(d, e, f, i)

		val p3p2 = p3p2s1 + p3p2s2 + p3p2s3
		if (p3p2 == p2p1)

		val p1p3 = compare(g, h, i, a) + compare(g, h, i, b) + compare(g, h, i, c)
		if (p1p3 == p2p1)
	} yield (a, b, c, d, e, f, g, h, i)

	println(solutions.toList.size)

}
