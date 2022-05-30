package reis.elvin;

public class Card {

   private CardType cardType;

   public Card(CardType cardType) {

    this.cardType = cardType;
   }

   public CardType getCardType() {
      
       return cardType;
   }

   @Override
   public String toString() {
       // TODO Auto-generated method stub
       return cardType.toString().charAt(0)+"";
   }
}
