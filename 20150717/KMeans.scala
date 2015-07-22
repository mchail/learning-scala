object KMeans extends App {

	val clusterCount = 3

	def assign(nums: List[Int], centroids: List[Double]) : List[List[Int]] = {
		println(centroids)
		val clusterIndex = nums.map{n =>
			centroids.map{c =>
				math.abs(c - n)
			}
			.zipWithIndex
			.sortBy(_._1)
			.head._2
		}.toSeq

		nums
			.zip(clusterIndex)
			.groupBy(_._2)
			.values
			.map{listOfTuples =>
				listOfTuples.map(_._1)
			}.toList
	}

	def findCentroids(numLists: List[List[Int]]) = {
		numLists.map{nums =>
			nums.sum.toDouble / nums.size
		}.sorted
	}

	val nums = List[Int](1, 3, 2, 5, 6, 8, 2, 2, 2)

	val initialCentroids = nums.slice(0, clusterCount).map(_.toDouble)
	val finalCentroids = (0 to 100).foldLeft(initialCentroids){(centroids, n) =>
		val clusters = assign(nums, centroids)
		// println(clusters)
		val newCentroids = findCentroids(clusters)
		println(newCentroids)
		newCentroids
	}

	println(finalCentroids)
	
}
