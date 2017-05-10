import Product.{Apple, Orange, Price}

trait Offer {
  def discount(products: Iterable[Product]): Price
}

object Offer {
  def buyOneGetOneFreeOnApples = BuyOneGetOneFreeOnApples()
  case class BuyOneGetOneFreeOnApples private () extends Offer {
    def discount(products: Iterable[Product]): Price =
      products.collect {
        case apple: Apple => apple
      }.zipWithIndex.filter {
        case (product, index) => index != 0 && (index + 1) % 2 == 0
      }.map(_._1.price).sum
  }
  def threeForTwoOnOranges = ThreeForTwoOnOranges()
  case class ThreeForTwoOnOranges private () extends Offer {
    def discount(products: Iterable[Product]): Price =
      products.collect {
        case orange: Orange => orange
      }.zipWithIndex.filter {
        case (product, index) => index != 0 && (index + 1) % 3 == 0
      }.map(_._1.price).sum
  }
}