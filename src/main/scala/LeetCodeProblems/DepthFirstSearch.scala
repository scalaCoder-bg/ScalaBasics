package LeetCodeProblems

object DepthFirstSearch extends App {

  def dfs(graph: Map[String, List[String]], start: String, visited: Set[String] = Set()): Unit = {
    if (!visited.contains(start)) {
      val newVisited = visited + start
      println(start)
      for (neighbour <- graph.getOrElse(start, Nil)) {
        dfs(graph, neighbour, newVisited)
      }
    }
  }

  val graph = Map(
    "A" -> List("B", "C"),
    "B" -> List("D", "E"),
    "C" -> List("F"),
    "D" -> Nil,
    "E" -> Nil,
    "F" -> Nil
  )
  println("DFS Traversal:")
  dfs(graph, "A")

}
