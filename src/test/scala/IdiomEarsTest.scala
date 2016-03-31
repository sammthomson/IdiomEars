import IdiomEars._
import cats.Applicative
import cats.std.all._
import cats.syntax.apply._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{Matchers, FlatSpec}

import scala.language.postfixOps


class IdiomEarsTest extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {
  val f = (a: Int) => (b: Int) => a * b
  val g = (a: Int) => (b: Int) => (s: String) => s + (a * b)

  behavior of "⊏| ... |⊐"

  it should "work for binary functions and Lists" in {
    forAll { (a: List[Int], b: List[Int]) =>
      val result = ⊏| (f) (a) (b) |⊐
      val expected = Applicative[List].pure(f).ap(a).ap(b)
      result should equal (expected)
    }
  }

  it should "work for ternary functions and Options" in {
    forAll { (a: Option[Int], b: Option[Int], s: Option[String]) =>
      val result = *| (g) (a) (b) (s) |*
      val expected = Applicative[Option].pure(g).ap(a).ap(b).ap(s)
      result should equal (expected)
    }
  }

  it should "nest" in {
    val F = Applicative[Option]
    forAll { (a: Option[Int], b: Option[Int]) =>
      val result = (  // ouch my eyes
          ⊏|
            (f)
            (a)
            (⊏| (f) (a) (b) |⊐)
          |⊐
        )
      val expected =
        F.pure(f)
            .ap(a)
            .ap(
              F.pure(f).ap(a).ap(b)
            )
      result should equal (expected)
    }
  }
}
