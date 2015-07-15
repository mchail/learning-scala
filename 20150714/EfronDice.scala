object EfronDice extends App {

	val max = 9

	def comparePlayers(p1: List[Int], p2: List[Int]) = {
		p1.flatMap{x =>
			p2.map{y =>
				(x, y)
			}
		}.count{xy => xy._1 > xy._2}
	}

	val solutions = for {
		a <- (1 to max)
		b <- (1 to max)
		c <- (1 to max)
		d <- (1 to max)
		e <- (1 to max)
		f <- (1 to max)
		g <- (1 to max)
		h <- (1 to max)
		i <- (1 to max)

		val p1 = List(a, b, c)
		val p2 = List(d, e, f)
		val p3 = List(g, h, i)

		val score1 = comparePlayers(p1, p2)
		val score2 = comparePlayers(p2, p3)
		val score3 = comparePlayers(p3, p1)

		if (score1 == score2)
		if (score2 == score3)
		if (score1 > max / 2)
	} yield ((a, b, c), (d, e, f), (g, h, i))

	println(solutions.toList.size)

}
