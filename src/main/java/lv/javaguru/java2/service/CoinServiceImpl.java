package lv.javaguru.java2.service;

import lv.javaguru.java2.database.springJPA.UserRepository;
import lv.javaguru.java2.domain.Task;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Component
@Transactional
public class CoinServiceImpl implements CoinService{

    private final float FIRST_PRIORITY_COINS = 1;
    private final float SECOND_PRIORITY_COINS = 2;
    private final float THIRD_PRIORITY_COINS = 3;
    private final float MAIN_TASK_COINS = 4;
    private final int TASK_SLOT_PRICE = 50;

    @Autowired
    UserRepository userRepository;

    @Override
    public void addCoinsToUser(Task task) {
        float coins = calculateCoins(task);
        User user = task.getUser();
        task.setCoins(coins);
        user.setCoins(user.getCoins() + coins);
    }

    @Override
    public void removeCoinsFromUser(Task task) {
        User user = task.getUser();
        float userCoins = user.getCoins();
        float taskCoins = task.getCoins();
        task.setCoins(0);
        user.setCoins(userCoins - taskCoins);
    }

    private float calculateCoins(Task task){
        float timeCoef;
        try {
            long doneDatetime = new Timestamp(System.currentTimeMillis()).getTime();
            long deadline = task.getDeadline().getTime();
            long creationDatetime = task.getCreationDateTime().getTime();
            long totalTimeForTask = deadline - creationDatetime;
            long timeDiff = doneDatetime - creationDatetime;

            timeCoef = 1 - (float) timeDiff / totalTimeForTask;
            System.out.println(timeCoef);
        }
        catch (Exception e) {
            timeCoef = 0;
        }

        if (task.isMainTask()) {
            return MAIN_TASK_COINS * (1 + timeCoef);
        }

        switch (task.getPriority()) {
            case 1:
                return FIRST_PRIORITY_COINS * (1 + timeCoef);
            case 2:
                return SECOND_PRIORITY_COINS * (1 + timeCoef);
            case 3:
                return THIRD_PRIORITY_COINS * (1 + timeCoef);
        }

        return 0;
    }

    @Override
    public void buyTaskSlots(User user, int slots) {
        float coins = user.getCoins();
        float slotSum = TASK_SLOT_PRICE * slots;
        if (coins >= slotSum) {
            user.setTaskSlots(user.getTaskSlots() + slots);
            user.setCoins(coins - slotSum);
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("Lack of coins");
        }
    }
}
