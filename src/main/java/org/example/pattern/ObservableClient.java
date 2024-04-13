package org.example.pattern;

import java.util.Random;
import java.util.concurrent.*;

class TemperatureSensor extends SubmissionPublisher<Float> {
}

class TemperatureDisplay implements Flow.Subscriber<Float> {
    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        System.out.println("Subscribed");
        subscription.request(1);
    }

    @Override
    public void onNext(Float item) {
        System.out.println("Temperature: %.2f".formatted(item));
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        subscription.cancel();
        System.out.println("Done");
    }
}

public class ObservableClient {
    public static void main(String[] args) throws InterruptedException {
        TemperatureSensor temperatureSensor = new TemperatureSensor();
        temperatureSensor.subscribe(new TemperatureDisplay());
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                float v = random.nextFloat() * 100;
                temperatureSensor.submit(v);
            });
        }
        executor.awaitTermination(1, TimeUnit.SECONDS);
        executor.shutdown();
        temperatureSensor.close();
    }
}
