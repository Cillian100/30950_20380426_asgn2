// Stores the mapping from card to value. Each card game uses its own mapping.
//https://docs.scala-lang.org/tour/traits.html
import Rank.*

type CardToValueFunction = Card => Int

val ShadowCardValues: CardToValueFunction = card => card.rank.ordinal + 1

val VisionCardValues: CardToValueFunction = card => Card.numberOfCardRanks - card.rank.ordinal

val WolfCardValues: CardToValueFunction = card =>
  card.rank match
    case Jack => 3
    case Queen => 2
    case King => 1
    case _ => 0