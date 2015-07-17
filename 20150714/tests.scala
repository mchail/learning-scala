var sols = for {
	a <- (1 to 4)
	b <- (a to 4)
	c <- (b to 4)
} yield {
	List(a, b, c).sorted
}
println(sols.distinct.size)
