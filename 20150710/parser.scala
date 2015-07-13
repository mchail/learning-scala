package parser

import scala.io.Source

class Parser {

	case class DmpSchema(user: String, url: String, recType: Int, count: Int)

	def rowToDmpSchema(row: String) : DmpSchema = {
		val cells = row.split(',')
		DmpSchema(cells(0), cells(1), cells(2).toInt, cells(3).toInt)
	}

	def dataAsList : List[DmpSchema] = {
		Source.fromFile("smaller.txt").getLines.map(rowToDmpSchema(_)).toList
	}

}
