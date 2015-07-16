object EfronDice extends App {

	val gridSize = 3
	val maxRating = 10
	val winningScore = 5
	val minSum = 12

	// returns count of comparisons that p1 "wins" over p2
	def comparePlayers(p1: (Int, Int, Int), p2: (Int, Int, Int)) = {
		// val a = p1(0)
		// val b = p1(1)
		// val c = p1(2)
		// val d = p2(0)
		// val e = p2(1)
		// val f = p2(2)
		val (a, b, c) = p1
		val (d, e, f) = p2
		List((a, d), (a, e), (a, f), (b, d), (b, e), (b, f), (c, d), (c, e), (c, f)).count{xy => xy._1 > xy._2}
	}

	val solutions = for {
		a <- (1 to maxRating)
		b <- (1 to maxRating)
		c <- (math.max(minSum - (a + b), 1) to maxRating)
		val p1 = (a, b, c)

		d <- (1 to maxRating)
		e <- (1 to maxRating)
		f <- (math.max(minSum - (d + e), 1) to maxRating)
		val p2 = (d, e, f)

		val score1 = comparePlayers(p1, p2)
		if (score1 >= winningScore)

		g <- (1 to maxRating)
		h <- (1 to maxRating)
		i <- (math.max(minSum - (g + h), 1) to maxRating)
		val p3 = (g, h, i)

		val score2 = comparePlayers(p2, p3)
		if (score1 == score2)

		val score3 = comparePlayers(p3, p1)
		if (score2 == score3)
	} yield (p1, p2, p3)

	println(solutions.toList.size)

}
