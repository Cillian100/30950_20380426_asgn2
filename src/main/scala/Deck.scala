import scala.collection.mutable.{ArrayBuffer, ListBuffer}

class Deck {
  var cards = ArrayBuffer[Card]()

  def addCard(card: Card) =
    if !cards.contains(card) then
      cards += card

  override def toString: String =
    cards.size match
      case 0 =>  ""
      case _ => cards.mkString(", ")

  def size = cards.size
}
