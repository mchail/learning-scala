// scalac *.scala && scala -J-Xmx2g -cp . Matrix

import parser.Parser

object Matrix extends App {

	val data = new Parser dataAsList

	val userCount = data.map(_.user).distinct.size
	val urlCount = data.map(_.url).distinct.size
	println("Total rows")
	println(data.size)
	println("Number of distinct users")
	println(userCount)
	println("Number of distinct urls")
	println(urlCount)
	println(s"Size of matrix = ${userCount}x${urlCount} = ${BigInt(userCount) * urlCount} cells")

	// List users to List of DmpSchema objects
	val groupedByUser = data.groupBy(_.user)

}
