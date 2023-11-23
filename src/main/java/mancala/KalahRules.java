package mancala;

public class KalahRules extends GameRules {
    
    @Override
    public int moveStones(final int startPit, final int playerNum) throws InvalidMoveException {
        if (playerNum == 1 && startPit >= 7//(stores.get(0).getOwner() == player && startPit >= 7 //&& startPit <= 12
            || playerNum == 2 && startPit <= 6) {
        throw new InvalidMoveException("Invalid move: Pit number is not allowed for the current player.");
        }
        if (getDataStructure().getNumStones(startPit) == 0){
            throw new InvalidMoveException("Invalid move: There are no stones in selected pit");
        }
        final int initialStoreCount = getDataStructure().getStoreCount(playerNum);

        distributeStones(startPit);
        return getDataStructure().getStoreCount(playerNum) - initialStoreCount;
    }
    @Override
    public int distributeStones(final int startingPoint){
        int player = whichPlayer(startingPoint);
        getDataStructure().setIterator(startingPoint, player, false);
        int currentIndex = getDataStructure().getIteratorPos();
        int stonesLeft = getDataStructure().removeStones(startingPoint);
        int total = stonesLeft;
        while (stonesLeft != 0){
            getDataStructure().next().addStone();
            stonesLeft--;
            currentIndex = getDataStructure().getIteratorPos();
        }
        if (currentIndex == 6 || currentIndex == 13){
            setExtraTurn(true);
        } else {
            setExtraTurn(false);
        }
        if ((player == 1 && currentIndex >= 0 && currentIndex <= 5 || player == 2 && currentIndex >= 7 && currentIndex <= 12)
            && getDataStructure().getNumStones(getPitNum(currentIndex)) == 1 && getDataStructure().getNumStones(13 - getPitNum(currentIndex)) != 0){
            int captureStones = captureStones(getPitNum(currentIndex));
            getDataStructure().addToStore(player, captureStones);
            //total += captureStones;
        }
        return total;
    }
    @Override 
    public int captureStones(final int stoppingPoint){
        final int oppositeIndex = 13 - stoppingPoint;
        return getDataStructure().removeStones(stoppingPoint) + getDataStructure().removeStones(oppositeIndex);
    }
}