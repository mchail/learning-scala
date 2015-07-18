// object MultiVariableGradient extends App {

	val x = (-10.0 to 10.0 by 0.5).toList
	val y = x.map{a => 2.0 * a + 3}

	def gradJ(a: Double, b: Double) = {
		val deltaa = math.abs(a / 100.0)
		val grada = (J(a, b) - J(a - deltaa, b)) / deltaa
		val deltab = math.abs(b / 100.0)
		val gradb = (J(a, b) - J(a, b - deltab)) / deltab
		(grada, gradb)
	}

	def J(a: Double, b: Double) = {
		math.sqrt(x.zip(y).foldLeft(0.0){(m, n) =>
			val (xi, yi) = n
			m + math.pow((a * xi + b - yi), 2)
		}) / x.size
	}

	def task(a: Double, b: Double, trainingRate: Double) = {
		val (grada, gradb) = gradJ(a, b)
		val newa = a - grada * trainingRate
		val newb = b - gradb * trainingRate
		println((newa, newb))
		(newa, newb)
	}


	val sol = (1 to 5000).foldLeft((1.0, 1.0)) { (m, n) =>
		val (a, b) = m
		task(a, b, 0.01)
	}

	println(sol)

// }
