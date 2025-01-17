import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main
{

    public static void main(String[] args)
    {
        System.out.println("********************");
        System.out.println("Задача 1");
        System.out.println("********************");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите дату рождения (в формате ГГГГ-ММ-ДД): ");
        String input = scanner.nextLine();
        LocalDate birthDate = LocalDate.parse(input);
        LocalDate currentDate = LocalDate.now();
        int age = calculateAge(birthDate, currentDate);
        System.out.println("Ваш возраст: " + age + " лет.");

        System.out.println("********************");
        System.out.println("Задача 2");
        System.out.println("********************");
        Map<String, LocalTime> events = new HashMap<>();
        events.put("Совещание", LocalTime.of(10, 0)); // 10:00
        events.put("Лекция", LocalTime.of(12, 30)); // 12:30
        events.put("Обед", LocalTime.of(13, 0)); // 13:00
        events.put("Встреча", LocalTime.of(15, 0)); // 15:00
        findNextEvent(events);
    }

    public static void findNextEvent(Map<String, LocalTime> events)
    {
        LocalTime currentTime = LocalTime.now();
        String nextEventName = null;
        LocalTime nextEventTime = null;
        for(Map.Entry<String, LocalTime> entry : events.entrySet())
        {
            String eventName = entry.getKey();
            LocalTime eventTime = entry.getValue();
            if(eventTime.isAfter(currentTime))
            {
                if((nextEventTime == null) || eventTime.isBefore(nextEventTime))
                {
                    nextEventName = eventName;
                    nextEventTime = eventTime;
                }
            }
        }
        if(nextEventName != null)
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            System.out.println("Ближайшее мероприятие: " + nextEventName + " в " + nextEventTime.format(formatter));
        }
        else
            System.out.println("Нет ближайших мероприятий.");
    }

    private static int calculateAge(LocalDate birthDate, LocalDate currentDate)
    {
        Period period = Period.between(birthDate, currentDate);
        return period.getYears();
    }
}