package algos.cannibals;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class MissionariesAndCannibals {
	
	public final int MAX_NUM;

    public MissionariesAndCannibals(int mAX_NUM) {
		this.MAX_NUM = mAX_NUM;
	}

	public List<MissionariesAndCannibalsState> successors(MissionariesAndCannibalsState mcs) {
        List<MissionariesAndCannibalsState> sucs = new ArrayList<>();
        if (mcs.isBoatOnWestBank) { // boat on the West bank
            if (mcs.wm > 1) {
                sucs.add(new MissionariesAndCannibalsState(mcs.wm - 2, mcs.wc, !mcs.isBoatOnWestBank, MAX_NUM));
            }
            if (mcs.wm > 0) {
                sucs.add(new MissionariesAndCannibalsState(mcs.wm - 1, mcs.wc, !mcs.isBoatOnWestBank, MAX_NUM));
            }
            if (mcs.wc > 1) {
                sucs.add(new MissionariesAndCannibalsState(mcs.wm, mcs.wc - 2, !mcs.isBoatOnWestBank, MAX_NUM));
            }
            if (mcs.wc > 0) {
                sucs.add(new MissionariesAndCannibalsState(mcs.wm, mcs.wc - 1, !mcs.isBoatOnWestBank, MAX_NUM));
            }
            if (mcs.wc > 0 && mcs.wm > 0) {
                sucs.add(new MissionariesAndCannibalsState(mcs.wm - 1, mcs.wc - 1, !mcs.isBoatOnWestBank, MAX_NUM));
            }
        } else { // boat on the East bank
            if (mcs.em > 1) {
                sucs.add(new MissionariesAndCannibalsState(mcs.wm + 2, mcs.wc, !mcs.isBoatOnWestBank, MAX_NUM));
            }
            if (mcs.em > 0) {
                sucs.add(new MissionariesAndCannibalsState(mcs.wm + 1, mcs.wc, !mcs.isBoatOnWestBank, MAX_NUM));
            }
            if (mcs.ec > 1) {
                sucs.add(new MissionariesAndCannibalsState(mcs.wm, mcs.wc + 2, !mcs.isBoatOnWestBank, MAX_NUM));
            }
            if (mcs.ec > 0) {
                sucs.add(new MissionariesAndCannibalsState(mcs.wm, mcs.wc + 1, !mcs.isBoatOnWestBank, MAX_NUM));
            }
            if (mcs.ec > 0 && mcs.em > 0) {
                sucs.add(new MissionariesAndCannibalsState(mcs.wm + 1, mcs.wc + 1, !mcs.isBoatOnWestBank, MAX_NUM));
            }
        }
        sucs.removeIf(Predicate.not(MissionariesAndCannibalsState::isLegal));
        return sucs;
    }

    public void displaySolution(List<MissionariesAndCannibalsState> path) {
        if (path.size() == 0)  // sanity check
            return;
        MissionariesAndCannibalsState oldState = path.get(0);
        System.out.println(oldState);
        for (MissionariesAndCannibalsState currentState : path.subList(1, path.size())) {
            if (currentState.isBoatOnWestBank) {
                System.out.printf("%d missionaries and %d cannibals moved from the east bank to the west bank.%n",
                        oldState.em - currentState.em,
                        oldState.ec - currentState.ec);
            } else {
                System.out.printf("%d missionaries and %d cannibals moved from the west bank to the east bank.%n",
                        oldState.wm - currentState.wm,
                        oldState.wc - currentState.wc);
            }
            System.out.println(currentState);
            oldState = currentState;
        }
    }
}