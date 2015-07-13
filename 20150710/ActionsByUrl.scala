// scalac *.scala && scala -J-Xmx2g -cp . ClicksByUrl

import parser.Parser

object ClicksByUrl extends App {

	val data = new Parser dataAsList

	def topByAction(recType: Int) : Map[String, Int] = {
		data.groupBy(_.url).
		map{
			kv => (
				kv._1,
				kv._2.
					filter{y => y.recType == recType}.
					map(_.count).
					sum
			)
		}.
		toList.
		sortBy(_._2).
		slice(0, 100)
	}

	println("top URLs by impressions")
	println(frequencyByAction(1))
	println("top URLs by clicks")
	println(frequencyByAction(2))
	println("top URLs by conversions")
	println(frequencyByAction(3))

}
