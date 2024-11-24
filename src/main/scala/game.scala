
// Represents what all games have in common
sealed abstract class Game(protected val deck: ShuffleDeck):
  def hand1: Hand // deferred
  def hand2: Hand // deferred

  final def play() = // simple Template Method
    for i <- 1 to hand1.numOfCards do
      hand1.addCard(deck.dealCard())
      hand2.addCard(deck.dealCard())

  final def result: (Int, Int) = (hand1.value, hand2.value)

// Each concrete Game extends Game and defines its own type of Hand.

class ShadowGame(deck: ShuffleDeck) extends Game(deck):
  override val hand1 = ShadowHand()
  override val hand2 = ShadowHand()

class VisionGame(deck: ShuffleDeck) extends Game(deck):
  override val hand1 = VisionHand()
  override val hand2 = VisionHand()

class WolfGame(deck: ShuffleDeck) extends Game(deck):
  override val hand1 = WolfHand()
  override val hand2 = WolfHand()
