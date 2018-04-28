package ru.sberbank.homework.kiseleva;

import java.util.Date;
import java.util.Scanner;

/**
 * Created by Ekaterina Kiseleva on 14.02.2018.
 */
public class TerminalClient {

    private TerminalImpl terminal = new TerminalImpl();
    private TerminalServer server = new TerminalServer();
    private PinValidator pinValidator = new PinValidator();

    public void start() {
        while (true) {
            try {
                System.out.println("please enter pin: ");
                Scanner scanner = new Scanner(System.in);
                String pin = scanner.nextLine();
                if (server.isLock()) {
                    server.locker(new Date());
                }
                if (pinValidator.validate(pin)) {
                    server.setWrongPins(0);
                    System.out.println("1 - check account");
                    System.out.println("2 - pull money");
                    System.out.println("3 - put money");
                    Integer bankOperation = scanner.nextInt();
                    switch (bankOperation) {
                        case 1:
                            System.out.println(terminal.checkAccount(server));
                            break;
                        case 2:
                            System.out.println("how much do you want to pull?");
                            terminal.pullMoney(server, scanner.nextInt());
                            break;
                        case 3:
                            System.out.println("how much do you want to put?");
                            terminal.putMoney(server, scanner.nextInt());
                            break;
                    }

                } else {
                    System.out.println("Wrong pin!");
                    server.setWrongPins(server.getWrongPins() + 1);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
