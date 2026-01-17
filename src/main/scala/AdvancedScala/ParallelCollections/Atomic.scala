package AdvancedScala.ParallelCollections

import java.util.concurrent.atomic.AtomicInteger
import scala.collection.parallel.CollectionConverters.*

object Atomic {

  def main(args:Array[String]): Unit = {
    val counter = new AtomicInteger(0)

    (1 to 1000).par.foreach(_ => counter.incrementAndGet())

    println(counter.get())

    var counter1 = 0

    (1 to 1000).par.foreach(_ => counter1 = counter1 + 1)

    println(counter1)
  }

}
