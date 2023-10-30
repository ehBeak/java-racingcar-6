package racingcar.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.validation.CarValidator;

public class RacingCar {

    private final List<Car> cars;

    public RacingCar(String userInput) {
        List<String> carNames = splitInputByComma(userInput);
        CarValidator.validateCarNameList(carNames);
        this.cars = carNames.stream().map(Car::new).toList();
    }

    RacingCar(List<Car> cars) {
        this.cars = cars;
    }

    public void moveRandomAllCar() {
        cars.forEach(car -> car.moveForward(createRandomNumber()));
    }

    public List<Car> findWinners() {
        Integer maxDistance = findMaxDistance();

        return this.cars.stream()
                .filter(car -> car.getMovingDistance().equals(maxDistance))
                .collect(Collectors.toList());
    }

    public List<Car> getCars() {
        return cars;
    }

    private int createRandomNumber() {
        return Randoms.pickNumberInRange(0, 9);
    }

    private Integer findMaxDistance() {
        int max = Integer.MIN_VALUE;
        for (Car car : cars) {
            max = Math.max(car.getMovingDistance(), max);
        }

        return max;
    }

    private List<String> splitInputByComma(String userInput) {
        return Arrays.asList(userInput.split(","));
    }
}
