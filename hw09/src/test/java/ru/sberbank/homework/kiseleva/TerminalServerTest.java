package ru.sberbank.homework.kiseleva;

import org.junit.*;
import ru.sberbank.homework.kiseleva.exceptions.AccountIsLockedException;

import java.util.Date;

/**
 * Created by Ekaterina Kiseleva on 14.02.2018.
 */
public class TerminalServerTest extends Assert {

    private TerminalServer server = new TerminalServer();
    private PinValidator pinValidator = new PinValidator();

    @Test
    public void isRightPassword() {
        pinValidator.setPin("1234");
        final String pass = pinValidator.getPin();
        assertTrue(pinValidator.validate(pass));
    }

    @Test
    public void isWrongPassword() {
        pinValidator.setPin("1234");
        final String pass = pinValidator.getPin() + "00";
        assertFalse(pinValidator.validate(pass));
    }

    @Test
    public void isLock() {
        server.setWrongPins(3);
        assertTrue(server.isLock());
    }

    @Test
    public void isLock5SecAndUnlock() throws InterruptedException {
        server.setWrongPins(3);
        assertTrue(server.isLock());

        Thread.sleep(5000);

        server.locker(new Date());
        assertFalse(server.isLock());
    }


    @Test(expected = AccountIsLockedException.class)
    public void isLockLess5Sec() throws InterruptedException {
        server.setWrongPins(3);
        assertTrue(server.isLock());

        Thread.sleep(3000);

        server.locker(new Date());
    }

}
