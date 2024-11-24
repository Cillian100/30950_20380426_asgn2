import org.scalatest.funsuite.AnyFunSuite
import Rank.*

class StudentTests extends AnyFunSuite{
  test("Asgn2: Deck.dealCard Dealing one card from deck works correctly") {
    val deck = ShuffleDeck() // Deck -> ShuffleDeck renaming required in Part 5, but you should do it now
    deck.addCard(Card(Ten))
    deck.addCard(Card(Seven))
    deck.addCard(Card(Six))
    assert(deck.dealCard().get.rank === Ten)
    assert(deck.size === 2)
    assert(deck.toString === "Seven, Six")
  }

  test("Asgn2: ShadowHand.cardValue return correct values") {
    val hand = ShadowHand()
    assert(hand.cardValue(Card(Ace)) === 1)
  }

  test("Asgn2: WolfCardValues compute correctly") {
    assert(WolfCardValues(Card(Jack)) === 3)
  }

  test("Student Test Asgn2Part5: RandomDeck.dealCard Dealing deck in reverse order correct") {
    val deck = RandomDeck()
    deck.rng = () => deck.size - 1 // equates to always dealing the last card from the deck
    deck.addCard(Card(Ten))
    deck.addCard(Card(Seven))
    deck.addCard(Card(Six))
    assert(deck.dealCard().get.rank === Six)
    assert(deck.dealCard().get.rank === Seven)
    assert(deck.dealCard().get.rank === Ten)
    assert(deck.dealCard() === None)
  }

  test("Student Test: Asgn2Part5: RandomDeck.dealCard Dealing an empty deck has no effect") {
    val deck = RandomDeck(() => 0)
    val card = deck.dealCard()
    assert(card === None)
    assert(deck.size === 0)
    assert(deck.toString === "")
    assert(card.getOrElse("deck empty") === "deck empty")
  }
}