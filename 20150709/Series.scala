import java.io.PrintWriter

object Series extends App {

	def seriesGenerator(a: Int, d: Int) : List[Int] = {
		(0 to d).map(a + _ * 7).toList
	}

	def writeSeriesToFile(a: Int, d: Int, pSeriesGenerator: (Int, Int) => List[Int], pFileWriter: List[Int] => Unit) = {
		pFileWriter(pSeriesGenerator(a, d))
	}

	def fileWriter(lines: List[Int]) = {
		val pw = new PrintWriter("seriesOutput.txt")
		lines.foreach(x => pw.println(x))
		pw.flush
		pw.close
	}

	println("For the series [a, a + d, a + 2d, ...], supply a:")
	val a = readInt
	println("Supply d:")
	val d = readInt

	writeSeriesToFile(a, d, seriesGenerator, fileWriter)

}
