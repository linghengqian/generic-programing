package sukai.java8.chapter02;

import lombok.Data;

@Data
public class Dish {
    private final String name;
    private final boolean vegetarian;

    private final int calories;

    private final Type type;
}

