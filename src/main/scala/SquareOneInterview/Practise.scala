package SquareOneInterview

import java.util.concurrent.Executors
import scala.concurrent.{Await, ExecutionContext, ExecutionContextExecutor, Future, blocking}
import scala.concurrent.duration.*
import scala.concurrent.ExecutionContext.Implicits.global

object Practise {
  def function1(n: Int): Future[Int] =
    Future {
      Thread.sleep(4000)
      n + 2
    }

  def function2(n: Int): Future[Int] = Future {
    println("function2 start")
    println("function2 end")
    Thread.sleep(4000)
    n * 5
  }

  def function3(n: Int): Future[Int] = Future {
    println("function3 start")
    println("function3 end")
    Thread.sleep(4000)
    n * n
  }


  def funcComposition(n: Int) = {
    for {
      value1 <- function1(n)
      value2 <- function2(value1)
      value3 <- function3(value2)
    } yield value3
  }
  
  println(Await.result(funcComposition(5), 20.seconds))

}
    
