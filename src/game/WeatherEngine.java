package game;
import java.util.ArrayList;
import java.util.List;
/**
 * Class representing the Weather Engine.
 * Created by:
 * @author Fabian Wise
 * @version 1.0
 */
public class WeatherEngine {

    private List<Enum<?>> weather = new ArrayList<>();

    public WeatherEngine() {
        // null weather
    }

    public WeatherEngine(Enum<?> name) {
        weather.add(Weather.SUNNY);
    }

    /**
     * Checks if a specific weather condition is present in the current weather.
     *
     * @param name The weather condition to check for, which must be an enumeration value.
     * @return true if the weather condition is present, false otherwise.
     */

    public boolean hasWeather(Enum<?> name) {
        return this.weather.contains(name);
    }

    /**
     * A method for adding an attribute to the actor.
     * @param name the name of the attribute to be added, which must be a value of an enumeration, such as BaseActorAttributes.STAMINA.
     */
    public void addWeather(Enum<?> name) {
        this.weather.add(name);
    }
    /**
     * Removes a weather condition from the current weather.
     *
     * @param name The weather condition to be removed, which must be an enumeration value.
     */

    public void removeWeather(Enum<?> name) {
        this.weather.remove(name);
    }
}
