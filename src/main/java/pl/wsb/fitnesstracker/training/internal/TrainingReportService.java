package pl.wsb.fitnesstracker.training.internal;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingRepository;

import java.util.List;

@Service
public class TrainingReportService {

    private final TrainingRepository trainingRepository;

    public TrainingReportService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Scheduled(fixedRate = 5000)
    public void generateReport() {
        List<Training> trainings = trainingRepository.findAll();

        trainings.forEach(training -> {
            String trainingReport = "Trening użytkownika " + training.getUser().getFirstName() + " " + training.getUser().getLastName()
                    + ". Wykonał: " + training.getActivityType().getDisplayName() + ". Dystans: " + training.getDistance()
                    + ". Czas startu: " + training.getStartTime() + ". Czas końca: " + training.getEndTime()
                    + ". Średnia prędkość: " + training.getAverageSpeed();
            System.out.println(trainingReport);
        });
    }
}
