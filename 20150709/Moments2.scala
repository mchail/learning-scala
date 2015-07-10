import scala.io.Source

object Moments2 extends App {

	def mean(nums: List[Double]) : Double = {
		nums.sum / nums.size
	}

	def variance(nums: List[Double]) : Double = {
		val mu = mean(nums)

		mean(nums.map{x => math.pow(x - mu, 2)})
	}

	def skewness(nums: List[Double]) : Double = {
		val mu = mean(nums)
		val delta = variance(nums)

		mean(nums.map{x => math.pow((x - mu) / delta, 3)})
	}

	def kurtosis(nums: List[Double]) : Double = {
		val mu = mean(nums)
		val delta = variance(nums)

		mean(nums.map{x => math.pow((x - mu) / delta, 4)})
	}

	val nums = Source.fromFile("moment_data.txt").getLines().toList.map(x => x.toDouble)

	println("mean:")
	println(mean(nums))
	println("variance:")
	println(variance(nums))
	println("skewness:")
	println(skewness(nums))
	println("kurtosis:")
	println(kurtosis(nums))

}
