import java.io.PrintWriter

object MyPgm extends App {

	def mean(nums: List[Double]) : Double = {
		nums.sum / nums.size
	}

	def variance(nums: List[Double], meanFn: List[Double] => Double) = {
		val mu = meanFn(nums)
		meanFn(nums.map{x => math.pow(x - mu, 2)})
	}

	def write(lines: List[Double]) = {
		val pw = new PrintWriter("foo.txt")
		lines.foreach(x => pw.println(x))
		pw.flush
		pw.close
	}

	val nums = (1 to 5).map{x =>
		println("next number:")
		readDouble
	}.toList

	// println("Average:")
	// println(mean(nums))
	// println("Variance:")
	// println(variance(nums, mean))
	write(List(mean(nums), variance(nums, mean)))

}
