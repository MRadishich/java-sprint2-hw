public class Greetings {
    private final String GOOD_MORNING = "Привет! Работа не ждет, пора показать ей кто тут главный!";
    private final String GOOD_AFTERNOON = "Привет!\n" + "После сытного обеда.\n" + "По закону Архимеда Полагается поспать.\n" + "Но, не в этот раз!";
    private final String GOOD_EVENING = "Привет! Не перетрудись, работай с умом, а не до ночи.";
    private final String GOOD_NIGHT = "Привет! Все как обычно? Крайний срок — вчера? Что ж, удачи!";

    public String getGoodMorning() {
        return GOOD_MORNING;
    }

    public String getGoodAfternoon() {
        return GOOD_AFTERNOON;
    }

    public String getGoodEvening() {
        return GOOD_EVENING;
    }

    public String getGoodNight() {
        return GOOD_NIGHT;
    }
}
