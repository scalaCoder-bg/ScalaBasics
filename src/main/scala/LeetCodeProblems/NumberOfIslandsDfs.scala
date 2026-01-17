package LeetCodeProblems

object NumberOfIslandsDfs extends App {

  val grid = Array(
    Array('1', '1', '1', '1', '0'),
    Array('1', '1', '0', '1', '0'),
    Array('1', '1', '0', '0', '0'),
    Array('0', '0', '0', '0', '0')
  )


  def numIslands(grid: Array[Array[Char]]): Int = {

    val rows = grid.length
    val cols = grid(0).length

    def dfs(r: Int, c: Int): Unit = {

      if (r < 0 || c < 0 || r >= rows || c >= cols || grid(r)(c) == '0') return

      grid(r)(c) = '0'


      dfs(r - 1, c)
      dfs(r + 1, c)
      dfs(r, c - 1)
      dfs(r, c + 1)
    }

    if (grid.isEmpty) {
      0
    } else {
      var counter = 0
      for (i <- 0 until rows; j <- 0 until cols) {
        if (grid(i)(j).toString == "1") {
          counter += 1
          dfs(i, j)
        }
      }
      println(s"Number of Islands present $counter")
      counter
    }
  }

  numIslands(grid)
}
