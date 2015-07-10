import scala.io.Source

object Moments extends App {

	type doubles = List[Double]

	def mean(nums: doubles) : Double = {
		nums.sum / nums.size
	}

	def variance(nums: doubles, meanFn: doubles => Double) : Double = {
		val mu = meanFn(nums)

		meanFn(nums.map{x => math.pow(x - mu, 2)})
	}

	def skewness(
		nums: doubles,
		meanFn: doubles => Double,
		varianceFn: (doubles, (doubles => Double)) => Double
	) : Double = {
		val mu = meanFn(nums)
		val delta = varianceFn(nums, meanFn)

		meanFn(nums.map{x => math.pow((x - mu) / delta, 3)})
	}

	def kurtosis(
		nums: doubles,
		meanFn: doubles => Double,
		varianceFn: (doubles, (doubles => Double)) => Double
	) : Double = {
		val mu = meanFn(nums)
		val delta = varianceFn(nums, meanFn)

		meanFn(nums.map{x => math.pow((x - mu) / delta, 4)})
	}

	val nums = Source.fromFile("moment_data.txt").getLines().toList.map(x => x.toDouble)

	println("mean:")
	println(mean(nums))
	println("variance:")
	println(variance(nums, mean))
	println("skewness:")
	println(skewness(nums, mean, variance))
	println("kurtosis:")
	println(kurtosis(nums, mean, variance))

}
