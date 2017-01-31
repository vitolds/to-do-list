package lv.javaguru.java2.service;

import lv.javaguru.java2.domain.Task;
import lv.javaguru.java2.domain.User;

public interface CoinService {

    void addCoinsToUser(Task task);

    void removeCoinsFromUser(Task task);

    void buyTaskSlots(User user, int slots);

}
