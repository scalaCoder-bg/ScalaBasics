package LeetCodeProblems

object PascalsTriangle extends App {
  def generate(numRows: Int): List[List[Int]] = {
    val grid = Array.ofDim[Int](numRows, numRows)
    for (i <- 0 until numRows) {
      for (j <- 0 until i + 1) {
        if (i == j || j == 0) {
          grid(i)(j) = 1
        } else {
          grid(i)(j) = grid(i - 1)(j - 1) + grid(i - 1)(j)
        }
      }
    }
    grid.zipWithIndex.map { case (row, i) =>
      row.take(i + 1).toList
    }.toList
  }

  println(generate(5))
}
