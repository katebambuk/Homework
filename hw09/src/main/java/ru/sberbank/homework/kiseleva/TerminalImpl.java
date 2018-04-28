package ru.sberbank.homework.kiseleva;

import ru.sberbank.homework.kiseleva.exceptions.NotEnoughMoneyException;
import ru.sberbank.homework.kiseleva.interfaces.Terminal;

/**
 * Created by Ekaterina Kiseleva on 14.02.2018.
 */
public class TerminalImpl implements Terminal {
    private static final int MINIMAL_VALUE_OF_BILL = 100;

    public int checkAccount(TerminalServer server) {
        return server.getAmountMoney();
    }

    public void pullMoney(TerminalServer server, int amountMoney) throws NotEnoughMoneyException {
        if (amountMoney % MINIMAL_VALUE_OF_BILL != 0) {
            throw new IllegalArgumentException("The amount must be a multiple of 100");
        }
        server.setAmountMoney(server.getAmountMoney() - amountMoney);
        System.out.println("now you have " + server.getAmountMoney());
    }

    public void putMoney(TerminalServer server, int amountMoney) {
        if (amountMoney % MINIMAL_VALUE_OF_BILL != 0) {
            throw new IllegalArgumentException("The amount must be a multiple of 100");
        }
        server.setAmountMoney(server.getAmountMoney() + amountMoney);
        System.out.println("now you have " + server.getAmountMoney());
    }
}
