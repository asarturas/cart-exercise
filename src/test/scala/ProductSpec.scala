import Product._
import org.scalatest.{FlatSpec, Matchers}

class ProductSpec extends FlatSpec with Matchers {
  it should "format the price" in {
    60.formatPrice should be("£0.60")
  }
  it should "return product from string" in {
    "Apple".toProduct should be(Some(Apple()))
  }
  it should "return list of products from list of strings" in {
    List("Apple", "Orange").toProductList should be(List(Apple(), Orange()))
  }
}