package objsets

trait Fixture {
  val one = new Succ(Zero)
  val two = new Succ(one)
}

object Test {
  def main(args: Array[String]): Unit = {
    new Fixture {
      assert(Zero.successor == one)
    }

    new Fixture {
      assert(one.successor == two)
    }

    new Fixture {
      assert((one + Zero) == one)
    }

    new Fixture {
      assert((one + one) == two)
    }
  }
}