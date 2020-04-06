package funsets

/**
 * 求值表达式
 */
trait Expr {}

case class Number(num: Int) extends Expr {}

case class Sum(leftOp: Expr, rightOp: Expr) extends Expr {}

case class Prod(e1: Expr, e2: Expr) extends Expr {}

case class Var(x: String) extends Expr {}

object Expression {

  def eval(expr: Expr): Int = {
    expr match {
      case Number(n) => n
      case Sum(leftOp, rightOp) => eval(leftOp) + eval(rightOp)
    }
  }

  def show(expr: Expr): String = {
    expr match {
      case Number(n) => n.toString
      case Sum(leftOp, rightOp) =>
        "%s + %s".format(show(leftOp), show(rightOp))
      case Var(name) => name
      case Prod(e1, e2) =>
        "(%s) * (%s)".format(show(e1), show(e2))
    }
  }

  def main(args: Array[String]): Unit = {
    val expr = Prod(Sum(Var("x"), Number(2)), Number(3))
    // println(eval(expr))
    println(show(expr))
  }
}
