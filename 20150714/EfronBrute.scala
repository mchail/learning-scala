object EfronBrute extends App {

	val max = 5

	def comparePlayers(p1: (Int, Int, Int), p2: (Int, Int, Int)) = {
		val (a, b, c) = p1
		val (d, e, f) = p2
		val diffs = List(
			(a, d), (a, e), (a, f),
			(b, d), (b, e), (b, f),
			(c, d), (c, e), (c, f)
		).map{kv =>
			val diff = kv._1 - kv._2
			if (diff == 0)
				0
			else
				diff / math.abs(diff)      // 1, -1
		}
		if (diffs.sum > 0)
			diffs.count{x => x == 1}
		else
			0
	}

	val solutions = for {
		a <- (1 to max)
		b <- (1 to max)
		c <- (1 to max)
		val p1 = (a, b, c)

		d <- (1 to max)
		e <- (1 to max)
		f <- (1 to max)
		val p2 = (d, e, f)
		val win1 = comparePlayers(p1, p2)
		if (win1 > 0)

		g <- (1 to max)
		h <- (1 to max)
		i <- (1 to max)
		val p3 = (g, h, i)

		val win2 = comparePlayers(p2, p3)
		val win3 = comparePlayers(p3, p1)

		if (win2 == win1)
		if (win3 == win2)
	} yield List(List(a, b, c), List(d, e, f), List(g, h, i))

	println(
		solutions.toList.map{x =>
			x.map{y =>
				y.sorted.mkString
			}
		}.map{y =>
			y.sorted.mkString
		}
	)

}
