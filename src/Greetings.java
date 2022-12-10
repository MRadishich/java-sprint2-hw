public enum Greetings {
    GOOD_MORNING("Привет! Работа не ждет, пора показать ей кто тут главный!"),
    GOOD_AFTERNOON("Привет!\n" +
                            "После сытного обеда.\n" +
                            "По закону Архимеда Полагается поспать.\n" +
                            "Но, не в этот раз!"),
    GOOD_EVENING("Привет! Не перетрудись, работай с умом, а не до ночи."),
    GOOD_NIGHT("Привет! Все как обычно? Крайний срок — вчера? Что ж, удачи!");

    final String message;

    Greetings(String message) {
        this.message = message;
    }
}
