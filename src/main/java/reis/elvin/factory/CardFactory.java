package reis.elvin.factory;

import reis.elvin.Card;
import reis.elvin.CardType;

public class CardFactory {
    
    public static Card createCard(CardType cardType) {

        switch (cardType) {

            case CITIZEN:
                return new Card(CardType.CITIZEN);
            
            case EMPEROR:
                return new Card(CardType.EMPEROR);

            case SLAVE:
                return new Card(CardType.SLAVE);
                        
            default:
                return new Card(CardType.CITIZEN);
        }
    }
}
