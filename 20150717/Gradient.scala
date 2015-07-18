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
