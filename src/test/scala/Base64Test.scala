import org.scalatest.{FunSuite, Matchers}

class Base64Test extends FunSuite with Matchers {

  val testTable: Seq[(String, String)] = Seq(
    ("45766964696e74", "RXZpZGludA=="),
    ("10", "EA=="),
    ("12AB34", "Eqs0"),
    ("8dbe92ce5cd7", "jb6SzlzX"),
    ("ebd4", "69Q=")
  )

  for ((input, expected) <- testTable) {
    test(s"encode should return $expected when passed $input") {
      Base64.encode(input) shouldEqual expected
    }
  }

}
