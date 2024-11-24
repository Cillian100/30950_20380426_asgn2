import scala.collection.mutable.{ArrayBuffer, ListBuffer}

//A randomDeck takes a function as a class parameter with the following signature
// () => Int

class RandomDeck(var rng: () => Int = () => scala.util.Random.nextInt()) extends Deck{
  def setRng(newRng: () => Int): Unit = {
    rng = newRng
  }
  
  def dealCard(): Option[Card] =
    if !cards.isEmpty then 
      var index = rng() % cards.size
      Some(cards.remove(cards.size-1))
    else
      None
}

def randomDeckFunction(): Int = {
  val random = new scala.util.Random
  return random.nextInt(99)+1
}
