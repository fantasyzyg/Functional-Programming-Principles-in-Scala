package recfun

object RecFun extends RecFunInterface {

  def main(args: Array[String]): Unit = {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(s"${pascal(col, row)} ")
      println()
    }
  }

  /**
   * Exercise 1
   *
   * @param c column
   * @param r row
   */
  def pascal(c: Int, r: Int): Int = {
    if (r == c || c == 0)
      1
    else
      pascal(c, r - 1) + pascal(c - 1, r - 1)
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    chars.filter(c => c == '(' || c == ')').foldLeft(0)((acc, c) => {
      if (acc < 0)
        acc
      else if (c == '(')
        acc + 1
      else
        acc - 1
    }) == 0
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0)
      1
    else if (coins.isEmpty)
      0
    else if (money >= coins.head)
      countChange(money, coins.tail) + countChange(money - coins.head, coins)
    else
      countChange(money, coins.tail)
  }

  /**
   * Tail call
   */
  @scala.annotation.tailrec
  def factorial(n: Int, result: Int): Int = {
    if (n == 0)
      result
    else
      factorial(n - 1, result * n)
  }

  /**
   * f: 值变换函数
   * combine：组合函数
   * 还需要一个区间
   */
  def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zeroValue: Int)(a: Int, b: Int): Int = {
    if (a > b)
      zeroValue
    else
      combine(f(a), mapReduce(f, combine, zeroValue)(a + 1, b))
  }

  /**
   * currying
   */
  def sum(f: Int => Int)(a: Int, b: Int): Int = mapReduce(f, (x, y) => x + y, 0)(a, b)

  def product(f: Int => Int)(a: Int, b: Int): Int = mapReduce(f, (x, y) => x * y, 1)(a, b)

  def fact(n: Int): Int = product(x => x)(1, n)


  /**
   * Fix Point algorithm
   */
  def fixedPoint(f: Double => Double, firstGuess: Double): Double = {
    val tolerance = 0.0001
    def isCloseEnough(x : Double, y: Double): Boolean = {
      Math.abs((x-y)/x) < tolerance
    }

    @scala.annotation.tailrec
    def iteration(guess: Double) : Double = {
      val next = f(guess)
      if (isCloseEnough(guess, next)) next
      else iteration(next)
    }

    iteration(firstGuess)
  }
}
