package practise

object Main{

  def main(args: Array[String]): Unit = {
     println("Hello World")

    val func = new MyFunction[Int,String] {
      def apply(myVar:Int):String = {
        s"Passed String ${myVar}"
      }
    }
    println(func(2))
  }

}
