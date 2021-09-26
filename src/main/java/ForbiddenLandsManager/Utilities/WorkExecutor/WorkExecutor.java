package ForbiddenLandsManager.Utilities.WorkExecutor;

import javafx.application.Platform;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class WorkExecutor implements IWorkExecutor{
    @Override
    public <T> void run(CompletableFuture<T> action, T data) {
        Platform.runLater(() -> action.complete(data));
    }
}
