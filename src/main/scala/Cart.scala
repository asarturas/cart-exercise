import Product.Price

trait SomeCart {
  def total: Price
  def apply(offer: Offer): SomeCart
}

case class Cart(products: Product*) extends SomeCart {
  def total: Price = products.map(_.price).sum
  def apply(offer: Offer): CartWithOffers = CartWithOffers(this, Set(offer))
}

case class CartWithOffers(cart: Cart, offers: Set[Offer]) extends SomeCart {
  def total: Price = cart.total - offers.map(_.discount(cart.products)).sum
  def apply(offer: Offer): CartWithOffers = this.copy(offers = offers + offer)
}