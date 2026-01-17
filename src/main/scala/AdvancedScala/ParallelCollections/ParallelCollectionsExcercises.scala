package AdvancedScala.ParallelCollections

import java.util.concurrent.atomic.{AtomicInteger, AtomicReference}
import java.util.concurrent.{Executors, ForkJoinPool}
import scala.collection.parallel.CollectionConverters.*
import scala.collection.parallel.{ExecutionContextTaskSupport, ForkJoinTaskSupport}
import scala.concurrent.ExecutionContext

object ParallelCollectionsExcercises extends App{

  val normalList = (1 to 1000).toList

  val parllelList = (1 to 1000).toList.par

  parllelList.tasksupport = new ExecutionContextTaskSupport(
    ExecutionContext.fromExecutor(
      Executors.newFixedThreadPool(10)
    )
  )

  val normalTime = measure {
    normalList.map{
      case num => {
        val x = 54
        num + 1
      }
    }
  }

  println(s"Normal Time $normalTime")

  val parallelTime = measure {
    parllelList.map{
      case num => {
        val x = 54
        num + 1
      }
    }
  }

  println(s"Parallel time $parallelTime")



  def measure[T](operation: =>T): Long = {
    val startTime = System.currentTimeMillis()
    operation
    System.currentTimeMillis() - startTime
  }


}
