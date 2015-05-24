package org.thephilz.riddles.bulb;

import org.thephilz.riddles.bulb.furniture.Room;
import org.thephilz.riddles.bulb.participants.Participant;
import org.thephilz.riddles.bulb.participants.Participants;
import org.thephilz.riddles.bulb.participants.ParticipantsSupplier;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 */
public class RiddleInstance {

    protected final Room room;
    protected final Participants participants;

    protected RiddleInstance(Room room, int noParticipants) {
        assert noParticipants > 0;

        this.room = room;
        this.participants = new Participants(noParticipants);
    }

    static class ParticipantProxy implements Participant {
        protected final Participant proxied;
        protected int timesWentToRoom;
        protected int timesAnsweredFalse;
        protected int timesAnsweredTrue;

        public ParticipantProxy(Participant proxied) {
            this.proxied = proxied;
            this.timesWentToRoom = 0;
            this.timesAnsweredFalse = 0;
            this.timesAnsweredTrue = 0;
        }


        @Override
        public void goToRoom(Room theRoom) {
            proxied.goToRoom(theRoom);
            this.timesWentToRoom++;
        }

        @Override
        public boolean hasEverybodyBeen() {
            boolean answer = proxied.hasEverybodyBeen();
            if (answer) {
                this.timesAnsweredTrue++;
            } else {
                this.timesAnsweredFalse++;
            }
            return answer;
        }
    }

    public RiddleResults solve() {
        final ArrayList<ParticipantProxy> proxies = new ArrayList<>();
        Stream.generate(new ParticipantsSupplier(this.participants))
                .map(
                    participant -> {
                        ParticipantProxy proxy = new ParticipantProxy(participant);
                        proxies.add(proxy);
                        return proxy;
                    }
                )
                .anyMatch(
                        participant -> {
                            participant.goToRoom(room);
                            return participant.hasEverybodyBeen();
                        }
                );
        return new RiddleResults();
    }

}
