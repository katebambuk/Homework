package ru.sberbank.homework.checker;

import lombok.*;

@Getter
@RequiredArgsConstructor
@ToString
public class Expression {
    private final String input;
    private final String expectedOutput;
    private boolean failed;
    @Setter
    private CommandSequence parent;

    public void fail() {
        failed = true;
        parent.fail();
    }
}
