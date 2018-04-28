package ru.sberbank.homework.kiseleva;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.sberbank.homework.kiseleva.exceptions.NotEnoughMoneyException;

/**
 * Created by Ekaterina Kiseleva on 14.02.2018.
 */
public class TerminalImplTest extends Assert {

    private TerminalImpl terminal = new TerminalImpl();
    private TerminalServer server = new TerminalServer();

    @Test
    public void amountMoney() {
        assertEquals(terminal.checkAccount(server), server.getAmountMoney());
    }

    @Test
    public void pullMoney() {
        int startMoney = server.getAmountMoney();
        int changeMoney = 100;
        terminal.pullMoney(server, changeMoney);
        assertEquals(startMoney - changeMoney, terminal.checkAccount(server));
    }

    @Test
    public void putMoney() {
        int startMoney = server.getAmountMoney();
        int changeMoney = 100;
        terminal.putMoney(server, changeMoney);
        assertEquals(startMoney + changeMoney, terminal.checkAccount(server));
    }

    @Test(expected = IllegalArgumentException.class)
    public void pullMoneyNotMultiply100() {
        int startMoney = server.getAmountMoney();
        int changeMoney = 150;
        terminal.pullMoney(server, changeMoney);
        assertEquals(startMoney - changeMoney, terminal.checkAccount(server));
    }

    @Test(expected = IllegalArgumentException.class)
    public void putMoneyNotMultiply100() {
        int startMoney = server.getAmountMoney();
        int changeMoney = 150;
        terminal.putMoney(server, changeMoney);
        assertEquals(startMoney + changeMoney, terminal.checkAccount(server));
    }

    @Test(expected = NotEnoughMoneyException.class)
    public void pullMoneyWhenNotEnough() {
        int startMoney = server.getAmountMoney();
        int changeMoney = startMoney + 100;
        terminal.pullMoney(server, changeMoney);
        assertEquals(startMoney - changeMoney, terminal.checkAccount(server));
    }

}
