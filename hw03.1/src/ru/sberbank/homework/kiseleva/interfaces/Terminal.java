package ru.sberbank.homework.kiseleva.interfaces;

import ru.sberbank.homework.kiseleva.exceptions.NotEnoughMoneyException;
import ru.sberbank.homework.kiseleva.TerminalServer;

/**
 * Created by Ekaterina Kiseleva on 14.02.2018.
 */
public interface Terminal {
    public int checkAccount(TerminalServer server);
    public void pullMoney(TerminalServer server, int i) throws NotEnoughMoneyException;
    public void putMoney(TerminalServer server, int i);
}
