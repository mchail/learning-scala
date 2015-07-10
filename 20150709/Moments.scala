import scala.io.Source

object Moments extends App {

	def mean(nums: List[Double]) : Double = {
		nums.sum / nums.size
	}

	def variance(nums: List[Double], meanFn: List[Double] => Double) : Double = {
		val mu = meanFn(nums)

		meanFn(nums.map{x => math.pow(x - mu, 2)})
	}

	def skewness(
		nums: List[Double],
		meanFn: List[Double] => Double,
		varianceFn: (List[Double], (List[Double] => Double)) => Double
	) : Double = {
		val mu = meanFn(nums)
		val delta = varianceFn(nums, meanFn)

		meanFn(nums.map{x => math.pow((x - mu) / delta, 3)})
	}

	def kurtosis(
		nums: List[Double],
		meanFn: List[Double] => Double,
		varianceFn: (List[Double], (List[Double] => Double)) => Double,
		skewnessFn: (List[Double], (List[Double] => Double), (List[Double], (List[Double] => Double)) => Double) => Double
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
	println(kurtosis(nums, mean, variance, skewness))

}
