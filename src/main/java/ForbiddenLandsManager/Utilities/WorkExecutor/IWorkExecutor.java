package ForbiddenLandsManager.Utilities.WorkExecutor;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public interface IWorkExecutor {
    <T> void run(CompletableFuture<T> action, T data);
}
