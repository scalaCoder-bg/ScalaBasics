package practise

object SlidingWindow extends App {

  def lengthOfLongestSubstring(s: String): Int = {
    var left = 0
    var maxLength = 0
    var charMap = scala.collection.mutable.Map[Char, Int]()

    for (right <- s.indices) {
      println(s"Left $left")
      println(s"Right $right")
      println(s"CharMap $charMap")
      println(s"maxLength $maxLength")

      if (charMap.contains(s(right)) && charMap(s(right)) > left) {
        left = charMap(s(right)) + 1
      }

      charMap(s(right)) = right

      maxLength = Math.max(maxLength, right - left + 1)
    }

    maxLength
  }

  println(lengthOfLongestSubstring("abba"))

}
