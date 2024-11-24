enum Rank(val value: Int):
  case Ace extends Rank(1)
  case Two extends Rank(2)
  case Three extends Rank(3)
  case Four extends Rank(4)
  case Five extends Rank(5)
  case Six extends Rank(6)
  case Seven extends Rank(7)
  case Eight extends Rank(8)
  case Nine extends Rank(9)
  case Ten extends Rank(10)
  case Jack extends Rank(11)
  case Queen extends Rank(12)
  case King extends Rank(13)
  case NoCard extends Rank(0)

case class Card(val rank: Rank):
  override def toString: String = rank.toString

  def cardValue(): Int = {
    return rank.value
  }

  def setCardValue(willy: Int): Unit = {
    rank.->(willy)
  }

object Card:
  val numberOfCardRanks = 13
  
//value < is not a member of Card, but could be made available as an extension method.