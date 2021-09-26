package ForbiddenLandsManager.Utilities.WorkExecutor;

import javafx.application.Platform;

import java.util.function.Consumer;

public class WorkExecutor implements IWorkExecutor{
    @Override
    public <T> void run(Consumer<T> action, T data) {
        Platform.runLater(() -> action.accept(data));
    }
}
