// scalac *.scala && scala -J-Xmx2g -cp . HistogramByCount

import parser.Parser

object HistogramByCount extends App {

	val data = new Parser dataAsList

	def frequencyByAction(recType: Int) = {
		data.groupBy(_.user).
		map{kv => (kv._1, kv._2.filter{y => y.recType == recType}.size)}. // map each user to number of sites clicked on
		values.
		groupBy(x => x).map{kv => (kv._1, kv._2.size)}. // frequency of click counts
		toList.
		sortBy(_._1)
	}

	println("number of users who saw impressions for n URLs")
	println(frequencyByAction(1))
	println("number of users who clicked on n URLs")
	println(frequencyByAction(2))
	println("number of users who converted on n URLs")
	println(frequencyByAction(3))

}
