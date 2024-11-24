import scala.collection.mutable.ListBuffer

// Represents what all hands have in common
sealed abstract class Hand(val numOfCards: Int):
  protected val cards = ListBuffer[Card]()
  def cardToValueMapping: CardToValueFunction // deferred method

  final def addCard(card: Option[Card]) = // simple Template Method
    if(card.isDefined){
      var myCard = card.getOrElse(null)
      if cards.length < numOfCards && !cards.contains(card) then
        myCard.setCardValue(cardToValueMapping(myCard))
        cards += myCard
    }
  
  def cardValue(card: Card): Int =
    cardToValueMapping(card)
   
  
  def value: Int
    if cards.length < numOfCards then 0
      
  override def toString =
    val sortedCards = cards.sortBy(_.rank.ordinal)
    sortedCards.size match
      case 0 => ""
      case _ => sortedCards.init.foldLeft("")(_ + _.toString + ", ") + sortedCards.last.toString

/* Each concrete Hand subclass needs only to tell its superclass how many cards
   in a hand, and provide an implementation of the cardToValueMapping method. */

class ShadowHand extends Hand(ShadowHand.numOfCardsInHand):
  override def cardToValueMapping = ShadowCardValues

  def value: Int =
    //if cards.length < numOfCards then 0
    //else 
    cards.foldLeft(0)(_ + _.cardValue())

// Could have stored the cardToValueMapping here in the companion object as well.
object ShadowHand:
  val numOfCardsInHand = 5

class VisionHand extends Hand(VisionHand.numOfCardsInHand):
  override def cardToValueMapping = VisionCardValues

  override def value: Int =
    // Nested function. Makes code clearer.
    def median(cards: ListBuffer[Card]) =
      val (lower, upper) = cards.sortWith(_.cardValue() < _.cardValue()).splitAt(cards.size / 2)
      if (cards.size % 2 == 0) then
        (lower.last.cardValue() + upper.head.cardValue()) / 2
      else
        upper.head.cardValue()
    // With median defined, the body of value is easy to write:
    if cards.length < numOfCards then 0
    else median(cards)

object VisionHand:
  val numOfCardsInHand = 4

class WolfHand extends Hand(WolfHand.numOfCardsInHand):
  override def cardToValueMapping = WolfCardValues

  //refactor the computation of the value of a WolfHand to use a single foldLeft operation: Hint use a tuple
  override def value: Int =
    if cards.length < numOfCards then 0
    else
      val toople = cards.foldLeft(0, Int.MaxValue){ case ((bestSoFar, worstSoFar), card) => (bestSoFar max card.cardValue(), worstSoFar min card.cardValue())}
      toople(0) + toople(1)

object WolfHand:
  val numOfCardsInHand = 3
