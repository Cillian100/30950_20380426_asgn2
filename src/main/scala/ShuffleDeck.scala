import Rank.NoCard

import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import scala.util.Random

class ShuffleDeck extends Deck:
  def shuffle(noOfTimes: Int = 1) =
    for (num <- 1 to noOfTimes) do
      val shuffledCards = ArrayBuffer.fill(size)(Card(Rank.NoCard))
      for (i <- 0 to (size / 2) - 1) do
        shuffledCards(i * 2 + 1) = cards(i)
      if (cards.size % 2 == 0) then
        for (i <- size / 2 to size - 1) do
          shuffledCards(i * 2 - size) = cards(i)
      else
        for (i <- size / 2 + 1 to size - 1) do
          shuffledCards(i * 2 - size - 1) = cards(i)
        shuffledCards(size - 1) = cards(size / 2)
      cards = shuffledCards

  def dealCard(): Option[Card] =
    if !cards.isEmpty then
      Some(cards.remove(0))
    else
      None