import org.scalatest.{FlatSpec, Matchers}

import Product._

class CartSpec extends FlatSpec with Matchers {
  it should "contain products" in {
    Cart(Apple(), Orange()).products should be(List(Apple(), Orange()))
  }
  it should "return a total of the cart" in {
    Cart(Apple(), Orange()).total should be(85)
  }
}
