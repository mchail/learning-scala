object EfronDice extends App {

	val maxRating = 10
	val gridSize = 3
	val winningScore = (gridSize * gridSize) / 2

	// returns count of comparisons that p1 "wins" over p2
	def comparePlayers(p1: List[Int], p2: List[Int]) = {
		p1.flatMap{x =>
			p2.map{y =>
				(x, y)
			}
		}.count{xy => xy._1 > xy._2}
	}

	val solutions = for {
		a <- (1 to maxRating)
		b <- (1 to maxRating)
		c <- (1 to maxRating)
		d <- (1 to maxRating)
		e <- (1 to maxRating)
		f <- (1 to maxRating)
		g <- (1 to maxRating)
		h <- (1 to maxRating)
		i <- (1 to maxRating)

		val p1 = List(a, b, c)
		val p2 = List(d, e, f)
		val p3 = List(g, h, i)

		val score1 = comparePlayers(p1, p2)
		val score2 = comparePlayers(p2, p3)
		val score3 = comparePlayers(p3, p1)

		if (score1 == score2)
		if (score2 == score3)
		if (score1 >= winningScore)
	} yield (p1, p2, p3)

	println(solutions.toList.size)

}
