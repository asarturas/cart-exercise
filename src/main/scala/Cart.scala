import Product.Price

trait SomeCart {
  def total: Price
}

case class Cart(products: Product*) extends SomeCart {
  def total: Price = products.map(_.price).sum
}