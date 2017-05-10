import Product.{Apple, Orange, Price}
import org.scalatest.{FlatSpec, Matchers}

class OfferSpec extends FlatSpec with Matchers {
  it should "calculate offer discount" in {
    val sample50off = new Offer {
      def discount(products: Iterable[Product]): Price = products.map(_.price / 2).sum
    }
    sample50off.discount(List(Apple(), Orange())) should be(42)
  }
  "buy one get one free on apples" should "discount every second apple" in {
    Offer.buyOneGetOneFreeOnApples.discount(List(Apple(), Orange(), Apple(), Orange(), Apple())) should be(60)
  }
  "three for two on oranges" should "discount every third orange" in {
    Offer.threeForTwoOnOranges.discount(List(Orange(), Orange(), Apple(), Orange(), Apple(), Orange())) should be(25)
  }
}