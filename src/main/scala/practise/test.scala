package practise

object test extends App{

  val list = List(1,2,3,4,5)

  var previousElem = list.head
  var errorFlag = false

  for(i <- 1 until list.length){
    if(previousElem + 1 == list(i)){
      previousElem +=1
    } else {
      errorFlag = true
    }
  }

  if (errorFlag){
    println("Non consecutive")
  } else {
    println("consecutive")
  }

  def multiplrFunc(a:Int):Int ={
    a*2
  }

  val mulList = list.map(elem => multiplrFunc(elem))

  println(mulList)

  val stringList = List("2","4","6")



  def convertIntoInt(list: List[String]) = {
    val intList = list.map(_.toInt)
    var result = List.empty[Int]
    for(elem <- intList){
      result = result ++ List(multiplrFunc(elem))
    }
    result
  }

  def isAnagram(s: String, t: String): Boolean = {
    if(s.length == t.length) {
      val a = new Array[Int](s.length + 1)
      val charMap = s.groupBy(identity).view.mapValues(_.length).toMap
      val (isValid, chrMap) = t.foldLeft((true, charMap)){
        case((false, _), char) => (false, charMap)
        case((true, characterMap), char) => {
          characterMap.get(char) match {
            case Some(count) => {
              if(count < 1){
                (false, charMap)
              } else {
                (true, characterMap + (char -> (count - 1)))
              }
            }
            case _ => (false, charMap)
          }
        }

      }

      isValid

    } else {
      false
    }
  }

  def isAnagram2(s: String, t: String): Boolean = charCounts(s) sameElements charCounts(t)

  private def charCounts(str: String): Array[Int] = {
    val counts = Array.ofDim[Int](26)
    for (ch <- str) counts(ch - 'a') += 1
    counts
  }

  def frequencySort(s: String): String = {
    val a = 3 + 'a';
    ""
  }

  val a = 3 + 'a'

  println("Printling a")
  println(a)


  println(convertIntoInt(stringList))

  
  class Animal
  class Dog extends Animal
  class Cat extends Animal
  
  val oneDog = new Dog()
  val twoDog = new Dog()
  val cat = new Cat()
  val a = List(oneDog, twoDog)
  val b = List(cat)
  
  val complexList = List(List(1,2),List(4,3), List(7,8))
  








}
