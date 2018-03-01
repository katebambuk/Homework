package ru.sberbank.homework.checker;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CommandSequence {
    private List<Expression> expressions = new ArrayList<>();
    private String description;
    private boolean failed;

    public static CommandSequence create() {
        return new CommandSequence();
    }

    private CommandSequence add(Expression expression) {
        expression.setParent(this);
        expressions.add(expression);
        return this;
    }

    public CommandSequence addAllExpressions(Expression[] expressionArray) {
        Arrays.stream(expressionArray).forEach(this::add);
        return this;
    }

    public CommandSequence addDescription(String description) {
        this.description = description;
        return this;
    }

    public void fail() {
        failed = true;
    }
}
