package enums;

public enum TimerConfiguration {

    TIMER("timer://Footimer?period=2500&delay=5s");

    private String timerConfig;

    TimerConfiguration(String timerConfig) {
        this.timerConfig = timerConfig;
    }

    public String getTimerConfig() {
        return timerConfig;
    }
}
