package HigherOrderFunctions



object HigerOrderFuncsAdv extends App {

  // Write a function compose2 that takes two functions f and g and returns a new function f ∘ g (i.e. x => f(g(x))).

  def compose2[A](f: A => A, g: A => A): A => A = {
    (x: A) => f(g(x))
  }

  // Write a function andThen2 that composes two functions in the reverse order (apply f first, then g).
  def andThen2[A](f: A => A, g: A => A): A => A = {
    (x: A) => g(f(x))
  }

  //Write a function that takes a list of transformations (functions) and chains them together into a single function.

  def chainOfFunc[A](funcList: List[A => A], acc: A => A = (x: A) => x): A => A = {
    if (funcList.isEmpty) {
      acc
    } else {
      chainOfFunc(funcList.tail, (x: A) => funcList.head(acc(x)))
    }
  }

  def identity[A](x: A) = x

  def chainOfFuncUsingFold[A](funcList: List[A => A]): A => A = {
    if (funcList.isEmpty) {
      (x: A) => x
    } else {
      funcList.tail.foldLeft((x: A) => funcList.head(x)) {
        (acc, func) => func.compose(acc)
      }
    }
  }


}
