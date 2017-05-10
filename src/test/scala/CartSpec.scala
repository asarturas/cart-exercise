import org.scalatest.{FlatSpec, Matchers}

import Product._

class CartSpec extends FlatSpec with Matchers {
  it should "contain products" in {
    Cart(Apple(), Orange()).products should be(List(Apple(), Orange()))
  }
  it should "return a total of the cart" in {
    Cart(Apple(), Orange()).total should be(85)
  }
  it should "apply an offer" in {
    val sample15pOff = new Offer {
      def discount(products: Iterable[Product]): Price = 15
    }
    Cart(Apple(), Orange()).apply(sample15pOff) should be(CartWithOffers(Cart(Apple(), Orange()), Set(sample15pOff)))
  }
  it should "apply multiple offers" in {
    val sample15pOff = new Offer {
      def discount(products: Iterable[Product]): Price = 15
    }
    val sample20percentOffOranges = new Offer {
      def discount(products: Iterable[Product]): Price =
        products.collect { case orange: Orange => orange }.map(_.price / 5).sum
    }
    Cart(Apple(), Orange()).apply(sample15pOff).apply(sample20percentOffOranges) should
      be(CartWithOffers(Cart(Apple(), Orange()), Set(sample15pOff, sample20percentOffOranges)))
  }
  it should "not apply the same offer more than once" in {
    val sample15pOff = new Offer {
      def discount(products: Iterable[Product]): Price = 15
    }
    Cart(Apple(), Orange()).apply(sample15pOff).apply(sample15pOff) should
      be(CartWithOffers(Cart(Apple(), Orange()), Set(sample15pOff)))
  }
}
