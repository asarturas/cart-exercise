sealed trait Product {
  def price: Product.Price
}

object Product {

  final case class Apple() extends Product {
    val price: Int = 60
  }

  final case class Orange() extends Product {
    val price: Int = 25
  }

  type Price = Int

  implicit class FormatPrice(value: Price) {
    def formatPrice: String = {
      val poundValue = value.toDouble / 100
      f"£$poundValue%.2f"
    }
  }

  implicit class StringToProduct(value: String) {
    def toProduct: Option[Product] = value match {
      case "Apple" => Some(Apple())
      case "Orange" => Some(Orange())
      case _ => None
    }
  }

  implicit class ListOfStringsToListOfProducts(value: List[String]) {
    def toProductList: List[Product] = value.flatMap(_.toProduct)
  }

}