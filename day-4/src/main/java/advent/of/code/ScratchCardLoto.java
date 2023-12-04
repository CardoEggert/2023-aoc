package advent.of.code;

import java.util.*;

public record ScratchCardLoto(List<ScratchCard> scratchCards) {

    public Integer getTotalScratchCardsCount() {
        int scratchCardCount = scratchCards.size();
        final List<ScratchCard> unprocessedScratchCards = new ArrayList<>(scratchCards);
        while (!unprocessedScratchCards.isEmpty()) {
            unprocessedScratchCards.sort(Comparator.comparingInt(ScratchCard::id));
            final ScratchCard processingScratchCard = unprocessedScratchCards.get(0);
            final List<ScratchCard> wonCopies = processingScratchCard.getWonCopies(getScratchCardsFromId(scratchCards, processingScratchCard.id()));
            final long countOfSameId = unprocessedScratchCards.stream().filter(scratchCard -> scratchCard.id() == processingScratchCard.id()).count();
            for (int sameIdIter = 0; sameIdIter < countOfSameId; sameIdIter++) {
                unprocessedScratchCards.addAll(wonCopies);
                scratchCardCount += wonCopies.size();
            }
            unprocessedScratchCards.removeIf(scratchCard -> scratchCard.id() == processingScratchCard.id());
        }
        return scratchCardCount;
    }

    private List<ScratchCard> getScratchCardsFromId(List<ScratchCard> scratchCards, int id) {
        return scratchCards.stream().filter(scratchCard -> scratchCard.id() > id).distinct().toList();
    }
}
