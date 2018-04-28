package ru.sberbank.homework.kiseleva;

import ru.sberbank.homework.kiseleva.exceptions.AccountIsLockedException;
import ru.sberbank.homework.kiseleva.exceptions.NotEnoughMoneyException;

import java.util.Date;

/**
 * Created by Ekaterina Kiseleva on 14.02.2018.
 */
public class TerminalServer {
    private int wrongPins = 0;
    private long lockTime = 0;
    private int amountMoney = 1000;
    private boolean lock = false;
    private static boolean UNBLOCK = false;
    private static boolean BLOCK = true;
    private static final int MAX_WRONG_PINS = 3;
    private static final int MAX_LOCK_TIME = 5;
    private static final long MS_IN_SEC = 1000L;

    public int getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(int amountMoney) throws NotEnoughMoneyException {
        if (amountMoney >= 0) {
            this.amountMoney = amountMoney;
        } else {
            throw new NotEnoughMoneyException("You have less money then you tried to take off!");
        }
    }

    public boolean isLock() {
        return lock;
    }

    public int getWrongPins() {
        return wrongPins;
    }

    public void setWrongPins(int wrongPins) throws AccountIsLockedException {
        this.wrongPins = wrongPins;
        checkIfNeedLocking();
    }

    private void checkIfNeedLocking() {
        if (this.wrongPins % MAX_WRONG_PINS == 0 && this.wrongPins != 0) {
            locking();
        }
    }

    private void locking() {
        lock = BLOCK;
        lockTime = new Date().getTime();
        System.out.println("Account is locked");
    }

    public void unlock() {
        lock = UNBLOCK;
    }

    public void locker(Date currentDate) {
        if ((currentDate.getTime() - lockTime) < MAX_LOCK_TIME * MS_IN_SEC) {
            long timeToUnlock = MAX_LOCK_TIME - (currentDate.getTime() - lockTime) / MS_IN_SEC;
            throw new AccountIsLockedException(timeToUnlock);
        } else {
            unlock();
        }
    }
}
