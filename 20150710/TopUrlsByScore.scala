// scalac *.scala && scala -J-Xmx2g -cp . TopUrlsByScore

import parser.Parser

object TopUrlsByScore extends App {

	val data = new Parser dataAsList

	def score(rows: List[parser.Parser#DmpSchema]) : Int = {
		rows.map{row =>
			math.pow(10, row.recType) * row.count
		}.sum.toInt
	}

	val topUrls = data.
		groupBy(_.url).
		map{kv => (kv._1, score(kv._2))}.
		toList.
		sortBy(-_._2).
		slice(0, 100)

	println("Top URLs")
	println(topUrls)

}
