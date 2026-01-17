package practise

object Recurssion extends App{

  val n = 5

  def factorial(n:Int, acc:Int):Int ={
    if(n ==1){
      1
    } else {
      factorial(n-1, acc*n)
    }
  }

  println(factorial(n,1))

}
